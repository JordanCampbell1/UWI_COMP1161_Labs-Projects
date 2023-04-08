import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



//Implement the AssignPlan class here


class AssignPlan{
  private String highestPriorityItem="";
  private int numAssignments, numComplete, hoursAvailable;
  private double totalPoints;

  
  //Question 1. Properly implement accessor methods for the private variables  highestPriorityItem, numAssignments, numComplete, hoursAvailable, totalPoints. 
  
  private String getHighestPriorityItem()
  {
      return highestPriorityItem;
  }
    
  public int getNumAssignments()
  {
      return numAssignments;
  }
    
  public int getNumComplete()
  {
      return numComplete;
  }
    
  public int getHoursAvailable()
  {
      return hoursAvailable;
  }
    
  public double getTotalPoints()
  {
      return totalPoints;
  }
  
  public String toString(){

    return "======================\n"
     + "-----TODAY'S PLAN-----\n"
     + "----------------------\n"
     + "Priority:"+getHighestPriorityItem()+"\n"
     + "Assignments:"+getNumAssignments()+"\n"
     + "Completed:"+getNumComplete()+"\n"
     + "Hours Avail:"+getHoursAvailable()+"\n"
     + "Tot. Points:"+getTotalPoints()+"\n";
           
  }
  

  public AssignPlan(){   
  }
  

  public AssignPlan(int numAssigns){   
     numAssignments = numAssigns;
  }
  
  
  //Question 2. Implement an alternate constructor for DailySchedule
  
   public AssignPlan(String hPriorityItem, int nAssigns, int nComplete, int hAvailable, double tPoints)
   {
         //complete this constructor
       
       highestPriorityItem = hPriorityItem;
       numAssignments = nAssigns;
       numComplete = nComplete;
       hoursAvailable = hAvailable;
       totalPoints = tPoints;
       
     
   }


    //Question 3.	Implement a mutator function to set the highestPriorityItem
  public void setPriorityTo(String aname)
  {
      
      highestPriorityItem = aname;
      
  }
  
 
  
  public void handleUrgentAssignment (String name, int effort, int resources, int difficulty)
  {
      
	  highestPriorityItem = name;
      
      Assignment UrgentAssignment = new Assignment(effort, resources, difficulty);
      
      numAssignments++;
      
      numComplete++;
      
      hoursAvailable -= effort;
      
      totalPoints += UrgentAssignment.getexpscore();
    
    
      
      
  }
//Question 5.	Complete the getUrgentAssignment method of the AssignPlan object so that it
//        a.    Updates the highest priority item to the value of the incoming name
//        b.	Creates an Assignment with knowledge of the difficulty, resources and effort
//        c.	Increments the number of assignments in the AssignPlan by 1
//        d.	Increments the number complete by 1
//        e.	Update the hoursAvailable by subtracting the effortHours 
//        f.	Update the total points by adding the points calculated on the assignment.
  
             
}


class Assignment{
  //Question 4.
  
  	//The Assignment class will privately store information on the effort (hours required to complete)(int), the number of resources to be referenced (int), the estimated difficulty(int), and the expected score(double). 
	//Create a constructor for an Assignment, that accepts the effort, resources and difficulty. The constructor then evaluates and stores the expected score of the asignment,
	//The Assignment returns accessors for all private variables
    //The method that returns the score should round to 1 decimal point
    
    private int Effort;
    private int numOfResources;
    private int estDifficulty; 
    private double expScore;
    
    public Assignment(int effort, int numofresources, int estdifficulty)
    {
        Effort = effort;
        
        numOfResources = numofresources;
        
        estDifficulty = estdifficulty;
        
        expScore = calcexpscore();
    }
    
    public int getEffort()
    {
        return Effort;
    }
    
    public int getnumofresources()
    {
     	return numOfResources; 
    }
    
    public int getestdifficulty()
    {
        return estDifficulty;
    }
    
    public double getexpscore()
    {
        return expScore;
    }
    
    public double calcexpscore()
    {
        return Math.round( 0.1 * ( Effort * estDifficulty + ( Math.pow(numOfResources * Effort,2) / (Math.PI * Math.sqrt(estDifficulty)) )) * 10) / 10.0;
    }
    
  
}



public class Tester {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        for(int test =0; test<t; test++)
        {
            try {
              AssignPlan aplan= new AssignPlan();
              String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
              if (firstMultipleInput.length ==5)
              {
                
                String highestPriorityItem = firstMultipleInput[0];
                int numOutStanding = Integer.parseInt(firstMultipleInput[1]);
                int numComplete = Integer.parseInt(firstMultipleInput[2]);
                int hoursAvailable = Integer.parseInt(firstMultipleInput[3]);
                double totalPoints = Double.parseDouble(firstMultipleInput[4]);
                AssignPlan a = new AssignPlan(highestPriorityItem,numOutStanding,numComplete,hoursAvailable,totalPoints);
                aplan = a;
              }
              if (firstMultipleInput.length ==1)
              {
                int numOutStanding = Integer.parseInt(firstMultipleInput[0]);
                AssignPlan a = new AssignPlan(numOutStanding);
                aplan = a;
              }
              String aname = bufferedReader.readLine();
              if (aname.length() >0)
                   aplan.setPriorityTo(aname);
              int assigns = Integer.parseInt(bufferedReader.readLine().trim());
              for (int anum=0; anum<assigns;anum++)
              {
              String secondInput= bufferedReader.readLine();
                
                if (!(secondInput==null))
                {
                String[] secondMultipleInput = secondInput.replaceAll("\\s+$", "").split(" ");
                if (secondMultipleInput.length ==4)
                {
                     String uaname = secondMultipleInput[0];
                     int effort = Integer.parseInt(secondMultipleInput[1]);
                     int resources = Integer.parseInt(secondMultipleInput[2]);
                     int difficulty = Integer.parseInt(secondMultipleInput[3]);
                     aplan.handleUrgentAssignment(uaname, effort, resources, difficulty);
                    
                  
                }
                
                }
              }//for anum
                
                 System.out.println(aplan.toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
      
     bufferedReader.close();
    }
}