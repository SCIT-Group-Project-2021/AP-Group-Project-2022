package JNWR.application;

import Entity.Customer;
import Entity.DBEntity;
import JNWR.Domain.Client;
import JNWR.application.utilities.CustomRoundComboBox;
import JNWR.application.utilities.defaultPanelAccessories;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class AddCustomerDialog extends JFrame implements defaultPanelAccessories {
    int qty = 1;
    final int frameWidth = 550;
    final int frameHeight = 585;

    JTextField searchBox;
    JButton cancelButton;
    JButton addCustomerButton;
    JButton searchButton;
    JButton clearCustomerButton;

    CustomRoundComboBox<String> filter;
    public static SearchDialog Instance;

    ArrayList<DBEntity> list;
    DefaultTableModel headerModel = new DefaultTableModel();
    String headers[] = {"ID#", "First Name", "Last Name", "Telephone Number", "Expiry Date"};
    String filterOptions[] = { "customerID", "fName", "lName","telephoneNum"};
    Customer cust;

    Client client;

    public AddCustomerDialog(PosPage posPage, Client client) {

        this.client = client;

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

        filter = new CustomRoundComboBox();
        filter.setModel(new javax.swing.DefaultComboBoxModel(filterOptions));

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
        customerTable.setFillsViewportHeight(true);
        customerTable.setBorder(null);

        customerTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {// alternate background color for rows
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected)
                    c.setBackground(row % 2 == 0 ? Color.decode("#E5EBF4") : Color.decode("#ECF3FA"));
                return c;
            };
        });

        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(null);
        renderer.setBackground(Color.decode("#ECF3FA"));
        renderer.setHorizontalAlignment(JLabel.CENTER);
        //renderer.setFont(new Font("SansSerif",Font.BOLD,50));
        renderer.setForeground(Color.decode("#707070"));
        renderer.setPreferredSize(new Dimension(100,50));


        JTableHeader jTableHeader = customerTable.getTableHeader();
        jTableHeader.setFont(new Font("SansSerif",Font.BOLD,50));
        jTableHeader.setDefaultRenderer(renderer);


        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(headerModel);
        customerTable.setRowSorter(sorter);

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
                if(searchId.equals("Search...")){
                    updateTable();
                }
                else{
                    list = client.getSpecificList("Customer",searchFilter,searchId);
                    for (DBEntity entity : list) {
                        //Inventory inven = (Inventory) list.get(i);
                        cust = (Customer) entity;

                        headerModel.addRow(new Object[] {cust.getCustomerId(),cust.getfName(),cust.getlName(),cust.getTelephoneNum(),cust.getDateOfMembershipExpiry()});
                    }
                }


            }
        });

        searchBox.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(searchBox.getText().equals("Search...")){
                    searchBox.setText("");
                }
              
            }
      
            public void focusLost(FocusEvent e) {
                if(searchBox.getText().equals("")){
                    searchBox.setText("Search...");
                }
            }});

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(posPage).setEnabled(true);
                dispose();
            }
        });

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int selectedRowIndex = customerTable.getSelectedRow();

                    posPage.updateCustomer((Customer) list.get(selectedRowIndex));
                }
                catch(IndexOutOfBoundsException ex){
                    logger.error("The invoice item was not selected.");
                    JOptionPane.showMessageDialog(new JFrame(),"No Customer Selected","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    SwingUtilities.getWindowAncestor(posPage).setEnabled(true);
                    dispose();
                }


            }
        });

        clearCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posPage.updateCustomer(null);
                SwingUtilities.getWindowAncestor(posPage).setEnabled(true);
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

        list = client.getList("Customer");

        for (int i = 0; i < list.size(); i++) {

            Customer cust = (Customer) list.get(i);

            headerModel.addRow(new Object[] {cust.getCustomerId(),cust.getfName(),cust.getlName(),cust.getTelephoneNum(),cust.getDateOfMembershipExpiry()});

        }

    }

}