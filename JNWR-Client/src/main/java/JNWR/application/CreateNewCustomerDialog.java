package JNWR.application;

import Entity.Customer;
import Entity.Inventory;
import Entity.Invoice;
import JNWR.Domain.Client;
import JNWR.application.customException.FutureDateException;
import JNWR.application.utilities.*;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class CreateNewCustomerDialog extends JFrame implements defaultPanelAccessories {
    Client client;
    int frameWidth = 1100;
    int frameHeight = 720;

    JLabel headingLabel;
    JLabel descriptionHeading;
    JLabel firstNameLabel;
    CustomRoundTextField firstNameField;
    CustomRoundTextField customerIdField;
    JLabel lastNameLabel;
    CustomRoundTextField lastNameField;
    JLabel telephoneNumberLabel;
    JFormattedTextField telephoneNumberField;
    JLabel emailLabel;
    CustomRoundTextField emailField;
    JLabel inventoryHeading;
    JLabel customerIdLabel;
    JLabel membershipDate;
    JXDatePicker membershipDateField;
    JLabel dobLabel;
    JXDatePicker dobDateField;
    JLabel expiryDateLabel;
    CustomRoundTextField expiryDateField; //TODO: Add dollar sign icon in textfield
    JButton cancelButton;
    JButton addCustomerButton;
    DateFormat dateFormat;
    MaskFormatter fmt;

    public CreateNewCustomerDialog(Client client, CustPage custPage) {

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

        JPanel personalInfoSection = defaultPanelAccessories.createJPanel(0, 350, 80);
        personalInfoSection.setLayout(new GridBagLayout());
        personalInfoSection.setBorder(round);
        personalInfoSection.setBackground(Color.white);

        JPanel membershipInfoSection = defaultPanelAccessories.createJPanel(0, 350, 220);
        membershipInfoSection.setLayout(new GridBagLayout());
        membershipInfoSection.setBorder(round);
        membershipInfoSection.setBackground(Color.white);

        JPanel buttonBox = defaultPanelAccessories.createJPanel(0, 350, 80);
        buttonBox.setLayout(new GridBagLayout());
        buttonBox.setBackground(Color.white);

        //endregion

        //region GUI Elements' Initialization
        headingLabel = new JLabel("Add New Customer");
        headingLabel.setFont(heading2);

        descriptionHeading = new JLabel("Personal Information");
        descriptionHeading.setFont(heading3);

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(smText);
        firstNameField = new CustomRoundTextField();
        firstNameField.setFont(miniText);

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(smText);
        lastNameField = new CustomRoundTextField();
        lastNameField.setFont(miniText);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setFont(smText);
        UIManager.put(CalendarHeaderHandler.uiControllerID, SpinningCalendarHeaderHandler.class.getName());
        dobDateField = new JXDatePicker();
        dobDateField.getMonthView().setZoomable(true);

        telephoneNumberLabel = new JLabel("Telephone Number");
        telephoneNumberLabel.setFont(smText);


        try {
            fmt = new MaskFormatter("1-###-###-####");
            telephoneNumberField = new JFormattedTextField(fmt);
            telephoneNumberField.setFont(miniText);
        } catch (ParseException e) {
           logger.error(e.toString());
        }


        emailLabel = new JLabel("Email");
        emailLabel.setFont(smText);

        emailField = new CustomRoundTextField();
        emailField.setFont(miniText);

        inventoryHeading = new JLabel("Membership Information");
        inventoryHeading.setFont(heading3);

        customerIdLabel = new JLabel("Customer ID");
        customerIdLabel.setFont(smText);
        customerIdField = new CustomRoundTextField();
        customerIdField.setFont(miniText);
        customerIdField.setEditable(false);
        customerIdField.setText(Integer.toString(getCustomerId()));


        membershipDate = new JLabel("Date of Membership");
        membershipDate.setFont(smText);

        UIManager.put(CalendarHeaderHandler.uiControllerID, SpinningCalendarHeaderHandler.class.getName());
        membershipDateField = new JXDatePicker();
        membershipDateField.getMonthView().setZoomable(true);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        membershipDateField.setFormats(dateFormat);
        membershipDateField.setDate(new Date());

        //membershipDateField.setFont(miniText);

        expiryDateLabel = new JLabel("Date of Membership Expiry");
        expiryDateLabel.setFont(smText);

        expiryDateField = new CustomRoundTextField();
        expiryDateField.setEditable(false);
        expiryDateField.setFont(miniText);
        expiryDateField.setText(getExpiryDate());

        cancelButton = defaultPanelAccessories.defaultButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(smText);
        cancelButton.setPreferredSize(new Dimension(20,50));
        cancelButton.setBorder(round);

        addCustomerButton = defaultPanelAccessories.defaultButton();
        addCustomerButton.setText("Create Customer");
        addCustomerButton.setFont(smText);
        addCustomerButton.setForeground(Color.white);
        addCustomerButton.setBackground(Color.decode("#005DFB"));
        addCustomerButton.setPreferredSize(new Dimension(20,50));
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
        mpCons.weighty = 1;
        mpCons.gridy = 2;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,20);
        mainSection.add(personalInfoSection,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,0);
        personalInfoSection.add(firstNameLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(firstNameField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(lastNameLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(lastNameField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(telephoneNumberLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(telephoneNumberField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(emailLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(emailField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(dobLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(dobDateField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        personalInfoSection.add(Box.createGlue(),mpCons);

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
        mainSection.add(membershipInfoSection,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,5,0);
        membershipInfoSection.add(customerIdLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(customerIdField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(membershipDate,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(membershipDateField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(expiryDateLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(expiryDateField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(Box.createGlue(),mpCons);

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
        buttonBox.add(addCustomerButton,mpCons);

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
                SwingUtilities.getWindowAncestor(custPage).setEnabled(true);
                dispose();
            }
        });

        membershipDateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ENGLISH);
                LocalDate date = LocalDate.parse(dateFormat.format(membershipDateField.getDate()), dtf);
                LocalDate dateEnd = date.withYear(date.getYear()+ 1);
                String DOME = dtf.format(dateEnd);
                expiryDateField.setText(DOME);
            }
        });





        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(firstNameField.getText() != "" && lastNameField.getText() != "" && telephoneNumberField.getText() != "" && membershipDateField.getDate() != null){
                        Customer cust = null;

                        Date enteredDate = null;
                        enteredDate = membershipDateField.getDate();
                        Date currentDate = new Date();
                        if(enteredDate.after(currentDate)){
                           throw new FutureDateException("Membership date cannot be after today.");
                        }
                        String telephoneNumber;
                        telephoneNumber = telephoneNumberField.getText().replaceAll("\\D", "");
                        String email = "";
                        String membershipStartDate = dateFormat.format(membershipDateField.getDate());
                        String dob = null;

                        if(dobDateField.getDate() != null){
                            enteredDate = dobDateField.getDate();
                            if(enteredDate.after(currentDate)){
                                throw new FutureDateException("Date of birth cannot be after today.");
                            }
                            else{
                                dob = dateFormat.format(dobDateField.getDate());
                            }
                        }

                        cust = new Customer(firstNameField.getText(),lastNameField.getText(),dob,telephoneNumber,emailField.getText(),membershipStartDate,expiryDateField.getText());

                        client.addEntity(cust);
                        custPage.updateTable();
                        SwingUtilities.getWindowAncestor(custPage).setEnabled(true);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"Please fill the required text fields","Cannot create new product", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(FutureDateException ex){
                    JOptionPane.showMessageDialog(new JFrame(),ex.getMessage(),"Date in the Future Error", JOptionPane.ERROR_MESSAGE);
                    logger.error(ex.toString());
                }
            }
        });


    }
    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }

    public String getExpiryDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(dateFormat.format(membershipDateField.getDate()), dtf);
        LocalDate dateEnd = date.withYear(date.getYear()+ 1);
        String DOME = dtf.format(dateEnd);
        return DOME;
    }

    private Integer getCustomerId() {

        Integer customerId = 100000;
        try {
            Customer entity = (Customer) client.findLastEntity("Customer","customerId");
            customerId = entity.getCustomerId()+ 1;
        } catch (NullPointerException e) {
            logger.warn("No customers in database throwing Up default start customer ID");
        }

        return customerId;
    }
}
