import java.io.*;
import java.util.*;


//---------------------------
//
class InternetThing{
//QUESTION 1. Complete this class as described in question 1 (after correcting the compile error)
    private String manufacturer, serialNumber, ipAddress;
    private static int nextId =0;
    private static int numThings = 0;
    private int id;
    private int powerUse =0;
    protected String password;
    
    
    public InternetThing()
    {   
    }
    
    //Complete InternetThing to pass case 1
    public InternetThing(String manufacturer, String serial){
         this.manufacturer = manufacturer;
        serialNumber = serial;
        id = nextId++;
        ipAddress = "192.168.0." + id;
        powerUse = 1;
        password = "admin";
        System.out.println("Created " + toString());
        numThings++;       
    }
    
    public String getSerialNo()
    {
        return serialNumber;
    }
    
    public String password()
    {
        return password;
    }
    
    
    public int getId(){
      return id;
    }
    
      public String getManufacturer(){
       return manufacturer;
    }
    
      public String getIPAddress(){
        return ipAddress;
    }  
    
    public int getPowerUse(){
        return powerUse;
    }
    
    public static int getNumThings(){
      return numThings;
    }
    
    public void setPassword(String something)
    {
        password = something;
    }
    
    public String toString(){
        return "Thing#"+getId()+":made by "+ manufacturer+"@IP:"+ipAddress;
    }
    

    
}

//QUESTION 3. Create class SmartPhone
//Pass case2 by completing SmartPhone
class SmartPhone extends InternetThing{
   String  model;
   int megaPixels;
   boolean locked; 
    
  public SmartPhone(String  manufacturer, String serialNumber,String model,int megaPixels){
      super(manufacturer, serialNumber);
      
      this.model = model;
      this.megaPixels = megaPixels;
      
      locked = true;
      
      System.out.println("Created " + toString());
      
  }
        

     public void lock(){
         locked = true;
         
         System.out.println("Locked " + toString());
     }
    
    
     public void unlock(String pw){
         if(password.equals(pw))
         {
             locked = false;
             
             System.out.println("Unlocked " + toString());
         }
    }
          
     
     public void setPassword(String oldPassword, String newPassword){
         if(locked == false && oldPassword.equals(password))
         {
             password = newPassword;
             
             System.out.println("Successfully changed password for " + toString());
         }
     }
    
    public boolean isLocked()
    {
        return locked == true;
    }
    
    public String toString()
    {
        return "Thing#"+getId()+"::PHONE made by "+ getManufacturer() +":Model="+model+"@IP:"+getIPAddress();
    }
    
    
}

//QUESTION 4. Create class LightBulb
//Pass case3 by completing LightBulb

class LightBulb extends InternetThing{
    int lumenCount;
    boolean lightOn;
    
    public LightBulb(String manufacturer, String serial, int lumenCount)
    {
        super(manufacturer, serial);
        this.lumenCount = lumenCount;
        lightOn = false;
        
        System.out.println("Created " + toString());
    }
    
    public void turnOn(){
        lightOn = true;
        
        System.out.println("Turned on " + toString());
    }
    public void turnOff(){
        lightOn = false;
        
        System.out.println("Turned off " + toString());
    }
    
    public int getPowerUse()
    {
        if(lightOn == true)
        {
            return lumenCount * super.getPowerUse();
        }
        else
        {
            return 0;
        }
        
    }
    
    
 }


///* UNCOMMENT AFTER IMPLEMENTING InternetThing
class Refrigerator extends InternetThing {

    private double basePower;
    private double powerRating;
    private int capacity;
    private int contents =0;
    

    public Refrigerator(String manufacturer,String serialNumber,
            double basePower,double powerRating, int capacity)
    {
        super(manufacturer, serialNumber);
        
        this.basePower = basePower;
        this.powerRating= powerRating;
        this.capacity = capacity;
        this.contents = 0;
        System.out.println("Created "+this.toString());
    }



    public void insertItem(int size)
    {
        if (contents+size<capacity)
        {
            contents+=size;
            System.out.println("Inserted to "+ this.toString());
        }
    }



    public void removeItem(int size)
    {
        if (contents-size>0)
        {
            contents-=size;
            System.out.println("Removed from "+ this.toString());
        }


    }

    public String toString()
    {
        return "Thing#"+getId() +"::REFRIGERATOR, made by "+getManufacturer()+":BasePower="+basePower+":Rating="+powerRating+"@IP:"+getIPAddress();
    }
    
    //QUESTION 5. Overload getPowerUse() in Refrigerator
    
    public int getPowerUse()
    {
       return (int) (basePower + capacity * powerRating);
    }

    //Pass case4 by completing getPowerUse here
  
 
}

//*/
class SmartHome {
    InternetThing baseAddress, router;
    private final int MAX_THINGS = 252;
    int numThings =0;
    public InternetThing[] things = new InternetThing[MAX_THINGS];
    //NB Making "things" public violates the principle of self-governing classes, but without additional knowledge that will be gained in coming weeks, this is the available strategy to aggregate a group of related sub-classes  
    public SmartHome()
    {
        InternetThing baseAddress = new InternetThing("BASENET","N00");
        InternetThing router = new InternetThing("BASENET","N01");
        things[0]=baseAddress;
        things[1]=router;
        numThings=2;
        System.out.println(baseAddress.toString());
        System.out.println(router.toString());
      
    }

