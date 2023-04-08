import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.text.NumberFormat;
import java.io.*;



interface Citizen {
    abstract String getTRN();

}

interface Consultant {
    public double earnFromSkill();
}

interface RegisteredExpat
{
    public String getWorkPermit();
}

class ExpatConsultant extends Person implements Consultant, RegisteredExpat{
    
    private double airfare , permitTax, skillPrice;
    private int id;
    static private int nextId;
    private String sector;

    public ExpatConsultant(){}

    public ExpatConsultant(String dob, String sector,
    double skillPrice,double taxRate, double airfare)
    {
        //dob is a string in the format mm/dd/yyyy
        super();
        String[] parts = dob.split("/");
        super.setDob(Integer.parseInt(parts[1]),Integer.parseInt(parts[0]),Integer.parseInt(parts[2])); 
        this.skillPrice = skillPrice;
        this.permitTax=skillPrice*taxRate;
        this.airfare=airfare;
        this.sector = sector;
        this.id = getNextId();

    }

    public String getSector()
    {
        return sector;
    }

    public String getContact()
    {
        return "Reg. Expatriate#"+id;
    }

    public int getNextId()
    {
        id=nextId;
        nextId++;
        return id;
    }

    public int getId()
    {
        return id;
    }

    public double getPay()
    {
        double earnings = earnFromSkill();
        return earnings - permitTax -airfare ;

    }

    public double earnFromSkill()
    {
        return skillPrice;
    }
    
    public String getWorkPermit()
    {
        return "WP00" + id;
    }

}

class LocalConsultant extends LocalResource implements Citizen, Consultant
{
    private double skillPrice, taxRate, permitTax;
    
    public LocalConsultant(String dob,String sector, double skillPrice, double taxRate)
    {
        super(dob, sector);
        
        this.skillPrice = skillPrice;
        this.taxRate = taxRate;
        
        permitTax = skillPrice * taxRate;
    }
    
    public double earnFromSkill()
    {
        return skillPrice;
    }
    
    public String getContact()
    {
        return "LocalConsultant#" + getId();
    }
    
    public double getPay()
    {
        return skillPrice - permitTax;
    }
    
}


abstract class LocalResource extends Person
{
    private static int nextId;
        
    private int id;
    
    private String sector;

    public LocalResource(String date, String sector)
    {
        String[] dob = date.split("/");
        int day = Integer.parseInt(dob[0]);
        int month = Integer.parseInt(dob[1]);
        int year = Integer.parseInt(dob[2]);
        setDob(day, month, year);

        this.sector = sector;
        id = nextId;
        nextId++;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getSector()
    {
        return sector;
    }
    
    public String getTRN()
    {
        return Integer.toString(id + 100000000);
    }
    
    
}

abstract class Person implements Comparable<Person> {
    private int dobYear, dobMonth, dobDay;

    public Person() {};

    public Person(int d, int m, int y)
    {
        dobYear =y;
        dobMonth=m;
        dobDay =d;
    }

    public void setDob(int d, int m, int y)
    {
        dobYear =y;
        dobMonth=m;
        dobDay =d;
    }

    public int getDobYear()
    {
        return dobYear;
    }

    public int getDobMonth()
    {
        return dobMonth;
    }

    public int getDobDay()
    {
        return dobDay;
    }

    abstract double getPay();

    abstract String getSector();    

    abstract String getContact();

    public String toString()
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return "["+getDobDay()+"]"+getContact() +" to be paid "+ formatter.format(getPay()) +" in the "+getSector() +" sector";
    }

    public int compareTo(Person other)
    {

        return (this.getDobDay()>other.getDobDay()?1:(this.getDobDay()<other.getDobDay()?-1:0));    
    }
}

class SectorPayOrder implements Comparator<Person>
{
	public int compare(Person p1, Person p2)
    {           
           if(p1.getSector().compareTo(p2.getSector()) == 0){         

                 return (int) (p1.getPay() - p2.getPay());         
    		}
            
        	return p1.getSector().compareTo(p2.getSector());
            
    }
}

