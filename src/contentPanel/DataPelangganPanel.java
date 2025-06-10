package contentPanel;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;

public class DataPelangganPanel extends JPanel {
    private JPanel leftPanel, formPanel, tanggalPanel, leftButtonPanel, rightPanel, cariPanel;
    private JLabel kodePelangganLabel, namaLabel, alamatLabel, telpLabel, paketLabel, tanggalLabel, biayaLabel, statusLabel;
    private JTextField kodepelangganField, namaField, alamatField, telpField, biayaField, cariField;
    private JComboBox<Integer> dayBox, monthBox, yearBox;
    private JComboBox<String> paketBox, statusBox;
    private JButton tambahButton, editButton, deleteButton, cariButton, refreshButton;
    private DefaultTableModel datapelangganModel;
    private JTable datapelangganTable;
    private Connection connection;

    public DataPelangganPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        connection = DatabaseConnection.getConnection();

        //left container
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Form Data Pelanggan"));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5,5,5,5);

        //formPanel
        formPanel = new JPanel(new GridBagLayout());
        //formPanel.setBorder(BorderFactory.createTitledBorder("formPanel"));
        GridBagConstraints gbcFormPanel = new GridBagConstraints();
        gbcFormPanel.insets = new Insets(5,5,5,5);
        gbcFormPanel.anchor = GridBagConstraints.WEST;

