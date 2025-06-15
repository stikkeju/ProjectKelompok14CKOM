package dashboard;

import contentPanel.*;
import login.Login;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel panelSidebar, panelHeader, contentPanel;
    private JButton firstButton, secondButton, thirdButton, fourthButton, fifthButton, logoutButton;
    private boolean isSidebarVisible = true;

    public Dashboard(){
        setTitle("Aplikasi Kelompok 1");
        setSize(1280, 720);
        setMinimumSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setGlobalButtonUI();

        //Layout
        setLayout(new BorderLayout());

        //Header
        panelHeader = new JPanel();
        panelHeader.setLayout(new BorderLayout());
        panelHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //panelHeader.setBorder(BorderFactory.createTitledBorder("Header"));
        panelHeader.setPreferredSize(new Dimension(0, 72));


        JButton toggleButton = new JButton("☰");
        toggleButton.addActionListener(e -> toggleSidebar());
        panelHeader.add(toggleButton, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0,0));
        panelHeader.add(headerLabel, BorderLayout.CENTER);

        //Sidebar
        panelSidebar = new JPanel();
        panelSidebar.setLayout(new BoxLayout(panelSidebar, BoxLayout.Y_AXIS));
        panelSidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //panelSidebar.setBorder(BorderFactory.createTitledBorder("Sidebar"));
        panelSidebar.setPreferredSize(new Dimension(256,0));

        //Sidebar Button
        firstButton = new JButton("Home");
        firstButton.addActionListener(e -> setContentPanel(new HomePanel()));
        firstButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, firstButton.getPreferredSize().height));
        panelSidebar.add(firstButton);

        panelSidebar.add(Box.createVerticalStrut(5));

        secondButton = new JButton("Data Pelanggan");
        secondButton.addActionListener(e -> setContentPanel(new DataPelangganPanel()));
        secondButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, secondButton.getPreferredSize().height));
        panelSidebar.add(secondButton);

        panelSidebar.add(Box.createVerticalStrut(5));

        thirdButton = new JButton("Data Transaksi");
        thirdButton.addActionListener(e -> setContentPanel(new DataTransaksiPanel()));
        thirdButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirdButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, thirdButton.getPreferredSize().height));
        panelSidebar.add(thirdButton);

        panelSidebar.add(Box.createVerticalStrut(5));

        fourthButton = new JButton("Riwayat Transaksi");
        fourthButton.addActionListener(e -> setContentPanel(new RiwayatTransaksiPanel()));
        fourthButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        fourthButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, fourthButton.getPreferredSize().height));
        panelSidebar.add(fourthButton);

        panelSidebar.add(Box.createVerticalGlue());

        logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, logoutButton.getPreferredSize().height));
        logoutButton.addActionListener(e -> {
            dispose();
            new Login();
        });
        panelSidebar.add(logoutButton);


        //Content
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //contentPanel.setBorder(BorderFactory.createTitledBorder("Content"));
        setContentPanel(new HomePanel());


        add(panelHeader, BorderLayout.NORTH);
        add(panelSidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);


        setVisible(true);
        panelSidebar.setVisible(isSidebarVisible);

    }

    private void setGlobalButtonUI(){
        UIManager.put("Button.background", new Color(40, 40, 40));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("SansSerif", Font.PLAIN, 14));
        UIManager.put("Button.focusPainted", false);
        UIManager.put("Button.preferredSize", new Dimension(100, 40));
    }

    private void toggleSidebar(){
        isSidebarVisible = !isSidebarVisible;
        panelSidebar.setVisible(isSidebarVisible);
        panelSidebar.revalidate();
        repaint();
    }

    private void setContentPanel(JPanel newContent){
        contentPanel.removeAll();
        //contentPanel.setBorder(BorderFactory.createTitledBorder(newContent.getClass().getSimpleName()));
        contentPanel.add(newContent, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.d3d", "false");
        System.setProperty("sun.java2d.noddraw", "true");
        new Dashboard();
    }
}
