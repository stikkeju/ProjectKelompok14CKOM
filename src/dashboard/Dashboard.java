package dashboard;

import contentPanel.*;
import login.Login;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel sidebarPanel, headerPanel, contentPanel;
    private JLabel headerLabel;
    private JButton toggleButton, homeButton, datapelangganButton, datatransaksiButton, riwayatButton, logoutButton;
    private boolean isSidebarVisible = true;

    public Dashboard(){
        setTitle("Aplikasi Billing WiFi - Kelompok 1 4C KOM");
        setSize(1280, 720);
        setMinimumSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setGlobalButtonUI();

        //Layout
        setLayout(new BorderLayout());

        //Header
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //panelHeader.setBorder(BorderFactory.createTitledBorder("Header"));
        headerPanel.setPreferredSize(new Dimension(0, 72));


        toggleButton = new JButton("☰");
        toggleButton.addActionListener(e -> toggleSidebar());
        headerPanel.add(toggleButton, BorderLayout.WEST);

        headerLabel = new JLabel("Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0,0));
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        //Sidebar
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //panelSidebar.setBorder(BorderFactory.createTitledBorder("Sidebar"));
        sidebarPanel.setPreferredSize(new Dimension(256,0));

        //Sidebar Button
        homeButton = new JButton("Home");
        homeButton.addActionListener(e -> setContentPanel(new HomePanel()));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, homeButton.getPreferredSize().height));
        sidebarPanel.add(homeButton);

        sidebarPanel.add(Box.createVerticalStrut(5));

        datapelangganButton = new JButton("Data Pelanggan");
        datapelangganButton.addActionListener(e -> setContentPanel(new DataPelangganPanel()));
        datapelangganButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        datapelangganButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, datapelangganButton.getPreferredSize().height));
        sidebarPanel.add(datapelangganButton);

        sidebarPanel.add(Box.createVerticalStrut(5));

        datatransaksiButton = new JButton("Data Transaksi");
        datatransaksiButton.addActionListener(e -> setContentPanel(new DataTransaksiPanel()));
        datatransaksiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        datatransaksiButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, datatransaksiButton.getPreferredSize().height));
        sidebarPanel.add(datatransaksiButton);

        sidebarPanel.add(Box.createVerticalStrut(5));

        riwayatButton = new JButton("Riwayat Transaksi");
        riwayatButton.addActionListener(e -> setContentPanel(new RiwayatTransaksiPanel()));
        riwayatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        riwayatButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, riwayatButton.getPreferredSize().height));
        sidebarPanel.add(riwayatButton);

        sidebarPanel.add(Box.createVerticalGlue());

        logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, logoutButton.getPreferredSize().height));
        logoutButton.addActionListener(e -> {
            dispose();
            new Login();
        });
        sidebarPanel.add(logoutButton);


        //Content
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //contentPanel.setBorder(BorderFactory.createTitledBorder("Content"));
        setContentPanel(new HomePanel());


        add(headerPanel, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);


        setVisible(true);
        sidebarPanel.setVisible(isSidebarVisible);

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
        sidebarPanel.setVisible(isSidebarVisible);
        sidebarPanel.revalidate();
        repaint();
    }

    private void setContentPanel(JPanel newContent){
        contentPanel.removeAll();
        String title = "Dashboard";
        if (newContent instanceof HomePanel){
            title = "Dashboard - Home";
        } else if (newContent instanceof DataPelangganPanel) {
            title = "Dashboard - Data Pelanggan";
        } else if (newContent instanceof DataTransaksiPanel) {
            title = "Dashboard - Data Transaksi";
        } else if (newContent instanceof RiwayatTransaksiPanel) {
            title = "Dashboard - Riwayat Transaksi";
        }
        headerLabel.setText(title);
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
