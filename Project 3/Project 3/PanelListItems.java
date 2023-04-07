import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class PanelListItems extends JPanel{

    private JTable mainTable = new JTable();

    private DefaultTableModel model = new DefaultTableModel();

    private JTable table;

    private JScrollPane scrollPane;
    

    public PanelListItems()
    {
        super(new GridLayout(2,1)); //establishes a new layout for the GUI to use


        String[] columnNames=  {"First Name",
                "Last Name",
                "Age",
                "Will Publish"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(plist); //list of data items

        table.setPreferredScrollableViewportSize(new Dimension(500, plist.size()*15 +50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        //the above deals with list of data items in a table

        

    }
}
