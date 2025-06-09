package login;

import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class lupapasswordPanel extends JPanel {
    private Login loginFrame;
    private JPanel formPanel;
    private JLabel logoLabel, titleLabel, emailLabel, passwordbaruLabel, konfirmpasswordLabel;
    private JTextField emailField;
    private JPasswordField passwordbaruField, konfirmpasswordField;
    private JButton resetButton, kembaliButton, keluarButton;

    public lupapasswordPanel(Login loginFrame){
        this.loginFrame = loginFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbcLupaPass = new GridBagConstraints();
        gbcLupaPass.insets = new Insets(10, 10, 10, 10);
        gbcLupaPass.fill = GridBagConstraints.BOTH;

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
        titleLabel = new JLabel("Lupa Password");
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

        //password baru label
        passwordbaruLabel = new JLabel("Password Baru");
        passwordbaruLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 4;
        formPanel.add(passwordbaruLabel, gbcFormPanel);

        //password field
        passwordbaruField = new JPasswordField("password");
        passwordbaruField.setPreferredSize(new Dimension(passwordbaruField.getWidth(), 35));
        passwordbaruField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordbaruField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passwordbaruField, gbcFormPanel);

        //konfirm password label
        konfirmpasswordLabel = new JLabel("Konfirm Password");
        konfirmpasswordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 6;
        formPanel.add(konfirmpasswordLabel, gbcFormPanel);

        //konfirm password field
        konfirmpasswordField = new JPasswordField("password");
        konfirmpasswordField.setPreferredSize(new Dimension(passwordbaruField.getWidth(), 35));
        konfirmpasswordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        konfirmpasswordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 7;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(konfirmpasswordField, gbcFormPanel);

        //reset password button
        resetButton = new JButton("Reset Password");
        setLoginUIButton(resetButton);
        resetButton.addActionListener(e -> resetPassword());
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 8;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(resetButton, gbcFormPanel);

        //atau lable
        JLabel atauLabel = new JLabel("atau");
        atauLabel.setForeground(Color.GRAY);
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 9;
        gbcFormPanel.insets = new Insets(15, 5, 15, 5);
        gbcFormPanel.fill = GridBagConstraints.NONE;
        gbcFormPanel.anchor = GridBagConstraints.CENTER;
        formPanel.add(atauLabel, gbcFormPanel);

        //kembali Button
        kembaliButton = new JButton("Kembali ke Login");
        setLoginUIButton(kembaliButton);
        kembaliButton.addActionListener(e -> {
            loginFrame.setPanelContent(new loginPanel(loginFrame));
        });

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 10;
        gbcFormPanel.insets = new Insets(5,10,5,10);
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(kembaliButton, gbcFormPanel);

        //exit button
        keluarButton = new JButton("Keluar");
        setLoginUIButton(keluarButton);
        keluarButton.addActionListener(e -> System.exit(0));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 11;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(keluarButton, gbcFormPanel);

        //bottomLabel
        JLabel bottomLabel = new JLabel("Aplikasi Billing WiFi by Kelompok 1 4C-KOM");
        bottomLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        bottomLabel.setForeground(Color.GRAY);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 12;
        gbcFormPanel.weighty = 1;
        gbcFormPanel.fill = GridBagConstraints.BOTH;
        formPanel.add(Box.createGlue(), gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 13;
        gbcFormPanel.fill = GridBagConstraints.NONE;
        formPanel.add(bottomLabel, gbcFormPanel);

        //add form panel ke login
        gbcLupaPass.gridx = 0; gbcLupaPass.gridy = 0;
        gbcLupaPass.weightx = 1; gbcLupaPass.weighty = 1;
        gbcLupaPass.fill = GridBagConstraints.BOTH;
        add(formPanel, gbcLupaPass);

    }

    private void setLoginUIButton(JButton button){
        button.setPreferredSize(new Dimension(button.getWidth(), 40));
        button.setBackground(new Color(40, 40, 40));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    //reset password
    private void resetPassword(){
        String email = emailField.getText();
        String passwordbaru = new String(passwordbaruField.getPassword());
        String konfirmpassword = new String(konfirmpasswordField.getPassword());

        if (email.isEmpty() || passwordbaru.isEmpty() || konfirmpassword.isEmpty()){
            JOptionPane.showMessageDialog(this, "Harap isi semua field!");
            return;
        }

        //cek format email pada emailField
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")){
            JOptionPane.showMessageDialog(this, "Format email tidak valid!\nContoh: a@mail.com", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!passwordbaru.equals(konfirmpassword)){
            JOptionPane.showMessageDialog(this, "Password baru dan konfirmasi password tidak cocok!");
            return;
        }

        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();

            String resetQuery = "UPDATE tb_user SET password = ? WHERE email = ?";
            PreparedStatement resetPS = connection.prepareStatement(resetQuery);
            resetPS.setString(1, passwordbaru);
            resetPS.setString(2, email);

            int result = resetPS.executeUpdate();
            if (result > 0){
                JOptionPane.showMessageDialog(this, "Password berhasil direset!");
                loginFrame.setPanelContent(new loginPanel(loginFrame));
            } else {
                JOptionPane.showMessageDialog(this, "Email tidak ditemukan!");
                return;
            }

        } catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kesalahan DB: " + e.getMessage());
        }
    }

}
