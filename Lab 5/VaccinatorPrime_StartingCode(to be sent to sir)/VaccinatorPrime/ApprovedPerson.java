
import java.util.ArrayList;
public class ApprovedPerson extends BasePerson implements Comparable<ApprovedPerson>{
    ArrayList<String> comorbidities;
    public ApprovedPerson(int age, String name, boolean publish, int id)
    {
        super( name, age,publish);
        super.setId(id);
        comorbidities = new ArrayList<String>();

    }

    public void addComorbidity(String comorbid)
    {
        comorbidities.add(comorbid);
    }

    public ArrayList<String> getComorbids()
    {
        return comorbidities;
    }

 

    
    public String getName()
    {
        String [] arrOfName = name.split(" ");

        String lastnameFirstname = arrOfName[1] + ", " + arrOfName[0];

        return lastnameFirstname;
    }
        
    public String getSimpleName()
    {
        return name;
    }
 
    
    
    public static String getAPHeader()
    {
     String returnval = "ID\tName\t\tComorbidities";
     returnval+="\n---------------------------------";
     return returnval;
     
    }

    public String toString()
    {
        return getId() + "\t" + getName() + "\t\t" + getComorbids();
    }

    public int compareTo(ApprovedPerson other)
    {
        return other.getAge() - this.getAge();
    }

      
    
}
