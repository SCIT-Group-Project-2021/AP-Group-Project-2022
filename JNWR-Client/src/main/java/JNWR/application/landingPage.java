package JNWR.application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh;

import JNWR.application.utilities.*;

public class landingPage extends JFrame implements defaultPanelAccessories{

    public landingPage() {

        RoundedBorder round = new RoundedBorder(25);

        //region Base Frame Setup
        setSize(1280, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1040,600));
        setLayout(new GridBagLayout());
        //endregion

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        //region Side Bar
        JPanel sideBar = createJPanel(0,80,720);
        sideBar.setBackground(Color.decode("#212020"));
        sideBar.setLayout(new GridBagLayout());
        //endregion

        //region Main Panel
        //JPanel mainPanel = createJPanel(0);
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#F9F9F9"));
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
        
        JButton icon = iconButton(sideBarX,sideBarY,"src/main/resources/JWR-Icons/logo.png");
        icon.setBorder(round);
        icon.setText("");

        JButton dashBoardButton = iconButton(sideBarX,sideBarY,"src/main/resources/JWR-Icons/White/icons8-dashboard-100.png");
        dashBoardButton.setBorder(round);
        dashBoardButton.setText("");

        JButton posButton = iconButton(sideBarX,sideBarY,"src/main/resources/JWR-Icons/White/icons8-create-order-100.png");
        posButton.setBorder(round);
        posButton.setText("");

        JButton customerButton = iconButton(sideBarX,sideBarY, "src/main/resources/JWR-Icons/White/icons8-teamwork-100.png");
        customerButton.setBorder(round);


        JButton inventoryButton = iconButton(sideBarX,sideBarY, "src/main/resources/JWR-Icons/White/icons8-glyph-100.png");
        inventoryButton.setBorder(round);
        inventoryButton.setText("");

        JButton reportButton = iconButton(sideBarX,sideBarY,"src/main/resources/JWR-Icons/White/icons8-futures-100.png");
        reportButton.setBorder(round);
        reportButton.setText("");

        JButton settingButton = iconButton(sideBarX,sideBarY,"src/main/resources/JWR-Icons/White/icons8-adjust-100.png");
        settingButton.setBorder(round);
        settingButton.setText("");

        //endregion

        //region SideBar.Add

        mpCons.insets = new Insets(25, 10, 10, 10);

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
        mpCons.insets = new Insets(10, 10, 10, 10);
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
        sideBar.add(reportButton,mpCons);

        mpCons.gridy++;
        mpCons.weighty = 1;
        sideBar.add(Box.createGlue(),mpCons);

        mpCons.gridy++;
        mpCons.weighty = 0;
        mpCons.insets = new Insets(10, 10, 25, 10);
        sideBar.add(settingButton,mpCons);


        //endregion


        //region Buttons
        dashBoardButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mpCons.fill = GridBagConstraints.BOTH;
                        mpCons.weightx = 1;
                        mpCons.weighty = 1;
                        mainPanel.add(new dashboardPage(),mpCons);
                        refresh();

                    }
                }
        );


        posButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mpCons.fill = GridBagConstraints.BOTH;
                        mpCons.weightx = 1;
                        mpCons.weighty = 1;
                        mainPanel.add(new custPage(),mpCons);
                        refresh();

                    }
                }
        );

        customerButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mpCons.fill = GridBagConstraints.BOTH;
                    mpCons.weightx = 1;
                    mpCons.weighty = 1;
                    mainPanel.add(new custPage(),mpCons);
                    refresh();
                
                }
            }
        );
        //endregion

        //region Product
        inventoryButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mpCons.fill = GridBagConstraints.BOTH;
                    mpCons.weightx = 1;
                    mpCons.weighty = 1;
                    mainPanel.add(new prodPage(),mpCons);
                    refresh();
                
                }
            }
        );

        reportButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mpCons.fill = GridBagConstraints.BOTH;
                        mpCons.weightx = 1;
                        mpCons.weighty = 1;
                        mainPanel.add(new prodPage(),mpCons);
                        refresh();

                    }
                }
        );
        //endregion

        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        //mainPanel.add(new dashboardPage(),mpCons);
        refresh();

    }

    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }

    public static JPanel createJPanel(int rnd) {
    
        PanelRound newJPanel = new PanelRound();
        newJPanel.setRound(rnd);
        newJPanel.setSize(250, 250);
        newJPanel.setVisible(true); 

        return newJPanel;
    
    }

    public static JPanel createJPanel(int rnd,int fixedWidth, int fixedHeight) {
    
        PanelRound newJPanel = new PanelRound() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(fixedWidth, fixedHeight);
            }
        };
        newJPanel.setRound(rnd);
        newJPanel.setSize(250, 250);
        newJPanel.setVisible(true);
    
        return newJPanel;
    
    }

    public static JLabel defaultLabel(String text) {
    
        JLabel newJLabel = new JLabel(text);
        newJLabel.setSize(200, 25);
        newJLabel.setVisible(true);
    
        return newJLabel;
    
    }

    public static JLabel defaultLabel(int w,int h, String text) {
    
        JLabel newJLabel = new JLabel("Default Label");
        newJLabel.setSize(w, h);
        newJLabel.setVisible(true);

        return newJLabel;
    
    }
    
    public static JButton iconButton() {
    
        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(175, 25);
        newJButton.setVisible(true);
        newJButton.setUI(new StyledButtonUI());
    
        return newJButton;
    
    }

    public static JButton iconButton(int w,int h, String src) {
        Icon icon = new ImageIcon(src);
        Image img = ((ImageIcon) icon).getImage() ;
        Image newimg = img.getScaledInstance( 55, 55,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        JButton newJButton = new JButton(icon);
        //JButton newJButton = new JButton("Default Button");
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        //newJButton.setUI(new StyledButtonUI());
        newJButton.setBorderPainted(false);
        newJButton.setContentAreaFilled(false);
        newJButton.setFocusPainted(false);
        newJButton.setOpaque(false);
        newJButton.setBorder(null);
        newJButton.setBorderPainted(false);
        return newJButton;
    
    }
    
    public static JTextField defaultTextField() {
    
        JTextField newJTextField = new JTextField();
        newJTextField.setSize(175, 25);
        newJTextField.setVisible(true);
    
        return newJTextField;
    
    }

    public static JTextField defaultTextField(int w,int h) {
    
        JTextField newJTextField = new JTextField();
        newJTextField.setSize(w, h);
        newJTextField.setVisible(true);
    
        return newJTextField;
    
    }
    
}
