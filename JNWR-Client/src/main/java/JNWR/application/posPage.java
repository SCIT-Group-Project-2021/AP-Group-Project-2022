package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Entity.Customer;
import Entity.Inventory;
import Entity.InvoiceItem;
import JNWR.Domain.Client;
import JNWR.application.utilities.defaultPanelAccessories;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class posPage extends JPanel implements defaultPanelAccessories{

    ButtonGroup paymentBtns = new ButtonGroup();
    posPage posPage = this;
    JLabel selectedItemName;
    JLabel unitPriceLabel;
    JLabel shortDescrip;
    JLabel qtyLabel;
    JTable itemTable;
    JButton addCustomer;
    JLabel subtotalAmt;
    JLabel discountLabel;

    JLabel totalAmt;
    float discountPercent = 0;
    Customer invoiceCustomer;

    JLabel discountAmtLabel;
    float subtotal = 0;
    JLabel taxAmt;

    Client client;

    public Customer getInvoiceCustomer() {
        return invoiceCustomer;
    }

    int qty = 0;
    DefaultTableModel headerModel = new DefaultTableModel();
    ArrayList<InvoiceItem> invoiceItemArrayList = new ArrayList<>();
    String headers[] = { "PRODUCT NAME", "UNIT PRICE", "QTY","TOTAL"};
    ArrayList<Inventory> checkOutItems= new ArrayList<>();
    float total = 0;
    final float tax = (float)0.16;

    public ArrayList<InvoiceItem> getInvoiceItemArrayList() {
        return invoiceItemArrayList;
    }

    public void setInvoiceItemArrayList(ArrayList<InvoiceItem> invoiceItemArrayList) {
        this.invoiceItemArrayList = invoiceItemArrayList;
    }
    JLabel invoiceNum;

    public posPage(Client client) {

        this.client = client;

        RoundedBorder round = new RoundedBorder(25);

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

        //region Jan's Wholesale Text
        JLabel companyLabel = new JLabel("Jan's Wholesale and Retail");
        companyLabel.setFont(heading1);
        //endregion

        //region Log Out Label & Button
        JLabel empName = new JLabel();
        empName.setText(defaultPanelAccessories.getCurrentUser());
        empName.setFont(heading3);
        JButton logOut = defaultPanelAccessories.iconButton(30,30,"src/main/resources/JWR-Icons/Black/icons8-logout-rounded-down-100.png");
        //endregion

        //endregion

        JPanel mainSection = new JPanel();
        mainSection.setLayout(new GridBagLayout());

        //region left section
        JPanel leftSection = defaultPanelAccessories.createJPanel(0,500,80);
        leftSection.setLayout(new GridBagLayout());

        headerModel.setColumnIdentifiers(headers);
        itemTable = new JTable(headerModel);
        JScrollPane userTableScroll = new JScrollPane(itemTable);
        userTableScroll.setBorder(round);

        JPanel upperPane  = defaultPanelAccessories.createJPanel(0,300,250);
        upperPane.setLayout(new GridBagLayout());

        JPanel buttonBox = defaultPanelAccessories.createJPanel(0,400,250);
        buttonBox.setLayout(new GridBagLayout());
    

        Image searchImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-search-100.png").getImage().getScaledInstance(33,33, Image.SCALE_SMOOTH);
        ImageIcon searchIcon = new ImageIcon(searchImage);
        JButton searchByCode = defaultPanelAccessories.defaultButton();
        searchByCode.setBackground(Color.white);
        searchByCode.setPreferredSize(new Dimension(150,70));
        searchByCode.setIcon(searchIcon);
        searchByCode.setText("Search in Catalog");
        searchByCode.setFont(new Font("Outfit", Font.BOLD, 14));
        searchByCode.setIconTextGap(8);
        searchByCode.setHorizontalAlignment(SwingConstants.LEFT);

        Image barcodeImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-barcode-100.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon barcodeIcon = new ImageIcon(barcodeImage);
        JButton barcodeScan = defaultPanelAccessories.defaultButton();
        barcodeScan.setBackground(Color.white);
        barcodeScan.setPreferredSize(new Dimension(150,70));
        barcodeScan.setIcon(barcodeIcon);
        barcodeScan.setText("Scan Barcode");
        barcodeScan.setFont(new Font("Outfit", Font.BOLD, 14));
        barcodeScan.setIconTextGap(8);
        barcodeScan.setHorizontalAlignment(SwingConstants.LEFT);

        Image addCustomerImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-add-contact-100.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon addCustomerIcon = new ImageIcon(addCustomerImage);
        addCustomer = defaultPanelAccessories.defaultButton();
        addCustomer.setBackground(Color.white);
        addCustomer.setPreferredSize(new Dimension(150,70));
        addCustomer.setIcon(addCustomerIcon);
        addCustomer.setText("Add Customer");
        addCustomer.setFont(new Font("Outfit", Font.BOLD, 14));
        addCustomer.setIconTextGap(8);
        addCustomer.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel itemInfo = defaultPanelAccessories.createJPanel(25,100,280);
        itemInfo.setLayout(new GridBagLayout());
        itemInfo.setBackground(Color.decode("#4C71B9"));

        PanelRound namePricePanel = (PanelRound)defaultPanelAccessories.createJPanel(0,100,100);
        namePricePanel.setLayout(new GridBagLayout());
        namePricePanel.setBackground(Color.white);
        namePricePanel.setRoundTopRight(25);


        PanelRound infoQuantityPanel = (PanelRound)defaultPanelAccessories.createJPanel(0,100,100);
        infoQuantityPanel.setLayout(new GridBagLayout());
        infoQuantityPanel.setBackground(Color.white);
        infoQuantityPanel.setRoundBottomRight(25);
        //endregion

        //region rightSection
        JPanel rightSection = defaultPanelAccessories.createJPanel(25,350,500);
        rightSection.setLayout(new GridBagLayout());
        rightSection.setBackground(Color.decode("#292c2d"));

        //TODO:Get based on product selected (Get object with info and use this.get[attribute])
        //TODO: Create an array with sources and link category id to array
        String[] categoryIconArray = new String[9];
        Image categoryImage = new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-cauliflower-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH);
        ImageIcon categoryIcon = new ImageIcon(categoryImage);
        JLabel categoryIconLabel = new JLabel(categoryIcon);

        selectedItemName = new JLabel(getItemName());
        selectedItemName.setFont(heading1);
        JLabel priceLabel = new JLabel("Price ea.");
        priceLabel.setFont(heading3);
        unitPriceLabel = new JLabel("$16.36");
        unitPriceLabel.setFont(heading2);

        shortDescrip = new JLabel("This is a short description");
        shortDescrip.setFont(medText);
        /*
        Image deleteImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-delete-100.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon deleteIcon = new ImageIcon(deleteImage);
        JLabel deleteIconLabel = new JLabel(deleteIcon);*/


        qtyLabel = new JLabel(Integer.toString(qty));
        qtyLabel.setFont(heading2);
/*
        Image addImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-add-100-2.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon addIcon = new ImageIcon(addImage);
        JLabel addIconLabel = new JLabel(addIcon);*/

        Image removeImage = new ImageIcon("src/main/resources/JWR-Icons/icons8-remove-100.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon removeIcon = new ImageIcon(removeImage);

        JButton removeButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/icons8-remove-100.png");

        JButton addButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/Black/icons8-add-100-2.png");

        JButton deleteButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/Black/icons8-delete-100.png");
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
        mpCons.weighty = 1;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        add(mainSection,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mainSection.add(leftSection,mpCons);

        mpCons.weightx = .40;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(0, 0, 0, 30);
        mainSection.add(rightSection,mpCons);
        
        //region TopBar.Add

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(10, 30, 10, 10);
        topBar.add(companyLabel,mpCons);

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


        //endregion

        //region leftSection.Add

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 40, 25, 60);
        leftSection.add(upperPane, mpCons);


        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 40, 0, 60);
        leftSection.add(userTableScroll, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 0, 0,0);
        upperPane.add(buttonBox, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(0, 30 , 0,0);
        upperPane.add(itemInfo, mpCons);


        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 10, 10, 10);
        buttonBox.add(searchByCode, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        buttonBox.add(barcodeScan, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        buttonBox.add(addCustomer, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 15, 0, 0);
        itemInfo.add(namePricePanel, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        itemInfo.add(infoQuantityPanel, mpCons);


        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(10, 30, 0, 26);
        namePricePanel.add(categoryIconLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        namePricePanel.add(selectedItemName, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        namePricePanel.add(Box.createGlue(),mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        namePricePanel.add(priceLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        namePricePanel.add(unitPriceLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(5, 30, 0, 26);
        infoQuantityPanel.add(shortDescrip, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(Box.createGlue(),mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(deleteButton, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(qtyLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(addButton, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(removeButton, mpCons);


        //endregion


        //region rightSection.Add
        
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25, 25, 0, 25);
        invoiceNum = new JLabel("Invoice #" + Integer.toString(getInvoiceNum()));
        invoiceNum.setFont(heading2);
        invoiceNum.setForeground(Color.WHITE);
        rightSection.add(invoiceNum, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 25, 0, 25);
        empName.setFont(heading3);
        empName.setForeground(Color.gray);
        rightSection.add(empName, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 2;
        mpCons.gridx = 0;
        rightSection.add(Box.createGlue(),mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 3;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 25, 5, 25);
        JLabel subtotalLabel = new JLabel("Subtotal");
        subtotalLabel.setFont(medText);
        subtotalLabel.setForeground(Color.WHITE);
        rightSection.add(subtotalLabel, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 3;
        mpCons.gridx = 1;
        subtotalAmt = new JLabel("$0.00");
        subtotalAmt.setFont(medText);
        subtotalAmt.setForeground(Color.WHITE);
        subtotalAmt.setHorizontalAlignment(SwingConstants.RIGHT);
        rightSection.add(subtotalAmt, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 4;
        mpCons.gridx = 0;
        discountLabel = new JLabel("Discount 10%");
        discountLabel.setFont(medText);
        discountLabel.setForeground(Color.WHITE);
        rightSection.add(discountLabel, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 4;
        mpCons.gridx = 1;
        discountAmtLabel = new JLabel("$0.00");
        discountAmtLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        discountAmtLabel.setFont(medText);
        discountAmtLabel.setForeground(Color.WHITE);
        rightSection.add(discountAmtLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 5;
        mpCons.gridx = 0;
        JLabel taxLabel = new JLabel("Tax 16%");
        taxLabel.setFont(medText);
        taxLabel.setForeground(Color.WHITE);
        rightSection.add(taxLabel, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 5;
        mpCons.gridx = 1;
        taxAmt = new JLabel("$0.00");
        taxAmt.setHorizontalAlignment(SwingConstants.RIGHT);
        taxAmt.setFont(medText);
        taxAmt.setForeground(Color.WHITE);
        rightSection.add(taxAmt, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 6;
        mpCons.gridx = 0;
        mpCons.gridwidth = 2;
        mpCons.fill = GridBagConstraints.HORIZONTAL;
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(100,2));
        rightSection.add(sep, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 7;
        mpCons.gridx = 0;
        JLabel totalLabel = new JLabel("Total");
        totalLabel.setFont(heading2);
        totalLabel.setForeground(Color.WHITE);
        rightSection.add(totalLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 7;
        mpCons.gridx = 1;
        totalAmt = new JLabel("$0.00");
        totalAmt.setHorizontalAlignment(SwingConstants.RIGHT);
        totalAmt.setFont(heading2);
        totalAmt.setForeground(Color.WHITE);
        rightSection.add(totalAmt, mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 8;
        mpCons.gridx = 0;
        rightSection.add(Box.createGlue(),mpCons);


        //region Payment
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 9;
        mpCons.gridx = 0;
        mpCons.gridwidth = 2;
        mpCons.insets = new Insets(0, 25, 30, 25);
        JLabel paymentLabel = new JLabel("Payment Method");
        paymentLabel.setFont(medText);
        paymentLabel.setForeground(Color.decode("#C4C4C4"));
        paymentLabel.setHorizontalAlignment(JLabel.CENTER);
        rightSection.add(paymentLabel, mpCons);

        RoundedBorder paymentOptionBorder = new RoundedBorder(Color.white,25);

        mpCons.fill = GridBagConstraints.VERTICAL;
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 10;
        mpCons.gridx = 0;
        mpCons.gridwidth = 1;
        mpCons.insets = new Insets(0, 25, 0, 25);
        JIconToggleButton cashButton = defaultPanelAccessories.iconToggleButton(150,115,75,75,"src/main/resources/JWR-Icons/White/icons8-us-dollar-circled-100.png","src/main/resources/JWR-Icons/Black/icons8-us-dollar-circled-100.png");
        paymentBtns.add(cashButton);
        cashButton.setHasBackground(false);
        cashButton.setBorder(paymentOptionBorder);
        cashButton.setBackground(Color.white);
        cashButton.setText("");
        cashButton.setSelected(true);
        rightSection.add(cashButton, mpCons);



        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 10;
        mpCons.gridx = 1;
        JIconToggleButton cardButton = defaultPanelAccessories.iconToggleButton(150,115,75,75,"src/main/resources/JWR-Icons/White/icons8-credit-card-100.png","src/main/resources/JWR-Icons/Black/icons8-credit-card-100.png");
        paymentBtns.add(cardButton);
        cardButton.setBorder(paymentOptionBorder);
        cardButton.setHasBackground(false);
        cardButton.setBackground(null);
        cardButton.setFocusPainted(false);
        cardButton.setSelected(false);
        cardButton.setBackground(Color.decode("#292C2D"));
        cardButton.setText("");
        rightSection.add(cardButton, mpCons);

        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 11;
        mpCons.gridx = 0;
        JLabel cashLabel = new JLabel("Cash");
        cashLabel.setFont(medText);
        cashLabel.setHorizontalAlignment(JLabel.CENTER);
        cashLabel.setForeground(Color.white);
        rightSection.add(cashLabel, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 11;
        mpCons.gridx = 1;
        JLabel cardLabel = new JLabel("Card");
        cardLabel.setFont(medText);
        cardLabel.setForeground(Color.white);
        cardLabel.setHorizontalAlignment(JLabel.CENTER);
        rightSection.add(cardLabel, mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 12;
        mpCons.gridx = 0;
        rightSection.add(Box.createGlue(),mpCons);

        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 13;
        mpCons.gridx = 0;
        mpCons.gridwidth = 2;
        JButton payButton = defaultPanelAccessories.defaultButton();
        payButton.setFont(medText);
        payButton.setText("Pay Now");
        payButton.setForeground(Color.decode("#292C2D"));
        payButton.setBackground(Color.white);
        mpCons.insets = new Insets(0, 60, 0, 60);
        rightSection.add(payButton, mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 14;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 25, 50, 25);
        rightSection.add(Box.createGlue(),mpCons);

        //endregion
        //mpCons.fill = GridBagConstraints.BOTH;


        //endregion

        //endregion

        //endregion


        searchByCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchDialog(posPage,client).setVisible(true);
            }
        });

        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addCustomerDialog(posPage,client);
            }
        });

        itemTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int selectedRowIndex = itemTable.getSelectedRow();
                if(selectedRowIndex != -1){
                    selectedRowIndex = itemTable.getSelectedRow();
                    InvoiceItem selectedItem = invoiceItemArrayList.get(selectedRowIndex);
                    Inventory inven = (Inventory)client.findEntity("Inventory","productCode",selectedItem.getProductCode());
                    selectedItemName.setText(inven.getName());
                    unitPriceLabel.setText("$" + inven.getUnitPrice());
                    shortDescrip.setText(inven.getShortDescrip());
                    qtyLabel.setText(Integer.toString(selectedItem.getItemQuantity()));
                    qty = selectedItem.getItemQuantity();
                }
                else{
                    selectedItemName.setText("Product Name");
                    unitPriceLabel.setText("$0.00");
                    shortDescrip.setText("This is a short description.");
                    qtyLabel.setText("0");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(qty > 1){
                        qty--;
                        qtyLabel.setText(Integer.toString(qty));
                        int selectedRowIndex = itemTable.getSelectedRow();
                        InvoiceItem selectedItem = invoiceItemArrayList.get(selectedRowIndex);
                        selectedItem.setItemQuantity(qty);

                        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
                        model.setValueAt(qty,selectedRowIndex,2);

                        float totalItemCost;
                        totalItemCost = Float.parseFloat(model.getValueAt(selectedRowIndex,3).toString())+Float.parseFloat(model.getValueAt(selectedRowIndex,1).toString());
                        totalItemCost = (float) (Math.round(totalItemCost*100) / 100.0);
                        model.setValueAt(totalItemCost,selectedRowIndex,3);
                        getTotalCost();
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"Quantity cannot be equal or less than 0","ERROR", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Quantity cannot be equal or less than 0");
                    }
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    logger.error("Item in table wasn't selected");
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int selectedRowIndex = itemTable.getSelectedRow();
                    InvoiceItem selectedItem = invoiceItemArrayList.get(selectedRowIndex);
                    Inventory inven = (Inventory)client.findEntity("Inventory","productCode",selectedItem.getProductCode());

                    if(qty != inven.getStock()){
                        qty++;
                        qtyLabel.setText(Integer.toString(qty));
                        selectedItem.setItemQuantity(qty);
                        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
                        model.setValueAt(qty,selectedRowIndex,2);
                        float totalItemCost;
                        totalItemCost = Float.parseFloat(model.getValueAt(selectedRowIndex,3).toString())+Float.parseFloat(model.getValueAt(selectedRowIndex,1).toString());
                        totalItemCost = (float) (Math.round(totalItemCost*100) / 100.0);
                        model.setValueAt(totalItemCost,selectedRowIndex,3);
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"Item quantity cannot exceed stock","ERROR", JOptionPane.ERROR_MESSAGE);
                        logger.error("Item quantity cannot exceed stock");
                    }



                    getTotalCost();
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    logger.error("Item in table wasn't selected");
                }

            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = itemTable.getSelectedRow();
                System.out.println(selectedRowIndex);
                InvoiceItem selectedItem = invoiceItemArrayList.get(selectedRowIndex);
                DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
                invoiceItemArrayList.remove(selectedRowIndex);
                updateTable();
                System.out.println("Row " + selectedRowIndex + " removed");
                getTotalCost();
            }
        });
    }

    public float getTax(){
        float taxValue = subtotal * tax;
        System.out.println(taxValue);
        taxValue = (float) (Math.round(taxValue*100) / 100.0);
        taxAmt.setText("$"+taxValue);
        return taxValue;
    }

    private float getTotalCost() {
        float totalCost = getSubtotal();
        totalCost += getTax();
        totalCost -= getDiscountValue();
        totalCost = (float) (Math.round(totalCost*100) / 100.0);
        totalAmt.setText("$"+totalCost);
        return totalCost;
    }

    private float getSubtotal() {
        subtotal = 0;
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
        for(int i = 0; i < model.getRowCount(); i++){
            subtotal += Float.parseFloat((model.getValueAt(i,3).toString()));
        }
        subtotal = (float) (Math.round(subtotal*100) / 100.0);
        subtotalAmt.setText("$" + subtotal);
        return subtotal;
    }

    private String getItemName() {
        String itemName = "Product Name";
        return itemName;
    }

    public void updateCustomer(Customer cust){
        this.invoiceCustomer = cust;
        if(cust != null){
            addCustomer.setText(invoiceCustomer.getfName()+ " " + invoiceCustomer.getlName());
            discountPercent = (float)0.1;
            discountLabel.setForeground(Color.decode("#4DB449"));
            discountAmtLabel.setForeground(Color.decode("#4DB449"));
        }
        else{
            addCustomer.setText("Add Customer");
            discountPercent = 0;
            discountLabel.setForeground(Color.white);
            discountAmtLabel.setForeground(Color.white);
        }
        getTotalCost();
    }

    public float getDiscountValue(){
        float discountValue = (float) (subtotal * discountPercent);
        discountValue = (float) (Math.round(discountValue*100) / 100.0);
        discountAmtLabel.setText("$"+ String.valueOf(discountValue));
        return discountValue;
    }


    private int getInvoiceNum() {
        int idNum = 437823;
        //SELECT * FROM Table ORDER BY ID DESC LIMIT 1
        //idNum = this.getIdNum();
        //
        //invoiceNum = invoiceNum + Integer.toString(idNum);
        return idNum;
    }

    public void updateInvoice(){
        InvoiceItem newItem = invoiceItemArrayList.get(invoiceItemArrayList.size() - 1);
        newItem.setInvoiceNum(getInvoiceNum());
        Inventory inven = (Inventory)client.findEntity("Inventory","productCode",newItem.getProductCode());
        float totalItemCost = newItem.getItemQuantity() * inven.getUnitPrice();
        totalItemCost = (float) (Math.round(totalItemCost*100) / 100.0);
        headerModel.addRow(new Object[] {inven.getName(),inven.getUnitPrice(),newItem.getItemQuantity(), totalItemCost});
        getTotalCost();
    }

    public void updateTable() {
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
        model.setRowCount(0);
        for (InvoiceItem item : invoiceItemArrayList) {
            Inventory inven = (Inventory)client.findEntity("Inventory","productCode",item.getProductCode());
            headerModel.addRow(new Object[] {inven.getName(),inven.getUnitPrice(),item.getItemQuantity(), (item.getItemQuantity() * inven.getUnitPrice())});
        }
    }

    public void changeQuantityInSearch(InvoiceItem item){
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
        for (int i = 0;i<invoiceItemArrayList.size();i++) {
            if(item.getProductCode().equals(invoiceItemArrayList.get(i).getProductCode())){
                invoiceItemArrayList.get(i).setItemQuantity(item.getItemQuantity());
                model.setValueAt(item.getItemQuantity(),i,2);
                model.setValueAt(Float.parseFloat(model.getValueAt(i,1).toString())*item.getItemQuantity(),i,3);
                getTotalCost();
                break;
            }
        }

    }

}
    

