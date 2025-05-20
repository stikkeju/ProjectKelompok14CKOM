import contentPanel.DataPelangganPanel;
import contentPanel.DataTransaksiPanel;
import contentPanel.HomePanel;
import contentPanel.RiwayatTransaksiPanel;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel panelSidebar;
    private JPanel panelHeader;
    private JPanel panelContent;
    private boolean isSidebarVisible = false;

    public Dashboard(){
        setTitle("Aplikasi Kelompok 1");
        setSize(1280, 720);
        setMinimumSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //Layout
        setLayout(new BorderLayout());

        //Header
        panelHeader = new JPanel();
        panelHeader.setLayout(new BorderLayout());
        panelHeader.setBorder(BorderFactory.createTitledBorder("Header"));
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
        panelSidebar.setBorder(BorderFactory.createTitledBorder("Sidebar"));
        panelSidebar.setPreferredSize(new Dimension(256,0));

        //Sidebar Button
        JButton firstButton = new JButton("Home");
        firstButton.addActionListener(e -> setPanelContent(new HomePanel()));
        firstButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, firstButton.getPreferredSize().height));
        panelSidebar.add(firstButton);

        panelSidebar.add(Box.createVerticalStrut(5));

        JButton secondButton = new JButton("Data Pelanggan");
        secondButton.addActionListener(e -> setPanelContent(new DataPelangganPanel()));
        secondButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, secondButton.getPreferredSize().height));
        panelSidebar.add(secondButton);

        panelSidebar.add(Box.createVerticalStrut(5));

        JButton thirdButton = new JButton("Data Transaksi");
        thirdButton.addActionListener(e -> setPanelContent(new DataTransaksiPanel()));
        thirdButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirdButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, thirdButton.getPreferredSize().height));
        panelSidebar.add(thirdButton);

        panelSidebar.add(Box.createVerticalStrut(5));

        JButton fourthButton = new JButton("Riwayat Transaksi");
        fourthButton.addActionListener(e -> setPanelContent(new RiwayatTransaksiPanel()));
        fourthButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        fourthButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, fourthButton.getPreferredSize().height));
        panelSidebar.add(fourthButton);

        panelSidebar.add(Box.createVerticalGlue());

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, logoutButton.getPreferredSize().height));
        logoutButton.addActionListener(e -> System.exit(0));
        panelSidebar.add(logoutButton);


        //Content
        panelContent = new JPanel(new BorderLayout());
        panelContent.setBorder(BorderFactory.createTitledBorder("Content"));
        setPanelContent(new HomePanel());


        add(panelHeader, BorderLayout.NORTH);
        add(panelSidebar, BorderLayout.WEST);
        add(panelContent, BorderLayout.CENTER);


        setVisible(true);
        panelSidebar.setVisible(isSidebarVisible);

    }

    private void toggleSidebar(){
        isSidebarVisible = !isSidebarVisible;
        panelSidebar.setVisible(isSidebarVisible);
        panelSidebar.revalidate();
        repaint();
    }

    private void setPanelContent(JPanel newContent){
        panelContent.removeAll();
        panelContent.setBorder(BorderFactory.createTitledBorder(newContent.getClass().getSimpleName()));
        panelContent.add(newContent, BorderLayout.CENTER);
        panelContent.revalidate();
        panelContent.repaint();
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
