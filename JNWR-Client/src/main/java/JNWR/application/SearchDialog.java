package JNWR.application;

import Entity.DBEntity;
import Entity.Inventory;
import Entity.InvoiceItem;
import JNWR.application.utilities.CustomRoundComboBox;
import JNWR.application.utilities.defaultPanelAccessories;
import JNWR.Domain.Client;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class SearchDialog extends JFrame implements defaultPanelAccessories {
    int qty = 1;
    final int frameWidth = 550;
    final int frameHeight = 585;

    ArrayList<DBEntity> list;
    JLabel addIconLabel;
    JTextField qtyTextField;
    JTextField searchBox;
    JButton cancelButton;
    JButton addItemButton;
    JButton searchButton;

    JComboBox<String> filter;
    public static SearchDialog Instance;

    Client client;


    DefaultTableModel headerModel = new DefaultTableModel();
    String headers[] = { "Product Code", "Name", "Stock","Unit Price"};
    String filterOptions[] = { "productCode", "Name"};

    public SearchDialog(PosPage posPage, Client client) {

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
        JTable searchTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };

        searchTable.setShowGrid(false);
        searchTable.setRowHeight(50);
        searchTable.setFillsViewportHeight(true);
        searchTable.setBorder(null);

        searchTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {// alternate background color for rows
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


        JTableHeader jTableHeader = searchTable.getTableHeader();
        jTableHeader.setFont(new Font("SansSerif",Font.BOLD,50));
        jTableHeader.setDefaultRenderer(renderer);


        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(headerModel);
        searchTable.setRowSorter(sorter);

        JScrollPane tableScroll = new JScrollPane(searchTable){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
        };
        //endregion

        qtyTextField = new JTextField();
        qtyTextField.setEditable(false);
        qtyTextField.setText(Integer.toString(qty));
        qtyTextField.setFont(medText);

        JButton addButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/Black/icons8-add-100-2.png");

        JButton removeButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/Black/icons8-delete-100.png");


        JPanel exitBtnsPanel = defaultPanelAccessories.createJPanel(0,350,80);
        mainSection.setLayout(new GridBagLayout());


        cancelButton = defaultPanelAccessories.defaultButton();
        cancelButton.setText("Cancel");

        addItemButton = defaultPanelAccessories.defaultButton();
        addItemButton.setText("Add Item");

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

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 3;
        mpCons.gridx = 0;
        mainSection.add(quantityLabel,mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 4;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,0);
        mainSection.add(removeButton,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 4;
        mpCons.gridx = 1;
        mpCons.insets = new Insets(0,10,5,10);
        qtyTextField.setHorizontalAlignment(SwingConstants.CENTER);
        mainSection.add(qtyTextField,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 4;
        mpCons.gridx = 2;
        mpCons.insets = new Insets(0,0,5,0);
        mainSection.add(addButton,mpCons);

        mpCons.gridwidth = 3;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 5;
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
        exitBtnsPanel.add(addItemButton,mpCons);

        //endregion

        refresh();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId;
                String searchFilter = String.valueOf(filter.getSelectedItem());
                searchId = searchBox.getText();

                list = client.getSpecificList("Inventory",searchFilter,searchId);

                DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
                model.setRowCount(0);
                if (searchId.equals("Search...")) {
                    updateTable();
                } else {
                    for (DBEntity entity : list) {

                        //Inventory inven = (Inventory) list.get(i);
                        Inventory inven = (Inventory) entity;
    
                        headerModel.addRow(new Object[] {inven.getProductCode(),inven.getName(),inven.getStock(),inven.getUnitPrice()});
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
            }}
        );

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(qty > 1){
                    qty--;
                    qtyTextField.setText(Integer.toString(qty));
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"Quantity cannot be equal or less than 0","ERROR", JOptionPane.ERROR_MESSAGE);
                    logger.warn("Quantity cannot be equal or less than 0");
                    
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    qty++;
                    qtyTextField.setText(Integer.toString(qty));
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(posPage).setEnabled(true);
                dispose();
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
                    //Gets the item selected from the table
                    int selectedRowIndex = searchTable.getSelectedRow();

                    //Gets the list of products that are already on the invoice
                    ArrayList<InvoiceItem> invoiceItemArrayList = posPage.getInvoiceItemArrayList();
                    qty = Integer.parseInt(qtyTextField.getText());

                    //Checks if the qty entered is less than or equal to the stock available
                    if(qty <= Integer.parseInt((model.getValueAt(selectedRowIndex,2).toString()))){
                        InvoiceItem newItem = new InvoiceItem(model.getValueAt(selectedRowIndex,0).toString(), qty);

                        //Checks if the item selected is already a part of the invoice
                        for(int i = 0; i < invoiceItemArrayList.size();i++){
                            if(invoiceItemArrayList.get(i).getProductCode().equals(newItem.getProductCode())){
                                //Adds the requested quantity to what is already in the invoice and checks if that is less than or equal to the stock provided
                                if(invoiceItemArrayList.get(i).getItemQuantity() + newItem.getItemQuantity() < Integer.parseInt((model.getValueAt(selectedRowIndex,2).toString()))){
                                    invoiceItemArrayList.get(i).setItemQuantity(invoiceItemArrayList.get(i).getItemQuantity() + newItem.getItemQuantity());
                                    posPage.changeQuantityInSearch(invoiceItemArrayList.get(i));
                                    return;
                                }
                                else{
                                    new JOptionPane();
                                    JOptionPane.showMessageDialog(new JFrame(),"Stock is less than requested quantity","ERROR", JOptionPane.ERROR_MESSAGE);
                                    logger.warn("Stock is less than requested quantity");
                                    return;
                                }
                            }
                        }
                        invoiceItemArrayList.add(new InvoiceItem(model.getValueAt(selectedRowIndex,0).toString(), qty));
                        posPage.setInvoiceItemArrayList(invoiceItemArrayList);
                        posPage.updateInvoice();
                        //dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"Stock is less than requested quantity","ERROR", JOptionPane.ERROR_MESSAGE);
                        logger.warn("Stock is less than requested quantity");
                    }
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    logger.error("The invoice item was not selected.");
                }
                finally{
                    SwingUtilities.getWindowAncestor(posPage).setEnabled(true);
                    dispose();
                }


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

        list = client.getList("Inventory");

        for (int i = 0; i < list.size(); i++) {

            Inventory inven = (Inventory) list.get(i);

            headerModel.addRow(new Object[] {inven.getProductCode(),inven.getName(),inven.getStock(),inven.getUnitPrice()});

        }

    }
}
