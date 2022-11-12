package JNWR.application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import JNWR.Domain.Client;
import JNWR.application.utilities.*;

public class landingPage extends JFrame implements defaultPanelAccessories{

    final int bottomOffset = 60;

    public JPanel mainPanel;
    public JPanel dashBoard;
    public JPanel customer;
    public JPanel reportPage;
    public JPanel staff;
    public JPanel inventory;
    public JPanel pos;

    public JToggleButton dashBoardButton;
    public JToggleButton posButton;
    public JToggleButton customerButton;
    public JToggleButton inventoryButton;
    public JToggleButton invoiceButton;
    public JToggleButton settingButton;

    public landingPage() {

        RoundedBorder round = new RoundedBorder(25);

        ButtonGroup sideBarButtons = new ButtonGroup();

        //region Base Frame Setup
        setSize(1280, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1380,820));
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Client client = new Client();
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
        sideBar.setBackground(Color.decode("#212020"));
        sideBar.setLayout(new GridBagLayout());
        //endregion

        //region Main Panel
        //JPanel mainPanel = createJPanel(0);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#f2f3f5"));
        mainPanel.setLayout(new GridBagLayout());
        //endregion

        //region Frame.Add
        mpCons.weightx = 0;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.ipadx = 50;
        mpCons.ipady = 0;
        add(sideBar,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 1;
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

        settingButton = defaultPanelAccessories.iconToggleButton(sideBarX,sideBarY,sideBarImageX,sideBarImageY,"src/main/resources/JWR-Icons/White/icons8-adjust-100.png","src/main/resources/JWR-Icons/Black/icons8-adjust-100.png");
        settingButton.setBorder(round);
        settingButton.setText("");
        sideBarButtons.add(settingButton);

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
        sideBar.add(settingButton,mpCons);


        //endregion

        dashBoard = new dashboardPage(client);
        customer = new custPage(client);
        inventory = new prodPage(client);
        staff = new staffPage(client);
        reportPage = new reportPage(client);
        pos = new posPage(client);

        //region Buttons
        dashBoardButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, dashBoard);

                    }
                }
        );


        posButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, pos);

                    }
                }
        );

        customerButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceWindow(mainPanel, customer);
                
                }
            }
        );
       
        inventoryButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceWindow(mainPanel, inventory);
                
                }
            }
        );

        invoiceButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, reportPage);

                    }
                }
        );

        settingButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceWindow(mainPanel, staff);

                    }
                }
        );
        //endregion

        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.insets = new Insets(0, 0, bottomOffset, 0);
        mainPanel.add(dashBoard,mpCons);
        refresh();
        pack();

    }

    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }

    public void replaceWindow(JPanel mainPanel, JPanel replacPanel) {
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;

        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.insets = new Insets(0, 0, bottomOffset, 0);
        mainPanel.removeAll();
        mainPanel.add(replacPanel,mpCons);
        refresh();
        
    }

}
