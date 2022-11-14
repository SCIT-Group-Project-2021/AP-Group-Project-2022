package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.table.*;

import Entity.DBEntity;
import Entity.Inventory;
import JNWR.Domain.Client;
import JNWR.application.utilities.*;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;


public class ProdPage extends JPanel implements defaultPanelAccessories{

    Client client;

    ArrayList<DBEntity> list; 
    Inventory inven;

    CustomRoundTextField searchBox;
    JButton searchButton;
    JComboBox<String> filter;
    JButton addProductButton;
    JButton addNewCategory;
    JFilterButton allGoodsButton;
    JFilterButton bakedGoodsButton;
    JFilterButton beverageButton;
    JFilterButton cannedGoodsButton;
    JFilterButton dairyButton;
    JFilterButton dryGoodsButton;
    JFilterButton frozenGoodsButton;
    JFilterButton householdSuppliesButton;
    JFilterButton meatButton;
    JFilterButton otherButton;
    JFilterButton produceButton;
    JFilterButton personalCareButton;
    JFilterButton petCareButton;
    JFilterButton seafoodButton;
    JFilterButton snacksButton;
    String filterOptions[] = { "productCode", "name", "categoryID"};


    DefaultTableModel headerModel = new DefaultTableModel();
        
    String headers[] = { "Product Code","Product Category", "Name", "Short Description", "Stock", "Unit Price"};

