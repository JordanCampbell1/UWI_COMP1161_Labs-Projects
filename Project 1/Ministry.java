import java.util.ArrayList;

public class Ministry {
    private String name;
    private final int ECONOMY_DAILYMAX =50000;
    private final int BUSINESSCLASS_DAILYMAX =5000;
    private final int LUXURY_DAILYMAX =500;

    public static final int ECONOMY_SEPARATION = 2;
    public static final int BUSINESSCLASS_SEPARATION = 4;
    public static final int LUXURY_SEPARATION  =6;


    private int comfortLevel;

    private ArrayList<ApprovalRequest> approvedRequests;
    private int [] maxTravellers;

    
    public Ministry(String name){
        this.name = name;
        approvedRequests = new ArrayList<ApprovalRequest>();
        maxTravellers = new int[3];
        maxTravellers[0] = ECONOMY_DAILYMAX;
        maxTravellers[1] = BUSINESSCLASS_DAILYMAX;
        maxTravellers[2] = LUXURY_DAILYMAX;
        }
    
        public int getSeparation(int level){
            int retval=0;
            switch(level){
                case 1:{
                    retval = ECONOMY_SEPARATION;
                    break;
                }
                case 2:{
                    retval = BUSINESSCLASS_SEPARATION;
                    break;
                }
                case 3:{
                    retval = LUXURY_SEPARATION;
                    break;
                }
            }
            return retval;
        }
        
    
        
        public int checkApproval(ApprovalRequest ar){
            int retval = -1;
            int maxAllowed = maxTravellers[ar.getTrip().getComfortLevel()-1];
            int travelDate = ar.getTrip().getDate().getDay();
            int travellersOnDay =0;
            for (ApprovalRequest a:approvedRequests)
               if (a.getTrip().getDate().getDay()==travelDate)
                  travellersOnDay+=a.getTrip().getNumPeople();
            
            if (travellersOnDay+ar.getTrip().getNumPeople() <=maxAllowed)
               retval = ar.getId();
            else
               System.out.println("Traveller limit of " + maxAllowed + " for class " +ar.getTrip().getComfortLevel() + " would be exceeded on day " + ar.getTrip().getDate().getDay()+".");
               
            return retval;
        }
        
        

}