import java.util.ArrayList;

public class TrainingBus extends Bus
{
    private int teacherArea, studentArea, wifiRange;

    private ArrayList<String> listOfCourses = new ArrayList<String>();
    
    public TrainingBus (String name, int basePrice, int wifiRange, int lev, Ministry mny, int teacherArea, int studentArea, String courseList)
    {
        super(name, teacherArea + studentArea, basePrice, lev, mny);

        tripTypes += ",TRAINING";

        for(String sport : courseList.split(","))
        {
            listOfCourses.add(sport);
        }

        this.teacherArea = teacherArea;
        this.studentArea = studentArea;
        this.wifiRange = wifiRange;
    }

    public double getTeacherArea()
    {
        return teacherArea;
    }

    public double getStudentArea()
    {
        return studentArea;
    }

    public int getWifiRange()
    {
        return wifiRange;
    }

    public int getBasePrice()
    {
        return 2 * super.getBasePrice();
    }

    public int getEstimate(String type, int numPassengers, int comfortLevel) 
    {
        return (int) ((5 * teacherArea * super.getEstimate(type, numPassengers, comfortLevel)) / listOfCourses.size());
    }
}