    ProdPage(Client client) {

        this.client = client;
        ProdPage prodPage = this;


        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        setBackground(Color.decode("#F2F3F5"));
        //endregion

        //region Main Panel
        setLayout(new GridBagLayout());
        //endregion

        //region Top Bar
        JPanel topBar = defaultPanelAccessories.createJPanel(0,80,120);
        topBar.setBackground(null);
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

        //region Filter Folders
        JPanel filterSection = defaultPanelAccessories.createJPanel(0, 350, 80);
        filterSection.setLayout(new GridBagLayout());
        filterSection.setBackground(null);

        addNewCategory = defaultPanelAccessories.iconButton(35,35,"src/main/resources/JWR-Icons/Black/icons8-trans-100.png");
        allGoodsButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-diversity-100.png", "All Goods");
        bakedGoodsButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Baked Goods");
        beverageButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Beverages");
        cannedGoodsButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Canned Goods");
        dairyButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Dairy");
        dryGoodsButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Baking/Dry Goods");
        frozenGoodsButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Frozen Goods");
        householdSuppliesButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png","<html>Household & Cleaning<br> Supplies<html>");
        meatButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Meat");
        otherButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png","Other");
        produceButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png","Produce");
        personalCareButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png","Personal Care");
        petCareButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Pet Care");
        seafoodButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Seafood");
        snacksButton = new JFilterButton(150,35,35,35,"src/main/resources/JWR-Icons/Black/icons8-opened-folder-100.png", "Snacks");


        addProductButton =  defaultPanelAccessories.iconButton(150,20,20,20,"src/main/resources/JWR-Icons/White/icons8-plus-math-100.png");

        addProductButton.setText("Add New Product");
        addProductButton.setUI(new StyledButtonUI());
        addProductButton.setForeground(Color.white);
        addProductButton.setFont(new Font("Outfit", Font.BOLD, 14));
        addProductButton.setIconTextGap(8);
        addProductButton.setBackground(Color.decode("#101E2D"));

        addProductButton.setHorizontalAlignment(SwingConstants.LEFT);


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

        JLabel tableHeading = new JLabel("Products");
        tableHeading.setFont(heading2);

        Image searchImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-search-100.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        ImageIcon searchIcon = new ImageIcon(searchImage);
        searchButton = defaultPanelAccessories.defaultButton();
        searchButton.setIcon(searchIcon);
        searchButton.setPreferredSize(new Dimension(25,200));

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(20,20,20,0);
        searchBar.add(tableHeading,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        mpCons.insets = new Insets(25,10,25,10);
        searchBar.add(addProductButton,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 2;
        searchBar.add(Box.createGlue(),mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 3;
        searchBar.add(filter,mpCons);

        mpCons.weightx = .5;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 4;
        searchBar.add(searchBox,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 5;
        mpCons.insets = new Insets(25,10,25,25);
        searchBar.add(searchButton,mpCons);
        //endregion
        //endregion



        //region Customer Table Bar
        
        headerModel.setColumnIdentifiers(headers);

        JTable prodTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        prodTable.setShowGrid(false);
        prodTable.setRowHeight(50);
        prodTable.setFillsViewportHeight(true);
        prodTable.setBorder(null);

        prodTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {// alternate background color for rows
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


        JTableHeader jTableHeader = prodTable.getTableHeader();
        jTableHeader.setFont(new Font("SansSerif",Font.BOLD,50));
        jTableHeader.setDefaultRenderer(renderer);


        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(headerModel);
        prodTable.setRowSorter(sorter);
        
        JScrollPane tableScroll = new JScrollPane(prodTable){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
        };


        //tableScroll.setBackground(Color.GREEN);
        //endregion

        //region Frame.Add

        mpCons.ipadx = 0;
        mpCons.ipady = 0;

        mpCons.gridwidth = 2;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        add(topBar,mpCons);

        mpCons.gridwidth = 1;
        mpCons.gridheight = 2;
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 0, 0, 0);
        add(filterSection,mpCons);

        mpCons.gridheight = 1;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 1;
        mpCons.gridx = 1;
        mpCons.insets = new Insets(0, 0, 0, 60);
        add(searchBar,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 2;
        mpCons.gridx = 1;
        mpCons.insets = new Insets(0, 0, 60, 60);
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

        //region Filter Buttons
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0, 0, 5, 0);
        filterSection.add(allGoodsButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        filterSection.add(addNewCategory,mpCons);


        mpCons.gridwidth = 2;
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(bakedGoodsButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(beverageButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(cannedGoodsButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(dairyButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(frozenGoodsButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(householdSuppliesButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(meatButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(produceButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(personalCareButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(petCareButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(seafoodButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(snacksButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(otherButton,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        filterSection.add(Box.createGlue(),mpCons);


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
    
                    DefaultTableModel model = (DefaultTableModel) prodTable.getModel();
                    model.setRowCount(0);
                    if(searchId.equals("Search...")){
                        updateTable();
                    }
                    else{
                        list = client.getSpecificList("Inventory",searchFilter,searchId);
                        for (DBEntity entity : list) {
                            inven = (Inventory) entity;
                            headerModel.addRow(new Object[] {inven.getProductCode(),inven.getCategoryID(),inven.getName(),inven.getShortDescrip(),inven.getStock(),inven.getUnitPrice()});
                        }
                    }

                }
            });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductDialog(client,prodPage);
                SwingUtilities.getWindowAncestor(prodPage).setEnabled(false);
            }
        });
        //endregion

        allGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });

        bakedGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(bakedGoodsButton.getText(),prodPage,client,headerModel);
            }
        });

        beverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(beverageButton.getText(),prodPage,client,headerModel);
            }
        });
        cannedGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(cannedGoodsButton.getText(),prodPage,client,headerModel);
            }
        });
        dairyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(dairyButton.getText(),prodPage,client,headerModel);
            }
        });
        frozenGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(frozenGoodsButton.getText(),prodPage,client,headerModel);
            }
        });

        householdSuppliesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable("Household & Cleaning Supplies",prodPage,client,headerModel);
            }
        });

        meatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(meatButton.getText(),prodPage,client,headerModel);
            }
        });

        produceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(produceButton.getText(),prodPage,client,headerModel);
            }
        });

        personalCareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(personalCareButton.getText(),prodPage,client,headerModel);
            }
        });

        petCareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(petCareButton.getText(),prodPage,client,headerModel);
            }
        });

        seafoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(seafoodButton.getText(),prodPage,client,headerModel);
            }
        });

        snacksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(snacksButton.getText(),prodPage,client,headerModel);
            }
        });

        otherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakedGoodsButton.filterInventoryTable(otherButton.getText(),prodPage,client,headerModel);
            }
        });

        updateTable();

        repaint();
        setSize(getSize());

    }

    public void updateTable() {

        ArrayList<DBEntity> list = client.getList("Inventory");

        for (DBEntity entity : list) {

            inven = (Inventory) entity;

            headerModel.addRow(new Object[] {inven.getProductCode(),inven.getCategoryID(),inven.getName(),inven.getShortDescrip(),inven.getStock(),inven.getUnitPrice()});

        }

    }



}
