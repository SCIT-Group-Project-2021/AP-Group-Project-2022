package JNWR.application.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class defaultAccesories {

    private static final Logger logger = LogManager.getLogger(defaultAccesories.class);

    //MigLayout mig = new MigLayout("fill,gap 5,insets 5");

    public class createBFrame{

        public JFrame createBFrame() {

            JFrame frame = new JFrame();
            //frame.setLayout(mig);
            frame.setSize(1200, 500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            logger.trace("created Default Frame");
        
            return frame;
        
        }

        public static JFrame createBFrame(int w,int h) {

            JFrame frame = new JFrame();
            //frame.setLayout(mig);
            frame.setSize(w, h);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            logger.trace("created with customized size Default Frame");
        
            return frame;
        
        }
        

    }
    
    public class createJPanel {
    
        public JPanel createJPanel(int rnd) {
    
        PanelRound newJPanel = new PanelRound();
        newJPanel.setRound(rnd);
        newJPanel.setSize(250, 250);
        newJPanel.setVisible(true);
        
        return newJPanel;
    
    }

        public JPanel createJPanel(int rnd,int fixedWidth, int fixedHeight) {
    
        PanelRound newJPanel = new PanelRound() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(fixedWidth, fixedHeight);
            }
        };
        newJPanel.setRound(rnd);
        newJPanel.setSize(250, 250);
        newJPanel.setVisible(true);
        logger.trace("created Default Panel");
    
        return newJPanel;
    
    }

        
    }

    public class defaultLabel{

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
        
        
    }

    public class defaultButton{
        public JButton defaultButton() {
    
            JButton newJButton = new JButton("Default Button");
            newJButton.setSize(175, 25);
            newJButton.setVisible(true);
            logger.trace("created Default Button");
            newJButton.setUI(new StyledButtonUI());
        
            return newJButton;
        
        }
    
        public JButton defaultButton(int w,int h) {
        
            JButton newJButton = new JButton("Default Button");
            newJButton.setSize(w, h);
            newJButton.setVisible(true);
            logger.trace("created Default Button");
            newJButton.setUI(new StyledButtonUI());
        
            return newJButton;
        
        }
        
    }

    public class defaultTextField{
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
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x,y,width-1,height-1,radius,radius);
            
        }
                        
    }

    public class PanelRound extends JPanel {

        public int getRoundTopLeft() {
            return roundTopLeft;
        }
    
        public void setRoundTopLeft(int roundTopLeft) {
            this.roundTopLeft = roundTopLeft;
            repaint();
        }
    
        public int getRoundTopRight() {
            return roundTopRight;
        }
    
        public void setRoundTopRight(int roundTopRight) {
            this.roundTopRight = roundTopRight;
            repaint();
        }
    
        public int getRoundBottomLeft() {
            return roundBottomLeft;
        }
    
        public void setRoundBottomLeft(int roundBottomLeft) {
            this.roundBottomLeft = roundBottomLeft;
            repaint();
        }
    
        public int getRoundBottomRight() {
            return roundBottomRight;
        }
    
        public void setRoundBottomRight(int roundBottomRight) {
            this.roundBottomRight = roundBottomRight;
            repaint();
        }

        public void setRound(int round) {
            setRoundBottomRight(round);
            setRoundBottomLeft(round);
            setRoundTopLeft(round);
            setRoundTopRight(round);
            repaint();
        }
    
        private int roundTopLeft = 0;
        private int roundTopRight = 0;
        private int roundBottomLeft = 0;
        private int roundBottomRight = 0;
    
        public PanelRound() {
            setOpaque(false);
        }
    
        @Override
        protected void paintComponent(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            Area area = new Area(createRoundTopLeft());
            if (roundTopRight > 0) {
                area.intersect(new Area(createRoundTopRight()));
            }
            if (roundBottomLeft > 0) {
                area.intersect(new Area(createRoundBottomLeft()));
            }
            if (roundBottomRight > 0) {
                area.intersect(new Area(createRoundBottomRight()));
            }
            g2.fill(area);
            g2.dispose();
            super.paintComponent(grphcs);
        }
    
        private Shape createRoundTopLeft() {
            int width = getWidth();
            int height = getHeight();
            int roundX = Math.min(width, roundTopLeft);
            int roundY = Math.min(height, roundTopLeft);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
            return area;
        }
    
        private Shape createRoundTopRight() {
            int width = getWidth();
            int height = getHeight();
            int roundX = Math.min(width, roundTopRight);
            int roundY = Math.min(height, roundTopRight);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
            return area;
        }
    
        private Shape createRoundBottomLeft() {
            int width = getWidth();
            int height = getHeight();
            int roundX = Math.min(width, roundBottomLeft);
            int roundY = Math.min(height, roundBottomLeft);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
            return area;
        }
    
        private Shape createRoundBottomRight() {
            int width = getWidth();
            int height = getHeight();
            int roundX = Math.min(width, roundBottomRight);
            int roundY = Math.min(height, roundBottomRight);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
            return area;
        }
    }
    
    public class StyledButtonUI extends BasicButtonUI {

        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorder(new EmptyBorder(5, 15, 5, 15));
        }
    
        @Override
        public void paint (Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            //paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            paintBackground(g, b, b.getModel().isPressed() ? c.getBackground().darker() : c.getBackground());
           
            super.paint(g, c);
        }
    
        private void paintBackground (Graphics g, JComponent c, Color f) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(f);
            g.fillRoundRect(0, 0, size.width, size.height, 25, 25);
        }

        private void paintBackground (Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 25, 25);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset -1, 25, 25);
        }
    }

}
