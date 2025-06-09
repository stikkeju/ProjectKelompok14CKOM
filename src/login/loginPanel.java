package login;

import dashboard.Dashboard;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class loginPanel extends JPanel {
    private Login loginFrame;
    private JPanel formPanel;
    private JLabel logoLabel, titleLabel, emailLabel, passwordLabel, lupaLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JCheckBox simpanCheck;
    private JButton loginButton, daftarButton, keluarButton;

    public loginPanel(Login loginFrame){
        this.loginFrame = loginFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbcLogin = new GridBagConstraints();
        gbcLogin.insets = new Insets(10,10,10,10);
        gbcLogin.fill = GridBagConstraints.BOTH;


        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        GridBagConstraints gbcFormPanel = new GridBagConstraints();
        gbcFormPanel.insets = new Insets(5,10,5,10);


        //Label logoLabel
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("logo.png"));
        Image imageLogo = logoIcon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
        logoLabel = new JLabel("Aplikasi Billing WiFi", new ImageIcon(imageLogo), JLabel.CENTER);
        logoLabel.setHorizontalTextPosition(JLabel.CENTER);
        logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        logoLabel.setForeground(new Color(60, 100, 100));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 0;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.insets = new Insets(10,10,5,10);
        gbcFormPanel.anchor = GridBagConstraints.CENTER;
        formPanel.add(logoLabel, gbcFormPanel);

        //Label titleLabel
        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 1;
        gbcFormPanel.insets = new Insets(5,10,5,10);
        gbcFormPanel.anchor = GridBagConstraints.WEST;
        formPanel.add(titleLabel, gbcFormPanel);


        //email Label
        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 2;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.anchor = GridBagConstraints.WEST;
        formPanel.add(emailLabel, gbcFormPanel);

        //email/username field
        emailField = new JTextField("Email");
        emailField.setPreferredSize(new Dimension(emailField.getWidth(), 35));
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(emailField, gbcFormPanel);

        //password label
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 4;
        formPanel.add(passwordLabel, gbcFormPanel);

        //password field
        passwordField = new JPasswordField("password");
        passwordField.setPreferredSize(new Dimension(passwordField.getWidth(), 35));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passwordField, gbcFormPanel);

        //Simpan checkBox
        simpanCheck = new JCheckBox("Simpan username");
        simpanCheck.setBackground(Color.WHITE);
        simpanCheck.setFont(new Font("SansSerif", Font.PLAIN, 12));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 6;
        gbcFormPanel.fill = GridBagConstraints.NONE;
        gbcFormPanel.gridwidth = 1;
        formPanel.add(simpanCheck, gbcFormPanel);

        //Lupa password label
        lupaLabel = new JLabel("Lupa password?");
        lupaLabel.setForeground(new Color(0, 0, 238));
        lupaLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lupaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lupaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.setPanelContent(new lupapasswordPanel(loginFrame));
            }
        });

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 6;
        gbcFormPanel.anchor = GridBagConstraints.EAST;
        formPanel.add(lupaLabel, gbcFormPanel);

        //Login button
        loginButton = new JButton("Login");
        setLoginUIButton(loginButton);

        //simpan input pada emailFIeld jika simpanBox dicentang
        Preferences prefs = Preferences.userRoot().node("loginPanel");
        String savedEmail  = prefs.get("savedEmail", "");
        emailField.setText(savedEmail);
        if (!savedEmail.isEmpty()){
            simpanCheck.setSelected(true);
        }

        loginButton.addActionListener(e -> {
            if (simpanCheck.isSelected()){
                prefs.put("savedEmail", emailField.getText());
            } else {
                prefs.remove("savedEmail");
            }
            loginUser();
        });



        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 7;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(loginButton, gbcFormPanel);

        //atau lable
        JLabel atauLabel = new JLabel("atau");
        atauLabel.setForeground(Color.GRAY);
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 8;
        gbcFormPanel.insets = new Insets(15, 5, 15, 5);
        gbcFormPanel.fill = GridBagConstraints.NONE;
        gbcFormPanel.anchor = GridBagConstraints.CENTER;
        formPanel.add(atauLabel, gbcFormPanel);

        //daftar button
        daftarButton = new JButton("Daftar Akun");
        setLoginUIButton(daftarButton);
        daftarButton.addActionListener(e -> {
            loginFrame.setPanelContent(new daftarPanel(loginFrame));
        });

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 9;
        gbcFormPanel.insets = new Insets(5,10,5,10);
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(daftarButton, gbcFormPanel);

        //exit button
        keluarButton = new JButton("Keluar");
        setLoginUIButton(keluarButton);
        keluarButton.addActionListener(e -> System.exit(0));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 10;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(keluarButton, gbcFormPanel);

        //bottomLabel
        JLabel bottomLabel = new JLabel("Aplikasi Billing WiFi by Kelompok 1 4C-KOM");
        bottomLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        bottomLabel.setForeground(Color.GRAY);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 11;
        gbcFormPanel.weighty = 1;
        gbcFormPanel.fill = GridBagConstraints.BOTH;
        formPanel.add(Box.createGlue(), gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 12;
        gbcFormPanel.fill = GridBagConstraints.NONE;
        formPanel.add(bottomLabel, gbcFormPanel);

        //add form panel ke login
        gbcLogin.gridx = 0; gbcLogin.gridy = 0;
        gbcLogin.weightx = 1; gbcLogin.weighty = 1;
        gbcLogin.fill = GridBagConstraints.BOTH;
        add(formPanel, gbcLogin);

        setVisible(true);
    }

    private void setLoginUIButton(JButton button){
        button.setPreferredSize(new Dimension(button.getWidth(), 40));
        button.setBackground(new Color(40, 40, 40));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    //loginUser
    private void loginUser(){
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Username atau Password salah! \nSilahkan Login Kembali.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //cek format email pada emailField
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")){
            JOptionPane.showMessageDialog(this, "Format email tidak valid!\nContoh: a@mail.com", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;
        PreparedStatement loginPS = null;
        ResultSet resultSet = null;
        Boolean login = false;
        String nama = "";

        try{
            connection = DatabaseConnection.getConnection();
            String loginQuery = "SELECT * FROM tb_user WHERE email = ? AND password = ?";
            loginPS = connection.prepareStatement(loginQuery);
            loginPS.setString(1, email);
            loginPS.setString(2, password);
            resultSet = loginPS.executeQuery();

            if (resultSet.next()){
                nama = resultSet.getString("nama");
                login = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (SQLException ignored) {}
            try { if (loginPS != null) loginPS.close(); } catch (SQLException ignored) {}
        }

        if (login){
            JOptionPane.showMessageDialog(this, "Login Berhasil \nWelcome " +nama);

            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JFrame){
                window.dispose();
            }
            new Dashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah! \nSilahkan Login Kembali.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
