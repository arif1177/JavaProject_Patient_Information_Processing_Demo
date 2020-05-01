/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author "Arif Khan"
 */
public class AdapterDemoDB 
{
    private final DBHelper dbHelper;
    public AdapterDemoDB()
    {
        this.dbHelper = new DBHelper();
    }
    /**
     * 
     * @param hName name of the hostpital
     * @param conn MSSQL DB connection
     * @return The Id of the hospital. If the hospital is not there, it is created and the DI is redturn
     */
    public int getHospitalID(String hName)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DemoHost\\SQLInstance;user=demo;password=demo;database=DBNAME");
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select hID from Hospital where hName = '"+hName+"'");
            if(rs.next())
            {
                int m = rs.getInt("hID");
                conn.close();
                return m;
            }
            else//create the row
            {
                sta.executeUpdate("INSERT INTO Hospital (hName) VALUES ('"+hName+"')");
                return this.getHospitalID(hName);//recursive call, this time should to IF.
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;//should never come here
    }
    /**
     * Inserts necessary entries needed to insert MBS code lists under a certain admission
     * @param aID Admission ID
     * @param mbsList List of MBS codes
     * @param prinMBS Principal MBS codes
     */
    public  void insertRelAdmissionMBSEntry(int aID, List<String>mbsList, String prinMBS)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DemoHost\\SQLInstance;user=demo;password=demo;database=DBNAME");
            Statement sta = conn.createStatement();
            int MBSID, ramISPrinMBS;
            for(String mbsCode:mbsList)
            {
                MBSID = this.getMBSIDforMBSCodes(mbsCode);
                ramISPrinMBS = mbsCode.equals(prinMBS)?1:0;
                sta.executeUpdate("INSERT INTO relAdmissionMBS (ramAID,ramMBSID,ramISPrinMBS) VALUES ("+aID+","+MBSID+","+ramISPrinMBS+")");
            }
            conn.close();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    /**
     * creates MBS table entry if needed. Else if, return the MBS ID
     * @param aID Admission ID
     * @param mbsList List of MBS codes
     * @return MBSID for that MBS code
     */
    public int getMBSIDforMBSCodes(String mbsCode)
    {        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DemoHost\\SQLInstance;user=demo;password=demo;database=DBNAME");
            Statement sta = conn.createStatement();
            
            ResultSet rs = sta.executeQuery("select MBSID from MBS where MBSCode = '"+mbsCode+"'");
            //find if this MBS code is present in the dataset
            if(rs.next())
            {
                int m = rs.getInt("MBSID");
                conn.close();
                return m;
            }
            else//create new row in MBS
            {
                sta.executeUpdate("INSERT INTO MBS (MBSCode) VALUES ('"+mbsCode+"')");
                return this.getMBSIDforMBSCodes(mbsCode);//recursive call, this time should to IF.
            }
        }        
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
    /**
     * Assumes the MSSQL already have patient info. For each patient searches 
     * admission details. And the writes all admission info into MSSQL
     */
    public void insertAdmissionSummaryRecordsInMSSQL()
    {        
        List<AdmissionSummary> asses;
        try
        {            
            //first find all the patients info form MSSQL
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DemoHost\\SQLInstance;user=demo;password=demo;database=DBNAME");
            Statement sta = conn.createStatement();
            Statement sta2 = conn.createStatement();
            String query = "select pID, pMemberID, pMemberShipID from Patient";
            ResultSet results = sta.executeQuery(query);
            int aID = 3657,hID, pID, turn = 1;
            while(results.next()) 
            {
                if(turn < 585)
                {
                    turn++;
                    continue;
                }
                pID = results.getInt("pID");
                System.out.println("Processing "+(turn++)+", Member ID: " + results.getString("pMemberID") + " MemberShip: " + results.getString("pMemberShipID"));                
                asses = this.dbHelper.getAdmissionSummaryGivenPatientIDs(results.getString("pMemberID"),results.getString("pMemberShipID"));
              //  continue;
                for(AdmissionSummary ass:asses)
                {
                    hID = this.getHospitalID(ass.hospitalID);
                    sta2.executeUpdate("INSERT INTO Admission (aID, aClaimNo,aIncidentDate,aLoS,aDRG,aHospBen,aMedBen,aProsBen,aDischargeDate,aPatientID,aHID) \n" +
"VALUES  ("+aID+",'"+ass.claim_number+"','"+ass.date_of_incident+"','"+ass.length_of_stay+"','"+ass.drg+"','"+ass.hospital_benefit+"','"+ass.medical_benefit+"','"+ass.prosthesis_benefit+"','"+ass.dischargeDate+"',"+pID+","+hID+")");
                    //call to add MBS codes/add relEntry with this aID
                    this.insertRelAdmissionMBSEntry(aID, ass.mbsList,ass.principal_mbs);
                    aID++;
                    //System.out.println(aID);
                }                
            }
            conn.close();
            System.out.println("Successfully entered every admission");
            return;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    /**     
     * @param diagCode Disease diagnose codes, e.g. diabetes code as string
     *
     */
    public void importAllToSQLDB(List<String> diagCode)
    {
        try
        {   
            //init MSSQL access
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DemoHost\\SQLInstance;user=demo;password=demo;database=DBNAME");
            Statement sta = conn.createStatement();
            String query;
            
            Patient patient;
            List<String[]> patientDetails = dbHelper.getPatientIDsGivenDiagCode(diagCode);
           // int i = 0;
            for(String[] s:patientDetails)
            {
             //  if(i == 620)
               //     System.out.println("Stopping her");
                patient = this.dbHelper.getPatientDetailsGivenPatientID(s[0], s[1]);
                if(patient != null)
                {
                    query = "INSERT INTO Patient (pIsMale,pMemberID,pMemberShipID,pDOB,pJoinDate,pTerminateDate,pPostCode)" 
                + " VALUES("+patient.member_sex+",'"+patient.member_id+"','"+patient.membership_id+"',"+patient.member_dob+","+patient.joined_date+","+patient.termination_date+",'"+patient.postcode+"')";
                    sta.executeUpdate(query);  
                }
                //System.out.println(i++);
            } 
            System.out.println("All patients are inserted");
            return;  
            
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