    public int addThing(String [] args )
    {
        int returnval=-1;
        //TO HELP YOUR DEBUGGING IF NECESSARY
        //String argList ="";
        //for (int i=0; i< args.length;i++)
        //     System.out.println(i+":"+args[i]);
        if (args[0].equals("REFRIGERATOR"))
        {
            String manufacturer = args[1];
            String serialNo = args[2];
            int basePower = Integer.parseInt(args[3]);
            int powerRating =Integer.parseInt(args[4]);
            int capacity = Integer.parseInt(args[5]);
             //Pass case 1 by instantiating Refrigerator as rf
            //1. Create a refrigerator object by calling the relevant constructor with the following arguments 
            //   (in  order... manufacturer, serialNo, basePower, powerRating, capacity)
            
            Refrigerator rf = new Refrigerator(manufacturer, serialNo, basePower, powerRating, capacity);
            //2. Set the value in array things, at the position that matches the ID of the refrigerator ie (rf.getId()), to the refrigerator object.            
            //Uncomment below after instantiating a Refrigerator  
              returnval = rf.getId();
            
            things[returnval] = rf;
            
            
        }
       
        if (args[0].equals("SMARTPHONE"))

        {
            String manufacturer = args[1];
            String serialNo = args[2];
            String model = args[3];
            int megaPix = Integer.parseInt(args[4]);

            SmartPhone sp = new SmartPhone(manufacturer,serialNo,model,megaPix);
            things[sp.getId()]=sp;
            returnval = sp.getId();
        }
        

         if (args[0].equals("LIGHTBULB"))
        {
            String manufacturer = args[1];
            String serialNo = args[2];
            int lumens = Integer.parseInt(args[3]);
            //Complete this section by instantiating the LightBulb from the arguments and then  adding it to the set of things
             LightBulb lb = new LightBulb(manufacturer, serialNo, lumens);
             
            //UNCOMMENT THE LINES BELOW AFTER INSTANTIATING LIGHTBULB as lb
            
            returnval = lb.getId();
             
            things[returnval]=lb;

            
        }


    
        return returnval;
    }


    public void showItemPower(int id)
    {

        System.out.println(things[id].getPowerUse() +"mW\t"+things[id].toString());
    }


    public void showThing(int id)
    {
        // shows the toString method of the thing at position id
        System.out.println( things[id].toString());
    }

    public void showAllThings()
    {
        System.out.println("===============SHOWING ALL "+InternetThing.getNumThings()+" THINGS===============");
        for(int i=0; i<InternetThing.getNumThings();i++)
            showThing(i);
    }

    public void showAllPower()
    {
        //Question 6
        //Pass case 5 by completing showAllPower()
     
        System.out.println("===============SHOWING ALL POWER===============");

        int sumPower =0;
        int i=0; 
        for(InternetThing example : things )//replace with appropriate code
        {  
            int powerVal = example.getPowerUse();//replace with appropriate code
            
            System.out.println(powerVal+"\t"+example.toString());
            
            sumPower += powerVal;
            
        }
        System.out.println("TOTAL POWER = "+sumPower+"mW");
    }
}


/**************DO NOT EDIT BELOW THIS LINE!!!***************************************/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Write your code here
        SmartHome sh = new SmartHome();
        String mode= "SMARTHOME";

        String[] primingInput = bufferedReader.readLine().replaceAll("\\s+$","").split(" ");
        int t = Integer.parseInt(primingInput[0]);
        for(int i=0;i<t;i++) {
            try {
                String inLine = bufferedReader.readLine().replaceAll("\\s+$","");
                String[] inputLine= inLine.split(" ");
                int itemId = sh.addThing(inputLine);
                mode = inputLine[0];
                String actionCount = bufferedReader.readLine();
                int numActions = Integer.parseInt(actionCount);
                for (int j=0; j<numActions;j++)
                {
                    String actionItem[] = bufferedReader.readLine().replaceAll("\\s+$","").split(" ");
                    {
                        if (actionItem[0].equals("Insert"))
                        {
                            int actionval = Integer.parseInt(actionItem[1]);
                            ((Refrigerator)sh.things[itemId]).insertItem(actionval);
                        }
                        if (actionItem[0].equals("Remove"))
                        {
                            int actionval = Integer.parseInt(actionItem[1]);
                            ((Refrigerator)sh.things[itemId]).removeItem(actionval);
                        }
                    }
                    {              
                        if (actionItem[0].equals("lock"))
                        {
                            ((SmartPhone)sh.things[itemId]).lock();
                        }
                        if (actionItem[0].equals("unlock"))
                        {
                            String pw = actionItem[1];
                            ((SmartPhone)sh.things[itemId]).unlock(pw);
                        }                    
                        if (actionItem[0].equals("setpassword"))
                        {
                            String oldpw = actionItem[1];
                            String newpw = actionItem[2];
                            ((SmartPhone)sh.things[itemId]).setPassword(oldpw,newpw);

                        }
                    }
                    {
                        if (actionItem[0].equals("turnOn"))
                        {
                            ((LightBulb)sh.things[itemId]).turnOn();

                        }
                        if (actionItem[0].equals("turnOff"))
                        {
                            ((LightBulb)sh.things[itemId]).turnOff();

                        }
                        if (actionItem[0].equals("showpower"))
                        {
                            sh.showItemPower(itemId);

                        }

                    }


                }

            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }



        }
        String finalMode = bufferedReader.readLine();
        if (finalMode.length()>0)
        {
            String actionCount = bufferedReader.readLine();
            int numActions = Integer.parseInt(actionCount);
            for (int j=0; j<numActions;j++)
            {
                String actionItem[] = bufferedReader.readLine().replaceAll("\\s+$","").split(" ");
                //System.out.println(actionItem[1]);
                if (actionItem[0].equals("showAllPower"))
                {

                    sh.showAllPower();
                }
                if (actionItem[0].equals("showAllThings"))
                {
                    sh.showAllThings();     
                }
            }
        }
    }
}