 
import java.util.ArrayList;
import java.io.*;

public class Driver {

    public static void main(String[] args) throws IOException {
        //TODO Auto-generated method stub
        PrintStream outFile = new PrintStream(new File("Project1.Output.txt"));
        System.setOut(outFile);
        //Nothing below here has changed
        ArrayList<Planner> planners = new ArrayList<Planner>();
        Ministry mny = new Ministry("TRANSPORT" );
        Bus[] buses = populateBuses(mny);
        

        Planner p1 = new Planner("Planner1",100000,mny,buses);
        Planner p2 = new Planner("Planner2",50000,mny, buses);
        Planner p3 = new Planner("Planner3",60000,mny,buses);
        Planner p4 = new Planner("Planner4",2000000,mny,buses);
        Planner p5 = new Planner("Planner5",72500000,mny,buses);

        planners.add(p1);
        planners.add(p2);
        planners.add(p3);
        planners.add(p4);
        planners.add(p5);

        int testDays=3;

        for (int i=0; i< testDays;i++)
        {
            for(Planner pl:planners)
            {
                int approvalId=-1;
                
                
                System.out.println("======================================");
                System.out.println(pl.getName()+ "[budget:$"+pl.getBudget()+"] planning basic transport for 10 people on day "+ i);
                if ((approvalId=pl.planTrip(10, "BASICTRANSPORT",new Date(i), 1))>=0)
                    System.out.println("Approval Certificate #"+approvalId + " granted to "+pl.getName() + " for trip");
                else
                    System.out.println("Didn't work out");
                System.out.println("--------------------------------------");
                
                System.out.println("======================================");
                System.out.println(pl.getName()+ "[budget:$"+pl.getBudget()+"] planning basic transport for 100 people on day "+ (i +1));
                 if ((approvalId=pl.planTrip(100, "BASICTRANSPORT",new Date(i+1), 1))>=0)
                    System.out.println("Approval Certificate #"+approvalId + " granted to "+pl.getName() + " for trip");
                else
                    System.out.println("Didn't work out");
                System.out.println("--------------------------------------");
                
                

                System.out.println("======================================");
                System.out.println(pl.getName()+ "[budget:$"+pl.getBudget()+"] planning a training trip with 10 people on day "+ (i+2));
                if ((approvalId=pl.planTrip(10, "TRAINING",new Date(i+2), 2))>=0)
                    System.out.println("Approval Certificate #"+approvalId + " granted to "+pl.getName() + " for trip");
                else
                    System.out.println("Didn't work out");
                System.out.println("--------------------------------------");
                
                
                System.out.println("======================================");
                System.out.println(pl.getName()+ "[budget:$"+pl.getBudget()+"] planning a sports trip with 20 competitors and 10 others on day "+ (i+3));
                if ((approvalId=pl.planTrip(30, "SPORT",new Date(i+3), 2))>=0)
                    System.out.println("Approval Certificate #"+approvalId + " granted to "+pl.getName() + " for trip");
                else
                    System.out.println("Didn't work out");
                                
                System.out.println("--------------------------------------");

            
                System.out.println("======================================");
                System.out.println(pl.getName()+ "[budget:$"+pl.getBudget()+"] planning a party trip with 20 people on day "+ (i+4));
                if ((approvalId=pl.planTrip(20, "PARTY",new Date(i+3), 3))>=0)
                    System.out.println("Approval Certificate #"+approvalId + " granted to "+pl.getName() + " for trip");
                else
                    System.out.println("Didn't work out");
                System.out.println("--------------------------------------");
                
                System.out.println("======================================");
                System.out.println(pl.getName()+ "[budget:$"+pl.getBudget()+"] planning a party trip with 500 people");
                 if ((approvalId=pl.planTrip(200, "PARTY",new Date(i+3), 3))>=0)
                    System.out.println("Approval Certificate #"+approvalId + " granted to "+pl.getName() + " for trip");
                else
                    System.out.println("Didn't work out");
                System.out.println("--------------------------------------");
                
                
            
            }

        }
        //mny.showEvents();
        for (Bus b : buses)
        {
            b.promoteTrips();
        }
            

            
    }

    public static  Bus[] populateBuses(Ministry mny)
    {   
        
        Bus [] buses = new Bus[8];
        buses[0] = new Bus("BUS_SML",300, 100,  1, mny);
        buses[1] = new Bus("BUS_BIG", 1000, 850, 1, mny);

        
        buses[2] = new SportBus("SPT_SML",800, 2,mny, 100,200,"Football,Hockey");
        buses[3] = new SportBus("SPT_BIG",3000, 2,mny,1000,2000,"Football,Hockey,Volleyball,Swimming,TKD");
        buses[4] = new TrainingBus("TRN_SML",200, 20,2,mny, 200,500,  "Math, Computing");
        buses[5] = new TrainingBus("TRN_BIG",5000,50,2,mny, 2500, 4000, "Math, Computing, Physics,Chemistry");
        //////////UNCOMMENT BELOW ONLY IF YOUR PartyBus is a SportBus
        buses[6] = new PartyBus("PRT_SML" , 1000,  3, mny, 500, 500, "Badminton,Tennis", 50,80, 5);
        buses[7] = new PartyBus("PRT_BIG" , 5000,  3, mny, 1500, 1000, "Swimming, Gymnastics,Badminton,Tennis", 100,200, 10);
        
        //////////UNCOMMENT BELOW ONLY IF YOUR PartyBus is a TrainingBus
        //buses[6] = new PartyBus("PRT_SML" , 5000,  3, 20, mny, 500, 500, "Badminton,Tennis", 50,80, 5);
        //buses[7] = new PartyBus("PRT_BIG" , 10000, 3, 50, mny, 1500, 1000, "Swimming, Gymnastics,Badminton,Tennis", 100,200, 10);
        
        
        return buses;
        
    }



}