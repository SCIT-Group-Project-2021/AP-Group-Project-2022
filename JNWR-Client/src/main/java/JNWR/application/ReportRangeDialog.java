package JNWR.application;

import Entity.*;
import JNWR.application.customException.FutureDateException;
import JNWR.application.utilities.defaultPanelAccessories;
import JNWR.Domain.Client;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ReportRangeDialog extends JFrame implements defaultPanelAccessories {
    final int frameWidth = 550;
    final int frameHeight = 585;

    ArrayList<DBEntity> list;
    JButton exitButton;
    JButton reportButton;
    ArrayList<DBEntity> invoices = null;
    ArrayList<DBEntity> invoiceItems  = null;
    int quantitySum;
    float totalSales;


    Client client;
    JXDatePicker startDateField;
    JXDatePicker endDateField;
    SimpleDateFormat dateFormat;
    JTable resultTable;
    DefaultTableModel headerModel = new DefaultTableModel();
    String[] headers = { "Product Code", "Name", "Total Quantity Sold","Total Price"};
    String[] detailedHeaders = {"Invoice Item", "Product Code", "Name", "Quantity Sold","Total Price", "Invoice Date"};

    public ReportRangeDialog(Client client, ProdPage prodPage, Inventory item) {

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

        // region Enables Undecorated Frame drag movement
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        // endregion

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        JLabel itemNameLabel = new JLabel(item.getName() + " Sales Report",2);
        itemNameLabel.setFont(heading3);

        exitButton = defaultPanelAccessories.defaultButton();
        exitButton.setText("Exit");

        startDateField = new JXDatePicker();
        startDateField.getMonthView().setZoomable(true);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        startDateField.setFormats(dateFormat);

        endDateField = new JXDatePicker();
        endDateField.getMonthView().setZoomable(true);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        endDateField.setFormats(dateFormat);

        reportButton = defaultPanelAccessories.defaultButton();
        reportButton.setText("Generate Report");
        reportButton.setFont(smText);
        reportButton.setForeground(Color.white);
        reportButton.setBackground(Color.decode("#005DFB"));
        reportButton.setPreferredSize(new Dimension(20,50));

        exitButton = defaultPanelAccessories.defaultButton();
        exitButton.setText("Exit");
        exitButton.setFont(smText);
        exitButton.setPreferredSize(new Dimension(20,50));

        JLabel detailedViewLabel = new JLabel("Detailed View");
        detailedViewLabel.setFont(smText);
        detailedViewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = detailedViewLabel.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        detailedViewLabel.setFont(font.deriveFont(attributes));

        JLabel startDateLabel = new JLabel("Start Date");
        startDateLabel.setFont(smText);

        JLabel endDateLabel = new JLabel("End Date");
        endDateLabel.setFont(smText);

        JLabel printReportLabel = new JLabel("Print Report");
        printReportLabel.setFont(smText);
        printReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        font = printReportLabel.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        printReportLabel.setFont(font.deriveFont(attributes));


        //region Customer Table Bar

        headerModel.setColumnIdentifiers(headers);
        resultTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
       /*
        headerModel.setColumnIdentifiers(detailedHeaders);
        headerModel.setColumnIdentifiers(headers);
        resultTable = new JTable(headerModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };*/

        resultTable.setShowGrid(false);
        resultTable.setRowHeight(50);

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);

        JScrollPane tableScroll = new JScrollPane(resultTable){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
        };
        //endregion

        //region Frame.Add

        mpCons.gridwidth = 2;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        add(itemNameLabel,mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        add(startDateLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridx++;
        add(endDateLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(10,0,10,10);
        add(startDateField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridx++;
        add(endDateField,mpCons);

        mpCons.gridwidth = 2;
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,0,0);
        add(tableScroll,mpCons);

        mpCons.gridwidth = 1;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        add(detailedViewLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridx = 1;
        add(printReportLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25,25,25,25);
        add(exitButton,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(25,25,25,25);
        add(reportButton,mpCons);

        //endregion

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(prodPage).setEnabled(true);
                dispose();
            }
        });

        printReportLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO: Print report
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        detailedViewLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(invoiceItems != null){
                    if(invoiceItems.size() != 0){
                        if(detailedViewLabel.getText() == "Detailed View"){
                            detailedViewLabel.setText("Simple View");
                            headerModel.setColumnIdentifiers(detailedHeaders);
                            resultTable = new JTable(headerModel) {
                                @Override
                                public boolean isCellEditable(int row, int column) {
                                    //all cells false
                                    return false;
                                }
                            };
                            DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
                            model.setRowCount(0);
                            for (DBEntity entity : invoiceItems) {
                                InvoiceItem currentInvoiceItem = (InvoiceItem) entity;
                                Invoice currentInvoice = (Invoice) client.findEntity("Invoice","invoiceNum",Integer.toString(currentInvoiceItem.getInvoiceNum()));
                                headerModel.addRow(new Object[] {currentInvoice.getInvoiceNum(), item.getProductCode(),item.getName(),currentInvoiceItem.getItemQuantity(),(item.getUnitPrice() * currentInvoiceItem.getItemQuantity()),currentInvoice.getBillingDate()});
                            }
                        }
                        else{
                            detailedViewLabel.setText("Detailed View");
                            headerModel.setColumnIdentifiers(headers);
                            resultTable = new JTable(headerModel) {
                                @Override
                                public boolean isCellEditable(int row, int column) {
                                    //all cells false
                                    return false;
                                }
                            };
                            DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
                            model.setRowCount(0);
                            headerModel.addRow(new Object[] {item.getProductCode(),item.getName(),quantitySum,totalSales});
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"The report must be generated before a detailed view can be shown.","Cannot generate detailed report", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(startDateField.getDate() != null){

                        String startDate = null;
                        String endDate = null;
                        Date enteredDate = null;

                        enteredDate = startDateField.getDate();
                        Date currentDate = new Date();
                        if(enteredDate.after(currentDate)){
                            throw new FutureDateException("Start date cannot be after today.");
                        }
                        startDate = dateFormat.format(startDateField.getDate());

                        if(endDateField.getDate() != null){
                            if(!startDateField.getDate().after(endDateField.getDate())){
                                enteredDate = endDateField.getDate();
                                if(enteredDate.after(currentDate)){
                                    throw new FutureDateException("End date cannot be after today.");
                                }
                                endDate = dateFormat.format(endDateField.getDate());
                            }
                            else{
                                throw new FutureDateException("Start date cannot be after end date.");
                            }

                        }

                        invoices = client.reportInvoiceList(startDate,endDate);
                        if(invoices.size() == 0){
                            JOptionPane.showMessageDialog(new JFrame(),"No invoices found for selected dates", "No invoice records",JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        invoiceItems = new ArrayList<>();
                        Invoice currentInvoice;
                        for(DBEntity inv : invoices){
                            currentInvoice = (Invoice) inv;
                            System.out.println("Current Invoice: " + currentInvoice);
                            if(client.getSpecificInvoiceReport("InvoiceItem","invoiceNum", Integer.toString(currentInvoice.getInvoiceNum()), "productCode", item.getProductCode()) != null){
                                invoiceItems.add(client.getSpecificInvoiceReport("InvoiceItem","invoiceNum", Integer.toString(currentInvoice.getInvoiceNum()), "productCode", item.getProductCode()));
                            }
                        }
                        if(invoiceItems.size() == 0){
                            JOptionPane.showMessageDialog(new JFrame(),"No product records were found for the selected dates", "No product records",JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }

                        quantitySum = 0;
                        totalSales = 0;
                        InvoiceItem currentItem;
                        for(DBEntity invoiceItem : invoiceItems){
                            currentItem = (InvoiceItem) invoiceItem;
                            quantitySum+= currentItem.getItemQuantity();
                            totalSales+= (currentItem.getItemQuantity() * item.getUnitPrice());
                        }
                        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
                        model.setRowCount(0);
                        headerModel.addRow(new Object[] {item.getProductCode(),item.getName(),quantitySum,totalSales});

                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"To generate a report for one day, start date field requires a date. For a range, fill the start date and end date field","Cannot generate report", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(FutureDateException ex){
                    JOptionPane.showMessageDialog(new JFrame(),ex.getMessage(),"Date in the Future Error", JOptionPane.ERROR_MESSAGE);
                    logger.error(ex.toString());
                }
            }
        });

        refresh();

    }

    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }
}
