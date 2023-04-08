
public class FullyVaccinatedPerson extends BasePerson implements Comparable<FullyVaccinatedPerson>{
    private String vaxname;
    public FullyVaccinatedPerson(int age, String name,boolean publish,int id, String vaxname)
    {
        super( name, age, publish);
        super.setId(id);
        this.vaxname= vaxname;
    }

    public String getVaxName()
    {
        return vaxname;
    }
    
    public String getName()
    {
        return name;
    }

    public String publish()
    {
        if(getPublish() == true)
        {
            return "<p>"+getName() + " took the " +vaxname + " vaccine!!!</p>";
        }
        
        return "";

    }


    
   public static String getFVHeader()
    {
     String returnval = "ID\tName\t\tVaccine";
     returnval+="\n---------------------------------";
     return returnval;
     
    }

    public String toString()
    {
        return getId() + (getPublish()?"*":"") + "\t" + getName() + "\t\t" + getVaxName();
    }

    public int compareTo(FullyVaccinatedPerson other) //when the first object uses compareTo and the result is positive then it will place the first object to the right of the second(compared) object
    {
        return getName().compareTo(other.getName());
    }

}
