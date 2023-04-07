import java.util.Scanner;



public class PartyBus extends SportsBus{
    private int barArea;
    

    /** 
    This class specializes the Sports Bus which is derived from the Bus class, addng an attirbute called barArea

    @param s uses the info from this object to use the constructor when creating the PartyBus object
    @param barArea the area of the bar
    */

    public PartyBus(SportsBus s, int barArea)
    {
        super( s.getBus(), s.getCompetitorArea(), s.getNumSecurity());
        this.barArea = barArea;      
        
        
    }
    
  
    public int getBarArea()
    {
        return barArea;
    }


    public void setBarArea(int barArea)
    {
        
        this.barArea =barArea;
    }    
    

    
    public String toString()
    {
       return "ID:"+this.getId()+";"+this.getName() +";#Price:"+this.getPrice()+";Bar Area:"+barArea+";#Sec:"+getNumSecurity();
         
    }
       
    public String toFile()
    {
        return ""+this.getId()+";"+this.getName() +";"+this.getPrice() + ";"+barArea+";"+getNumSecurity();
               
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
        int currcompetitorArea= getCompetitorArea();
        int currnumSecurity = getNumSecurity();
        int currbarArea = getBarArea();



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

        System.out.println("Hit enter to keep competitorArea at ["+currcompetitorArea +"] or enter new competitorArea:");
        String competitorAreaEntry = scan.nextLine();
        int competitorArea;
        if (competitorAreaEntry.equals(""))
            competitorArea = currcompetitorArea;
        else
            competitorArea = Integer.parseInt(competitorAreaEntry);

        System.out.println("Hit enter to keep numSecurity at ["+currnumSecurity +"] or enter new numSecurity:");
        String numSecurityEntry=scan.nextLine();
        int numSecurity;
        if (numSecurityEntry.equals(""))
            numSecurity = currnumSecurity;
        else
            numSecurity = Integer.parseInt(numSecurityEntry);

        System.out.println("Hit enter to keep barArea at ["+currbarArea +"] or enter new barArea:");
        String barAreaEntry=scan.nextLine();
        int barArea;
        if (barAreaEntry.equals(""))
            barArea = currbarArea;
        else
            barArea = Integer.parseInt(barAreaEntry);



        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);
        setCompetitorArea(competitorArea);
        setNumSecurity(numSecurity);
        setBarArea(barArea);


    }
    

}
