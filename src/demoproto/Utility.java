/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;

import java.io.File;
import java.util.List;
import jxl.*; 
import jxl.write.*; 
/**
 *
 * @author Arif Khan
 */
public class Utility 
{
    /**
     * Insert unique string pairs
     * @param target unique string pairs against which to test
     * @param source string pairs to insert in the target if unique
     * @return 
     */
    public static List<String[]> insertUniqueMembers(List<String[]> target,List<String[]> source)
    {
//        if(source.size() > 15)
//            System.out.println(source.size());
        for (int i = 0; i < source.size(); i++)
        {
            boolean found = false;
            for(int j = 0;j<target.size();j++)
            {
                if(source.get(i)[0].equals(target.get(j)[0]) &&  source.get(i)[1].equals(target.get(j)[1]))
                {
                    found = true;
                    break;
                }
            }
            if(!found)//iinsert into target
            {
                target.add(source.get(i));
            }
        }
        return target;
    }
    public  static void  writeExcelData(MBSSummary mbsSummary)
    {
        try
        {
            WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Arif\\Docs\\Analysis Files\\diabetesAnalyses.xls"));
            WritableSheet sheet  = workbook.createSheet("Main", 0);
            Label label ; 
            jxl.write.Number number ;
            for(int j=0,i=1;j<mbsSummary.mbsCode.size();j++,i++)//start after header
            {
                label = new Label(0,i,mbsSummary.mbsCode.get(j));
                sheet.addCell(label);
                number = new jxl.write.Number(1,i,mbsSummary.freqOfMBSInPatient.get(j));
                sheet.addCell(number);
                number = new jxl.write.Number(2,i,mbsSummary.freqOfMBSInAdmission.get(j));
                sheet.addCell(number);
                number = new jxl.write.Number(3,i,mbsSummary.freqOfPrincipalMBSInPatient.get(j));
                sheet.addCell(number);
                number = new jxl.write.Number(4,i,mbsSummary.freqOfPrincipalMBSInAdmission.get(j));
                sheet.addCell(number);
                number = new jxl.write.Number(5,i,mbsSummary.losInPrinCMBS.get(j));
                sheet.addCell(number);
                number = new jxl.write.Number(6,i,mbsSummary.hospBenInPrinCMBS.get(j));
                sheet.addCell(number);
                number = new jxl.write.Number(7,i,mbsSummary.medBenInPrinCMBS.get(j));
                sheet.addCell(number);    
                number = new jxl.write.Number(8,i,mbsSummary.prosBenInPrinCMBS.get(j));
                sheet.addCell(number);                 
            }
          //  jxl.write.Number number = new jxl.write.Number(3, 4, 3.1459); 
           // sheet.addCell(number);
            workbook.write(); 
            workbook.close();
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * This method generates an excel file which has edge list (source, target, freq) to build a graph 
     * where nodes are MBS codes, edges are number of patients that have those two MBS codes.
     * To do this, this method generates all possible MBS codes pairs and for each pair search the whole patient list 
     * to see if they have those MBS codes.
     * @param mbsCode List of mbsCodes as String
     * @param patientSummary Summary of patient information as a list
     * @return true if successfully written, false otherwise
     */
    public static boolean writeMBSCoOccurrenceInExcel(List<String> mbsCode, List<PatientSummary> patientSummary)
    {
        try
        {
            WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Arif\\Docs\\Analysis Files\\graphDataEdgeList.xls"));
            WritableSheet sheet  = workbook.createSheet("Main", 0);
            Label label ; 
            jxl.write.Number number ;
            String source, target;
            int k,l,freq;
            int row = 0;
            
            for(int i = 0;i<mbsCode.size()-1;i++)
            {
                source = mbsCode.get(i);
                for(int j = i+1;j<mbsCode.size();j++)
                {//the mbs code pair is generated, Search all patient information to find the frequency
                    target = mbsCode.get(j);
                    freq = 0;
                    for(PatientSummary ps:patientSummary)
                    {
                        k = ps.MBSList.indexOf(source);
                        l = ps.MBSList.indexOf(target);
                        if(k > -1 && l>-1)//co-occurrence found for one patient
                            freq++;
                    }
                    if(freq>0)//write in excel
                    {
                        label = new Label(0, row, source);
                        sheet.addCell(label);
                        label = new Label(1, row, target);
                        sheet.addCell(label);
                        number = new jxl.write.Number(2,row,freq);
                        sheet.addCell(number);
                        row++;
                    }
                }
            }
            workbook.write(); 
            workbook.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public static boolean addItemInStringListWithFreqAndRetIsUnique(String item, int freq, List<String>itemList,List<Integer>itemFreqList)
    {
        int i = itemList.indexOf(item);
        if(i==-1)//new, so add
        {
            itemList.add(item);
            itemFreqList.add(freq);
            return true;
        }
        else
        {//add freq to existing
            itemFreqList.set(i,itemFreqList.get(i)+freq);
            return false;
        }
    }

}
