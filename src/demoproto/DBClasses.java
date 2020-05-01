/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;

/**
 *
 * @author "Arif Khan"
 */
public class DBClasses {

}
class Patient
{
    public String member_id;
    public String membership_id;
    public int member_sex;
    public String member_dob;
    public String joined_date;
    public String termination_date;
    public String postcode;
    public void setSex(String sex)
    {
        this.member_sex = sex.equals("M")?1:0;
    }
    public  void setMember_dob(String member_dob)
    {
        this.member_dob = this.setQuoteOrNot(member_dob);                 
    }
    public  void setJoined_date(String joined_date)
    {
        this.joined_date = this.setQuoteOrNot(joined_date);                 
    }
    public  void setTermination_date(String termination_date)
    {
        this.termination_date = this.setQuoteOrNot(termination_date);                 
    }
    private String setQuoteOrNot(String value)
    {
        if(value == null)
            return null;
        else
            return "'" +value+ "'";
    }
}
