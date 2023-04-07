public class Date
{
    private int day;

    public Date(int day)
    {
        this.day = day;
    }
    
    public void setDay(int day)
    {
        this.day = day;
    }

    public int getDay()
    {
        return day;
    }

    public Date next(int day) 
    {
        return (new Date(day++));
    }

    public String toString()
    {
        return Integer.toString(day);
    }
}