        //tambah label & textfield di formPanel
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 0;
        kodePelangganLabel = new JLabel("Kode Pelanggan");
        formPanel.add(kodePelangganLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        kodepelangganField = new JTextField();
        formPanel.add(kodepelangganField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 0;
        namaLabel = new JLabel("Nama Pelanggan");
        formPanel.add(namaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        namaField = new JTextField();
        formPanel.add(namaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0;
        alamatLabel = new JLabel("Alamat");
        formPanel.add(alamatLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        alamatField = new JTextField();
        formPanel.add(alamatField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 0;
        telpLabel = new JLabel("No. Telp");
        formPanel.add(telpLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 1;
        telpField = new JTextField();
        formPanel.add(telpField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 0;
        paketLabel = new JLabel("Jenis Paket");
        formPanel.add(paketLabel, gbcFormPanel);


        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 1;
        String[] paketArray = {
                "20 Mbps", "30 Mbps", "50 Mbps", "100 Mbps"
        };
        paketBox = new JComboBox<>(paketArray);
        paketBox.addActionListener(e -> updateBiayaField());
        formPanel.add(paketBox, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 0;
        tanggalLabel = new JLabel("Tanggal");
        formPanel.add(tanggalLabel, gbcFormPanel);

        //Combobox tanggal
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

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(tanggalPanel, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 6;
        gbcFormPanel.weightx = 0;
        biayaLabel = new JLabel("Biaya");
        formPanel.add(biayaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 6;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        biayaField = new JTextField();
        lockField(biayaField);
        formPanel.add(biayaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 7;
        gbcFormPanel.weightx = 0;
        statusLabel = new JLabel("Status");
        formPanel.add(statusLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 7;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        String statusArray[] = {"Aktif", "Nonaktif"};
        statusBox = new JComboBox<>(statusArray);
        formPanel.add(statusBox, gbcFormPanel);

        //left button container
        leftButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcLeftButton = new GridBagConstraints();
        gbcLeftButton.insets = new Insets(0, 5, 0, 5);
        gbcLeftButton.fill = GridBagConstraints.HORIZONTAL;

        gbcLeftButton.gridx = 0;
        gbcLeftButton.weightx = 1;
        tambahButton = new JButton("Tambah");
        tambahButton.addActionListener(e -> tambahDataPelanggan());
        leftButtonPanel.add(tambahButton, gbcLeftButton);

        gbcLeftButton.gridx = 1;
        gbcLeftButton.weightx = 1;
        editButton = new JButton("Update");
        editButton.addActionListener(e -> editDataPelanggan());
        leftButtonPanel.add(editButton, gbcLeftButton);

        gbcLeftButton.gridx = 2;
        gbcLeftButton.weightx = 1;
        deleteButton = new JButton("Hapus");
        deleteButton.addActionListener(e -> deleteDataPelanggan());
        leftButtonPanel.add(deleteButton, gbcLeftButton);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 8;
        gbcFormPanel.weightx = 1;
        formPanel.add(leftButtonPanel, gbcFormPanel);


        //Tambah formPanel ke leftPanel
        gbcLeft.gridx = 0; gbcLeft.gridy = 0;
        gbcLeft.weightx =  1; gbcLeft.weighty = 1;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(formPanel, gbcLeft);

        //right container
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Data Pelanggan"));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5,5,5,5);

        cariPanel = new JPanel(new GridBagLayout());
        //cariPanel.setBorder(BorderFactory.createTitledBorder("Cari Panel"));
        GridBagConstraints gbcCari = new GridBagConstraints();
        gbcCari.insets = new Insets(5,5,5,5);

        gbcCari.gridx = 0; gbcCari.gridy = 0;
        gbcCari.weightx = 1;
        gbcCari.fill = GridBagConstraints.HORIZONTAL;
        cariField = new JTextField();
        cariPanel.add(cariField, gbcCari);

        gbcCari.gridx = 1; gbcCari.gridy = 0;
        gbcCari.weightx = 0;
        cariButton = new JButton("Cari");
        cariButton.addActionListener(e -> cariDataPelanggan());
        cariPanel.add(cariButton, gbcCari);

        gbcCari.gridx = 2; gbcCari.gridy = 0;
        gbcCari.weightx = 0;
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            loadDataPelanggan();
            clearFormPelanggan();
        });

        cariPanel.add(refreshButton, gbcCari);

        datapelangganModel = new DefaultTableModel(
                new String[]{"Kode Pelanggan", "Nama", "Alamat", "Telp", "Paket", "Tanggal", "Biaya", "Status"}, 0
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        datapelangganTable = new JTable(datapelangganModel);
        JScrollPane tableScroll = new JScrollPane(datapelangganTable);

        //set text status table ke aktif/nonaktif
        datapelangganTable.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Boolean) {
                    boolean status = (Boolean) value;
                    setText(status ? "Aktif" : "Nonaktif");
                } else {
                    super.setValue(value);
                }
            }
        });

        //listener ketika row table clicked
        datapelangganTable.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                int selectedRow = datapelangganTable.getSelectedRow();
                if (selectedRow != -1){
                    String kodepelanggan = (String) datapelangganModel.getValueAt(selectedRow, 0);
                    String nama = (String) datapelangganModel.getValueAt(selectedRow, 1);
                    String alamat = (String) datapelangganModel.getValueAt(selectedRow, 2);
                    String telp = (String) datapelangganModel.getValueAt(selectedRow, 3);
                    String paket = (String) datapelangganModel.getValueAt(selectedRow, 4);
                    Date tanggal = (Date) datapelangganModel.getValueAt(selectedRow, 5);
                    int biaya = (int) datapelangganModel.getValueAt(selectedRow, 6);
                    boolean status = (boolean) datapelangganModel.getValueAt(selectedRow, 7);

                    kodepelangganField.setText(String.valueOf(kodepelanggan));
                    namaField.setText(nama);
                    alamatField.setText(alamat);
                    telpField.setText(telp);
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
                }
            }
        });


        gbcRight.gridx = 0; gbcRight.gridy = 0;
        gbcRight.weightx = 1;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(cariPanel, gbcRight);

        gbcRight.gridx = 0; gbcRight.gridy = 1;
        gbcRight.weightx = 1; gbcRight.weighty = 1;
        gbcRight.fill = GridBagConstraints.BOTH;
        rightPanel.add(tableScroll, gbcRight);

        //add leftpanel & rightpanel ke main panel
        gbcPanel.gridx = 0; gbcPanel.gridy = 0;
        gbcPanel.weightx = 0.5; gbcPanel.weighty = 1;
        gbcPanel.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbcPanel);

        gbcPanel.gridx = 1; gbcPanel.gridy = 0;
        gbcPanel.weightx = 0.5; gbcPanel.weighty = 1;
        gbcPanel.fill = GridBagConstraints.BOTH;
        add(rightPanel, gbcPanel);

        loadDataPelanggan();
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

    private void lockField(JTextField field){
        field.setEditable(false);
        field.setFocusable(false);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setBorder(UIManager.getBorder("TextField.border"));
    }

    private void loadDataPelanggan(){
        PreparedStatement loadState = null;
        ResultSet resultSet = null;
        datapelangganModel.setRowCount(0);
        try{
            String loadQuery = "SELECT * FROM tb_datapelanggan";
            loadState = connection.prepareStatement(loadQuery);
            resultSet = loadState.executeQuery();

            while(resultSet.next()){
                Object[] row ={
                        resultSet.getString("kodepelanggan"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("telp"),
                        resultSet.getString("paket"),
                        resultSet.getDate("tanggal"),
                        resultSet.getInt("biaya"),
                        resultSet.getBoolean("status"),
                };
                datapelangganModel.addRow(row);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage());
        }
    }

    private void tambahDataPelanggan() {
        String tambahQuery = "INSERT INTO tb_datapelanggan (kodepelanggan, nama, alamat, telp, paket, tanggal, biaya, status) VALUES (?, ?, ?, ?, ?, ?,?, ?)";

        //Deklarasi variabel untuk insert
        String kodepelanggan = kodepelangganField.getText();
        String nama = namaField.getText();
        String alamat = alamatField.getText();
        String telp = telpField.getText();

        //Deklarasi variabel paket dari combobox paketBox
        String paket = (String) paketBox.getSelectedItem();

        int biaya = Integer.parseInt(biayaField.getText());

        //deklarasi variabel status dari combobox statusBox
        String selectedStatus = (String) statusBox.getSelectedItem();
        boolean status = false;
        if (selectedStatus.equals("Sudah Bayar")) {
            status = true;
        } else if (selectedStatus.equals("Belum Bayar")) {
            status = false;
        }

        //item combobox tanggal ke variabel sqlDate
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
        Date tanggal = Date.valueOf(localDate);

        if (kodepelangganField.getText().isEmpty() ||
                namaField.getText().isEmpty() ||
                alamatField.getText().isEmpty() ||
                telpField.getText().isEmpty() ||
                biayaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak lengkap! Mohon isi kembali", "Data tidak lengkap", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (datapelangganModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tolong masukkan data terlebih dahulu!");
            return;
        }
        try {
            PreparedStatement tambahPS = connection.prepareStatement(tambahQuery);
            tambahPS.setString(1, kodepelanggan);
            tambahPS.setString(2, nama);
            tambahPS.setString(3, alamat);
            tambahPS.setString(4, telp);
            tambahPS.setString(5, paket);
            tambahPS.setDate(6, tanggal);
            tambahPS.setInt(7, biaya);
            tambahPS.setBoolean(8, status);

            tambahPS.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            loadDataPelanggan();
            clearFormPelanggan();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format angka salah!:  " + e.getMessage());
            clearFormPelanggan();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            clearFormPelanggan();
        }
    }

    private void editDataPelanggan() {
        String editdatapelangganQuery = "UPDATE tb_datapelanggan SET kodepelanggan = ?, nama = ?, alamat = ?, telp = ?, paket = ?, tanggal = ?, biaya = ?, status = ? WHERE kodepelanggan = ?";
        //Query untuk edit nama pada tb_datatransaksi agar tetap sama dengan tb_datapelanggan
        String editdatatransaksiQuery = "UPDATE tb_datatransaksi SET nama = ? WHERE kodepelanggan = ?";
        int selectedRow = datapelangganTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diedit dari tabel!");
            return;
        }

        //Deklarasi variabel
        String kodepelanggan = (String) datapelangganModel.getValueAt(selectedRow, 0);
        String nama = namaField.getText();
        String alamat = alamatField.getText();
        String telp = telpField.getText();
        String kodepelangganbaru = kodepelangganField.getText();

        //Deklarasi variabel paket dari combobox paketBox
        String paket = (String) paketBox.getSelectedItem();

        int biaya = Integer.parseInt(biayaField.getText());

        //deklarasi variabel status dari combobox statusBox
        String selectedStatus = (String) statusBox.getSelectedItem();
        boolean status = false;
        if (selectedStatus.equals("Aktif")) {
            status = true;
        } else if (selectedStatus.equals("Nonaktif")) {
            status = false;
        }

        //item combobox tanggal ke variabel sqlDate
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
        Date tanggal = Date.valueOf(localDate);

        if (kodepelangganField.getText().isEmpty() ||
                namaField.getText().isEmpty() ||
                alamatField.getText().isEmpty() ||
                telpField.getText().isEmpty() ||
                biayaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak lengkap! Mohon isi kembali", "Data tidak lengkap", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (datapelangganModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tolong masukkan data terlebih dahulu!");
            return;
        }

        try{
            PreparedStatement editPS = connection.prepareStatement(editdatapelangganQuery);
            editPS.setString(1, kodepelangganbaru);
            editPS.setString(2, nama);
            editPS.setString(3, alamat);
            editPS.setString(4, telp);
            editPS.setString(5, paket);
            editPS.setDate(6, tanggal);
            editPS.setInt(7, biaya);
            editPS.setBoolean(8, status);
            editPS.setString(9, kodepelanggan);

            int affectedRow = editPS.executeUpdate();
            if (affectedRow > 0){
                PreparedStatement editTransaksiPS = connection.prepareStatement(editdatatransaksiQuery);
                editTransaksiPS.setString(1, nama);
                editTransaksiPS.setString(2, kodepelanggan);
                editTransaksiPS.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");
                loadDataPelanggan();
                clearFormPelanggan();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal diperbarui.");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Format angka salah: " + e.getMessage());
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error saat update: " + e.getMessage());
        }
    }

    //cari datapelangganTable
    private void cariDataPelanggan() {
        String keyword = cariField.getText().trim();
        PreparedStatement searchStatement = null;
        ResultSet resultSet = null;
        String searchQuery = "SELECT * FROM tb_datapelanggan WHERE " +
                "REGEXP_REPLACE(kodepelanggan, '[^0-9]', '') LIKE ? OR " +
                "nama LIKE ?";

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kata kunci untuk mencari!");
            return;
        }

        datapelangganModel.setRowCount(0);

        try {
            searchStatement = connection.prepareStatement(searchQuery);
            String wildcard = "%" + keyword + "%";
            for (int i = 1; i <= 2; i++) {
                searchStatement.setString(i, wildcard);
            }

            resultSet = searchStatement.executeQuery();

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getString("kodepelanggan"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("telp"),
                        resultSet.getString("paket"),
                        resultSet.getDate("tanggal"),
                        resultSet.getInt("biaya"),
                        resultSet.getBoolean("status"),
                };
                datapelangganModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencari data: " + e.getMessage());
        }
    }

    private void deleteDataPelanggan(){
        String deleteQuery = "DELETE FROM tb_datapelanggan WHERE kodepelanggan = ?";
        int selectedRow = datapelangganTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus dari tabel!");
            return;
        }

        //Deklarasi variabel
        String kodepelanggan = (String) datapelangganModel.getValueAt(selectedRow, 0);
        try {
            PreparedStatement deletePS = connection.prepareStatement(deleteQuery);
            deletePS.setString(1, kodepelanggan);
            int barisTerhapus = deletePS.executeUpdate();

            if (barisTerhapus > 0){
                JOptionPane.showMessageDialog(this,"Data berhasil dihapus!");
                loadDataPelanggan();
                clearFormPelanggan();
            } else{
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }
    }

    private void clearFormPelanggan() {
        kodepelangganField.setText("");
        namaField.setText("");
        alamatField.setText("");
        telpField.setText("");
        paketBox.setSelectedIndex(0);
        dayBox.setSelectedIndex(0);
        monthBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        biayaField.setText("");
        statusBox.setSelectedIndex(0);
        cariField.setText("");
        datapelangganTable.getSelectionModel().clearSelection();
    }
}
