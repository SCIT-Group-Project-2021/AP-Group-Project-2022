package JNWR.application.utilities;

import Entity.DBEntity;
import Entity.InvenCategory;
import Entity.Inventory;
import JNWR.ClientApplication;
import JNWR.Domain.Client;
import JNWR.application.LandingPage;
import JNWR.application.ProdPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JPanel;

public interface defaultPanelAccessories {

    public static final Logger logger = LogManager.getLogger(ClientApplication.class);
    Font outfitBold = null;
    Font heading1 = new Font("Outfit", Font.BOLD, 40);
    Font heading2 = new Font("Outfit", Font.BOLD, 30);
    Font heading3 = new Font("Outfit", Font.BOLD, 24);
    //Font subHeading = new Font("Outfit",Medium,24);
    Font medText = new Font("Outfit", Font.PLAIN, 22);
    Font smText = new Font("Outfit", Font.PLAIN, 18);
    Font miniText = new Font("Outfit", Font.PLAIN, 14);


    public static class RoundedBorder implements Border {
        
        private int radius;
        private Color color = Color.decode("#CED4DA");
        
        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public RoundedBorder(Color color, int radius) {
            this.color = color;
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
            g2.setColor(color);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x,y,width-1,height-1,radius,radius);

        }
        /*
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }*/
                        
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
        Color newBackGround = Color.white;

        boolean hasBackground;

        public Icon getPrimaryIcon() {

            return primaryIcon;
            
        }

        public void setPrimaryIcon(Icon icon) {

            this.primaryIcon = icon;
      
        }

        public void setPrimaryIcon(Icon icon, Color bg, boolean hasBackGround) {

            this.primaryIcon = icon;

        }

        public Icon getSecondaryIcon() {

            return secondaryIcon;
            
        }

        public void setSecondaryIcon(Icon icon) {

            this.secondaryIcon = icon;
            
        }

        public void setNewBackGround(Color bg) {

            this.newBackGround = bg;

        }

        public Color getNewBackGround() {

            return this.newBackGround;

        }

        public boolean hasBackground() {
            return hasBackground;
        }

        public void setHasBackground(boolean hasBackground) {
            this.hasBackground = hasBackground;
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
                g.setColor(getNewBackGround());
                g.fillRoundRect(0, 0, getSize().width, getSize().height, 25, 25);
            }else {
                icon = getPrimaryIcon();
                if(hasBackground()){
                    g.setColor(getNewBackGround());
                    g.fillRoundRect(0, 0, getSize().width, getSize().height, 25, 25);
                }
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
        LocalTime dateTime = LocalTime.now();
        //LocalDateTime date = LocalDateTime.parse(LocalDateTime.now().toString(), dtf);
        String time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm ")).toString();
        return time;
    }

    public static String getTodayDate() {
        LocalDate dateTime = LocalDate.now();
        String dateString = dateTime.format(DateTimeFormatter.ofPattern("MMMM d ")).toString();
        /*
        Date = getDate();
        String dateString = "";
        dateString = getDay().toString() + " " + getMonth().toString();
         */
        return dateString;
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
        newJButton.setPreferredSize(new Dimension(w,h));
        newJButton.setVisible(true);
        //newJButton.setUI(new StyledButtonUI());
        newJButton.setContentAreaFilled(false);
        newJButton.setFocusPainted(false);
        newJButton.setOpaque(false);
        newJButton.setBorder(null);
        newJButton.setBorderPainted(false);
        return newJButton;
    
    }

    public static JIconToggleButton iconToggleButton(int w,int h, String src, String src2) {
        
        Image img = new ImageIcon(src).getImage().getScaledInstance(w,h, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        Image img2 = new ImageIcon(src2).getImage().getScaledInstance(w,h, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(img2);
        JIconToggleButton newJButton = new JIconToggleButton(icon,icon2);
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

    public static JIconToggleButton iconToggleButton(int w,int h,int imgW, int imgH, String src, String src2) {
        
        Image img = new ImageIcon(src).getImage().getScaledInstance(imgW,imgH, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        Image img2 = new ImageIcon(src2).getImage().getScaledInstance(imgW,imgH, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(img2);
        JIconToggleButton newJButton = new JIconToggleButton(icon,icon2);
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

        JButton newJButton = new JButton();
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

    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
        // got from
        // https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
    }

    public static class JFilterButton extends JButton{

        public JFilterButton(int w,int h,int imgW, int imgH, String src, String btnText){
            Image img = new ImageIcon(src).getImage().getScaledInstance(imgW,imgH, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            setIcon(icon);
            setText(btnText);
            setPreferredSize(new Dimension(w,h));
            setVisible(true);
            setContentAreaFilled(false);
            setFocusPainted(false);
            //setOpaque(false);
            setBorder(null);
            //setBorderPainted(false);
            setHorizontalAlignment(SwingConstants.LEFT);
            setFont(miniText);
        }
        public void filterInventoryTable(String filterValue, ProdPage prodPage, Client client, DefaultTableModel headerModel){
            InvenCategory category = (InvenCategory) client.findEntity("InvenCategory","name",filterValue);
            String categoryId = category.getCategoryID();
            ArrayList<DBEntity> list = client.getSpecificList("Inventory","categoryID",categoryId);
            headerModel.setRowCount(0);
            for (DBEntity entity : list) {
                Inventory inven = (Inventory) entity;
                headerModel.addRow(new Object[] {inven.getProductCode(),inven.getCategoryID(),inven.getName(),inven.getShortDescrip(),inven.getStock(),inven.getUnitPrice()});

            }
        }
    }


}
