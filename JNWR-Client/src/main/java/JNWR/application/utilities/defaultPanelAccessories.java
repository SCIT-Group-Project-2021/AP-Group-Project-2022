package JNWR.application.utilities;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public interface defaultPanelAccessories {
    Font heading1 = new Font("Outfit", Font.BOLD, 40);
    Font heading2 = new Font("Outfit", Font.BOLD, 30);
    Font heading3 = new Font("Outfit", Font.BOLD, 24);
    //Font subHeading = new Font("Outfit",Medium,24);
    Font medText = new Font("Outfit", Font.PLAIN, 22);
    Font smText = new Font("Outfit", Font.PLAIN, 18);

    public static class RoundedBorder implements Border {
        
        private int radius;
        
        public RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
            
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
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
 
    public class JIconToggleButton extends JToggleButton {

        Icon primaryIcon = getIcon();
        Icon secondaryIcon = getIcon();

        public Icon getPrimaryIcon() {

            return primaryIcon;
            
        }

        public void setPrimaryIcon(Icon icon) {

            this.primaryIcon = icon;
      
        }

        public Icon getSecondaryIcon() {

            return secondaryIcon;
            
        }

        public void setSecondaryIcon(Icon icon) {

            this.secondaryIcon = icon;
            
        }

        public JIconToggleButton (Icon icon, Icon icon2) {
            this.setPrimaryIcon(icon);
            this.setSecondaryIcon(icon2);
        }

        public void paintComponent(Graphics g) {
            Icon icon;
            
            if(isSelected()) {
                icon = getSecondaryIcon();
                //setOpaque(true);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(Color.white);
                g.fillRoundRect(0, 0, getSize().width, getSize().height, 25, 25);
            }else {
                icon = getPrimaryIcon();
                //setOpaque(false);  
            }
            

            setIcon(icon);

            super.paintComponent(g);
            
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




    }

    public static String getCurrentTime() {
        //TODO: Get current time function
        String time = "16:35";
        return time;
    }

    public static String getTodayDate() {
        //TODO: Get month and day
        String dateString = "9 November";
        /*
        Date = getDate();
        String dateString = "";
        dateString = getDay().toString() + " " + getMonth().toString();
         */
        return dateString;
    }

    public static String getCurrentUser() {
        String empName = "Cassie C.";
        //TODO: Get current user's name
        return empName;
    }

    public static JButton iconButton(int w,int h, String src) {
        
        Image img = new ImageIcon(src).getImage().getScaledInstance(w,h, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        JButton newJButton = new JButton(icon);
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        //newJButton.setUI(new StyledButtonUI());
        newJButton.setContentAreaFilled(false);
        newJButton.setFocusPainted(false);
        newJButton.setOpaque(false);
        newJButton.setBorder(null);
        newJButton.setBorderPainted(false);
        return newJButton;
    
    }

    public static JButton iconButton(int w,int h,int imgW, int imgH, String src) {
        
        Image img = new ImageIcon(src).getImage().getScaledInstance(imgW,imgH, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        JButton newJButton = new JButton(icon);
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        //newJButton.setUI(new StyledButtonUI());
        newJButton.setContentAreaFilled(false);
        newJButton.setFocusPainted(false);
        newJButton.setOpaque(false);
        newJButton.setBorder(null);
        newJButton.setBorderPainted(false);
        return newJButton;
    
    }

    public static JToggleButton iconToggleButton(int w,int h, String src, String src2) {
        
        Image img = new ImageIcon(src).getImage().getScaledInstance(w,h, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        Image img2 = new ImageIcon(src2).getImage().getScaledInstance(w,h, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(img2);
        JToggleButton newJButton = new JIconToggleButton(icon,icon2);
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        //newJButton.setUI(new StyledButtonUI());
        newJButton.setOpaque(false);
        newJButton.setContentAreaFilled(false);
        newJButton.setFocusPainted(false);
        newJButton.setBorder(null);
        newJButton.setBorderPainted(false);
        return newJButton;
    
    }

    public static JToggleButton iconToggleButton(int w,int h,int imgW, int imgH, String src, String src2) {
        
        Image img = new ImageIcon(src).getImage().getScaledInstance(imgW,imgH, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        Image img2 = new ImageIcon(src2).getImage().getScaledInstance(imgW,imgH, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(img2);
        JToggleButton newJButton = new JIconToggleButton(icon,icon2);
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        //newJButton.setUI(new StyledButtonUI());
        newJButton.setOpaque(false);
        newJButton.setContentAreaFilled(false);
        newJButton.setFocusPainted(false);
        newJButton.setBorder(null);
        newJButton.setBorderPainted(false);
        return newJButton;
    
    }

    public static JButton defaultButton() {

        JButton newJButton = new JButton("Default Button");
        newJButton.setSize(175, 25);
        newJButton.setVisible(true);
        newJButton.setUI(new StyledButtonUI());

        return newJButton;

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
}
