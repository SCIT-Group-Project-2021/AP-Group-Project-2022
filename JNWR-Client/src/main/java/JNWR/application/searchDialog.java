package JNWR.application;

import Entity.DBEntity;
import Entity.Inventory;
import Entity.InvoiceItem;
import JNWR.application.utilities.defaultPanelAccessories;
import JNWR.Domain.Client;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class searchDialog extends JFrame implements defaultPanelAccessories {
    int qty = 1;
    final int frameWidth = 550;
    final int frameHeight = 585;

    JLabel addIconLabel;
    JTextField qtyTextField;
    JTextField searchBox;
    JButton cancelButton;
    JButton addItemButton;
    JButton searchButton;

    JComboBox<String> filter;
    public static searchDialog Instance;


    DefaultTableModel headerModel = new DefaultTableModel();
    String headers[] = { "Product Code", "Name", "Stock","Unit Price"};
    String filterOptions[] = { "productCode", "Name"};

    public searchDialog(posPage posPage) {
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
        JTable searchTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };

        searchTable.setShowGrid(false);
        searchTable.setRowHeight(50);

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);

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

                ArrayList<DBEntity> list = new Client().getSpecificList("Inventory",searchFilter,searchId);

                for (DBEntity entity : list) {

                    //Inventory inven = (Inventory) list.get(i);
                    Inventory inven = (Inventory) entity;

                    headerModel.addRow(new Object[] {inven.getProductCode(),inven.getName(),inven.getStock(),inven.getUnitPrice()});
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(qty > 1){
                    qty--;
                    qtyTextField.setText(Integer.toString(qty));
                }
                else{
                    //TODO: Add prompt
                    System.out.println("Quantity cannot be equal or less than 0");
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
                dispose();
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check that qty isnt more than stock
                try{
                    DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
                    int selectedRowIndex = searchTable.getSelectedRow();
                    ArrayList<InvoiceItem> invoiceItemArrayList = posPage.getInvoiceItemArrayList();
                    qty = Integer.parseInt(qtyTextField.getText());

                    if(qty < Integer.parseInt((model.getValueAt(selectedRowIndex,2).toString()))){
                        InvoiceItem newItem = new InvoiceItem(model.getValueAt(selectedRowIndex,0).toString(), qty);
                        for(int i = 0; i < invoiceItemArrayList.size();i++){
                            if(invoiceItemArrayList.get(i).getProductCode().equals(newItem.getProductCode())){
                                if(invoiceItemArrayList.get(i).getItemQuantity() + newItem.getItemQuantity() < Integer.parseInt((model.getValueAt(selectedRowIndex,2).toString()))){
                                    invoiceItemArrayList.get(i).setItemQuantity(invoiceItemArrayList.get(i).getItemQuantity() + newItem.getItemQuantity());
                                    posPage.changeQuantityInSearch(invoiceItemArrayList.get(i));
                                    return;
                                }
                                else{
                                    //TODO: Make popup for error
                                    System.out.println("Stock is less than requested quantity");
                                    return;
                                }
                            }
                        }
                        System.out.println(newItem);
                        invoiceItemArrayList.add(new InvoiceItem(model.getValueAt(selectedRowIndex,0).toString(), qty));
                        posPage.setInvoiceItemArrayList(invoiceItemArrayList);
                        posPage.updateInvoice();
                        //dispose();
                    }
                    else{
                        //TODO: Make popup for error
                        System.out.println("Stock is less than requested quantity");
                    }
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    logger.error("The invoice item was not selected.");
                }
                finally{
                    dispose();
                }


            }
        });
    }

    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }

}