import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonEntry extends JFrame
{
    private JTextField  txtName;       //name
    private JTextField  txtAge;        //age
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JButton     cmdClearAll;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;

    private JCheckBox willpublisBox;

    private PersonListing mainPanel;

    private PersonEntry something;
  
    public PersonEntry(PersonListing mainPanel)
    {
        something = this;

        this.mainPanel = mainPanel;

        setTitle("Entering new person");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Age:"));
        txtAge = new JTextField(3);
        pnlDisplay.add(txtAge);

        pnlDisplay.add(new JLabel("Will Publish?"));
        willpublisBox = new JCheckBox();
        pnlDisplay.add(willpublisBox);

        pnlDisplay.setLayout(new GridLayout(3,4));
       
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        cmdSave.setBackground(Color.ORANGE);
        cmdClose.setBackground(Color.RED);

        cmdClose.addActionListener(new Close_PersonEntry_Listener());
        cmdSave.addActionListener(new Save_PersonEntry_Listener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    private class Close_PersonEntry_Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false); // something.setVisible(false); does the same thing
        }
    }

    private class Save_PersonEntry_Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try{
                String personName = "";

                if(!(txtName.getText().equals("")))
                {
                    personName = txtName.getText();
                }

                int age = Integer.parseInt(txtAge.getText()); //will accept 0 and negative numbers with this implementation

                boolean willPublish = willpublisBox.isSelected();

                Person p = new Person(personName, age, willPublish);

                mainPanel.addPerson(p);

                setVisible(false);
            }
            catch(NumberFormatException e1)
            {}
            catch(Exception e2)
            {}
        }
    }




}