package JNWR.application;

import Entity.Customer;
import Entity.DBEntity;
import Entity.Inventory;
import Entity.InvoiceItem;
import JNWR.Domain.Client;
import JNWR.application.utilities.defaultPanelAccessories;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class addCustomerDialog extends JFrame implements defaultPanelAccessories {
    int qty = 1;
    final int frameWidth = 550;
    final int frameHeight = 585;

    JTextField searchBox;
    JButton cancelButton;
    JButton addCustomerButton;
    JButton searchButton;
    JButton clearCustomerButton;

    JComboBox<String> filter;
    public static searchDialog Instance;

    ArrayList<DBEntity> list;
    DefaultTableModel headerModel = new DefaultTableModel();
    String headers[] = {"ID#", "First Name", "Last Name", "Telephone Number", "Expiry Date"};
    String filterOptions[] = { "customerID", "fName", "lName","telephoneNum"};
    Customer cust;
    public addCustomerDialog(posPage posPage) {
        //region Base Frame Setup
        this.setUndecorated(true);
        this.setShape(new RoundRectangle2D.Double(0,0,frameWidth,frameHeight,30,30));
        RoundedBorder round = new RoundedBorder(30);
        this.getRootPane().setBorder(round);
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(frameWidth,frameHeight));

        setLayout(new GridBagLayout());
        setVisible(true);
        //endregion

        // #region Enables Undecorated Frame drag movement
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        // #endregion

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion


        JPanel mainSection = defaultPanelAccessories.createJPanel(0,350,80);
        mainSection.setLayout(new GridBagLayout());

        searchBox = new JTextField("Search...");

        filter = new JComboBox<>(filterOptions);

        Image searchImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-search-100.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        ImageIcon searchIcon = new ImageIcon(searchImage);
        searchButton = defaultPanelAccessories.defaultButton();
        searchButton.setIcon(searchIcon);


        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(heading3);
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);


        JPanel searchBar = defaultPanelAccessories.createJPanel(0,350,80);
        searchBar.setLayout(new GridBagLayout());


        //region Customer Table Bar

        headerModel.setColumnIdentifiers(headers);
        JTable customerTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

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
        //endregion


        JPanel exitBtnsPanel = defaultPanelAccessories.createJPanel(0,350,80);
        mainSection.setLayout(new GridBagLayout());


        cancelButton = defaultPanelAccessories.defaultButton();
        cancelButton.setText("Cancel");

        addCustomerButton = defaultPanelAccessories.defaultButton();
        addCustomerButton.setText("Add Customer");

        clearCustomerButton = defaultPanelAccessories.defaultButton();
        clearCustomerButton.setText("Clear Customer");

        //region Frame.Add

        mpCons.ipadx = 0;
        mpCons.ipady = 0;

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25,25,10,25);
        add(mainSection,mpCons);

        mpCons.gridwidth = 3;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,0);
        mainSection.add(searchBar,mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25,0,5,0);
        searchBar.add(searchBox,mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        mpCons.insets = new Insets(25,0,5,0);
        searchBar.add(filter,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 2;
        searchBar.add(searchButton,mpCons);

        mpCons.gridwidth = 3;
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 2;
        mpCons.gridx = 0;
        mainSection.add(tableScroll,mpCons);

        mpCons.gridwidth = 3;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 3;
        mpCons.gridx = 0;
        mainSection.add(exitBtnsPanel,mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        exitBtnsPanel.add(cancelButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        exitBtnsPanel.add(clearCustomerButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 2;
        exitBtnsPanel.add(addCustomerButton,mpCons);

        //endregion

        refresh();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId;
                String searchFilter = String.valueOf(filter.getSelectedItem());
                searchId = searchBox.getText();


                DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
                model.setRowCount(0);
                if(searchId.equals("")){
                    updateTable();
                }
                else{
                    list = new Client().getSpecificList("Customer",searchFilter,searchId);
                    for (DBEntity entity : list) {
                        //Inventory inven = (Inventory) list.get(i);
                        cust = (Customer) entity;

                        headerModel.addRow(new Object[] {cust.getCustomerId(),cust.getfName(),cust.getlName(),cust.getTelephoneNum(),cust.getDateOfMembershipExpiry()});
                    }
                }


            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
                    int selectedRowIndex = customerTable.getSelectedRow();

                    posPage.updateCustomer((Customer) list.get(selectedRowIndex));
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    logger.error("The invoice item was not selected.");
                }
                finally{
                    dispose();
                }


            }
        });

        clearCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posPage.updateCustomer(null);
                dispose();
            }
        });

        updateTable();
    }

    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }

    public void updateTable() {

        list = new Client().getList("Customer");

        for (int i = 0; i < list.size(); i++) {

            Customer cust = (Customer) list.get(i);

            headerModel.addRow(new Object[] {cust.getCustomerId(),cust.getfName(),cust.getlName(),cust.getTelephoneNum(),cust.getDateOfMembershipExpiry()});

        }

    }

}