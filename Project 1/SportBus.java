import java.lang.String;
import java.util.ArrayList;

public class SportBus extends Bus
{
    private int competitorArea, spectatorArea;

    private ArrayList<String> listOfSports = new ArrayList<String>();

    public SportBus(String name, int basePrice, int lev, Ministry mny, int competitorArea, int spectatorArea, String sportList)
    {
        super(name, competitorArea + spectatorArea, basePrice, lev, mny);


        this.competitorArea = competitorArea;
        this.spectatorArea = spectatorArea;

        
        for(String sport : sportList.split(","))
        {
            listOfSports.add(sport);
        }

        tripTypes += ",SPORT";
    }

    public double getCompetitorArea() //was double in the document
    {
        return competitorArea;
    }

    public double getSpectatorArea() //was double in the document
    {
        return spectatorArea;
    }

    public int getBasePrice()
    {
        return 3 * super.getBasePrice();
    }

    public int getEstimate(String type, int numPersons, int level) 
    {
        return getBasePrice() * 10 * (competitorArea + spectatorArea) / competitorArea;
    }
}