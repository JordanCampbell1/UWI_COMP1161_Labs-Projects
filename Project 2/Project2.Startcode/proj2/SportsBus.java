
import java.util.Scanner;



public class SportsBus extends  Bus {
    private int competitorArea;
    private int numSecurity;
    private Bus bus;

    /**
    This class is dervied from the Bus class, adding competitorArea and numSecurity attributes 

    @param b uses the info from this object to use the constructor when creating the SportBus object
    @param competitorArea the integer value of the competitorArea
    @param numSecurity the integer value of numSecurity
    */

    public SportsBus(Bus b, int competitorArea, int numSecurity)
    {
        super(b.getName(), b.getSize(), b.getPrice(), b.getLevel(), b.getMinistry()); 
        bus = b;
        this.competitorArea =     competitorArea;
        this.numSecurity=numSecurity;

    }

    public int getCompetitorArea()
    {
        return competitorArea;
    }

    public int getNumSecurity()
    {
        return numSecurity;
    }

    public Bus getBus(){
        return bus;
    }

    public void setCompetitorArea(int competitorArea)
    {

        this.competitorArea =competitorArea;
    }

    public void setNumSecurity(int numSecurity)
    {

        this.numSecurity =numSecurity;
    }

    public String toString()
    {
        return "ID:"+this.getId()+";"+this.getName() +";#Price:"+this.getPrice()+";Compet Area:"+competitorArea+";#Sec:"+numSecurity;

    }

    public String toFile()
    {
        return ""+this.getId()+";"+this.getName() +";"+this.getPrice()+";"+competitorArea+";"+numSecurity;

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



        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);
        setCompetitorArea(competitorArea);
        setNumSecurity(numSecurity);

    }
               

}