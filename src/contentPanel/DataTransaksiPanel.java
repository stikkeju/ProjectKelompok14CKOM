package contentPanel;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DataTransaksiPanel extends JPanel {
    private JPanel leftPanel, formPanel, tanggalPanel, rightPanel, detailPanel, leftButtonPanel, invoicePanel;
    private JLabel kodetransaksiLabel, kodepelangganLabel, namaLabel, paketLabel, tanggalLabel, biayaLabel, statusLabel;
    private JTextField kodetransaksiField, kodepelangganField, namaField, biayaField;
    private JComboBox<String> paketBox, statusBox;
    private JComboBox<Integer> dayBox, monthBox, yearBox;
    private JTextArea invoiceArea;
    private JButton konfirmasiButton, editButton, deleteButton, generateButton, copyButton;
    private DefaultTableModel datatransaksiModel;
    private JTable datatransaksiTable;
    private Connection connection;

    public DataTransaksiPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        connection = DatabaseConnection.getConnection();

        // Left Panel
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Data Transaksi"));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5, 5, 5, 5);

        // Form Panel
        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcFormPanel = new GridBagConstraints();
        gbcFormPanel.insets = new Insets(5, 5, 5, 5);
        gbcFormPanel.anchor = GridBagConstraints.WEST;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 0;
        kodetransaksiLabel = new JLabel("Kode Transaksi");
        formPanel.add(kodetransaksiLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 1;
        kodetransaksiField = new JTextField();
        formPanel.add(kodetransaksiField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 0;
        kodepelangganLabel = new JLabel("Kode Pelanggan");
        formPanel.add(kodepelangganLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 1;
        kodepelangganField = new JTextField();
        formPanel.add(kodepelangganField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0;
        namaLabel = new JLabel("Nama");
        formPanel.add(namaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0;
        namaField = new JTextField();
        formPanel.add(namaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 0;
        paketLabel = new JLabel("Paket");
        formPanel.add(paketLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 1;
        String[] paketArray = {"20 Mbps", "30 Mbps", "50 Mbps", "100 Mbps"};
        paketBox = new JComboBox<>(paketArray);
        paketBox.addActionListener(e -> updateBiayaField());
        formPanel.add(paketBox, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 0;
        tanggalLabel = new JLabel("Tanggal");
        formPanel.add(tanggalLabel, gbcFormPanel);

        tanggalPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTanggal = new GridBagConstraints();

        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = 1 + i;
        }
        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++){
            months[i] = 1 + i;
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer[] years = new Integer[5];
        for (int i = 0; i < years.length; i++){
            years[i] = currentYear + i;
        }
        dayBox = new JComboBox<>(days);
        monthBox = new JComboBox<>(months);
        yearBox = new JComboBox<>(years);

        gbcTanggal.gridx = 0; gbcTanggal.weightx = 1;
        gbcTanggal.fill = GridBagConstraints.HORIZONTAL;
        tanggalPanel.add(dayBox, gbcTanggal);

        gbcTanggal.gridx = 1; gbcTanggal.weightx = 1;
        gbcTanggal.fill = GridBagConstraints.HORIZONTAL;
        tanggalPanel.add(monthBox, gbcTanggal);

        gbcTanggal.gridx = 2; gbcTanggal.weightx = 1;
        gbcTanggal.fill = GridBagConstraints.HORIZONTAL;
        tanggalPanel.add(yearBox, gbcTanggal);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 1;
        formPanel.add(tanggalPanel, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 0;
        biayaLabel = new JLabel("Biaya");
        formPanel.add(biayaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 1;
        biayaField = new JTextField();
        formPanel.add(biayaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 6;
        gbcFormPanel.weightx = 0;
        statusLabel = new JLabel("Status");
        formPanel.add(statusLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 6;
        gbcFormPanel.weightx = 1;
        String statusArray[] = {"Sudah Bayar", "Belum Bayar"};
        statusBox = new JComboBox<>(statusArray);
        formPanel.add(statusBox, gbcFormPanel);

        leftButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcLeftButton = new GridBagConstraints();
        gbcLeftButton.insets = new Insets(0, 5, 0, 5);
        gbcLeftButton.fill = GridBagConstraints.HORIZONTAL;

        gbcLeftButton.gridx = 0;
        gbcLeftButton.weightx = 0.3;
        konfirmasiButton = new JButton("Konfirmasi Pembayaran");
        konfirmasiButton.addActionListener(e -> konfirmasiDataTransaksi());
        leftButtonPanel.add(konfirmasiButton, gbcLeftButton);

        gbcLeftButton.gridx = 1;
        gbcLeftButton.weightx = 0.3;
        editButton = new JButton("Edit");
        editButton.addActionListener(e -> editDataTransaksi());
        leftButtonPanel.add(editButton, gbcLeftButton);

        gbcLeftButton.gridx = 2;
        gbcLeftButton.weightx = 0.3;
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteDataTransaksi());
        leftButtonPanel.add(deleteButton, gbcLeftButton);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 7;
        gbcFormPanel.weightx = 1;
        formPanel.add(leftButtonPanel, gbcFormPanel);

        gbcLeft.gridx = 0; gbcLeft.gridy = 0;
        gbcLeft.weightx =  1;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(formPanel, gbcLeft);

        // Table Panel
        datatransaksiModel = new DefaultTableModel(
                new String[]{"Kode Transaksi", "Kode Pelanggan", "Nama", "Paket", "Tanggal", "Biaya", "Status"}, 0
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        datatransaksiTable = new JTable(datatransaksiModel);
        JScrollPane tableScroll = new JScrollPane(datatransaksiTable);
        datatransaksiTable.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Boolean) {
                    boolean status = (Boolean) value;
                    setText(status ? "Sudah Bayar" : "Belum Bayar");
                } else {
                    super.setValue(value);
                }
            }
        });



        gbcLeft.gridx = 0; gbcLeft.gridy = 2;
        gbcLeft.fill = GridBagConstraints.BOTH;
        gbcLeft.weightx = 1;
        gbcLeft.weighty = 1;
        leftPanel.add(tableScroll, gbcLeft);

        // Right Panel
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Detail & Invoice"));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5, 5, 5, 5);
        gbcRight.fill = GridBagConstraints.HORIZONTAL;


        // Detail Panel
        detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBorder(BorderFactory.createTitledBorder("Detail Transaksi"));
        GridBagConstraints gbcDetail = new GridBagConstraints();
        gbcDetail.insets = new Insets(5, 5, 5, 5);
        gbcDetail.anchor = GridBagConstraints.WEST;

        JLabel kodetransaksiValueLabel = new JLabel();
        JLabel kodepelangganValueLabel = new JLabel();
        JLabel namaValueLabel = new JLabel();
        JLabel paketValueLabel = new JLabel();
        JLabel tanggalValueLabel = new JLabel();
        JLabel biayaValueLabel = new JLabel();
        JLabel statusValueLabel = new JLabel();

        gbcDetail.gridx = 0; gbcDetail.gridy = 0;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Kode Transaksi:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(kodetransaksiValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 1;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Kode Pelanggan:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(kodepelangganValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 2;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Nama:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(namaValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 3;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Paket:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(paketValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 4;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Tanggal:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(tanggalValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 5;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Biaya:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(biayaValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 6;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Status:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(statusValueLabel, gbcDetail);


        // Invoice Panel
        invoicePanel = new JPanel(new GridBagLayout());
        invoicePanel.setBorder(BorderFactory.createTitledBorder("Invoice Text"));
        GridBagConstraints gbcInvoice = new GridBagConstraints();
        gbcInvoice.insets = new Insets(5, 5, 5, 5);

        invoiceArea = new JTextArea(10, 20);
        invoiceArea.setLineWrap(true);
        invoiceArea.setWrapStyleWord(true);
        JScrollPane invoiceScroll = new JScrollPane(invoiceArea);

        generateButton = new JButton("Generate Invoice");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String invoiceText =
                        "=== INVOICE TRANSAKSI BILLING WIFI ===\n" +
                                "Kode Transaksi : " + kodetransaksiValueLabel.getText() + "\n" +
                                "Kode Pelanggan : " + kodepelangganValueLabel.getText() + "\n" +
                                "Nama : " + namaValueLabel.getText() + "\n" +
                                "Paket : " + paketValueLabel.getText() + "\n" +
                                "Tanggal :" + tanggalValueLabel.getText() + "\n" +
                                "Jumlah Bayar : Rp. " + biayaValueLabel.getText() + "\n" +
                                "Status : " + statusValueLabel.getText() + "\n\n" +
                                "Silakan segera melakukan pembayaran. Terima kasih.";
                invoiceArea.setText(invoiceText);
            }
        });

        copyButton = new JButton("Copy Invoice");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection selection = new StringSelection(invoiceArea.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            }
        });

        gbcInvoice.gridx = 0; gbcInvoice.gridy = 0;
        gbcInvoice.weightx = 1; gbcInvoice.weighty = 1;
        gbcInvoice.fill = GridBagConstraints.BOTH;
        invoicePanel.add(invoiceScroll, gbcInvoice);

        gbcInvoice.gridx = 0; gbcInvoice.gridy = 1;
        gbcInvoice.weighty = 0;
        gbcInvoice.fill = GridBagConstraints.HORIZONTAL;
        invoicePanel.add(generateButton, gbcInvoice);

        gbcInvoice.gridx = 0; gbcInvoice.gridy = 2;
        gbcInvoice.weighty = 0;
        gbcInvoice.fill = GridBagConstraints.HORIZONTAL;
        invoicePanel.add(copyButton, gbcInvoice);


        gbcRight.gridx = 0; gbcRight.gridy = 0;
        gbcRight.weightx = 1;
        rightPanel.add(detailPanel, gbcRight);

        gbcRight.gridy = 1;
        gbcRight.fill = GridBagConstraints.BOTH;
        gbcRight.weighty = 1;
        rightPanel.add(invoicePanel, gbcRight);


        datatransaksiTable.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                int selectedRow = datatransaksiTable.getSelectedRow();
                if (selectedRow != -1){
                    String kodetransaksi = (String) datatransaksiTable.getValueAt(selectedRow, 0);
                    String kodepelanggan = (String)  datatransaksiTable.getValueAt(selectedRow, 1);
                    String nama = (String) datatransaksiTable.getValueAt(selectedRow, 2);
                    String paket = (String) datatransaksiTable.getValueAt(selectedRow, 3);
                    Date tanggal = (Date) datatransaksiTable.getValueAt(selectedRow, 4);
                    int biaya = (int) datatransaksiTable.getValueAt(selectedRow, 5);
                    boolean status = (boolean) datatransaksiTable.getValueAt(selectedRow, 6);

                    //setText untuk form
                    kodetransaksiField.setText(kodetransaksi);
                    kodepelangganField.setText(kodepelanggan);
                    namaField.setText(nama);
                    paketBox.setSelectedItem(paket);

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(tanggal);
                    dayBox.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
                    monthBox.setSelectedItem(cal.get(Calendar.MONTH) + 1);
                    yearBox.setSelectedItem(cal.get(Calendar.YEAR));

                    biayaField.setText(String.valueOf(biaya));

                    if (status){
                        statusBox.setSelectedItem("Sudah Bayar");
                    } else {
                        statusBox.setSelectedItem("Belum Bayar");
                    }

                    //setText untuk detailPanel
                    kodetransaksiValueLabel.setText(datatransaksiTable.getValueAt(selectedRow, 0).toString());
                    kodepelangganValueLabel.setText(datatransaksiTable.getValueAt(selectedRow, 1).toString());
                    namaValueLabel.setText(datatransaksiTable.getValueAt(selectedRow, 2).toString());
                    paketValueLabel.setText(datatransaksiTable.getValueAt(selectedRow, 3).toString());
                    tanggalValueLabel.setText(datatransaksiTable.getValueAt(selectedRow, 4).toString());
                    biayaValueLabel.setText(datatransaksiTable.getValueAt(selectedRow, 5).toString());
                    if (status){
                        statusValueLabel.setText("Sudah Bayar");
                    } else {
                        statusValueLabel.setText("Belum Bayar");
                    }

                }
            }
        });

        // Add panels to main layout with 1:1 ratio
        gbcPanel.gridx = 0;
        gbcPanel.weightx = 0.5;
        gbcPanel.weighty = 1;
        gbcPanel.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbcPanel);

        gbcPanel.gridx = 1;
        gbcPanel.weightx = 0.5;
        add(rightPanel, gbcPanel);


        lockField(kodetransaksiField);
        lockField(kodepelangganField);
        lockField(namaField);
        lockField(biayaField);
        loadDataTransaksi();
    }

    private void lockField(JTextField field){
        field.setEditable(false);
        field.setFocusable(false);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setBorder(UIManager.getBorder("TextField.border"));
    }

    private void loadDataTransaksi(){
        PreparedStatement loadState = null;
        ResultSet resultSet = null;
        datatransaksiModel.setRowCount(0);
        try{
            String loadQuery = "SELECT * FROM tb_datatransaksi";
            loadState = connection.prepareStatement(loadQuery);
            resultSet = loadState.executeQuery();
            while(resultSet.next()) {
                Object[] row = {
                        resultSet.getString("kodetransaksi"),
                        resultSet.getString("kodepelanggan"),
                        resultSet.getString("nama"),
                        resultSet.getString("paket"),
                        resultSet.getDate("tanggal"),
                        resultSet.getInt("biaya"),
                        resultSet.getBoolean("status"),
                };
                datatransaksiModel.addRow(row);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage());
        }
    }

    private String generateKodeTransaksi(Connection connection, String kodePelanggan, java.sql.Date tanggal){
        String kodeTransaksi = null;
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        String formatTanggal = sdf.format(tanggal);
        String baseKode = "WF" + kodePelanggan + formatTanggal;

        try{
            String sqlQuery = "SELECT kodetransaksi FROM tb_datatransaksi " +
                    "WHERE kodetransaksi LIKE ? ORDER BY kodetransaksi DESC LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, baseKode + "-%");

            ResultSet rs = ps.executeQuery();

            int nextSuffix = 1;
            if (rs.next()) {
                String lastKode = rs.getString("kodetransaksi");
                String[] parts = lastKode.split("-");
                if (parts.length == 2) {
                    try {
                        nextSuffix = Integer.parseInt(parts[1]) + 1;
                    } catch (NumberFormatException e) {
                        nextSuffix = 1;
                    }
                }
            }

            String suffix = String.format("-%02d", nextSuffix);
            kodeTransaksi = baseKode + suffix;

            rs.close();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return kodeTransaksi;
    }

    //function konfirmasiDataTransaksi
    private void konfirmasiDataTransaksi(){
        String konfirmQuery = "UPDATE tb_datatransaksi SET status = ? WHERE kodetransaksi = ?";
        String updatapelangganQuery = "UPDATE tb_datapelanggan SET paket = ?, tanggal = ?, biaya = ?, status = ? WHERE kodepelanggan = ?";
        int selectedRow = datatransaksiTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dikonfirmasi dari tabel!");
            return;
        }

        String kodetransaksi = (String) datatransaksiModel.getValueAt(selectedRow, 0);
        String kodepelanggan = kodepelangganField.getText();
        String paket = (String) paketBox.getSelectedItem();

        int day = (int) dayBox.getSelectedItem();
        int month = (int) monthBox.getSelectedItem();
        int year = (int) yearBox.getSelectedItem();

        LocalDate localDate;
        try {
            localDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(this, "Tanggal yang dipilih tidak valid!", "Tanggal Salah", JOptionPane.WARNING_MESSAGE);
            return;
        }
        java.sql.Date tanggal = java.sql.Date.valueOf(localDate);

        int biaya = Integer.parseInt(biayaField.getText());

        String selectedStatus = (String) statusBox.getSelectedItem();
        boolean status = false;
        if (selectedStatus.equals("Sudah Bayar")) {
            status = true;
        } else if (selectedStatus.equals("Belum Bayar")) {
            status = false;
        }

        if (kodetransaksiField.getText().isEmpty() ||
                kodepelangganField.getText().isEmpty() ||
                namaField.getText().isEmpty() ||
                biayaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak lengkap! Mohon isi kembali", "Data tidak lengkap", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (datatransaksiModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tolong masukkan data terlebih dahulu!");
        }

        try{
            PreparedStatement konfirmPS = connection.prepareStatement(konfirmQuery);
            konfirmPS.setBoolean(1, status);
            konfirmPS.setString(2, kodetransaksi);
            konfirmPS.executeUpdate();

            int affectedRow = konfirmPS.executeUpdate();
            if (affectedRow > 0){
                PreparedStatement updatePS = connection.prepareStatement(updatapelangganQuery);
                updatePS.setString(1, paket);
                updatePS.setDate(2, tanggal);
                updatePS.setInt(3, biaya);
                updatePS.setBoolean(4, status);
                updatePS.setString(5, kodepelanggan);

                int updatedPelanggan = updatePS.executeUpdate();
                if (updatedPelanggan > 0) {
                    JOptionPane.showMessageDialog(this, "Transaksi dan data pelanggan berhasil dikonfirmasi!");
                } else {
                    JOptionPane.showMessageDialog(this, "Data pelanggan gagal diperbarui.");
                }
                editDataTransaksi();
                loadDataTransaksi();
                clearFormTransaksi();
            } else {
                JOptionPane.showMessageDialog(this, "Transaksi gagal diperbarui.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saat update: " + e.getMessage());
        }
    }

    //function editDataTransaksi
    private void editDataTransaksi(){
        //SQL query untuk mengganti paket, tanggal, biaya dan status
        String editQuery = "UPDATE tb_datatransaksi SET kodetransaksi = ?, paket = ?, tanggal = ?, biaya = ?, status = ? WHERE kodetransaksi = ?";
        int selectedRow = datatransaksiTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diedit dari tabel!");
            return;
        }

        //deklarasi variable
        String kodetransaksi = (String) datatransaksiModel.getValueAt(selectedRow, 0);
        String kodepelanggan = kodepelangganField.getText();
        String paket = (String) paketBox.getSelectedItem();

        int day = (int) dayBox.getSelectedItem();
        int month = (int) monthBox.getSelectedItem();
        int year = (int) yearBox.getSelectedItem();

        LocalDate localDate;
        try {
            localDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(this, "Tanggal yang dipilih tidak valid!", "Tanggal Salah", JOptionPane.WARNING_MESSAGE);
            return;
        }
        java.sql.Date tanggal = java.sql.Date.valueOf(localDate);

        int biaya = Integer.parseInt(biayaField.getText());

        String selectedStatus = (String) statusBox.getSelectedItem();
        boolean status = false;
        if (selectedStatus.equals("Sudah Bayar")) {
            status = true;
        } else if (selectedStatus.equals("Belum Bayar")) {
            status = false;
        }

        String kodetransaksibaru = generateKodeTransaksi(connection, kodepelanggan, tanggal);

        if (kodetransaksiField.getText().isEmpty() ||
                kodepelangganField.getText().isEmpty() ||
                namaField.getText().isEmpty() ||
                biayaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak lengkap! Mohon isi kembali", "Data tidak lengkap", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (datatransaksiModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tolong masukkan data terlebih dahulu!");
            return;
        }

        try{
            PreparedStatement editPS = connection.prepareStatement(editQuery);
            editPS.setString(1, kodetransaksibaru);
            editPS.setString(2, paket);
            editPS.setDate(3, tanggal);
            editPS.setInt(4, biaya);
            editPS.setBoolean(5, status);
            editPS.setString(6, kodetransaksi);

            int affectedRow = editPS.executeUpdate();
            if (affectedRow > 0){
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");
                loadDataTransaksi();
                clearFormTransaksi();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal diperbarui.");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Format angka salah: " + e.getMessage());
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error saat update: " + e.getMessage());
        }
    }

    private void deleteDataTransaksi(){
        String deleteQuery = "DELETE FROM tb_datatransaksi WHERE kodetransaksi = ?";
        int selectedRow = datatransaksiTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus dari tabel!");
            return;
        }
        String kodetransaksi = (String) datatransaksiModel.getValueAt(selectedRow, 0);
        try {
            PreparedStatement deletePS = connection.prepareStatement(deleteQuery);
            deletePS.setString(1, kodetransaksi);
            int barisTerhapus = deletePS.executeUpdate();

            if (barisTerhapus > 0){
                JOptionPane.showMessageDialog(this,"Data berhasil dihapus!");
                loadDataTransaksi();
                clearFormTransaksi();
            } else{
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }

    }

    private void updateBiayaField(){
        String paket = (String) paketBox.getSelectedItem();
        int biaya = switch (paket){
            case "20 Mbps" -> 200000;
            case "30 Mbps" -> 300000;
            case "50 Mbps" -> 500000;
            case "100 Mbps" -> 800000;
            default -> 0;
        };
        biayaField.setText(String.valueOf(biaya));
    }

    private void clearFormTransaksi(){
        kodetransaksiField.setText("");
        kodepelangganField.setText("");
        namaField.setText("");
        paketBox.setSelectedIndex(0);
        dayBox.setSelectedIndex(0);
        monthBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        biayaField.setText("");
        statusBox.setSelectedIndex(0);
        datatransaksiTable.getSelectionModel().clearSelection();
    }
}
