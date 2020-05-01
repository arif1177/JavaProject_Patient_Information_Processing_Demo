/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class has necessary patient information e.g. IDs, total LoS, (different) total benefits, list of principalMBS and MBS codes that they had
 * @author akhan 
 */
public class PatientSummary 
{
    public String memberID;
    public String memberShipID;
    public int totalAdmission;
    public int LoS;
    public double hospBen;
    public double medBen;
    public double prosBen;
    public List<AdmissionSummary> admissionSummary;
    public List<String> principalMBSList;
    public List<Integer> principalMBSFreq;
    public List<String> MBSList;
    public List<Integer> MBSFreq;
    public PatientSummary(String memberID, String memberShipID, int totalAdmission, int LoS, double hospBen,double medBen, double prosBen)
    {
        this.memberID = memberID;
        this.memberShipID = memberShipID;                
        this.totalAdmission = totalAdmission;
        this.LoS = LoS;
        this.hospBen = hospBen;
        this.medBen = medBen;
        this.prosBen = prosBen;
        this.MBSList = new ArrayList<>();
        this.MBSFreq = new ArrayList<>();
        this.principalMBSList = new ArrayList<>();
        this.principalMBSFreq = new ArrayList<>();
    }
    /**
     * MBS list and their summary information is updated from information of all the admissions
     * that this patient have made. Also patients own MBS code summaries are updated from these input information
     * @param asm Admission summary of this patient. That is saved into this class.
     * @param mbsSummary Main MBS summary class that have all the list and related summary information. This class is 
     * iteratively updated after the function is executed.
     */
    public void processPatientAndMBSSummaryfromAdmissionSummary(List<AdmissionSummary> asm, MBSSummary mbsSummary)            
    {
        this.admissionSummary = asm;
        for(AdmissionSummary as:this.admissionSummary)
        {
            if(Utility.addItemInStringListWithFreqAndRetIsUnique(as.principal_mbs,1,principalMBSList,principalMBSFreq))
            {//this is unique prin MBS for a patient. Plus counter
                mbsSummary.addFreqOfPrincipalMBSInPatient(as.principal_mbs);
            }
            //add counter for prin MBS in admission
            mbsSummary.addFreqForPrinMBSInAdmission(as.principal_mbs, as);
            
            for(String s:as.mbsList)
            {
                if(Utility.addItemInStringListWithFreqAndRetIsUnique(s, 1, MBSList, MBSFreq))
                {//this is first time this MBS is occurring for the patient. Plus counter
                    mbsSummary.addFreqOfMBSInPatient(s);
                }
                //add counter for MBS in admission
                mbsSummary.addFreqOfMBSInAdmission(s);
            }
        }
    }
    
    /**
     * 
     * @return necessary JTable row data to show in appropriate format
     */
    public Object[] getSummaryPatientInfoRow()
    {
        return new Object[]{this.memberID, this.memberShipID,this.totalAdmission, this.LoS, this.hospBen,this.medBen, this.prosBen};
    }
}
