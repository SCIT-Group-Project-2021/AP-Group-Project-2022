package JNWR.application;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

import JNWR.application.utilities.defaultPanelAccessories;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets; 

public class posPage implements defaultPanelAccessories{

    public posPage() {

        RoundedBorder round = new RoundedBorder(25);

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(5, 5, 5, 5);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        //region Base Frame Setup
        JFrame frame = createBFrame(1840,1000);
        frame.setMinimumSize(new Dimension(1040,600));
        frame.setLayout(new GridBagLayout());
        //endregion

        //region Header Bar
        JPanel menuBar = createJPanel(25);
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
        //endregion

        //region Center Panel
        JPanel mainPanel = createJPanel(25);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(round);
        mainPanel.setLayout(new GridBagLayout());
        //endregion

        //region invoicePanel
        JPanel sidePanelUpper = createJPanel(25);
        sidePanelUpper.setBackground(Color.WHITE);
        sidePanelUpper.setBorder(round);
        //sidePanelUpper.setLayout();
        //endregion
        
        //region sumPanel
        JPanel sidePanelLower = createJPanel(25);
        sidePanelLower.setBackground(Color.WHITE);
        sidePanelLower.setBorder(round);
        //sidePanelLower.setLayout(new BoxLayout(sidePanelLower, BoxLayout.X_AXIS));
        //endregion

        //region CheckoutPanel
        JPanel endPanel = createJPanel(25);
        endPanel.setBackground(Color.WHITE);
        endPanel.setBorder(round);
        //endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS));
        //endregion

        //region Item Panel
        JPanel itemPanel = createJPanel(25);
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(round);
        //itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        //endregion

        //region
        mpCons.weightx = .5;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.gridwidth = 3;
        mpCons.ipady = 60;
        frame.add(menuBar,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        mpCons.gridheight = 1;
        mpCons.gridwidth = 1;
        mpCons.ipady = 100;
        mpCons.ipadx = 40;
        frame.add(sidePanelUpper,mpCons);

        mpCons.weightx = 1;
        mpCons.gridx++;
        mpCons.gridheight = 2;
        mpCons.ipady = 100;
        mpCons.ipadx = 200;
        frame.add(mainPanel, mpCons);

        mpCons.weightx =  1;
        mpCons.gridx++;
        mpCons.gridheight = 2;
        mpCons.ipady = 100;
        mpCons.ipadx = 30;
        frame.add(endPanel,mpCons);

        mpCons.weighty = 0;
        mpCons.gridy = 2;
        mpCons.gridx = 0;
        mpCons.gridheight = 1;
        mpCons.ipady = 120;
        mpCons.ipadx = 60;
        frame.add(sidePanelLower, mpCons);
        //endregion

        //region checkout
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 1;
        cons.weighty = 0;
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 5, 5, 5);
        cons.anchor = GridBagConstraints.NORTH;

        endPanel.setLayout(new GridBagLayout());

        JButton voidButton = defaultButton();
        voidButton.setBorder(round);
        voidButton.setText("Void");

        JButton holdButton = defaultButton();
        holdButton.setBorder(round);
        holdButton.setText("Hold");

        JButton checkOutButton = defaultButton();
        checkOutButton.setBorder(round);
        checkOutButton.setText("Checkout");
        JButton Button4 = defaultButton();
        Button4.setBorder(round);

        cons.ipady = 10;
        endPanel.add(Button4,cons);
        cons.gridy++;

        cons.weighty = 1;
        endPanel.add(Box.createGlue(),cons);
        cons.gridy++;

        cons.weighty = 0;
        cons.ipady = 5;
        endPanel.add(voidButton,cons);
        cons.gridy++;
        
        cons.ipady = 5;
        endPanel.add(holdButton,cons);
        cons.gridy++;

        cons.ipady = 10;
        endPanel.add(checkOutButton,cons);
        cons.gridy++;

        //endregion

        //region Center Panel
        JPanel centralItemPanel = createJPanel(25);
        centralItemPanel.setBackground(Color.WHITE);
        centralItemPanel.setBorder(round);
        centralItemPanel.setLayout(new GridBagLayout());
        //endregion

        //region Center Panel
        JPanel centralButtonPanel = createJPanel(25);
        centralButtonPanel.setBackground(Color.WHITE);
        centralButtonPanel.setBorder(round);
        centralButtonPanel.setLayout(new GridBagLayout());
        //endregion

        //region Center Panel
        JPanel customerItemPanel = createJPanel(25);
        customerItemPanel.setBackground(Color.WHITE);
        customerItemPanel.setBorder(round);
        customerItemPanel.setLayout(new GridBagLayout());
        //endregion

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.gridheight = 1;
        mpCons.gridwidth = 1;
        mpCons.ipady = 30;
        mpCons.ipadx = 100;
        mainPanel.add(centralItemPanel,mpCons);

        mpCons.ipady = 100;
        mpCons.gridy++;
        mainPanel.add(centralButtonPanel,mpCons);

        mpCons.ipady = 40;
        mpCons.gridy++;
        mainPanel.add(customerItemPanel,mpCons);

        frame.repaint();
        frame.setSize(1820,980);   

        
        
    }
    
    public static JFrame createBFrame() {

        JFrame frame = new JFrame();
        //frame.setLayout(mig);
        frame.setSize(1200, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    
        return frame;
    
    }

    public static JFrame createBFrame(int w,int h) {

        JFrame frame = new JFrame();
        //frame.setLayout(mig);
        frame.setSize(w, h);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    
        return frame;
    
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
    
    public static JButton defaultButton() {
    
        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(175, 25);
        newJButton.setVisible(true);
        newJButton.setUI(new StyledButtonUI());
    
        return newJButton;
    
    }

    public static JButton defaultButton(int w,int h) {
    
        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        newJButton.setUI(new StyledButtonUI());
    
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
    

