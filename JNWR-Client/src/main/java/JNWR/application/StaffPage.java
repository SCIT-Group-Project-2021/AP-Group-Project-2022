package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import Entity.*;
import JNWR.Domain.Client;
import JNWR.application.utilities.*;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList; 


public class StaffPage extends JPanel implements defaultPanelAccessories{

    StaffPage staffPage = this;
    DefaultTableModel headerModel = new DefaultTableModel();
        
    String headers[] = { "ID#", "First Name", "Last Name", "Telephone Number", "Employee Type", "Department Code"};

    Client client;
    Staff employee;

    JTable staffTable;
    ArrayList<DBEntity> list; 
    Staff staff;

    JTextField searchBox;
    JButton searchButton;
    JButton addNewEmpButton;
    JComboBox<String> filter;
    JButton editButton;
    JButton deleteButton;
    String filterOptions[] = { "idNum", "fName", "lName","employeeType","departmentCode"};

    StaffPage(Client client, Staff employee) {

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
        empName.setText(employee.getfName() + " " + employee.getlName().substring(0,1)+".");
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

        JLabel tableHeading = new JLabel("Staff");
        tableHeading.setFont(heading2);

        Image image = new ImageIcon("src/main/resources/JWR-Icons/White/icons8-add-contact-100.png").getImage().getScaledInstance(33,33, Image.SCALE_SMOOTH);
        ImageIcon btnIcon = new ImageIcon(image);
        addNewEmpButton = defaultPanelAccessories.defaultButton();
        addNewEmpButton.setBackground(Color.white);
        addNewEmpButton.setPreferredSize(new Dimension(150,70));
        addNewEmpButton.setIcon(btnIcon);
        addNewEmpButton.setText("Add new staff");
        addNewEmpButton.setForeground(Color.white);
        addNewEmpButton.setBackground(Color.decode("#101E2D"));
        addNewEmpButton.setFont(new Font("Outfit", Font.BOLD, 14));
        addNewEmpButton.setIconTextGap(8);
        addNewEmpButton.setHorizontalAlignment(SwingConstants.LEFT);

        JButton editButton = defaultPanelAccessories.iconButton(23,23,"src/main/resources/JWR-Icons/icons8-pencil-100.png");
        JButton deleteButton = defaultPanelAccessories.iconButton(33,33,"src/main/resources/JWR-Icons/icons8-remove-100.png");

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25,10,25,10);
        searchBar.add(tableHeading,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        searchBar.add(addNewEmpButton,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 2;
        searchBar.add(Box.createGlue(),mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        searchBar.add(editButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        searchBar.add(deleteButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        searchBar.add(filter,mpCons);

        mpCons.weightx = .5;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        searchBar.add(searchBox,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(25,10,25,25);
        searchBar.add(searchButton,mpCons);
        //endregion

        //region Staff Table Bar
        
        headerModel.setColumnIdentifiers(headers);

        staffTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        staffTable.setShowGrid(false);
        staffTable.setRowHeight(50);
        staffTable.setFillsViewportHeight(true);
        staffTable.setBorder(null);

        staffTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {// alternate background color for rows
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


        JTableHeader jTableHeader = staffTable.getTableHeader();
        jTableHeader.setFont(new Font("SansSerif",Font.BOLD,50));
        jTableHeader.setDefaultRenderer(renderer);


        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(headerModel);
        staffTable.setRowSorter(sorter);

        JScrollPane tableScroll = new JScrollPane(staffTable){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
            
        };
        //tableScroll.setBorder(round);
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
    
                    DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
                    model.setRowCount(0);
                    if(searchId.equals("Search...")){
                        updateTable();
                    }
                    else{
                        list = client.getSpecificList("Staff",searchFilter,searchId);
                        for (DBEntity entity : list) {
                            //Inventory inven = (Inventory) list.get(i);
                            staff = (Staff) entity;

                            headerModel.addRow(new Object[] {staff.getIdNum(),staff.getfName(),staff.getlName(),staff.getPhoneNum(),staff.getEmployeeType(),staff.getDepartmentCode()});
                        }
                    }
    
    
                }
            });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(staffTable.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"Please select a record","Cannot edit product", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
                    int selectedRowIndex = staffTable.getSelectedRow();

                    //Gets product code and name from selected entity row
                    String staffId = model.getValueAt(selectedRowIndex,0).toString();
                    String staffName = model.getValueAt(selectedRowIndex,1).toString() + " " + model.getValueAt(selectedRowIndex,2).toString();

                    int result = JOptionPane.showConfirmDialog(new Frame(), "Are you sure you want to delete \""+staffName+ "\"?", "Delete Confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_OPTION){
                        Integer queryResult = client.removeEntity(new Staff(), Integer.parseInt(staffId));
                        switch(queryResult){
                            case 0:
                                JOptionPane.showMessageDialog(new JFrame(),"ID Number: " + staffId + "\n" + staffName + " has been deleted.", "Record removed",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(new JFrame(),"This employee is involved in invoice records so they cannot be deleted.","Cannot delete staff", JOptionPane.ERROR_MESSAGE);
                                /*
                                //If invoices are allowed to be deleted then this code should be uncommented
                                int deleteConfirmation = JOptionPane.showConfirmDialog(new Frame(), "If you delete \""+ staffName+ "\", all invoice records associated with this person must be deleted as well. Are you sure you want to delete them?", "Delete Confirmation", JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                                    if(deleteConfirmation == JOptionPane.YES_OPTION){

                                        //Gets the list of invoices that include the employee that wants to be deleted
                                        ArrayList<DBEntity> invoiceArrayList = client.getExactList("Invoice","staffID", staffId);
                                        for(DBEntity entity : invoiceArrayList){
                                            Invoice inv = (Invoice) entity;
                                            //Gets the list of invoice items that include the invoice number code that wants to be deleted
                                            ArrayList<DBEntity> entityArrayList = client.getExactList("InvoiceItem","invoiceNum", Integer.toString(inv.getInvoiceNum()));

                                            //Removes the invoice items from the invoice item table
                                            for(DBEntity dbEntity : entityArrayList){
                                                InvoiceItem item = (InvoiceItem) dbEntity;
                                                client.removeInvoiceItem(item, item.getInvoiceNum(),item.getProductCode());
                                            }
                                            //After the invoice items are removed, the invoice can be removed from the invoice table
                                            queryResult = client.removeEntity(new Invoice(), inv.getInvoiceNum());
                                        }
                                        //After the invoices are removed, the employee can be removed from the staff table
                                        queryResult = client.removeEntity(new Staff(), Integer.parseInt(staffId));
                                        if(queryResult == 0){
                                            JOptionPane.showMessageDialog(new JFrame(),"Staff Id: " + staffId + "\n" + staffName + " has been deleted.", "Record removed",JOptionPane.INFORMATION_MESSAGE);
                                        }else{
                                            JOptionPane.showMessageDialog(new JFrame(),"Something has gone wrong. Record has not been deleted","Cannot delete staff", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                */
                                break;
                            default:
                                JOptionPane.showMessageDialog(new JFrame(),"Something has gone wrong. Record has not been deleted","Cannot delete employee", JOptionPane.ERROR_MESSAGE);
                                break;
                        }

                        updateTable();
                    }
                }
            }
        });
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

        addNewEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStaffDialog(client, staffPage);
                SwingUtilities.getWindowAncestor(staffPage).setEnabled(false);
            }
        });

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(staffTable.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"Please select a record","Cannot edit product", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
                    int selectedRowIndex = staffTable.getSelectedRow();
                    Staff staff = new Staff(Integer.parseInt(model.getValueAt(selectedRowIndex,0).toString()),model.getValueAt(selectedRowIndex,1).toString(),model.getValueAt(selectedRowIndex,2).toString(), model.getValueAt(selectedRowIndex,3).toString(),model.getValueAt(selectedRowIndex,4).toString(),model.getValueAt(selectedRowIndex,5).toString());
                    new EditStaffDialog(client, staffPage, staff);
                    SwingUtilities.getWindowAncestor(staffPage).setEnabled(false);
                }
            }
        });

        updateTable();

        repaint();
        setSize(getSize());

    }

    public void updateTable() {

        list = client.getList("Staff");
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < list.size(); i++) {

            staff = (Staff) list.get(i);

            Department department = (Department)client.findEntity("Department", "departmentCode", staff.getDepartmentCode());

            headerModel.addRow(new Object[] {staff.getIdNum(),staff.getfName(),staff.getlName(),staff.getPhoneNum(),staff.getEmployeeType(),department.getName()});
        
        }
        
    }

}
