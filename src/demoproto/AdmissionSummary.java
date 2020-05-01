/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author akhan
 */
public class AdmissionSummary {
    public String claim_number;
    public String date_of_incident;
    public int length_of_stay;
    public String principal_mbs;
    public String mbs_1;
    public String mbs_2;
    public String mbs_3;
    public String mbs_extra;
    public List<String> mbsList;
    public String drg;
    public Double hospital_benefit;
    public Double medical_benefit;
    public Double prosthesis_benefit;
    //newly added
    public String hospitalID;
    public int hospitalType;
    public String dischargeDate;

    public AdmissionSummary(String claim_number,String date_of_incident, int length_of_stay, String principal_mbs,
           String  mbs_1,String mbs_2,String mbs_3,String mbs_extra,String drg,double hospital_benefit,double medical_benefit,double prosthesis_benefit, String hospitalID, String hospitalType, String dischargeDate)
    {
        this.claim_number = claim_number;
        this.date_of_incident = date_of_incident;
        this.length_of_stay = length_of_stay;
        this.principal_mbs = principal_mbs;
        this.mbs_1 = mbs_1;
        this.mbs_2 = mbs_2;
        this.mbs_3 = mbs_3;
        this.mbs_extra = mbs_extra;
        this.drg = drg;
        this.hospital_benefit = hospital_benefit;
        this.medical_benefit = medical_benefit;
        this.prosthesis_benefit = prosthesis_benefit;
        //newlyAdded starts
        this.hospitalID = hospitalID;
        this.hospitalType = Integer.parseInt(hospitalType);
        this.dischargeDate = dischargeDate;
        //newlyAdded ends
        this.processMBSCodes();
    }
    public void processMBSCodes()
    {
        this.mbsList = new ArrayList<>();
        if(principal_mbs != null && !principal_mbs.isEmpty())
            mbsList.add(principal_mbs);
        if(mbs_1 != null && !mbs_1.isEmpty() && !mbsList.contains(mbs_1))
            mbsList.add(mbs_1);
        if(mbs_2 != null && !mbs_2.isEmpty() && !mbsList.contains(mbs_2))
            mbsList.add(mbs_1);
        if(mbs_3 != null && !mbs_3.isEmpty() && !mbsList.contains(mbs_3))
            mbsList.add(mbs_3);
        //tokenize using comma
        if(mbs_extra != null && !mbs_extra.isEmpty())
        {
            List<String> mbss = Arrays.asList(mbs_extra.split(","));
            String s;
            for(int i = 0;i<mbss.size();i++)
            {
                s = mbss.get(i);
                s = s.trim();
                if(s != null && !s.isEmpty() && !mbsList.contains(s))
                    mbsList.add(s);
            }
        }        
    }
}
