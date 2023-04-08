
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
    
    @Override
    public String getName()
    {
        //String[] nParts = name.split(" ");
        //return nParts[1] + ", " + nParts[0];
        return name;
    
    }

    public String publish()
    {
        String returnval ="";

        if (super.getPublish()){

            returnval = "<p>" + getName() + " took the " + getVaxName() + "vaccine!!! </p>";
        }
        return returnval;

    }


    
   public static String getFVHeader()
    {
     String returnval = "ID\tName\t\tVaccine";
     returnval+="\n---------------------------------";
     return returnval;
     
    }

@Override
public int compareTo(FullyVaccinatedPerson o) {
    
  return this.getName().compareTo(o.getName());
}

 

}