class NineToFiver extends LocalResource{//should extend LocalResource

    private int starthour, endhour;
    private int numWorkDays;
    private double hourlyRate;
    private double taxRate;

    public NineToFiver(String dob, String sector,
    int starthour, int endhour, int numWorkDays, 
    double hourlyRate, double taxRate)
    {
        super(dob,sector);
        this.starthour= starthour;
        this.endhour = endhour;
        this.numWorkDays=numWorkDays;
        this.hourlyRate=hourlyRate;

    }

    public double getPay()
    {
        return (1 - taxRate)*hourlyRate*(endhour-starthour)*numWorkDays;
    }

    public String getContact()
    {
        //UNCOMMENT BELOW AFTER IMPLEMENTING LOCALRESOURCE
        return "Local Employee #"+getId();
    }
}

class PayOrder implements Comparator<Person> {
    public int compare(Person p1, Person p2)
    {
        if (p1.getPay()>p2.getPay())
            return 1;
        else
        {
            if (p1.getPay()<p2.getPay())
                return -1;
            else
                return 0;
        }
        //try this -- return p1.getPay() - p2.getPay();
    }

}

public class SML{

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // TODO Auto-generated method stub
        double taxRate =0.15;
        Random r = new Random();
        //comment the line below to have fun with test cases 4 and 5
        r.setSeed(7);

        String test = bufferedReader.readLine().replaceAll("\\s+$","");
     
        ArrayList<Person> plist= new ArrayList<Person>();
   //MOVE DOWN BEGINNING OF COMMENT BLOCK AFTER DOING WORK TO PASS TEST CASE
        if (test.equals("CASE0-Q1"))
        {
            ExpatConsultant ec0 = new ExpatConsultant("2/1/2001","Computing",200000,taxRate,50000);
            if (ec0 instanceof RegisteredExpat)
                System.out.println(ec0);
            else
                System.out.println("RegisteredExpat not implemented");  
        }
        
         //MOVE COMMENT BLOCK TO HERE TO PASS CASE0
        if (test.equals("CASE1-Q2"))
        {
            ExpatConsultant ec0 = new ExpatConsultant("2/1/2001","Computing",200000,taxRate, 50000);
            System.out.println(ec0.getWorkPermit()+";Birthday:"+ec0.getDobDay()+"/"+ec0.getDobMonth()+"/"+ec0.getDobYear());
        }
        //MOVE COMMENT BLOCK TO HERE TO PASS CASE1
        taxRate =0.1;

