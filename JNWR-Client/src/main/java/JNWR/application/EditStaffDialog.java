package JNWR.application;

import Entity.Staff;
import JNWR.Domain.Client;
import JNWR.application.customException.InvalidFieldInputException;
import JNWR.application.customException.InvalidIdNumberException;
import JNWR.application.utilities.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;

public class EditStaffDialog extends JFrame implements defaultPanelAccessories {
    Client client;
    int frameWidth = 1100;
    int frameHeight = 600;

    JLabel headingLabel;
    JLabel descriptionHeading;
    JLabel firstNameLabel;
    CustomRoundTextField firstNameField;
    CustomRoundTextField staffIdField;
    JLabel lastNameLabel;
    CustomRoundTextField lastNameField;
    JLabel telephoneNumberLabel;
    JFormattedTextField telephoneNumberField;
    JLabel emailLabel;
    CustomRoundTextField emailField;
    JLabel inventoryHeading;
    JLabel staffIdLabel;
    JLabel departmentLabel;
    JLabel dobLabel;
    JLabel empTypeLabel;
    JButton cancelButton;
    JButton editEmployeeButton;
    MaskFormatter fmt;

    CustomRoundComboBox<String> departmentCombo;
    CustomRoundComboBox<String> empTypeCombo;

    String department[] = {"Select a department","Accounting and Sales", "Inventory","Management"};

    String empType[] = {"Select a employee type","Manager", "Supervisor", "Line worker"};

    public EditStaffDialog(Client client, StaffPage staffPage, Staff staff) {

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
        headingLabel = new JLabel("Edit Staff");
        headingLabel.setFont(heading2);

        descriptionHeading = new JLabel("Personal Information");
        descriptionHeading.setFont(heading3);

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(smText);
        firstNameField = new CustomRoundTextField();
        firstNameField.setFont(miniText);
        firstNameField.setText(staff.getfName());

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(smText);
        lastNameField = new CustomRoundTextField();
        lastNameField.setFont(miniText);
        lastNameField.setText(staff.getlName());

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setFont(smText);

        telephoneNumberLabel = new JLabel("Telephone Number");
        telephoneNumberLabel.setFont(smText);



        try {
            fmt = new MaskFormatter("1-###-###-####");
            telephoneNumberField = new JFormattedTextField(fmt);
            telephoneNumberField.setFont(miniText);
            telephoneNumberField.setText(staff.getPhoneNum());
        } catch (ParseException e) {
            logger.error(e.toString());
        }

        inventoryHeading = new JLabel("Department Information");
        inventoryHeading.setFont(heading3);

        staffIdLabel = new JLabel("Staff ID");
        staffIdLabel.setFont(smText);
        staffIdField = new CustomRoundTextField();
        staffIdField.setFont(miniText);
        staffIdField.setText(Integer.toString(staff.getIdNum()));
        staffIdField.setEditable(false);


        departmentLabel = new JLabel("Department");
        departmentLabel.setFont(smText);

        departmentCombo = new CustomRoundComboBox();
        departmentCombo.sendFont(miniText);
        departmentCombo.setModel(new javax.swing.DefaultComboBoxModel(department));
        departmentCombo.setSelectedItem(staff.getDepartmentCode());

        //membershipDateField.setFont(miniText);

        empTypeLabel = new JLabel("Employee Type");
        empTypeLabel.setFont(smText);

        empTypeCombo = new CustomRoundComboBox();
        empTypeCombo.sendFont(miniText);
        empTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(empType));
        empTypeCombo.setSelectedItem(staff.getEmployeeType());

        cancelButton = defaultPanelAccessories.defaultButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(smText);
        cancelButton.setPreferredSize(new Dimension(20,50));
        cancelButton.setBorder(round);

        editEmployeeButton = defaultPanelAccessories.defaultButton();
        editEmployeeButton.setText("Edit Employee");
        editEmployeeButton.setFont(smText);
        editEmployeeButton.setForeground(Color.white);
        editEmployeeButton.setBackground(Color.decode("#005DFB"));
        editEmployeeButton.setPreferredSize(new Dimension(20,50));
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

        mpCons.gridwidth = 2;
        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(0,0,10,0);
        mainSection.add(headingLabel,mpCons);

        mpCons.gridwidth = 1;
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
        membershipInfoSection.add(staffIdLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(staffIdField,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(departmentLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(departmentCombo,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(empTypeLabel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy++;
        mpCons.gridx = 0;
        membershipInfoSection.add(empTypeCombo,mpCons);

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
        buttonBox.add(editEmployeeButton,mpCons);

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
                SwingUtilities.getWindowAncestor(staffPage).setEnabled(true);
                dispose();
            }
        });




        editEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstNameField.getText() != "" && lastNameField.getText() != "" && !telephoneNumberField.getText().equals("1-   -   -    ") && staffIdField.getText() !="" && departmentCombo.getSelectedIndex() != 0 && empTypeCombo.getSelectedIndex() != 0){
                    try{
                        if(!firstNameField.getText().matches("^[a-zA-Z]+$") || !lastNameField.getText().matches("^[a-zA-Z]+$")){
                            throw new InvalidFieldInputException("Staff member's name should only contain letters");
                        }

                        Staff employee = null;
                        String telephoneNumber;
                        String departmentName = departmentCombo.getSelectedItem().toString();
                        String departmentID = "";
                        switch(departmentName) {
                            case "Accounting and Sales":
                                departmentID = "ACS";
                                break;
                            case "Inventory":
                                departmentID = "INV";
                                break;
                            case "Management":
                                departmentID = "MGT";
                                break;
                        }

                        telephoneNumber = telephoneNumberField.getText().replaceAll("\\D", "");

                        employee = new Staff(Integer.parseInt(staffIdField.getText()), firstNameField.getText(),lastNameField.getText(),telephoneNumber,empTypeCombo.getSelectedItem().toString(), departmentID);

                        client.alterEntity(employee, employee.getIdNum());
                        staffPage.updateTable();
                        JOptionPane.showMessageDialog(new JFrame(), "Staff member edit completed successfully!");
                        SwingUtilities.getWindowAncestor(staffPage).setEnabled(true);
                        dispose();
                    }
                    catch (InvalidFieldInputException ex) {
                        JOptionPane.showMessageDialog(new JFrame(),ex.getMessage(),"Cannot edit staff member", JOptionPane.ERROR_MESSAGE);
                        logger.warn(ex.toString());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"Please fill the required text fields","Cannot edit staff member", JOptionPane.ERROR_MESSAGE);
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
