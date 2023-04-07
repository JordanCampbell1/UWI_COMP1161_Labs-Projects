import java.util.ArrayList;

public class Bus {
    private String name;
    private int size;
    private int basePrice;
    private ArrayList<Trip> approvedTrips;
    private int level; // 1,2,3 for low,medium, high repectively;
    private int id;
    private static int nextId=0;
    private Ministry mny;
    protected String tripTypes;
    
    private int getNextId(){
       return nextId++; 
       }
    
    public Bus(){
        approvedTrips=new ArrayList<Trip>();
        }
            
    public Bus( String name, int size, int basePrice, int lev, Ministry mny) {
            approvedTrips=new ArrayList<Trip>();
            this.name = name;
            this.size =size;
            this.basePrice = basePrice;
            this.level = lev;
            this.id = getNextId();
            this.mny = mny;
            tripTypes = "BASICTRANSPORT"; 
        
    }
        
    public boolean available(Date date){
            boolean retval = true;
            for (Trip t:approvedTrips)
               if  (t.getDate().getDay() == date.getDay())
                   retval = false;
            return retval;
    }
        
    public int getBasePrice(){
            return basePrice;
    }
    public int getId(){
            return id;
    }
        
    public String getName(){
            return name;
    }
        
    public double getSize(){
            return size;
    }
        
    public String toString(){
            return name;
    }
        
    public boolean isSuitable(String type){
            return tripTypes.contains(type);
    }
        
    public int getEstimate(String type, int numPassengers, int comfortLevel){              
            return basePrice;
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
                  if (trip.getPlanner().getBudget() >= est){
                    approvedTrips.add(trip);
                    trip.setBus(this);
                    retval = result;
                }
            }
            return retval;
             
            
            
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
}