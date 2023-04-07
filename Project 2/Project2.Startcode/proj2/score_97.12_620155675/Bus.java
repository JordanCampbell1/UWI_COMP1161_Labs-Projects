 

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Bus implements Comparable<Bus> {
    private String name;
    private int size;
    private int price;
    private ArrayList<Trip> approvedTrips;
    private int level; // 1,2,3 for low,medium, high repectively;
    private int id;
    private static int nextId=0;
    private Ministry mny;
    protected String tripTypes;
    
    private int getNextId(){
       return ++nextId; 
       }
    
    
    
    
    public Bus(){
        approvedTrips=new ArrayList<Trip>();
        }
            

    /**
    This is the basic bus that the other buses are dervied from

    @param name the name of the particular bus
    @param size the size of the bus
    @param price the cost of using the bus
    @param lev the particular level of the bus
    @param mny the particular minitry that the bus is attached to
    */
        public Bus( String name, int size, int price, int lev, Ministry mny) {
            approvedTrips=new ArrayList<Trip>();
            this.name = name;
            this.size =size;
            this.price = price;
            this.level = lev;
            this.id = getNextId();
            this.mny = mny;
            tripTypes = "BASICTRANSPORT";
        
        }
        public int compareTo(Bus other)
        {
            return this.getName().compareTo(other.getName());
        }
        public boolean available(Date date){
            boolean retval = true;
            for (Trip t:approvedTrips)
               if  (t.getDate().getDay() == date.getDay())
                   retval = false;
            return retval;
        }
        
        public int getPrice(){
            return price;
        }
        public int getId(){
            return id;
        }
        
        public String getName(){
            return name;
        }
        
        public int getSize(){
            return size;
        }
        
        public int getLevel(){
            return level;
        }
        
        public Ministry getMinistry(){
            return mny;
        }
        

        
        public void setName(String name){
            this.name = name;
        }
                
        public void setPrice(int price){
            this.price = price;
        }
        public void setSize(int size){
            this.size = size;
        }
        
        public void setLevel(int level){
            this.level = level;
        }
        
        
        
        
        public boolean isSuitable(String type){
            return tripTypes.contains(type);
        }
        
        public int getEstimate(String type, int numPassengers, int comfortLevel){              
            return price;
        }
        
        public boolean canHold(int numPassengers, int comfortLevel){
            int capacity = (int)(size / mny.getSeparation(comfortLevel));
            return numPassengers <=capacity;
            
        }
        
        
        
        public void promoteTrips(){
          System.out.println();
          System.out.println("TRIPS ON " +getName());
          System.out.println("===================");
          for (Trip t:approvedTrips)
              System.out.println(t);
                            
        }
        
        public int reserve(Trip trip,  Ministry mny){
            int retval = -1;
            ApprovalRequest ar = new ApprovalRequest(trip, this);
            int result = mny.checkApproval(ar);
            if (result >=0){
                int est = getEstimate(trip.getType(), trip.getNumPeople(), trip.getComfortLevel());
                  if (trip.getPlanner().getBudget() >= getEstimate(trip.getType(), trip.getNumPeople(), trip.getComfortLevel())){
                    approvedTrips.add(trip);
                    trip.setBus(this);
                    retval = result;
                }
            }
            return retval;
             
            
            
        }


        
    public void promoteTrips(PrintStream outStream)
    {
        outStream.println("TRIPS ON "+getName());
        outStream.println("===================");
        for(Trip t:approvedTrips)
           outStream.println(t);
        outStream.println("--------------------");
           
    }
    
    public ArrayList<Trip> getApprovedTrips()
    {
        
        return approvedTrips;
    }
    

    public String toString()
    {
        return "ID:"+this.getId()+";"+this.name +";#Price:"+this.getPrice()+";Area:"+this.getSize();
        
    }
    
    public String toFile()
    {
        return ""+this.getId()+";"+this.name +";"+this.getPrice()+";"+this.getSize();
        
    }
      
    
    
    public static void resetId()
    {
        
        nextId=0;
    }

    /**
    This function allows the user to update all of the attributes associated with this class
    @param scan this allows the program to accept data from the keyboard

     */
    
    public void updateLocalData(Scanner scan)
    {
        scan.nextLine();
        String currname = getName();
        int currSize = getSize();
        int currPrice = getPrice();
        int currLevel = getLevel();



        System.out.println("Hit enter to keep name as ["+currname+"], or enter new name:");
        String name = scan.nextLine();
        if (name.equals(""))
            name = currname;
            
        System.out.println("Hit enter to keep size at ["+currSize +"] or enter new size:");
        String sizeEntry=scan.nextLine();
        int size;
        if (sizeEntry.equals(""))
            size = currSize;
        else
            size = Integer.parseInt(sizeEntry);

        System.out.println("Hit enter to keep price at ["+currPrice+"] or enter new price:");
        String priceEntry = scan.nextLine();
        int price;
        if(priceEntry.equals(""))
            price = currPrice;
        else
            price = Integer.parseInt(priceEntry);

        System.out.println("Hit enter to keep level at ["+currLevel+"] or enter new level:");
        String levelEntry = scan.nextLine();
        int level;
        if(levelEntry.equals(""))
            level = currLevel;
        else
            level = Integer.parseInt(levelEntry);



        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);

    }

    
    
        
}