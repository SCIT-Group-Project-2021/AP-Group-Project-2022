package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import JNWR.application.utilities.defaultPanelAccessories;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

public class posPage extends JPanel implements defaultPanelAccessories{

    DefaultTableModel headerModel = new DefaultTableModel();
        
    String headers[] = { "PRODUCT NAME", "UNIT PRICE", "QTY","TOTAL"};
    ArrayList checkOutItems= new ArrayList<>();
    float subtotal = 0;
    float total = 0;
    final float tax = (float)0.16;

    public posPage() {


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
        JTable itemTable = new JTable(headerModel);
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
        JButton addCustomer = defaultPanelAccessories.defaultButton();
        addCustomer.setBackground(Color.white);
        addCustomer.setPreferredSize(new Dimension(150,70));
        addCustomer.setIcon(addCustomerIcon);
        addCustomer.setText("Add Customer");
        addCustomer.setFont(new Font("Outfit", Font.BOLD, 14));
        addCustomer.setIconTextGap(8);
        addCustomer.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel itemInfo = defaultPanelAccessories.createJPanel(0,100,280);
        itemInfo.setLayout(new GridBagLayout());

        PanelRound namePricePanel = (PanelRound)defaultPanelAccessories.createJPanel(0,100,100);
        namePricePanel.setLayout(new GridBagLayout());
        namePricePanel.setBackground(Color.cyan);
        namePricePanel.setRoundTopLeft(25);
        namePricePanel.setRoundTopRight(25);


        PanelRound infoQuantityPanel = (PanelRound)defaultPanelAccessories.createJPanel(0,100,100);
        infoQuantityPanel.setLayout(new GridBagLayout());
        infoQuantityPanel.setBackground(Color.MAGENTA);
        infoQuantityPanel.setRoundBottomLeft(25);
        infoQuantityPanel.setRoundBottomRight(25);
        //endregion

        //region rightSection
        JPanel rightSection = defaultPanelAccessories.createJPanel(25,350,500);
        rightSection.setLayout(new GridBagLayout());
        rightSection.setBackground(Color.decode("#292c2d"));

        //TODO:Get based on product selected (Get object with info and use this.get[attribute])
        Image categoryImage = new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-cauliflower-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH);
        ImageIcon categoryIcon = new ImageIcon(categoryImage);
        JLabel categoryIconLabel = new JLabel(categoryIcon);

        JLabel selectedItemName = new JLabel(getItemName());
        selectedItemName.setFont(heading1);
        JLabel priceLabel = new JLabel("Price ea.");
        priceLabel.setFont(heading3);
        JLabel unitPriceLabel = new JLabel("$16.36");
        unitPriceLabel.setFont(heading2);

        JLabel shortDescrip = new JLabel("This is a short description");
        shortDescrip.setFont(medText);

        Image deleteImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-delete-100.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon deleteIcon = new ImageIcon(deleteImage);
        JLabel deleteIconLabel = new JLabel(deleteIcon);

        int qty = 5;
        JLabel qtyLabel = new JLabel(Integer.toString(qty));
        qtyLabel.setFont(heading2);

        Image addImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-add-100-2.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon addIcon = new ImageIcon(addImage);
        JLabel addIconLabel = new JLabel(addIcon);

        Image removeImage = new ImageIcon("src/main/resources/JWR-Icons/icons8-remove-100.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon removeIcon = new ImageIcon(removeImage);
        JLabel removeIconLabel = new JLabel(removeIcon);
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
        //mpCons.insets = new Insets(0, 5 , 0,10);
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
        mpCons.insets = new Insets(0, 30, 0, 0);
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
        infoQuantityPanel.add(deleteIconLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(qtyLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(addIconLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        infoQuantityPanel.add(removeIconLabel, mpCons);


        //endregion


        //region rightSection.Add
        
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25, 25, 25, 25);
        JLabel invoiceNum = new JLabel(getInvoiceNum());
        invoiceNum.setFont(heading3);
        invoiceNum.setForeground(Color.WHITE);
        rightSection.add(invoiceNum, mpCons);

        //endregion

        //endregion

        //endregion

        
    }

    private String getItemName() {
        //TODO: Get item name in POS page
        String itemName = "Avocado";
        return itemName;
    }

    private String getInvoiceNum() {
        String invoiceNum = "Invoice #";
        int idNum = 437823;
        //SELECT * FROM Table ORDER BY ID DESC LIMIT 1
        //idNum = this.getIdNum();
        //
        invoiceNum = invoiceNum + Integer.toString(idNum);
        return invoiceNum;
    }

}
    

