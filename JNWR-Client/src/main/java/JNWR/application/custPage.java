package JNWR.application;

import java.awt.Dimension;
import java.awt.Color;
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

        //region top Bar
        JPanel topBar = createJPanel(0,80,120);
        topBar.setBackground(Color.YELLOW);
        topBar.setLayout(new GridBagLayout());
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
