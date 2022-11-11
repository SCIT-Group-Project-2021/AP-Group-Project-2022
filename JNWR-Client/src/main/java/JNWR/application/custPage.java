package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        //region Main Panel
        setLayout(new GridBagLayout());
        //endregion

        //region Top Bar
        JPanel topBar = defaultPanelAccessories.createJPanel(0,80,120);
        topBar.setLayout(new GridBagLayout());

        //region date/time bar
        JPanel dateTimePanel = defaultPanelAccessories.createJPanel(50,450,80);
        dateTimePanel.setBackground(Color.WHITE);
        dateTimePanel.setLayout(new GridBagLayout());


        Image calendarImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-desk-calendar-100.png").getImage().getScaledInstance(55,55, Image.SCALE_SMOOTH);
        ImageIcon calendarIcon = new ImageIcon(calendarImage);
        JLabel calendar = new JLabel(calendarIcon);
        JLabel date = new JLabel(defaultPanelAccessories.getTodayDate());
        date.setFont(heading3);

        Image clockImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-clock-100-2.png").getImage().getScaledInstance(45,45, Image.SCALE_SMOOTH);
        ImageIcon clockIcon = new ImageIcon(clockImage);
        JLabel clock = new JLabel(clockIcon);
        JLabel time = new JLabel(defaultPanelAccessories.getCurrentTime());
        time.setFont(heading3);
        Timer timeTimer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.setText(defaultPanelAccessories.getCurrentTime());
               
            }
        });
        timeTimer.start();
        //endregion

        //region Log Out Label & Button
        JLabel empName = new JLabel();
        empName.setText(defaultPanelAccessories.getCurrentUser());
        empName.setFont(heading3);
        JButton logOut = defaultPanelAccessories.iconButton(30,30,"src/main/resources/JWR-Icons/Black/icons8-logout-rounded-down-100.png");
        //endregion

        //endregion

        //region Customer Bar
        PanelRound searchBar = (PanelRound)defaultPanelAccessories.createJPanel(0,80,60);
        searchBar.setLayout(new GridBagLayout());
        searchBar.setBackground(Color.GRAY);
        searchBar.setRoundTopLeft(25);
        searchBar.setRoundTopRight(25);
        //endregion

        //region Customer Table Bar
        
        headerModel.setColumnIdentifiers(headers);

        JTable customerTable = new JTable(headerModel);
        
        customerTable.setShowGrid(false);
        customerTable.setRowHeight(50);

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);

        JScrollPane tableScroll = new JScrollPane(customerTable){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
            
        };
        //tableScroll.setBorder(round);
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
        mpCons.insets = new Insets(0, 60, 0, 60);
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
        mpCons.insets = new Insets(25, 20, 25, 5);
        dateTimePanel.add(calendar, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(25, 5, 25, 5);
        dateTimePanel.add(date, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx++;
        JLabel separator = new JLabel();
        separator.setFont(new Font("Roboto", Font.BOLD, 40));
        //Color c = new Color(r,g,b,a);
        separator.setForeground(Color.decode("#dedee0"));
        separator.setText("  |  ");
        dateTimePanel.add(separator);

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

}
