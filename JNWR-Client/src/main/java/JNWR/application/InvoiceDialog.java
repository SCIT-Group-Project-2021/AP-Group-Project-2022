package JNWR.application;

import Entity.DBEntity;
import Entity.Inventory;
import Entity.InvoiceItem;
import JNWR.application.utilities.defaultPanelAccessories;
import JNWR.Domain.Client;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class InvoiceDialog extends JFrame implements defaultPanelAccessories {
    final int frameWidth = 550;
    final int frameHeight = 585;

    ArrayList<DBEntity> list;
    JButton exitButton;

    int invoiceNum;

    public static SearchDialog Instance;

    Client client;

    DefaultTableModel headerModel = new DefaultTableModel();
    String headers[] = { "Product Code", "Name", "Quantity","Total Price"};
    String filterOptions[] = { "productCode", "Name"};

    public InvoiceDialog(Client client, int invoiceNum, JPanel reportPage) {

        this.invoiceNum = invoiceNum;

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

        JLabel invoiceLabel = new JLabel("Invoice #"+String.valueOf(invoiceNum),2);
        invoiceLabel.setFont(heading2);

        exitButton = defaultPanelAccessories.defaultButton();
        exitButton.setText("Exit");


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

        //region Frame.Add

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        add(invoiceLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        add(tableScroll,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 2;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25,25,25,25);
        add(exitButton,mpCons);

        //endregion
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(reportPage).setEnabled(true);
                dispose();
            }
        });

        updateTable();

        refresh();
        
    }

    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }

    public void updateTable() {

        list = client.getExactList("InvoiceItem","invoiceNum",String.valueOf(invoiceNum));

        for (DBEntity entity : list) {

            InvoiceItem invoiceInven = (InvoiceItem) entity;
            Inventory inven = (Inventory) client.findEntity(new Inventory(), invoiceInven.getProductCode());
            
            headerModel.addRow(new Object[] {invoiceInven.getProductCode(),inven.getName(),invoiceInven.getItemQuantity(),inven.getUnitPrice()*invoiceInven.getItemQuantity()});

        }

    }
}
