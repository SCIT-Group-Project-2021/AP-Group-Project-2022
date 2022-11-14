package JNWR.application;

import Entity.Inventory;
import JNWR.Domain.Client;
import JNWR.application.utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class EditProductDialog extends JFrame implements defaultPanelAccessories {
    Client client;
    int frameWidth = 1100;
    int frameHeight = 720;

    JLabel headingLabel;
    JLabel descriptionHeading;
    JLabel productIdLabel;
    CustomRoundTextField productIdField;
    JLabel productNameLabel;
    CustomRoundTextField productNameField;
    JLabel shortDescripLabel;
    JTextArea shortDescripField;
    JScrollPane shortDescripPane;
    JLabel longDescripLabel;
    JTextArea longDescripField;
    JScrollPane longDescripPane;
    JLabel inventoryHeading;
    JLabel categoryLabel;
    CustomRoundComboBox<String> categoryCombo;
    JLabel quantityLabel;
    CustomRoundTextField quantityField;
    JLabel priceLabel;
    CustomRoundTextField priceField; //TODO: Add dollar sign icon in textfield
    JButton cancelButton;
    JButton addProductButton;

    String category[] = { "Select a Category","Baked Goods", "Beverages","Canned Goods","Dairy","Baking/Dry Goods","Frozen Goods","Household & Cleaning Supplies","Meat","Produce","Personal Care","Pet Care","Seafood","Snacks","Other"};


    public EditProductDialog(Client client, ProdPage prodPage, Inventory item) {

        this.client = client;
        //region Base Frame Setup
        this.setUndecorated(true);
        this.setBackground(Color.white);
        this.setShape(new RoundRectangle2D.Double(0, 0, frameWidth, frameHeight, 30, 30));
        RoundedBorder round = new RoundedBorder(Color.decode("#CED4DA"),30);
        this.getRootPane().setBorder(round);
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(frameWidth, frameHeight));

        setLayout(new GridBagLayout());
        setVisible(true);
        //endregion

        // region Enables Undecorated Frame drag movement
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        // endregion

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(10,10,25,10);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        // region GUI Sections
        JPanel mainSection = defaultPanelAccessories.createJPanel(0, 350, 80);
        mainSection.setLayout(new GridBagLayout());
        mainSection.setBackground(Color.white);

        JPanel descripSection = defaultPanelAccessories.createJPanel(0, 350, 80);
        descripSection.setLayout(new GridBagLayout());
        descripSection.setBorder(round);
        descripSection.setBackground(Color.white);

        JPanel inventorySection = defaultPanelAccessories.createJPanel(0, 350, 220);
        inventorySection.setLayout(new GridBagLayout());
        inventorySection.setBorder(round);
        inventorySection.setBackground(Color.white);

        JPanel buttonBox = defaultPanelAccessories.createJPanel(0, 350, 80);
        buttonBox.setLayout(new GridBagLayout());
        buttonBox.setBackground(Color.white);

        //endregion

        //region GUI Elements' Initialization
        headingLabel = new JLabel("Edit Product");
        headingLabel.setFont(heading2);

        descriptionHeading = new JLabel("Description");
        descriptionHeading.setFont(heading3);

        productIdLabel = new JLabel("Product ID");
        productIdLabel.setFont(smText);

        productIdField = new CustomRoundTextField();
        productIdField.setFont(miniText);
        productIdField.setText(item.getProductCode());
        productIdField.setEditable(false);

        productNameLabel = new JLabel("Product Name");
        productNameLabel.setFont(smText);
        productNameField = new CustomRoundTextField();
        productNameField.setFont(miniText);
        productNameField.setText(item.getName());

        shortDescripLabel = new JLabel("Short Description");
        shortDescripLabel.setFont(smText);

        shortDescripField = new JTextArea();
        shortDescripField.setLineWrap(true);
        shortDescripField.setFont(miniText);
        shortDescripField.setBorder(null);
        shortDescripField.setText(item.getShortDescrip());

        shortDescripPane = new JScrollPane(shortDescripField);
        shortDescripPane.setVerticalScrollBar(new ScrollBarCustom());
        shortDescripPane.setBorder(new RoundedBorder(Color.decode("#CED4DA"),10));
        shortDescripPane.setOpaque(false);


        longDescripLabel = new JLabel("Long Description");
        longDescripLabel.setFont(smText);

        longDescripField = new JTextArea();
        longDescripField.setFont(miniText);
        longDescripField.setLineWrap(true);
        longDescripField.setBorder(null);
        longDescripField.setText(item.getLongDescrip());

        longDescripPane = new JScrollPane(longDescripField);
        longDescripPane.setVerticalScrollBar(new ScrollBarCustom());
        longDescripPane.setBorder(new RoundedBorder(Color.decode("#CED4DA"),10));
        longDescripPane.setOpaque(false);

        inventoryHeading = new JLabel("Inventory");
        inventoryHeading.setFont(heading3);

        categoryLabel = new JLabel("Category");
        categoryLabel.setFont(smText);
        categoryCombo = new CustomRoundComboBox();
        categoryCombo.sendFont(miniText);
        categoryCombo.setModel(new javax.swing.DefaultComboBoxModel(category));
        categoryCombo.setSelectedItem(item.getCategoryID());

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(smText);

        quantityField = new CustomRoundTextField();
        quantityField.setFont(miniText);
        quantityField.setText(Integer.toString(item.getStock()));

        priceLabel = new JLabel("Unit Price");
        priceLabel.setFont(smText);

        priceField = new CustomRoundTextField(); //TODO: Add dollar sign icon in textfield
        priceField.setFont(miniText);
        priceField.setText(Float.toString(item.getUnitPrice()));

        cancelButton = defaultPanelAccessories.defaultButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(smText);
        cancelButton.setPreferredSize(new Dimension(20,50));
        cancelButton.setBorder(round);

        addProductButton = defaultPanelAccessories.defaultButton();
        addProductButton.setText("Edit Product");
        addProductButton.setFont(smText);
        addProductButton.setForeground(Color.white);
        addProductButton.setBackground(Color.decode("#005DFB"));
        addProductButton.setPreferredSize(new Dimension(20,50));
        //endregion


        //region Frame.Add

        mpCons.ipadx = 0;
        mpCons.ipady = 0;

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,0,0);
        add(mainSection,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mainSection.add(headingLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(10,0,10,0);
        mainSection.add(descriptionHeading,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0.7;
        mpCons.gridy = 2;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,20);
        mainSection.add(descripSection,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,0);
        descripSection.add(productIdLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        descripSection.add(productIdField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        descripSection.add(productNameLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        descripSection.add(productNameField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        descripSection.add(shortDescripLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0.2;
        mpCons.gridy++;
        mpCons.gridx = 0;
        descripSection.add(shortDescripPane,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        descripSection.add(longDescripLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        descripSection.add(longDescripPane,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 1;//3
        mpCons.gridx = 1;
        mpCons.insets = new Insets(10,0,10,0); //to30
        mainSection.add(inventoryHeading,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0.2;
        mpCons.gridy = 2; //4
        mpCons.gridx = 1;
        mpCons.insets = new Insets(0,0,5,0);
        mainSection.add(inventorySection,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,0);
        inventorySection.add(categoryLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0.05;
        mpCons.gridy++;
        mpCons.gridx = 0;
        inventorySection.add(categoryCombo,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        inventorySection.add(quantityLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        inventorySection.add(quantityField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        inventorySection.add(priceLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        inventorySection.add(priceField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        inventorySection.add(Box.createGlue(),mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridwidth = 2;
        mpCons.gridy = 3;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,0,0);
        mainSection.add(buttonBox,mpCons);

        mpCons.weightx = 1;
        mpCons.gridwidth = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        buttonBox.add(Box.createGlue(),mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        buttonBox.add(cancelButton,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(0, 50, 0, 0);
        buttonBox.add(addProductButton,mpCons);

        mpCons.weightx = 1;
        mpCons.gridwidth = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        buttonBox.add(Box.createGlue(),mpCons);
        //endregion

        refresh();

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(prodPage).setEnabled(true);
                dispose();
            }
        });

        shortDescripField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                shortDescripPane.setBorder(new RoundedBorder(Color.decode("#80BDFF"),10));
            }

            public void focusLost(FocusEvent e) {
                shortDescripPane.setBorder(new RoundedBorder(Color.decode("#CED4DA"),10));
            }}
        );

        longDescripField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                longDescripPane.setBorder(new RoundedBorder(Color.decode("#80BDFF"),10));
            }

            public void focusLost(FocusEvent e) {
                longDescripPane.setBorder(new RoundedBorder(Color.decode("#CED4DA"),10));
            }}
        );

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productIdField.getText() != "" && productNameField.getText() != "" && shortDescripField.getText() != "" && categoryCombo.getSelectedItem().toString() != "Select a Category" && priceField.getText() != "" && quantityField.getText() != ""){
                    Inventory product = null;
                    String categoryName = categoryCombo.getSelectedItem().toString();
                    String categoryId = "";
                    switch(categoryName){
                        case "Baked Goods":
                            categoryId="BKY";
                            break;
                        case "Beverages":
                            categoryId="BVG";
                            break;
                        case "Canned Goods":
                            categoryId="CNG";
                            break;
                        case "Dairy":
                            categoryId="DAI";
                            break;
                        case "Baking/Dry Goods":
                            categoryId="DRY";
                            break;
                        case "Frozen Goods":
                            categoryId="FZG";
                            break;
                        case "Household & Cleaning Supplies":
                            categoryId="HSS";
                            break;
                        case "Meat":
                            categoryId="MT";
                            break;
                        case "Other":
                            categoryId="OTR";
                            break;
                        case "Produce":
                            categoryId="PRD";
                            break;
                        case "Personal Care":
                            categoryId="PSC";
                            break;
                        case "Pet Care":
                            categoryId="PTC";
                        case "Seafood":
                            categoryId="SFD";
                            break;
                        case "Snacks":
                            categoryId="SNK";
                            break;
                    }

                    product = new Inventory(productIdField.getText(),productNameField.getText(),shortDescripField.getText(), longDescripField.getText(), Integer.parseInt(quantityField.getText()),Float.parseFloat(priceField.getText()), categoryId);

                    client.alterEntity(product, product.getProductCode());
                    prodPage.updateTable();
                    JOptionPane.showMessageDialog(new JFrame(), "Product edited successfully!");
                    SwingUtilities.getWindowAncestor(prodPage).setEnabled(true);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"Please fill the required text fields","Cannot edit product", JOptionPane.ERROR_MESSAGE);
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
