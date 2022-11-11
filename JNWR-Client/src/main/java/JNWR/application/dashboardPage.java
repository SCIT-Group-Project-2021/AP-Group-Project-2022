package JNWR.application;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import JNWR.Domain.Client;
import JNWR.application.utilities.*;

public class dashboardPage extends JPanel implements defaultPanelAccessories {

    DefaultTableModel headerModel = new DefaultTableModel();
        
    String headers[] = { "Time Management", "Clocked Users"};

    Client client;

    dashboardPage(Client client) {

        this.client = client;

        RoundedBorder round = new RoundedBorder(25);

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        //region Main Panel
        setBackground(Color.RED);
        setLayout(new GridBagLayout());
        //endregion

        //region Top Bar
        JPanel topBar = defaultPanelAccessories.createJPanel(0,80,120);
        //topBar.setBackground(Color.YELLOW);
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

        //region main section
        JPanel mainSection = defaultPanelAccessories.createJPanel(0,350,80);
        mainSection.setLayout(new GridBagLayout());

        //region userBox
        JPanel userBox = defaultPanelAccessories.createJPanel(0,350,350);
        userBox.setOpaque(false);
        userBox.setLayout(new GridBagLayout());

        headerModel.setColumnIdentifiers(headers);

        JTable userTable = new JTable(headerModel);
        
        JScrollPane userTableScroll = new JScrollPane(userTable){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
        };

        userTableScroll.setBorder(round);
        //userTableScroll.setBackground(Color.GREEN);
        //endregion

        //region buttonBox
        JPanel buttonBox = defaultPanelAccessories.createJPanel(0,350,350);
        buttonBox.setOpaque(false);
        buttonBox.setLayout(new GridBagLayout());

        final int buttonX = 100;
        final int buttonY = buttonX;

        JButton clockOutButton = defaultPanelAccessories.defaultButton();
        clockOutButton.setBorder(round);
        Image clockOutButtonImg = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-clock-100.png").getImage().getScaledInstance(buttonX,buttonY, Image.SCALE_SMOOTH);
        ImageIcon clockOutButtonIcon = new ImageIcon(clockOutButtonImg);
        clockOutButton.setIcon(clockOutButtonIcon);
        clockOutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clockOutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clockOutButton.setText("Clock Out");
        clockOutButton.setBackground(Color.white);

        JButton posButton = defaultPanelAccessories.defaultButton();
        posButton.setBorder(round);
        Image posButtonImg = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-create-order-100.png").getImage().getScaledInstance(buttonX,buttonY, Image.SCALE_SMOOTH);
        ImageIcon posButtonIcon = new ImageIcon(posButtonImg);
        posButton.setIcon(posButtonIcon);
        posButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        posButton.setHorizontalTextPosition(SwingConstants.CENTER);
        posButton.setText("POS");
        posButton.setBackground(Color.white);

        JButton inventoryButton = defaultPanelAccessories.defaultButton();
        inventoryButton.setBorder(round);
        Image inventoryButtonImg = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-glyph-100.png").getImage().getScaledInstance(buttonX,buttonY, Image.SCALE_SMOOTH);
        ImageIcon inventoryButtonIcon = new ImageIcon(inventoryButtonImg);
        inventoryButton.setIcon(inventoryButtonIcon);
        inventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
        inventoryButton.setText("Inventory");
        inventoryButton.setBackground(Color.white);

        JButton invoiceButton = defaultPanelAccessories.defaultButton();
        invoiceButton.setBorder(round);
        Image invoiceButtonnImg = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-bill-100.png").getImage().getScaledInstance(buttonX,buttonY, Image.SCALE_SMOOTH);
        ImageIcon invoiceButtonIcon = new ImageIcon(invoiceButtonnImg);
        invoiceButton.setIcon(invoiceButtonIcon);
        invoiceButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        invoiceButton.setHorizontalTextPosition(SwingConstants.CENTER);
        invoiceButton.setText("Invoices");
        invoiceButton.setBackground(Color.white);

        //endregion

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

        //region mainSection.Add

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25, 25, 25, 25);
        mainSection.add(userBox, mpCons);

        //region userBox.Add

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        userBox.add(userTableScroll, mpCons);

        //endregion

        mpCons.weightx = .5;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        mpCons.insets = new Insets(25, 25, 180, 25);
        mainSection.add(buttonBox, mpCons);
        mpCons.insets = new Insets(25, 25, 25, 25);

        //region buttonBox.Add
        
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        buttonBox.add(clockOutButton, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        buttonBox.add(posButton, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        buttonBox.add(invoiceButton, mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 1;
        mpCons.gridx = 1;
        buttonBox.add(inventoryButton, mpCons);

        //endregion

        //endregion

        //endregion
        
        //region Buttons
        posButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        landingPage parent = (landingPage) SwingUtilities.getAncestorOfClass(JFrame.class, getParent());
                        parent.replaceWindow(parent.mainPanel, parent.pos);
                        parent.posButton.setSelected(true);
                        
                    }
                }
        );

        invoiceButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        landingPage parent = (landingPage) SwingUtilities.getAncestorOfClass(JFrame.class, getParent());
                        //TODO Create Invoice Page
                        //parent.replaceWindow(parent.mainPanel, null);
                        parent.invoiceButton.setSelected(true);
                        
                    }
                }
        );

        inventoryButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        landingPage parent = (landingPage) SwingUtilities.getAncestorOfClass(JFrame.class, getParent());
                        parent.replaceWindow(parent.mainPanel, parent.inventory);
                        parent.inventoryButton.setSelected(true);
                        
                    }
                }
        );
    
    }
}
