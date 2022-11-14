package JNWR.application;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import Entity.Staff;
import JNWR.Domain.Client;
import JNWR.application.utilities.CustomTextField;
import JNWR.application.utilities.defaultPanelAccessories;
import JNWR.application.utilities.defaultPanelAccessories.FrameDragListener;
import JNWR.application.utilities.defaultPanelAccessories.PanelRound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage extends JFrame{

    private static final Logger logger = LogManager.getLogger(Client.class);


    public LoginPage() {

        //region Base Frame Setup
        this.setUndecorated(true);
        this.setShape(new RoundRectangle2D.Double(0,0,500,720,30,30));
        setSize(500, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //endregion

        // #region Enables Undecorated Frame drag movement
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        // #endregion

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(40, 40, 40, 40);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion



        PanelRound mainPanel = new PanelRound();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setRound(25);
        mainPanel.setPreferredSize(new Dimension(300,500));
        mainPanel.setBackground(Color.white);

        Image logoImg = new ImageIcon("src/main/resources/JWR-Icons/logo-black.png").getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(logoImg);
        JLabel logo = new JLabel(logoIcon);
        JLabel title = new JLabel("<html><center>Jan's Wholesale <br> and Retail</center></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        title.setFont(defaultPanelAccessories.heading3);

        CustomTextField staffID = new CustomTextField();
        staffID.setPreferredSize(new Dimension(200,40));
        staffID.setLabelText("Staff ID");

        JButton loginButton = defaultPanelAccessories.defaultButton();
        loginButton.setText("Login");
        loginButton.setForeground(Color.white);
        loginButton.setBackground(Color.decode("#005DFB"));
        loginButton.setPreferredSize(new Dimension(20,40));

        JButton exitButton = defaultPanelAccessories.defaultButton();
        exitButton.setText("Exit");
        exitButton.setForeground(Color.black);
        exitButton.setOpaque(false);
        exitButton.setPreferredSize(new Dimension(20,40));

       
        mpCons.weightx = 1;
        mpCons.weighty = 1;
        add(mainPanel,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25, 25, 25, 25);
        mainPanel.add(logo,mpCons);

        mpCons.gridy++;
        mpCons.insets = new Insets(25, 25, 25, 25);
        mainPanel.add(title,mpCons);

        mpCons.weightx = 1;
        mpCons.gridy++;
        mpCons.insets = new Insets(25, 50, 25, 50);
        mainPanel.add(staffID,mpCons);

        mpCons.gridy++;
        mpCons.insets = new Insets(25,70, 25, 70);
        mainPanel.add(loginButton,mpCons);

        mpCons.gridy++;
        mpCons.insets = new Insets(0,120, 25, 120);
        mainPanel.add(exitButton,mpCons);

        refresh();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = new Client();
                    if (rootPaneCheckingEnabled) {
                        
                        Staff staff = (Staff) client.findEntity("Staff", "idnum", staffID.getText());
                        if(staff != null) {
                            logger.info(staff);
                            new LandingPage(staff, client);
                            setVisible(false);
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"Invalid User","ERROR", JOptionPane.ERROR_MESSAGE);
                             logger.error("Invalid User");
                        }
                        
                        
                    }
                } catch (ConnectException ex) {
                    JOptionPane.showMessageDialog(new JFrame(),"Server Unavailable","ERROR", JOptionPane.ERROR_MESSAGE);
                    logger.error(ex.toString());
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(new JFrame(),"Server Unavailable","ERROR", JOptionPane.ERROR_MESSAGE);
                    logger.error(ex.toString());
            }
        }});

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });   

    }

    private void refresh() {
        repaint();
        setSize(new Dimension(getWidth()+1,getHeight()+1));
        setSize(new Dimension(getWidth()-1,getHeight()-1));
    }
    
    
}
