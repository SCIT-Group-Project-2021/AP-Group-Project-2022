package JNWR.application;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Entity.Customer;
import Entity.DBEntity;
import Entity.Inventory;
import JNWR.Domain.Client;
import JNWR.application.utilities.*;

public class dashboardPage extends JPanel implements defaultPanelAccessories {
    dashboardPage() {
        RoundedBorder round = new RoundedBorder(25);

        //region GridBagConstraints
        GridBagConstraints mpCons = new GridBagConstraints();
        mpCons.fill = GridBagConstraints.BOTH;
        mpCons.insets = new Insets(0, 0, 0, 0);
        mpCons.anchor = GridBagConstraints.NORTH;
        //endregion

        //region Main Panel
        setBackground(Color.RED);
        setLayout(new GridBagLayout());
        //endregion

        //region Top Bar
        JPanel topBar = createJPanel(0,80,120);
        topBar.setBackground(Color.YELLOW);
        topBar.setLayout(new GridBagLayout());

        //region date/time bar
        JPanel dateTimePanel = createJPanel(50,400,80);
        dateTimePanel.setBackground(Color.WHITE);
        dateTimePanel.setLayout(new GridBagLayout());


        Image calendarImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-desk-calendar-100.png").getImage().getScaledInstance(55,55, Image.SCALE_SMOOTH);
        ImageIcon calendarIcon = new ImageIcon(calendarImage);
        JLabel calendar = new JLabel(calendarIcon);
        JLabel date = new JLabel(getTodayDate());
        date.setFont(new Font("Outfit", Font.BOLD, 24));

        Image clockImage = new ImageIcon("src/main/resources/JWR-Icons/Black/icons8-clock-100-2.png").getImage().getScaledInstance(55,55, Image.SCALE_SMOOTH);
        ImageIcon clockIcon = new ImageIcon(clockImage);
        JLabel clock = new JLabel(clockIcon);
        JLabel time = new JLabel(getCurrentTime());
        time.setFont(new Font("Outfit", Font.BOLD, 24));
        //endregion

        //region Log Out Label & Button
        JLabel empName = new JLabel();
        empName.setText(getCurrentUser());
        empName.setFont(new Font("Outfit", Font.BOLD, 24));
        JButton logOut = iconButton(30,30,"src/main/resources/JWR-Icons/Black/icons8-logout-rounded-down-100.png");
        //endregion

        //endregion

        //region main section
        JPanel mainSection = createJPanel(0,350,80);
        mainSection.setBackground(Color.GREEN);
        mainSection.setLayout(new GridBagLayout());
        //endregion


        //region Frame.Add

        mpCons.ipadx = 0;
        mpCons.ipady = 0;

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        add(topBar,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 1;
        mpCons.gridy = 1;
        mpCons.gridx = 0;
        add(mainSection,mpCons);
        //endregion

        //region Top Bar.Add
        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(10, 30, 10, 10);
        topBar.add(dateTimePanel,mpCons);

        mpCons.weightx = 1;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        topBar.add(Box.createGlue(),mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        topBar.add(empName,mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        topBar.add(logOut,mpCons);
        //endRegion

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx = 0;
        mpCons.insets = new Insets(25, 25, 25, 5);
        dateTimePanel.add(calendar, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(25, 5, 25, 5);
        dateTimePanel.add(date, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 1;
        mpCons.gridy = 0;
        mpCons.gridx++;
        //TODO: Fix separator
        JSeparator line = new JSeparator(SwingConstants.VERTICAL);
        line.setPreferredSize(new Dimension(25,25));
        dateTimePanel.add(line);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        dateTimePanel.add(clock, mpCons);

        mpCons.weightx = 0;
        mpCons.weighty = 0;
        mpCons.gridy = 0;
        mpCons.gridx++;
        mpCons.insets = new Insets(25, 5, 25, 25);
        dateTimePanel.add(time, mpCons);


    }

    private String getCurrentTime() {
        //TODO: Get current time function
        String time = "16:35";
        return time;
    }

    private String getTodayDate() {
        //TODO: Get month and day
        String dateString = "9 November";
        /*
        Date = getDate();
        String dateString = "";
        dateString = getDay().toString() + " " + getMonth().toString();
         */
        return dateString;
    }

    private String getCurrentUser() {
        String empName = "Cassie C.";
        //TODO: Get current user's name
        return empName;
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

    public static JButton iconButton(int w,int h, String src) {
        Icon icon = new ImageIcon(src);
        Image img = ((ImageIcon) icon).getImage() ;
        Image newimg = img.getScaledInstance( 55, 55,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        JButton newJButton = new JButton(icon);
        newJButton.setSize(w, h);
        newJButton.setVisible(true);
        newJButton.setBorderPainted(false);
        newJButton.setContentAreaFilled(false);
        newJButton.setFocusPainted(false);
        newJButton.setOpaque(false);
        newJButton.setBorder(null);
        newJButton.setBorderPainted(false);
        return newJButton;

    }
}
