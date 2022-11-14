package JNWR.application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Entity.Staff;
import JNWR.Domain.Client;
import JNWR.application.utilities.*;

public class LandingPage extends JFrame implements defaultPanelAccessories{

    final int bottomOffset = 60;

    public JPanel mainPanel;
    public JPanel dashBoard;
    public JPanel customer;
    public JPanel report;
    public JPanel staff;
    public JPanel inventory;
    public JPanel pos;

    Staff employee;

    public CardLayout card;

    Client client;

    public JToggleButton dashBoardButton;
    public JToggleButton posButton;
    public JToggleButton customerButton;
    public JToggleButton inventoryButton;
    public JToggleButton invoiceButton;
    public JToggleButton staffButton;

    public Font outfitBold;

    public Font getOutfitBold() {
        return outfitBold;
    }

    public LandingPage(Staff currenStaff,Client client) {

        setEmployee(currenStaff);
        logger.info(employee);

        RoundedBorder round = new RoundedBorder(25);

        ButtonGroup sideBarButtons = new ButtonGroup();

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.putIfAbsent("Table.alternateRowColor", Color.decode("#e5ebf4"));
        defaults.putIfAbsent("Table.rowColor", Color.decode("#e5ebf4"));

        //region Base Frame Setup
        setSize(1280, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1380,820));
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                client.sendAction("shutDown");
            }
        }, "Shutdown-thread"));
        //endregion

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        //region Side Bar
        JPanel sideBar = defaultPanelAccessories.createJPanel(0,50,720);
        sideBar.setVisible(false);
        JPanel sideBartrigger = defaultPanelAccessories.createJPanel(0,40,720);
        sideBartrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (sideBar.isVisible()) {
                    //sideBar.setVisible(false);
                    //sideBartrigger.setVisible(true);
                }else {
                    sideBar.setVisible(true);
                    sideBartrigger.setVisible(false);
                }
            }   
        });
        
        sideBar.setBackground(Color.decode("#212020"));
        sideBar.setLayout(new GridBagLayout());
        //endregion

        //region Main Panel
        //JPanel mainPanel = createJPanel(0);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#f2f3f5"));
        card = new CardLayout();
        mainPanel.setLayout(card);
        mainPanel.setBackground(Color.RED);
        //endregion

        //region Frame.Add
        mpCons.weightx = 0;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.ipadx = 50;
        mpCons.ipady = 0;
        add(sideBar,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
        mpCons.ipadx = 0;
        mpCons.ipady = 0;
        add(sideBartrigger,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 2;
        mpCons.ipadx = 1000;
        mpCons.ipadx = 0;
        add(mainPanel,mpCons);
        //endregion

        //region Buttons

        final int sideBarX = 25;
        final int sideBarY = 25;
        final int sideBarImageX = 35;
        final int sideBarImageY = 35;
        
        JButton icon = defaultPanelAccessories.iconButton(sideBarX,sideBarY,50,50,"src/main/resources/JWR-Icons/logo.png");
        icon.setBorder(round);
        icon.setText("");
        
        
        dashBoardButton = defaultPanelAccessories.iconToggleButton(sideBarX,sideBarY,sideBarImageX,sideBarImageY,"src/main/resources/JWR-Icons/White/icons8-dashboard-100.png","src/main/resources/JWR-Icons/Black/icons8-dashboard-100.png");
        dashBoardButton.setBorder(round);
        dashBoardButton.setText("");
        dashBoardButton.setSelected(true);
        sideBarButtons.add(dashBoardButton);

        posButton = defaultPanelAccessories.iconToggleButton(sideBarX,sideBarY,sideBarImageX,sideBarImageY,"src/main/resources/JWR-Icons/White/icons8-create-order-100.png","src/main/resources/JWR-Icons/Black/icons8-create-order-100.png");
        posButton.setBorder(round);
        posButton.setText("");
        sideBarButtons.add(posButton);

        customerButton = defaultPanelAccessories.iconToggleButton(sideBarX,sideBarY,sideBarImageX,sideBarImageY, "src/main/resources/JWR-Icons/White/icons8-teamwork-100.png","src/main/resources/JWR-Icons/Black/icons8-teamwork-100.png");
        customerButton.setBorder(round);
        customerButton.setText("");
        sideBarButtons.add(customerButton);


        inventoryButton = defaultPanelAccessories.iconToggleButton(sideBarX,sideBarY,sideBarImageX,sideBarImageY, "src/main/resources/JWR-Icons/White/icons8-glyph-100.png","src/main/resources/JWR-Icons/Black/icons8-glyph-100.png");
        inventoryButton.setBorder(round);
        inventoryButton.setText("");
        sideBarButtons.add(inventoryButton);

        invoiceButton = defaultPanelAccessories.iconToggleButton(sideBarX,sideBarY,sideBarImageX,sideBarImageY,"src/main/resources/JWR-Icons/White/icons8-futures-100.png","src/main/resources/JWR-Icons/Black/icons8-futures-100.png");
        invoiceButton.setBorder(round);
        invoiceButton.setText("");
        sideBarButtons.add(invoiceButton);

        staffButton = defaultPanelAccessories.iconToggleButton(sideBarX,sideBarY,sideBarImageX,sideBarImageY,"src/main/resources/JWR-Icons/White/icons8-name-tag-100.png","src/main/resources/JWR-Icons/Black/icons8-name-tag-100.png");
        staffButton.setBorder(round);
        staffButton.setText("");
        sideBarButtons.add(staffButton);

        //endregion

        //region SideBar.Add

        mpCons.insets = new Insets(25, 10, 5, 10);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.ipady = 0;
        mpCons.ipadx = 0;

        mpCons.gridx = 0;
        mpCons.gridy = 0;

        mpCons.fill = GridBagConstraints.BOTH;

        sideBar.add(icon,mpCons);

        mpCons.gridy++;
        mpCons.weighty = 1;
        mpCons.insets = new Insets(0, 10, 0, 10);
        sideBar.add(Box.createGlue(),mpCons);

        mpCons.gridy++;
        mpCons.weighty = 0;
        sideBar.add(dashBoardButton,mpCons);
        
        mpCons.gridy++;
        sideBar.add(posButton,mpCons);

        mpCons.gridy++;
        sideBar.add(customerButton,mpCons);

        mpCons.gridy++;
        sideBar.add(inventoryButton,mpCons);

        mpCons.gridy++;
        sideBar.add(invoiceButton,mpCons);

        mpCons.gridy++;
        mpCons.weighty = 1;
        sideBar.add(Box.createGlue(),mpCons);

        mpCons.gridy++;
        mpCons.weighty = 0;
        mpCons.insets = new Insets(0, 10, 25, 10);
        sideBar.add(staffButton,mpCons);


        //endregion

        dashBoard = new DashboardPage(client,employee);
        customer = new CustPage(client,employee);
        report = new ReportPage(client,employee);
        staff = new StaffPage(client,employee);
        inventory = new ProdPage(client,employee);
        pos = new PosPage(client,employee);


        //region Buttons
        dashBoardButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, card, "dashBoard");

                    }
                }
        );


        posButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, card, "pos");

                    }
                }
        );

        customerButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceWindow(mainPanel, card, "customer");
                
                }
            }
        );
       
        inventoryButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceWindow(mainPanel, card, "inventory");
                
                }
            }
        );

        invoiceButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, card, "invoice");

                    }
                }
        );

        staffButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, card, "staff");

                    }
                }
        );
        //endregion

        mainPanel.add(dashBoard,"dashBoard");
        mainPanel.add(customer,"customer");
        mainPanel.add(report,"invoice");
        mainPanel.add(staff,"staff");
        mainPanel.add(inventory,"inventory");
        mainPanel.add(pos,"pos");

        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (sideBar.isVisible()) {
                    sideBar.setVisible(false);
                    sideBartrigger.setVisible(true);
                }
            }
        });
        refresh();
        pack();

    }

    private void refresh() {
        revalidate();
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }

    public void replaceWindow(JPanel mainPanel,CardLayout card, String replacPanel) {
       
        card.show(mainPanel, replacPanel);
        //refresh();
        
    }

    public void setEmployee(Staff employee) {
        this.employee = employee;
    }

    public Staff getEmployee() {

        return this.employee;
        
    }

}
