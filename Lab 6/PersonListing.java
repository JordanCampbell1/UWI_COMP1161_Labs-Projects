import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;


public class PersonListing extends JPanel{
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortAge;
    private JButton     cmdSortName;
  
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private ArrayList<Person> plist;
    private PersonListing thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public PersonListing() {
        super(new GridLayout(2,1));
        thisForm = this;
        

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        plist= loadPersons("person.dat");
        String[] columnNames=  {"First Name",
                "Last Name",
                "Age",
                "Will Publish"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(plist);

        table.setPreferredScrollableViewportSize(new Dimension(500, plist.size()*15 +50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
       
        add(scrollPane);

       
        cmdAddPerson  = new JButton("Add Person");
        cmdSortName = new JButton("Sort By First Name");
        cmdSortAge  = new JButton("Sort by Age");
        cmdClose   = new JButton("Close");

        cmdAddPerson.setBackground(Color.GREEN);
        cmdSortName.setBackground(Color.GRAY);
        cmdSortAge.setBackground(Color.MAGENTA);
        cmdClose.setBackground(Color.PINK);

        cmdClose.addActionListener(new CloseButtonListener());
        cmdAddPerson.addActionListener(new AddButtonListener());
        cmdSortAge.addActionListener(new SortAgeButtonListener());
        cmdSortName.addActionListener(new SortNameButtonListener());
        
        
        pnlCommand.add(cmdAddPerson);
        pnlCommand.add(cmdSortName);
        pnlCommand.add(cmdSortAge);
        pnlCommand.add(cmdClose);
       
        add(pnlCommand);
    }

    private void showTable(ArrayList<Person> plist)
    {
        for(Person p: plist)
        {
            addToTable(p);
        }
    }
    private void addToTable(Person p)
    {
        String[] name= p.getName().split(" ");
        String[] item={name[0],name[1],""+ p.getAge(),""+p.getPublish()};
        model.addRow(item);        

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("List of persons who are requesting a vaccine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        PersonListing newContentPane = new PersonListing();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
    }

    public void addPerson(Person p)
    {
        plist.add(p);
        addToTable(p);

    }

    private ArrayList<Person> loadPersons(String pfile)
    {
        Scanner pscan = null;
        ArrayList<Person> plist = new ArrayList<Person>();
        try
        {
            pscan  = new Scanner(new File(pfile));
            while(pscan.hasNext())
            {
                String [] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0]+ " " + nextLine[1];
                int age = Integer.parseInt(nextLine[2]);
                boolean publish = false;
                if (nextLine[3].equals("0"))
                    publish = false;
                else
                    publish =true;
                Person p = new Person(name, age, publish);
                plist.add(p);
            }

            pscan.close();
        }
        catch(IOException e)
        {}
        return plist;
    }



    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }

    }

    private class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            PersonEntry random = new PersonEntry(PersonListing.this);
        }
    }

    public class AgeSort implements Comparator<Person>
    {
        public int compare(Person p1, Person p2)
        {
            return p1.getAge() - p2.getAge();
        }
    }

    private class SortAgeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);

            Collections.sort(plist, new AgeSort());

            showTable(plist);
        }
    }

    public class NameSort implements Comparator<Person>
    {
        public int compare(Person p1, Person p2)
        {
            return p1.getName().compareTo(p2.getName());
        }
    }

    private class SortNameButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);

            Collections.sort(plist, new NameSort());

            showTable(plist);
        }
    }

}