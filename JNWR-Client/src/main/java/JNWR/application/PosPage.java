package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import Entity.*;
import JNWR.Domain.Client;
import JNWR.application.utilities.defaultPanelAccessories;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
  
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class PosPage extends JPanel implements defaultPanelAccessories{

    ButtonGroup paymentBtns = new ButtonGroup();
    PosPage posPage = this;
    JLabel selectedItemName;
    JLabel unitPriceLabel;
    JLabel shortDescrip;
    JLabel qtyLabel;
    JTable itemTable;
    JButton addCustomer;
    JLabel subtotalAmt;
    JLabel discountLabel;
    JLabel empName;

    JLabel empInvoiceName;

    JLabel totalAmt;
    float discountPercent = 0;
    Customer invoiceCustomer;

    JLabel discountAmtLabel;
    float subtotal = 0;
    JLabel taxAmt;

    Integer currentInvoiceNum;

    Client client;
    Staff employee;
    JLabel categoryIconLabel;
    JIconToggleButton cashButton;
    JIconToggleButton cardButton;

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

    public PosPage(Client client, Staff employee) {

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

        //region Jan's Wholesale Text
        JLabel companyLabel = new JLabel("Jan's Wholesale and Retail");
        companyLabel.setFont(heading1);
        //endregion

        //region Log Out Label & Button
        empName = new JLabel();
        empName.setText(employee.getfName() + " " + employee.getlName());
        empName.setFont(heading3);
        JButton logOut = defaultPanelAccessories.iconButton(30,30,"src/main/resources/JWR-Icons/Black/icons8-logout-rounded-down-100.png");
        //endregion
        empInvoiceName = new JLabel();
        //endregion

        JPanel mainSection = new JPanel();
        
        mainSection.setLayout(new GridBagLayout());

        //region left section
        JPanel leftSection = defaultPanelAccessories.createJPanel(0,500,80);
        leftSection.setLayout(new GridBagLayout());

        headerModel.setColumnIdentifiers(headers);
        itemTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        itemTable.setShowGrid(false);
        itemTable.setRowHeight(50);
        itemTable.setFillsViewportHeight(true);
        itemTable.setBorder(null);

        itemTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {// alternate background color for rows
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


        JTableHeader jTableHeader = itemTable.getTableHeader();
        jTableHeader.setFont(new Font("SansSerif",Font.BOLD,50));
        jTableHeader.setDefaultRenderer(renderer);


        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(headerModel);
        itemTable.setRowSorter(sorter);


        JScrollPane userTableScroll = new JScrollPane(itemTable);
        //userTableScroll.setBorder(round);

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
        ImageIcon[] categoryIconArray = new ImageIcon[14];
        categoryIconArray[0] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-croissant-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[1] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-cola-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[2] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-canned-sardines-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[3] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-cheese-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[4] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-flour-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[5] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-ice-pop-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[6] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/detergent-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[7] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-meat-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[8] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-categorize-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[9] =  new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-cauliflower-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[10] = new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-tooth-cleaning-kit-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[11] = new ImageIcon(new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-dog-paw-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[12] = new ImageIcon(new ImageIcon( "src/main/resources/JWR-Icons/Category Icons/icons8-octopus-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));
        categoryIconArray[13] = new ImageIcon(new ImageIcon( "src/main/resources/JWR-Icons/Category Icons/snacks-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH));

        Image categoryImage = new ImageIcon("src/main/resources/JWR-Icons/Category Icons/icons8-cauliflower-100.png").getImage().getScaledInstance(80,80 , Image.SCALE_SMOOTH);
        ImageIcon categoryIcon = new ImageIcon(categoryImage);
        categoryIconLabel = new JLabel(categoryIcon);

        selectedItemName = new JLabel(getItemName());
        selectedItemName.setFont(heading1);
        JLabel priceLabel = new JLabel("Price ea.");
        priceLabel.setFont(heading3);
        unitPriceLabel = new JLabel("$0.00");
        unitPriceLabel.setFont(heading2);

        shortDescrip = new JLabel("[Short Description Section]");
        shortDescrip.setFont(medText);
        shortDescrip.setPreferredSize(new Dimension(400,30));
        /*
        Image deleteImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-delete-100.png").getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        ImageIcon deleteIcon = new ImageIcon(deleteImage);
        JLabel deleteIconLabel = new JLabel(deleteIcon);*/


        qtyLabel = new JLabel(Integer.toString(qty));
        qtyLabel.setFont(heading2);

        JButton removeButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/icons8-remove-100.png");

        JButton addButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/Black/icons8-add-100-2.png");

        JButton deleteButton = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/Black/icons8-delete-100.png");
        //endregion

        //region Frame.Add


        mpCons.ipadx = 0;
        mpCons.ipady = 0;

        mpCons.insets = new Insets(25,10,25,25);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        add(topBar,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 0, 60, 0);
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
        mpCons.insets = new Insets(10, 25, 0, 10);
        namePricePanel.add(categoryIconLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(10, 15, 0, 10);
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
        mpCons.insets = new Insets(5, 15, 0, 25);
        namePricePanel.add(unitPriceLabel, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(5, 30, 0, 25);
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
        currentInvoiceNum = getInvoiceNum();
        invoiceNum = new JLabel("Invoice #" + Integer.toString(currentInvoiceNum));
        invoiceNum.setFont(heading2);
        invoiceNum.setForeground(Color.WHITE);
        rightSection.add(invoiceNum, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 25, 0, 25);
        empInvoiceName.setFont(heading3);
        empInvoiceName.setForeground(Color.gray);
        rightSection.add(empInvoiceName, mpCons);

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
        cashButton = defaultPanelAccessories.iconToggleButton(150,115,75,75,"src/main/resources/JWR-Icons/White/icons8-us-dollar-circled-100.png","src/main/resources/JWR-Icons/Black/icons8-us-dollar-circled-100.png");
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
        cardButton = defaultPanelAccessories.iconToggleButton(150,115,75,75,"src/main/resources/JWR-Icons/White/icons8-credit-card-100.png","src/main/resources/JWR-Icons/Black/icons8-credit-card-100.png");
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
        payButton.setText("Check out");
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
                new SearchDialog(posPage,client).setVisible(true);
                SwingUtilities.getWindowAncestor(posPage).setEnabled(false);
            }
        });

        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCustomerDialog(posPage,client);
                SwingUtilities.getWindowAncestor(posPage).setEnabled(false);
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
                    shortDescrip.setToolTipText(shortDescrip.getText());
                    qtyLabel.setText(Integer.toString(selectedItem.getItemQuantity()));
                    qty = selectedItem.getItemQuantity();
                    ImageIcon categoryIcon = null;

                    switch(inven.getCategoryID()){
                        case "BKY":
                            categoryIcon = categoryIconArray[0];
                            break;
                        case "BVG":
                            categoryIcon = categoryIconArray[1];
                            break;
                        case "CNG":
                            categoryIcon = categoryIconArray[2];
                            break;
                        case "DAI":
                            categoryIcon = categoryIconArray[3];
                            break;
                        case "DRY":
                            categoryIcon = categoryIconArray[4];
                            break;
                        case "FZG":
                            categoryIcon = categoryIconArray[5];
                            break;
                        case "HSS":
                            categoryIcon = categoryIconArray[6];
                            break;
                        case "MT":
                            categoryIcon = categoryIconArray[7];
                            break;
                        case "OTR":
                            categoryIcon = categoryIconArray[8];
                            break;
                        case "PRD":
                            categoryIcon = categoryIconArray[9];
                            break;
                        case "PSC":
                            categoryIcon = categoryIconArray[10];
                            break;
                        case "PTC":
                            categoryIcon = categoryIconArray[11];
                        case "SFD":
                            categoryIcon = categoryIconArray[12];
                            break;
                        case "SNK":
                            categoryIcon = categoryIconArray[13];
                            break;
                    }
                    categoryIconLabel.setIcon(categoryIcon);
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
                    int selectedRowIndex = itemTable.getSelectedRow();
                    InvoiceItem selectedItem = invoiceItemArrayList.get(selectedRowIndex);
                    
                    if(qty > 1){
                        qty--;
                        qtyLabel.setText(Integer.toString(qty));
                        selectedItem.setItemQuantity(qty);                        

                        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
                        model.setValueAt(qty,selectedRowIndex,2);

                        float totalItemCost;
                        totalItemCost = Float.parseFloat(model.getValueAt(selectedRowIndex,2).toString())*Float.parseFloat(model.getValueAt(selectedRowIndex,1).toString());
                        totalItemCost = (float) (Math.round(totalItemCost*100) / 100.0);
                        model.setValueAt(totalItemCost,selectedRowIndex,3);
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"Quantity cannot be equal or less than 0","ERROR", JOptionPane.ERROR_MESSAGE);
                        logger.warn("Quantity cannot be equal or less than 0");
                    }
                    getTotalCost();
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
                        totalItemCost = Float.parseFloat(model.getValueAt(selectedRowIndex,2).toString())*Float.parseFloat(model.getValueAt(selectedRowIndex,1).toString());
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
                invoiceItemArrayList.remove(selectedRowIndex);
                updateTable();
                getTotalCost();
            }
        });
    
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (invoiceItemArrayList.isEmpty()) {

                    JOptionPane.showMessageDialog(new JFrame(),"cant Process Empty Invoice","ERROR", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    LocalDate dateTime = LocalDate.now();
                    String dateString = dateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd")).toString();
                    if (invoiceCustomer != null) {
                        Invoice invoice = new Invoice(dateString,getInvoiceCustomer().getCustomerId(),319219);
                        printInvoice(getInvoiceNum(),invoice,invoiceItemArrayList,dateString);
                        client.addEntity(invoice);
                        for (InvoiceItem checkoutItems : invoiceItemArrayList) {
                            checkoutItems.setInvoiceNum(currentInvoiceNum);
                            client.checkOutEntity(checkoutItems);

                        }     
                    } 
                    else {
                    //TODO: Login must set StaffID
                    
                        Invoice invoice = new Invoice(dateString,319219);
                        printInvoice(getInvoiceNum(),invoice,invoiceItemArrayList,dateString);
                        client.addEntity(invoice);
                        for (InvoiceItem checkoutItems : invoiceItemArrayList) {
                            checkoutItems.setInvoiceNum(currentInvoiceNum);
                            client.checkOutEntity(checkoutItems);   
                        }
                        
                    }

                invoiceItemArrayList.clear();

                }

                updateTable();
                getTotalCost();
            
                invoiceCustomer = null;
                currentInvoiceNum = getInvoiceNum();
                invoiceNum.setText("Invoice #" + Integer.toString(currentInvoiceNum));
                updateCustomer(invoiceCustomer);
        
            }
        });

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public float getTax(){
        float taxValue = subtotal * tax;
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

    private Integer getInvoiceNum() {
        
        Integer idNum = 1000;
        try {
            Invoice entity = (Invoice) client.findLastEntity("Invoice", "invoiceNum");
            idNum = entity.getInvoiceNum()+ 1;   
        } catch (NullPointerException e) {
            logger.warn("No Invoices in database throwing Up Default Start Invoice Number");
        }
        
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

    public void printInvoice(int invoiceNum, Invoice invoice, ArrayList<InvoiceItem> invoiceItems, String date) {   

        File directory = new File("Invoice");
        directory.mkdir();
        String paymentType = "Cash";

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter("Invoice/invoice#"+getInvoiceNum()+".pdf"))) {
			Document doc = new Document(pdfDoc);
            LineSeparator linebreak = new LineSeparator(new DashedLine());

			Paragraph invoiceTitleP = new Paragraph();
            invoiceTitleP.setTextAlignment(TextAlignment.CENTER);

            Paragraph invoiceTotal = new Paragraph();
            Paragraph invoiceThankYou = new Paragraph();
            invoiceThankYou.setTextAlignment(TextAlignment.CENTER);

            invoiceTitleP.add(new Text("Jan's Wholesale and Retail \n").setFontSize(24).setBold());
            invoiceTitleP.add(new Text("Invoice #"+getInvoiceNum()+"\n").setFontSize(18));
            invoiceTitleP.add(new Text("Cashed on: "+ date+"\n").setFontSize(18));
            invoiceTitleP.add(new Text("Cashier: "+ empName.getText()+"\n"));
            
            Table invoiceItemTable = new Table(UnitValue.createPercentArray(new float[]{5,2,1,(float) 1.5}));
            invoiceItemTable.setBorder(Border.NO_BORDER);
            invoiceItemTable.setWidth(UnitValue.createPercentValue(100));

            invoiceItemTable.addHeaderCell(new Cell().add(new Paragraph("Item Name")).setBorder(Border.NO_BORDER));
            invoiceItemTable.addHeaderCell(new Cell().add(new Paragraph("Unit Price").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            invoiceItemTable.addHeaderCell(new Cell().add(new Paragraph("Quantity").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            invoiceItemTable.addHeaderCell(new Cell().add(new Paragraph("Price").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            
			for (InvoiceItem checkoutItems : invoiceItemArrayList) {
                Inventory inven = (Inventory) client.findEntity(new Inventory(), checkoutItems.getProductCode());
			    invoiceItemTable.addCell(new Cell().add(new Paragraph(""+inven.getName())).setBorder(Border.NO_BORDER));
                invoiceItemTable.addCell(new Cell().add(new Paragraph("$"+inven.getUnitPrice()).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
                invoiceItemTable.addCell(new Cell().add(new Paragraph(""+checkoutItems.getItemQuantity() + "x").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
                invoiceItemTable.addCell(new Cell().add(new Paragraph("$"+(inven.getUnitPrice()*checkoutItems.getItemQuantity())).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
			}

            invoiceTotal.add(new Text("\n Payment Type: "));
            if(cardButton.isSelected()){
                paymentType = "Card";
            }
            invoiceTotal.add(new Text(paymentType+"\n").setTextAlignment(TextAlignment.RIGHT));
            invoiceTotal.add(new Text("\n Subtotal: "));
            invoiceTotal.add(new Text(subtotalAmt.getText()+"\n").setTextAlignment(TextAlignment.RIGHT));
            invoiceTotal.add(new Text("Tax: "));
            invoiceTotal.add(new Text(taxAmt.getText()+"\n").setTextAlignment(TextAlignment.RIGHT));
            invoiceTotal.add(new Text("Discount: "));
            invoiceTotal.add(new Text(discountAmtLabel.getText()+"\n").setTextAlignment(TextAlignment.RIGHT));
            invoiceTotal.add(new Text("Total: ").setFontSize(20).setBold());
            invoiceTotal.add(new Text(totalAmt.getText()).setFontSize(20).setBold().setTextAlignment(TextAlignment.RIGHT)); 
            
            invoiceThankYou.add(new Text("Thank You").setFontSize(20).setBold());
            
            doc.add(invoiceTitleP);
            doc.add(linebreak);
			doc.add(invoiceItemTable);
            doc.add(linebreak);
            doc.add(invoiceTotal);
            doc.add(linebreak);
            doc.add(invoiceThankYou);
            doc.add(linebreak);
            doc.close();

            Object[] options = {"Yes","No"};


            if (JOptionPane.showOptionDialog(posPage,"Would you like to open the invoice?","Purchase Complete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, options[0])
            == JOptionPane.YES_OPTION) {
                try {
                    Desktop.getDesktop().open(new File("Invoice/invoice#"+getInvoiceNum()+".pdf"));
                } catch (IOException e1) {
                    logger.error(e1.toString());
                };    
            }
            
		} 

        
            
        catch (IOException e) {
            logger.error("An error occurred.");
            logger.error(e.toString());
        }

        String.format("%20s %20s \r\n", "column 1", "column 2");
        
    }

}
    