        if (test.equals("CASE2-Q3"))
        {
            NineToFiver n50 = new NineToFiver("2/1/2001","Computing",9,17,5,taxRate,200);

            if (n50 instanceof LocalResource)
            {
                System.out.println("LocalResource implemented");
                System.out.println(n50.getContact() +";Birthday:"+n50.getDobDay()+"/"+n50.getDobMonth()+"/"+n50.getDobYear());
                System.out.println(n50);//"TRN:" +n50.getTRN() +"; working in the "+n50.getSector()+" sector, is paid ");

            }
        }
       //MOVE COMMENT BLOCK TO HERE TO PASS CASE2
        if (test.equals("CASE3-Q4"))
        {
            LocalConsultant lc0 = new LocalConsultant("2/1/2001","Computing",200000,taxRate);
            if (lc0 instanceof LocalResource)
                System.out.println(lc0);
        }
       //MOVE COMMENT BLOCK TO HERE TO PASS CASE3
        if (test.equals("CASE4-Q5") ||test.equals("CASE5-Q6") )
        {

            double basefare = 50000;
            //add some nine to fivers
            int minwage = 100,numCases =5;
            for (int i=0; i< numCases;i++)
            {
                String birthday = getBirthDay("Jamaican",r);
                String sector = getSector(r);
                double hourlyRate = getHourlyRate(minwage,r);
                NineToFiver n5 = new NineToFiver(birthday, sector,9,17,5, hourlyRate, taxRate);
                plist.add(n5);
            }
            taxRate =0.15;
            //then some local consultants
            for (int i=0; i< numCases;i++)
            {
                String birthday = getBirthDay("Jamaican",r);
                String sector = getSector(r);
                double skillPrice = getConsultRate(r);
                LocalConsultant lc = new LocalConsultant(birthday, sector,skillPrice, taxRate);
                plist.add(lc);
            }

            // then some expat consultants
            for (int i=0; i< numCases;i++)
            {
                String birthday = getBirthDay("US/CAN",r);
                String sector = getSector(r);
                double skillPrice = getConsultRate(r);
                double airfare = getAirfare(basefare,r);
                ExpatConsultant ec = new ExpatConsultant(birthday, sector,skillPrice,taxRate, airfare);
                plist.add(ec);
            }    
            //thenprint some lists in various orders
            System.out.println("====================================");
            System.out.println("Displayed in order entered");
            System.out.println("====================================");
            for(Person p:plist)
                System.out.println(p);    
            System.out.println("-------------------------------------");
           
            if (test.equals("CASE4-Q5"))
            {
                System.out.println("====================================");
                System.out.println("Displayed in order of day of birth");
                System.out.println("====================================");
                //QUESTION 5  
                ///NEED CODE TO SORT plist --HINT- Person implements Comparable!//////
                Collections.sort(plist);
                for(Person p:plist)
                    System.out.println(p);    
                System.out.println("-------------------------------------");

            }
            //MOVE COMMENT BLOCK TO HERE TO PASS CASE5
            if (test.equals("CASE5-Q6"))
            {
                System.out.println("====================================");
                System.out.println("Displayed in order of pay rate");
                System.out.println("====================================");
                Collections.sort(plist,new PayOrder());
                for (Person p:plist)
                    System.out.println(p);    
                System.out.println("-------------------------------------");

                System.out.println("====================================");
                System.out.println("Displayed in order of sector and pay rate");
                System.out.println("====================================");
                //CODE TO EFFECT THE SORT//
                //CODE TO WRITE OUT SORTED DATA//    

				Collections.sort(plist, new SectorPayOrder());
                for(Person p:plist)
                {
                	System.out.println(p);
                }

                System.out.println("-------------------------------------");

            }
            
        
        }
    }
    static String getBirthDay(String country, Random r)
    {
        int d = Math.abs(r.nextInt())%28+1;
        int m = Math.abs(r.nextInt())%12+1;
        int y = 1956 + Math.abs(r.nextInt())%65;

        String daypart = (d<10)?"0"+d:""+d;
        String monthpart = (m<10)?"0"+m:""+m;
        String yearpart = (y<10)?"0"+y:""+y;
        String returnval="";
        if(country.equals("Jamaica"))
            returnval= daypart+"/"+monthpart+"/"+yearpart;
        else
            returnval= monthpart+"/"+daypart+"/"+yearpart;

        return returnval;
    }

    static String getSector(Random r)
    {
        String returnval="";
        int d = Math.abs(r.nextInt())%8;
        switch(d)
        {
            case 0:
                returnval ="Finance";
                break;
            case 1:
                returnval ="Entertainment";
                break;
            case 2:
                returnval ="Sports";
                break;
            case 3:
                returnval ="Computing";
                break;
            case 4:
                returnval ="Medical";
                break;        
            case 5:
                returnval ="Manufacturing";
                break;
            case 6:
                returnval ="Legal";
                break;
            default:
                returnval ="Unknown";
        }
        return returnval;
    }

    static double getHourlyRate(int base, Random r)
    {
        int d = Math.abs(r.nextInt())%1000;
        return (double)(base+d);

    }
    static double getConsultRate(Random r)
    {
        return Math.abs(r.nextInt())%500000;

    }

    static double getAirfare(double basefare, Random r)
    {
        return basefare + Math.abs(r.nextInt())%150000;
    }
}

