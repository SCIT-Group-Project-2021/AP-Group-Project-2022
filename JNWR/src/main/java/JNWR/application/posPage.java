package JNWR.application;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

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
        endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.X_AXIS));

        JPanel itemPanel = createJPanel();
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(round);
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

        frame.add(menuBar, "h 50!, north");
        frame.add(sidePanelUpper,"cell 0 0 1 2,grow");
        frame.add(mainPanel, "cell 1 0 1 3,grow");
        frame.add(endPanel, "cell 2 0 1 3,grow");
        frame.add(sidePanelLower, "newline,cell 0 2 1 1,grow");
        

        frame.repaint();
        frame.setSize(1820,980);   
        
    }
    
}
    

