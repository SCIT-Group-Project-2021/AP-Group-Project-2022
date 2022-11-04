package JNWR.application;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets; 

public class posPage extends JNWR.application.utilities.defaultAccesories{

    public posPage() {

        JFrame frame = createBFrame(1840,1000);
        frame.setMinimumSize(new Dimension(1040,600));

        RoundedBorder round = new RoundedBorder(25);

        JPanel menuBar = createJPanel();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));

        JPanel mainPanel = createJPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(round);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel sidePanelUpper = createJPanel();
        sidePanelUpper.setBackground(Color.WHITE);
        sidePanelUpper.setBorder(round);
        sidePanelUpper.setLayout(new BoxLayout(sidePanelUpper, BoxLayout.X_AXIS));

        JPanel sidePanelLower = createJPanel();
        sidePanelLower.setBackground(Color.WHITE);
        sidePanelLower.setBorder(round);
        sidePanelLower.setLayout(new BoxLayout(sidePanelLower, BoxLayout.X_AXIS));

        JPanel endPanel = createJPanel();
        endPanel.setBackground(Color.WHITE);
        endPanel.setBorder(round);
        endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS));

        JPanel itemPanel = createJPanel();
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(round);
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

        frame.add(menuBar, "h 50!, north");
        frame.add(sidePanelUpper,"cell 0 0 1 2,grow");
        frame.add(mainPanel, "cell 1 0 1 3,grow");
        frame.add(endPanel, "cell 2 0 1 3,grow");
        frame.add(sidePanelLower, "newline,cell 0 2 1 1,grow");

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

        cons.ipady = 100;
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

        cons.ipady = 100;
        endPanel.add(checkOutButton,cons);
        cons.gridy++;

        frame.repaint();
        frame.setSize(1820,980);   
        
    }
    
}
    

