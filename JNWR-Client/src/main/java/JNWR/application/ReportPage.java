package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import Entity.DBEntity;
import Entity.Invoice;
import Entity.Staff;
import JNWR.Domain.Client;
import JNWR.application.utilities.*;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList; 


public class ReportPage extends JPanel implements defaultPanelAccessories{

    DefaultTableModel headerModel = new DefaultTableModel();
        
    String invoiceHeaders[] = { "Invoice#", "Billing Date", "Customer ID", "Staff ID"};
    String invoiceItemHeaders[] = { "ID#", "First Name", "Last Name", "Telephone Number", "Employee Type", "Department Code"};

    Client client;
    Staff employee;

    ArrayList<DBEntity> list; 
    Invoice invoice;

    ReportPage invoicePage = this;

    JTextField searchBox;
    JButton searchButton;
    JComboBox<String> filter;
    String filterOptions[] = { "invoiceNum", "billingDate", "customerID","staffID"};

    ReportPage(Client client, Staff employee) {

        this.client = client;
        this.employee = employee;

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
        empName.setText(employee.getfName() + employee.getlName());
        empName.setFont(heading3);
        JButton logOut = defaultPanelAccessories.iconButton(30,30,"src/main/resources/JWR-Icons/Black/icons8-logout-rounded-down-100.png");
        //endregion

        //endregion

        //region Search Bar
        PanelRound searchBar = (PanelRound)defaultPanelAccessories.createJPanel(0,80,60);
        searchBar.setLayout(new GridBagLayout());
        searchBar.setBackground(Color.decode("#E5EBF4"));
        searchBar.setRoundTopLeft(25);
        searchBar.setRoundTopRight(25);
 
        searchBox = new CustomRoundTextField();
        searchBox.setText("Search...");
 
        filter = new CustomRoundComboBox();
        filter.setModel(new javax.swing.DefaultComboBoxModel(filterOptions));
 
         Image searchImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-search-100.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
         ImageIcon searchIcon = new ImageIcon(searchImage);
         searchButton = defaultPanelAccessories.defaultButton();
         searchButton.setIcon(searchIcon);
         searchButton.setPreferredSize(new Dimension(25,200));
 
         mpCons.weightx = 1;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx = 0;
         searchBar.add(Box.createGlue(),mpCons);
         mpCons.insets = new Insets(25,25,25,25);
 
         mpCons.weightx = .5;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx = 2;
         mpCons.insets = new Insets(25,10,25,10);
         searchBar.add(searchBox,mpCons);
 
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx = 1;
         searchBar.add(filter,mpCons);
 
         mpCons.weightx = 0;
         mpCons.weighty = 0;
         mpCons.gridy = 0;
         mpCons.gridx = 3;
         mpCons.insets = new Insets(25,10,25,25);
         searchBar.add(searchButton,mpCons);
         //endregion
 
        //region Table Bar
        
        headerModel.setColumnIdentifiers(invoiceHeaders);

        JTable invoiceTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        invoiceTable.setShowGrid(false);
        invoiceTable.setRowHeight(50);
        invoiceTable.setFillsViewportHeight(true);
        invoiceTable.setBorder(null);

        invoiceTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {// alternate background color for rows
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


        JTableHeader jTableHeader = invoiceTable.getTableHeader();
        jTableHeader.setFont(new Font("SansSerif",Font.BOLD,50));
        jTableHeader.setDefaultRenderer(renderer);


        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(headerModel);
        invoiceTable.setRowSorter(sorter);

        JScrollPane tableScroll = new JScrollPane(invoiceTable){
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
        mpCons.insets = new Insets(0, 60, 60, 60);
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

        //region Buttons
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

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String searchId;
                    String searchFilter = String.valueOf(filter.getSelectedItem());
                    searchId = searchBox.getText();
    
                    DefaultTableModel model = (DefaultTableModel) invoiceTable.getModel();
                    model.setRowCount(0);
                    if(searchId.equals("Search...")){
                        updateTable();
                    }
                    else{
                        list = client.getSpecificList("Invoice",searchFilter,searchId);
                        for (DBEntity entity : list) {
                            invoice = (Invoice) entity;

                            Integer customerID = null;

                            try {
   
                                customerID = invoice.getCustomerID();
                
                            } catch (NullPointerException ex) {
                                customerID = null;
                            }

                            headerModel.addRow(new Object[] {invoice.getInvoiceNum(),invoice.getBillingDate(),customerID,invoice.getStaffID()});
        
                            }
                    }
    
    
                }
            });

            invoiceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {

                    int selectedRowIndex = invoiceTable.getSelectedRow();
                    int invoiceNum = (int)invoiceTable.getValueAt(selectedRowIndex, 0);
                    new InvoiceDialog(client,invoiceNum,invoicePage);
                    SwingUtilities.getWindowAncestor(invoicePage).setEnabled(false);;
                }
            });

            logOut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        //endregion


        updateTable();

        repaint();
        setSize(getSize());

    }

    public void updateTable() {

        list = client.getList("Invoice");

        for (int i = 0; i < list.size(); i++) {

            invoice = (Invoice) list.get(i);

            Integer customerID = null;

            try {
   
                customerID = invoice.getCustomerID();

            } catch (NullPointerException e) {
                customerID = null;
            }

            
            
            headerModel.addRow(new Object[] {invoice.getInvoiceNum(),invoice.getBillingDate(),customerID,invoice.getStaffID()});
        
        }
        
    }

}
