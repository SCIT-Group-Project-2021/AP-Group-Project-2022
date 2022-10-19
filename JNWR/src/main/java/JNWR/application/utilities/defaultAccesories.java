package JNWR.application.utilities;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.miginfocom.swing.MigLayout;


public class defaultAccesories {

    private static final Logger logger = LogManager.getLogger(defaultAccesories.class);

    MigLayout mig = new MigLayout("fill,gap 5,insets 5");

    public JFrame createBFrame() {

        JFrame frame = new JFrame();
        frame.setLayout(mig);
        frame.setSize(1200, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        logger.trace("created Default Frame");
    
        return frame;
    
    }

    public JFrame createBFrame(int w,int h) {

        JFrame frame = new JFrame();
        frame.setLayout(mig);
        frame.setSize(w, h);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        logger.trace("created with customized size Default Frame");
    
        return frame;
    
    }
    
    public JPanel createJPanel() {
    
        JPanel newJPanel = new JPanel();
        newJPanel.setSize(250, 250);
        newJPanel.setVisible(true);
        logger.trace("created Default Panel");
    
        return newJPanel;
    
    }

    public JPanel createJPanel(int w,int h) {
    
        JPanel newJPanel = new JPanel();
        newJPanel.setSize(w, h);
        newJPanel.setVisible(true);
        logger.trace("created with customized size Default Frame");
    
        return newJPanel;
    
    }
    
    public JLabel defaultLabel(String text) {
    
        JLabel newJLabel = new JLabel(text);
        newJLabel.setSize(200, 25);
        newJLabel.setVisible(true);
        logger.trace("created Default Label");
    
        return newJLabel;
    
    }

    public JLabel defaultLabel(int w,int h, String text) {
    
        JLabel newJLabel = new JLabel("Default Label");
        newJLabel.setSize(w, h);
        newJLabel.setVisible(true);
        logger.trace("created Default Label");

        return newJLabel;
    
    }
    
    public JButton defaultButton() {
    
        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(175, 25);
        newJButton.setVisible(true);
        logger.trace("created Default Button");
    
        return newJButton;
    
    }

    public JButton defaultButton(int w,int h) {
    
        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        logger.trace("created Default Button");
    
        return newJButton;
    
    }
    
    public JTextField defaultTextField() {
    
        JTextField newJTextField = new JTextField();
        newJTextField.setSize(175, 25);
        newJTextField.setVisible(true);
        logger.trace("created Default TextBox");
    
        return newJTextField;
    
    }

    public JTextField defaultTextField(int w,int h) {
    
        JTextField newJTextField = new JTextField();
        newJTextField.setSize(w, h);
        newJTextField.setVisible(true);
        logger.trace("created Default TextBox");
    
        return newJTextField;
    
    }

    public static class RoundedBorder implements Border {
        
        private int radius;
        
        public RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            logger.trace("Inset Border for rounded edge");
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
            
        }

        @Override
        public boolean isBorderOpaque() {
            logger.trace("Sets Border to be opaque");
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            logger.trace("Draws rounded edge");
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
            
        }
                        
    }
    
}
