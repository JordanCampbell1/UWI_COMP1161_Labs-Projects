
import java.util.Scanner;



public class TrainingBus extends Bus {
    private int teacherArea;
    
    /**
    This is a specialization of the bus class that adds the teacherArea attribute

    @param b uses the info from this object to use the constructor when creating the TrainingBus object
    @param teacherArea the integer value of the teacherArea
    */

    
    public TrainingBus(Bus b, int teacherArea)
    {
        super(b.getName(), b.getSize(), b.getPrice(), b.getLevel(), b.getMinistry());
        this.teacherArea =     teacherArea;
     
    }

    public int getEstimate(String type)
    {
        int price = super.getPrice();
     
        //System.out.println(this.getName()+":estimate  to hold a "+type +" is "+ price);
        return price;

    }


    public int getTeacherArea()
    {
        return teacherArea;
    }




    public void setTeacherArea(int instructorArea)
    {

        this.teacherArea =instructorArea;
    }



    public String toString()
    {
        return "ID:"+this.getId()+";"+this.getName() +";#Price:"+this.getPrice()+";Teacher Area:"+teacherArea+"";

    }

    public String toFile()
    {
        return ""+this.getId()+";"+this.getName() +";"+this.getPrice()+";"+teacherArea;

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
        int currTeacherArea= getTeacherArea();



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
        
        System.out.println("Hit enter to keep teacherArea at ["+currTeacherArea+"] or enter new teacherArea:");
        String teacherAreaEntry = scan.nextLine();
        int teacherArea;
        if(teacherAreaEntry.equals(""))
            teacherArea = currTeacherArea;
        else
            teacherArea = Integer.parseInt(teacherAreaEntry);



        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);
        setTeacherArea(teacherArea);

    }

}

