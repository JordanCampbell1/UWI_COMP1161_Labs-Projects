import java.util.ArrayList;

public class PartyBus extends SportBus
{
    private int barArea, foodArea, securityCrew;

    private ArrayList<String> SportsList = new ArrayList<String>();

    public PartyBus(String name, int basePrice, int lev, Ministry mny, int competitorArea, int spectatorArea, String sportList ,int barArea, int foodArea, int securityCrew)
    {
        super(name, basePrice, lev, mny, competitorArea, spectatorArea, sportList);

        this.barArea = barArea;
        this.foodArea = foodArea;
        this.securityCrew = securityCrew;

        tripTypes += ",PARTY";

        for(String sport : sportList.split(","))
        {
            SportsList.add(sport);
        }

    }

    public double getBarArea()
    {
        return barArea;
    }

    public double getFoodArea()
    {
        return foodArea;
    }

    public int getSecurityCrew()
    {
        return securityCrew;
    }

    public int getEstimate(String type, int numPassengers, int comfortLevel)
    {
       return (comfortLevel + 1) * barArea * super.getEstimate(type, numPassengers, comfortLevel) / foodArea;
    }
}