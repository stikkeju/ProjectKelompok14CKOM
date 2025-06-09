package login;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JPanel contentPanel;
    public Login(){
        setTitle("Login Aplikasi Billing WiFi");
        setSize(450, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new GridBagLayout());
        GridBagConstraints gbcLogin = new GridBagConstraints();
        gbcLogin.fill = GridBagConstraints.BOTH;

        contentPanel = new JPanel(new GridBagLayout());
        setPanelContent(new loginPanel(this));

        gbcLogin.gridx = 0; gbcLogin.gridy = 0;
        gbcLogin.weightx = 1; gbcLogin.weighty = 1;
        add(contentPanel, gbcLogin);

        setVisible(true);
    }

    public void setPanelContent(JPanel newContent){
        contentPanel.removeAll();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0; gbcContent.gridy = 0;
        gbcContent.weightx = 1; gbcContent.weighty = 1;
        gbcContent.fill = GridBagConstraints.BOTH;
        contentPanel.add(newContent, gbcContent);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.d3d", "false");
        System.setProperty("sun.java2d.noddraw", "true");
        new Login();
    }
}
