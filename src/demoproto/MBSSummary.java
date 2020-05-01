/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akhan
 */
public class MBSSummary 
{
    public List<String> mbsCode;
    public List<Integer> freqOfMBSInPatient;
    public List<Integer> freqOfMBSInAdmission;
    public List<Integer> freqOfPrincipalMBSInPatient;
    public List<Integer> freqOfPrincipalMBSInAdmission;
    public List<Integer> losInPrinCMBS;
    public List<Double> hospBenInPrinCMBS;
    public List<Double> medBenInPrinCMBS;
    public List<Double> prosBenInPrinCMBS;    
    public List<Double> mbsCodeType;
    
    public MBSSummary()
    {
        this.mbsCode = new ArrayList<>();
        this.freqOfMBSInPatient = new ArrayList<>();
        this.freqOfMBSInAdmission = new ArrayList<>();
        this.freqOfPrincipalMBSInPatient = new ArrayList<>();
        this.freqOfPrincipalMBSInAdmission = new ArrayList<>();
        this.losInPrinCMBS = new ArrayList<>();
        this.hospBenInPrinCMBS = new ArrayList<>();
        this.medBenInPrinCMBS = new ArrayList<>();
        this.prosBenInPrinCMBS = new ArrayList<>();    
        this.mbsCodeType = new ArrayList<>();        
    }
    public void addFreqOfMBSInPatient(String mbsCode)
    {//this MBS has occurred uniquely for a patient
        if(Utility.addItemInStringListWithFreqAndRetIsUnique(mbsCode, 1, this.mbsCode, this.freqOfMBSInPatient))
            this.addEmptyStatInLists();
    }
    public void addFreqOfMBSInAdmission(String mbsCode)
    {
        //this MBS has occurred in a admission
        if(Utility.addItemInStringListWithFreqAndRetIsUnique(mbsCode, 1, this.mbsCode, this.freqOfMBSInAdmission))
            this.addEmptyStatInLists();
    }
    public void addFreqOfPrincipalMBSInPatient(String mbsCode)
    {//this Prin MBS has occurred as prin MBS uniquely for a patient. Add the counter.
        if(Utility.addItemInStringListWithFreqAndRetIsUnique(mbsCode,1,this.mbsCode,this.freqOfPrincipalMBSInPatient))
            this.addEmptyStatInLists();
    }
    public void addFreqForPrinMBSInAdmission(String mbsCode, AdmissionSummary as)
    {
        //this MBS has occurred in principal MBS in a admission (may be more than one for one patient)
        //increase counter basic stats. First find the right index
        int i = this.mbsCode.indexOf(mbsCode);
        if(i == -1)
        {
            this.mbsCode.add(mbsCode);
            this.freqOfPrincipalMBSInAdmission.add(1);
            this.losInPrinCMBS.add(as.length_of_stay);
            this.hospBenInPrinCMBS.add(as.hospital_benefit);
            this.medBenInPrinCMBS.add(as.medical_benefit);
            this.prosBenInPrinCMBS.add(as.prosthesis_benefit);
        }
        else//add to existing
        {
            this.freqOfPrincipalMBSInAdmission.set(i,this.freqOfPrincipalMBSInAdmission.get(i)+1);
            this.losInPrinCMBS.set(i,this.losInPrinCMBS.get(i)+as.length_of_stay);
            this.hospBenInPrinCMBS.set(i,+this.hospBenInPrinCMBS.get(i)+as.hospital_benefit);
            this.medBenInPrinCMBS.set(i,medBenInPrinCMBS.get(i)+as.medical_benefit);
            this.prosBenInPrinCMBS.set(i,this.prosBenInPrinCMBS.get(i)+as.prosthesis_benefit);
        }
        
    }
    private void addEmptyStatInLists()
    {
        this.freqOfMBSInPatient.add(0);// = new ArrayList<>();
        this.freqOfMBSInAdmission.add(0);// = new ArrayList<>();
        this.freqOfPrincipalMBSInPatient.add(0);// = new ArrayList<>();
        this.freqOfPrincipalMBSInAdmission.add(0);// = new ArrayList<>();
        this.losInPrinCMBS.add(0);// = new ArrayList<>();
        this.hospBenInPrinCMBS.add(0.0);// = new ArrayList<>();
        this.medBenInPrinCMBS.add(0.0);// = new ArrayList<>();
        this.prosBenInPrinCMBS.add(0.0);// = new ArrayList<>();    
        this.mbsCodeType.add(0.0);// = new ArrayList<>();  
    }
}

