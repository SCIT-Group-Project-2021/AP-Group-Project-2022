package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Entity.Customer;
import Entity.DBEntity;
import JNWR.Domain.Client;
import JNWR.application.utilities.*;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList; 


public class custPage extends JPanel implements defaultPanelAccessories{

    DefaultTableModel headerModel = new DefaultTableModel();
        
    String headers[] = { "ID#", "First Name", "Last Name", "DOB", "Telephone Number", "Email", "Date of Membership", "Expiry Date", "Options"};

    custPage() {

        RoundedBorder round = new RoundedBorder(25);

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        //region Main Panel
        setBackground(Color.RED);
        setLayout(new GridBagLayout());
        //endregion

        //region Top Bar
        JPanel topBar = createJPanel(0,80,120);
        //topBar.setBackground(Color.YELLOW);
        topBar.setLayout(new GridBagLayout());

        //region date/time bar
        JPanel dateTimePanel = createJPanel(50,450,80);
        dateTimePanel.setBackground(Color.WHITE);
        dateTimePanel.setLayout(new GridBagLayout());


        Image calendarImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-desk-calendar-100.png").getImage().getScaledInstance(55,55, Image.SCALE_SMOOTH);
        ImageIcon calendarIcon = new ImageIcon(calendarImage);
        JLabel calendar = new JLabel(calendarIcon);
        JLabel date = new JLabel(defaultPanelAccessories.getTodayDate());
        date.setFont(heading3);

        Image clockImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-clock-100-2.png").getImage().getScaledInstance(55,55, Image.SCALE_SMOOTH);
        ImageIcon clockIcon = new ImageIcon(clockImage);
        JLabel clock = new JLabel(clockIcon);
        JLabel time = new JLabel(defaultPanelAccessories.getCurrentTime());
        time.setFont(heading3);
        //endregion

        //region Log Out Label & Button
        JLabel empName = new JLabel();
        empName.setText(defaultPanelAccessories.getCurrentUser());
        empName.setFont(heading3);
        JButton logOut = defaultPanelAccessories.iconButton(30,30,"src/main/resources/JWR-Icons/Black/icons8-logout-rounded-down-100.png");
        //endregion

        //endregion


        //region top Bar
        JPanel searchBar = createJPanel(0,80,120);
        searchBar.setBackground(Color.BLACK);
        searchBar.setLayout(new GridBagLayout());
        //endregion

        //region Customer Table Bar
        
        headerModel.setColumnIdentifiers(headers);

        JTable customerTable = new JTable(headerModel);
        
        JScrollPane tableScroll = new JScrollPane(customerTable){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
        };
        tableScroll.setBackground(Color.GREEN);
        //endregion

        //region Frame.Add

        mpCons.ipadx = 0;
        mpCons.ipady = 0;


        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        add(topBar,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        add(searchBar,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 2;
        mpCons.gridx = 0;
        add(tableScroll,mpCons);

         //region TopBar.Add

         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx = 0;
         mpCons.insets = new Insets(10, 30, 10, 10);
         topBar.add(dateTimePanel,mpCons);
 
         mpCons.weightx = 1;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx++;
         topBar.add(Box.createGlue(),mpCons);
 
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx++;
         topBar.add(empName,mpCons);
 
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx++;
         topBar.add(logOut,mpCons);        
 
         //region DateTime.Add
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx = 0;
         mpCons.insets = new Insets(25, 25, 25, 5);
         dateTimePanel.add(calendar, mpCons);
 
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx++;
         mpCons.insets = new Insets(25, 5, 25, 5);
         dateTimePanel.add(date, mpCons);
 
         /*mpCons.weightx = 0;
         mpCons.weighty = 1;
         mpCons.gridy = 0;
         mpCons.gridx++;
         //TODO: Fix separator
         JSeparator line = new JSeparator(SwingConstants.VERTICAL);
         line.setPreferredSize(new Dimension(25,25));
         dateTimePanel.add(line);*/
 
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx++;
         dateTimePanel.add(clock, mpCons);
 
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx++;
         mpCons.insets = new Insets(25, 5, 25, 25);
         dateTimePanel.add(time, mpCons);
 
         //endregion
 
         //endregion
 

        //endregion

        updateTable();

        repaint();
        setSize(getSize());

    }

    public void updateTable() {

        ArrayList<DBEntity> list = new Client().getList("Customer");

        for (int i = 0; i < list.size(); i++) {

            Customer cust = (Customer) list.get(i);

            headerModel.addRow(new Object[] {cust.getCustomerId(),cust.getfName(),cust.getlName(),cust.getDob(),cust.getTelephoneNum(),cust.getEmail(),cust.getDateOfMembership(),cust.getDateOfMembershipExpiry(),"exit"});
        
        }
        
    }

    public static JPanel createJPanel(int rnd) {
    
        PanelRound newJPanel = new PanelRound();
        newJPanel.setRound(rnd);
        newJPanel.setSize(250, 250);
        newJPanel.setVisible(true); 

        return newJPanel;
    
    }

    public static JPanel createJPanel(int rnd,int fixedWidth, int fixedHeight) {
    
        PanelRound newJPanel = new PanelRound() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(fixedWidth, fixedHeight);
            }
        };
        newJPanel.setRound(rnd);
        newJPanel.setSize(250, 250);
        newJPanel.setVisible(true);
    
        return newJPanel;
    
    }

    public static JButton defaultButton() {
    
        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(175, 25);
        newJButton.setVisible(true);
        newJButton.setUI(new StyledButtonUI());
    
        return newJButton;
    
    }

    public static JButton defaultButton(int w,int h) {
    
        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        newJButton.setUI(new StyledButtonUI());
    
        return newJButton;
    
    }
    
    public static JTextField defaultTextField() {
    
        JTextField newJTextField = new JTextField();
        newJTextField.setSize(175, 25);
        newJTextField.setVisible(true);
    
        return newJTextField;
    
    }

    public static JTextField defaultTextField(int w,int h) {
    
        JTextField newJTextField = new JTextField();
        newJTextField.setSize(w, h);
        newJTextField.setVisible(true);
    
        return newJTextField;
    
    }

}