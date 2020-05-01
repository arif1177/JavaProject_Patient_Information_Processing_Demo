/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DBHelper {
    private Connection c;
    Statement stmt = null;
    private void connectToDB() throws Exception
    {
        if(c == null)
        {
            
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://server.com:5432/DEMOInstance","DemoUser", "DemoPassword");
            
        }
    }
    private void disconnect() throws Exception
    {
        if(c != null && c.isClosed())
        {
            c.close();
            
        }
            
    }
    /**
     * Calculates summary of admissions that a patient has made
     * @param member memberID
     * @param memberShip memberShipID
     * @return Admission Summary List
     */
    public List<AdmissionSummary> getAdmissionSummaryGivenPatientIDs(String member, String memberShip)
    {
        List<AdmissionSummary> ass = new ArrayList<>();
        try
        {
            this.connectToDB();
            stmt = c.createStatement();            
            ResultSet rs = stmt.executeQuery( "SELECT rsa.claim_number,rsa.date_of_incident, rsa.length_of_stay,rsa.principal_mbs, ahc.mbs_1, ahc.mbs_2, ahc.mbs_3, ahc.mbs_extra,rsa.drg,rsa.hospital_benefit, rsa.medical_benefit, rsa.prosthesis_benefit, rsa.hosp_provider_id, rsa.hospital_type, rsa.discharge_date\n" +
"  FROM lorica.report_summary_admissions_101 as rsa\n" +
"  inner join lorica.admission_hcp_codes_101 as ahc \n" +
"  on rsa.claim_number = ahc.claim_number\n" +
    "  where member_id = '"+member+"' and membership_id = '"+memberShip+
                    "' order by date_of_incident asc;"
                    );
            AdmissionSummary tempPS;
            while(rs.next())
            { 
                tempPS = new AdmissionSummary(rs.getString("claim_number"),
                rs.getString("date_of_incident"),
                rs.getInt("length_of_stay"),                
                rs.getString("principal_mbs"),
                rs.getString("mbs_1"),
                rs.getString("mbs_2"),
                rs.getString("mbs_3"),
                rs.getString("mbs_extra"),
                rs.getString("drg"),
                rs.getDouble("hospital_benefit"),
                rs.getDouble("medical_benefit"),
                rs.getDouble("prosthesis_benefit"),
                rs.getString("hosp_provider_id"),
                rs.getString("hospital_type"),
                rs.getString("discharge_date"));
                ass.add(tempPS);      
            }
            return ass;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Calculates patient summary (total cost, LoS etc.)
     * @param member memberID
     * @param memberShip memberShipID
     * @return patient information summary class
     */
    public PatientSummary getPatientSummary(String member, String memberShip)
    {
        Double[] tempDouble = {0.0,0.0,0.0,0.0,0.0};
        try
        {
            this.connectToDB();
            stmt = c.createStatement();            
            ResultSet rs = stmt.executeQuery( "SELECT  length_of_stay, hospital_benefit, medical_benefit, prosthesis_benefit\n" +
"  FROM lorica.report_summary_admissions_101\n" +
"  where member_id = '"+member+"' and membership_id = '"+memberShip+"';");
            while(rs.next())
            {
                tempDouble[0] = tempDouble[0]+ 1.0;
                tempDouble[1] += (double)rs.getInt("length_of_stay");
                tempDouble[2] += rs.getDouble("hospital_benefit");
                tempDouble[3] += rs.getDouble("medical_benefit");
                tempDouble[4] += rs.getDouble("prosthesis_benefit");
            }
            this.disconnect();
            return new PatientSummary(member, memberShip,(int)Math.round(tempDouble[0]),(int)Math.round(tempDouble[1]),tempDouble[2],tempDouble[3],tempDouble[4]);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public  Patient getPatientDetailsGivenPatientID(String memberID, String memberShipID)
    {
        try
        {
            this.connectToDB();
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT  member_id, membership_id, member_sex, member_dob, joined_date, termination_date, postcode "
  + " FROM lorica.member as m where m.member_id like '"+memberID+"' and m.membership_id like '" +memberShipID+"'");
            while(rs.next())
            {
                Patient patient = new Patient();
                patient.member_id =  rs.getString("member_id");
                patient.membership_id =  rs.getString("membership_id");
                patient.setSex(rs.getString("member_sex"));
                patient.setMember_dob(rs.getString("member_dob"));
                patient.setJoined_date(rs.getString("joined_date"));
                patient.setTermination_date(rs.getString("termination_date"));                
				patient.postcode =  rs.getString("postcode");                
                
                return patient;//only one is possible
            }
            return null;
        }
        catch(Exception e)
        {
          e.printStackTrace();
          return null;
        }
        
    }
    /**
     * Finds the patients (member and membership ID) who have certain diagnosis code
     * @param diagnoseCode List of diagnose codes, , e.g. diabetes code as string
     * @return member and membership ID as list of string pairs (array) who have
     * the diagnose code
     */    
    public List<String[]> getPatientIDsGivenDiagCode(List<String>diagnoseCode)
    {
        try
        {
            this.connectToDB();
            stmt = c.createStatement();
            List<String[]> target = new ArrayList<>();
            List<String[]> source;
            String[] s ;
            for(int i =0;i<diagnoseCode.size();i++)
            {
                source = new ArrayList<>();
                ResultSet rs = stmt.executeQuery( "SELECT rsa.member_id, rsa.membership_id" +
    "  FROM lorica.hospital_admission_diagnosis_101 as had\n" +
    "  INNER JOIN lorica.report_summary_admissions_101 as rsa on rsa.claim_number = had.admission_no::varchar " +                 
    "  where had.diagnosis_code like '" + diagnoseCode.get(i)+"'");
                
                while(rs.next())
                {
                    s = new String[2];
                    s[0] = rs.getString("member_id");
                    s[1] = rs.getString("membership_id");
                    source.add(s);
                }
                //remove duplicates
                Utility.insertUniqueMembers(target, source);
            }
            this.disconnect();
            return target;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
