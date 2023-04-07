
public class ApprovalRequest
{
    private Trip trip;
    private Bus bus;
    private int id;
    private static int nextId;
    public ApprovalRequest(Trip trip, Bus bus){
        this.trip = trip;
        this.bus = bus;
        this.id = nextId++;
    }
    
    public Trip getTrip(){
        return trip;
    }
    
    public Bus getBus(){
        return bus;
    }
    
    public int getId(){
        return id;
    }
  
}
