package login;

import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class daftarPanel extends JPanel {
    private Login loginFrame;
    private JPanel formPanel;
    private JLabel logoLabel, titleLabel, namaLabel, emailLabel, passwordLabel;
    private JTextField namaField, emailField;
    private JPasswordField passwordField;
    private JButton daftarButton, kembaliButton, keluarButton;

    public daftarPanel(Login loginFrame) {
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
        titleLabel = new JLabel("Daftar Akun");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 1;
        gbcFormPanel.insets = new Insets(5,10,5,10);
        gbcFormPanel.anchor = GridBagConstraints.WEST;
        formPanel.add(titleLabel, gbcFormPanel);

        //email Label
        namaLabel = new JLabel("Nama");
        namaLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 2;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.anchor = GridBagConstraints.WEST;
        formPanel.add(namaLabel, gbcFormPanel);

        //email/username field
        namaField = new JTextField("Nama");
        namaField.setPreferredSize(new Dimension(namaField.getWidth(), 35));
        namaField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        namaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(namaField, gbcFormPanel);

        //email label
        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 4;
        formPanel.add(emailLabel, gbcFormPanel);

        //email field
        emailField = new JTextField("Email");
        emailField.setPreferredSize(new Dimension(emailField.getWidth(), 35));
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(emailField, gbcFormPanel);

        //password label
        passwordLabel = new JLabel("Konfirm Password");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 6;
        formPanel.add(passwordLabel, gbcFormPanel);

        //password field
        passwordField = new JPasswordField("password");
        passwordField.setPreferredSize(new Dimension(emailField.getWidth(), 35));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 7;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passwordField, gbcFormPanel);

        //daftar button
        daftarButton = new JButton("Daftar");
        setLoginUIButton(daftarButton);
        daftarButton.addActionListener(e -> daftarUser());
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 8;
        gbcFormPanel.gridwidth = 2;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(daftarButton, gbcFormPanel);

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
        keluarButton.addActionListener(e-> System.exit(0));
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

    //daftarUser untuk menambahkan data ke tb_user
    private void daftarUser(){
        String nama = namaField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        //Cek field kosong / tidak
        if (nama.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Data tidak lengkap!! \nSilahkan isi kembali.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //cek format email pada emailField
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")){
            JOptionPane.showMessageDialog(this, "Format email tidak valid!\nContoh: a@mail.com", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;

        try{
            connection = DatabaseConnection.getConnection();
            String daftarQuery = "INSERT INTO tb_user (nama, email, password) VALUES (?, ?, ?)";
            PreparedStatement daftarPS = connection.prepareStatement(daftarQuery);
            daftarPS.setString(1, nama);
            daftarPS.setString(2, email);
            daftarPS.setString(3, password);

            int result = daftarPS.executeUpdate();
            if (result > 0){
                JOptionPane.showMessageDialog(this, "Pendaftaran berhasil!");
                loginFrame.setPanelContent(new loginPanel(loginFrame));
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mendaftar.");
            }
        } catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kesalahan DB: " + e.getMessage());
        }
    }

}
