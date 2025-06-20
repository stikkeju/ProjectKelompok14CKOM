package contentPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;

import database.DatabaseConnection;

public class HomePanel extends JPanel {
    private JPanel topPanel, cardPanel, rightPanel, formPanel, buttonPanel, tanggalPanel, bottomPanel, cariPanel, card1, card2, card3, card4;
    private JLabel kodePelangganLabel, namaLabel, alamatLabel, telpLabel, tanggalLabel, paketLabel, biayaLabel, statusLabel;
    private JTextField kodepelangganField, namaField, alamatField, telpField, biayaField, cariField;
    private JComboBox<Integer> dayBox, monthBox, yearBox;
    private JComboBox<String> paketBox, statusBox;
    private JButton bayarButton, refreshButton, cariButton;
    private DefaultTableModel datapelangganModel;
    private JTable datapelangganTable;
    private JScrollPane tableScroll;
    private Connection connection;

    public HomePanel() {
        setLayout(new BorderLayout());
        connection = DatabaseConnection.getConnection();

        //Top Container
        topPanel = new JPanel(new GridBagLayout());
        //topPanel.setBorder(BorderFactory.createTitledBorder("Top Panel"));
        GridBagConstraints gbcTop = new GridBagConstraints();
        gbcTop.insets = new Insets(5,5,5,5);

        //Card Panel
        cardPanel = new JPanel(new GridBagLayout());
        //cardPanel.setBorder(BorderFactory.createTitledBorder("Card Panel"));
        GridBagConstraints gbcCard = new GridBagConstraints();
        gbcCard.insets = new Insets(5,5,5,5);

        gbcCard.gridx = 0; gbcCard.gridy = 0;
        String totalaktif = totalPelangganAktif(connection);
        card1 = new CreateCardPanel("Jumlah Pelanggan Aktif", totalaktif, "Pelanggan dengan status layanan aktif.");
        cardPanel.add(card1, gbcCard);

        gbcCard.gridx = 1; gbcCard.gridy = 0;
        String totalnonaktif = totalPelangganNonaktif(connection);
        card2 = new CreateCardPanel("Pelanggan Nonaktif", totalnonaktif, "Pelanggan dengan status layanan nonaktif.");
        cardPanel.add(card2, gbcCard);

        gbcCard.gridx = 0; gbcCard.gridy = 1;
        String totaltransaksi = totalTransaksiBulan(connection);
        card3 = new CreateCardPanel("Jumlah Pembayaran", totaltransaksi, "Jumlah pembayaran bulan ini.");
        cardPanel.add(card3, gbcCard);

        gbcCard.gridx = 1; gbcCard.gridy = 1;
        String totalpendapatan = totalPendapatanBulan(connection);
        card4 = new CreateCardPanel("Jumlah Pendapatan", totalpendapatan, "Jumlah pendapatan bulan ini.");
        cardPanel.add(card4, gbcCard);

        //Right Panel
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Form Pembayaran"));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5,5,5,5);
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        //Right formPanel
        formPanel = new JPanel(new GridBagLayout());
        //formPanel.setBorder(BorderFactory.createTitledBorder("formPanel"));
        GridBagConstraints gbcFormPanel = new GridBagConstraints();
        gbcFormPanel.insets = new Insets(5,5,5,5);
        gbcFormPanel.anchor = GridBagConstraints.WEST;

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 0;
        kodePelangganLabel = new JLabel("Kode Pelanggan");
        formPanel.add(kodePelangganLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        kodepelangganField = new JTextField();
        lockField(kodepelangganField);
        formPanel.add(kodepelangganField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 0;
        namaLabel = new JLabel("Nama Pelanggan");
        formPanel.add(namaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        namaField = new JTextField();
        lockField(namaField);
        formPanel.add(namaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0;
        alamatLabel = new JLabel("Alamat");
        formPanel.add(alamatLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        alamatField = new JTextField();
        lockField(alamatField);
        formPanel.add(alamatField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 0;
        telpLabel = new JLabel("No. Telp");
        formPanel.add(telpLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 1;
        telpField = new JTextField();
        lockField(telpField);
        formPanel.add(telpField, gbcFormPanel);

        gbcFormPanel.gridx = 2; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 0;
        paketLabel = new JLabel("Jenis Paket");
        formPanel.add(paketLabel, gbcFormPanel);

        gbcFormPanel.gridx = 3; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 0.5;
        String[] paketArray = {
                "20 Mbps", "30 Mbps", "50 Mbps", "100 Mbps"
        };
        paketBox = new JComboBox<>(paketArray);
        paketBox.addActionListener(e -> updateBiayaField());
        formPanel.add(paketBox, gbcFormPanel);

        gbcFormPanel.gridx = 2; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 0;
        tanggalLabel = new JLabel("Tanggal");
        formPanel.add(tanggalLabel, gbcFormPanel);

        //tanggalPanel untuk comboBox tanggal
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

        gbcFormPanel.gridx = 3; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 0.5;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(tanggalPanel, gbcFormPanel);

        gbcFormPanel.gridx = 2; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0;
        biayaLabel = new JLabel("Biaya");
        formPanel.add(biayaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 3; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0.5;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        biayaField = new JTextField("");
        lockField(biayaField);
        formPanel.add(biayaField, gbcFormPanel);

        gbcFormPanel.gridx = 2; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 0;
        statusLabel = new JLabel("Status");
        formPanel.add(statusLabel, gbcFormPanel);

        gbcFormPanel.gridx = 3; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 0.5;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        String statusArray[] = {"Sudah Bayar", "Belum Bayar"};
        statusBox = new JComboBox<>(statusArray);
        formPanel.add(statusBox, gbcFormPanel);

        //button Panel
        buttonPanel = new JPanel(new GridBagLayout());
        //rightbuttonPanel.setBorder(BorderFactory.createTitledBorder("Right Button panel"));
        GridBagConstraints gbcRightButton = new GridBagConstraints();
        gbcRightButton.insets = new Insets(5,5,5,5);
        gbcRightButton.anchor = GridBagConstraints.WEST;
        gbcRightButton.fill = GridBagConstraints.HORIZONTAL;

        bayarButton = new JButton(" Proses Bayar");
        bayarButton.addActionListener(e -> {
            prosesBayar();
            refreshCardData();
        });
        gbcRightButton.gridx = 0; gbcRightButton.gridy = 0;
        gbcRightButton.weightx = 0.5;
        buttonPanel.add(bayarButton, gbcRightButton);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            loadDataPelanggan();
            refreshCardData();
            clearForm();
        });
        gbcRightButton.gridx = 0; gbcRightButton.gridy = 1;
        gbcRightButton.weightx = 0.5;
        buttonPanel.add(refreshButton, gbcRightButton);

        //buttonPanel ke formPanel
        gbcFormPanel.gridx = 3; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 0.5;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(buttonPanel, gbcFormPanel);

        //Tambah formPanel ke rightPanel
        gbcRight.gridx = 0; gbcRight.gridy = 0;
        gbcRight.weightx = 1;
        rightPanel.add(formPanel, gbcRight);


        //bottom panel
        bottomPanel = new JPanel(new GridBagLayout());
        //bottomPanel.setBorder(BorderFactory.createTitledBorder("Bottom Panel"));
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.insets = new Insets(5,5,5,5);

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



        //Tabel Data Pelanggan
        datapelangganModel = new DefaultTableModel(
                new String[]{"Kode Pelanggan", "Nama", "Alamat", "Telp", "Paket", "Tanggal", "Biaya", "Status"}, 0
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        datapelangganTable = new JTable(datapelangganModel);
        tableScroll = new JScrollPane(datapelangganTable);
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
        //listener ketika row pada tabel diklik
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

                    kodepelangganField.setText(kodepelanggan);
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


        gbcTop.gridx = 0; gbcTop.gridy = 0;
        gbcTop.weightx = 0;
        gbcTop.fill = GridBagConstraints.NONE;
        gbcTop.anchor = GridBagConstraints.NORTHWEST;
        topPanel.add(cardPanel, gbcTop);

        gbcTop.gridx = 1; gbcTop.gridy = 0;
        gbcTop.weightx = 1;
        gbcTop.fill = GridBagConstraints.BOTH;
        gbcTop.anchor = GridBagConstraints.NORTH;
        topPanel.add(rightPanel, gbcTop);

        gbcBottom.gridx = 0; gbcBottom.gridy = 0;
        gbcBottom.weightx = 1;
        gbcBottom.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(cariPanel, gbcBottom);

        gbcBottom.gridx = 0; gbcBottom.gridy = 1;
        gbcBottom.weightx = 1; gbcBottom.weighty = 1;
        gbcBottom.fill = GridBagConstraints.BOTH;
        bottomPanel.add(tableScroll, gbcBottom);

        //Add top & bottom panel ke HomePanel
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);


        loadDataPelanggan();

    }

    private void refreshCardData(){
        String totalaktif = totalPelangganAktif(connection);
        ((CreateCardPanel) card1).updateValue(totalaktif);

        String totalnonaktif = totalPelangganNonaktif(connection);
        ((CreateCardPanel) card2).updateValue(totalnonaktif);

        String totaltransaksi = totalTransaksiBulan(connection);
        ((CreateCardPanel) card3).updateValue(totaltransaksi);

        String totalpendapatan = totalPendapatanBulan(connection);
        ((CreateCardPanel) card4).updateValue(totalpendapatan);

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private String totalPelangganAktif(Connection connection){
        int count  = 0;
        String query = "SELECT COUNT(*) AS total_aktif FROM tb_datapelanggan WHERE status = TRUE";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                count = rs.getInt("total_aktif");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error menghitung pelanggan aktif: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return String.valueOf(count);
    }

    private String totalPelangganNonaktif(Connection connection){
        int count  = 0;
        String query = "SELECT COUNT(*) AS total_nonaktif FROM tb_datapelanggan WHERE status = FALSE";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                count = rs.getInt("total_nonaktif");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error menghitung pelanggan nonaktif: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return String.valueOf(count);
    }

    private String totalTransaksiBulan(Connection connection){
        int count = 0;
        String query = "SELECT COUNT(*) AS total_transaksi " +
                "FROM tb_datatransaksi WHERE " +
                "MONTH(tanggal) = MONTH(CURDATE()) " +
                "AND YEAR(tanggal) = YEAR(CURDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                count = rs.getInt("total_transaksi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error menghitung total transaksi: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return String.valueOf(count);
    }

    private String totalPendapatanBulan(Connection connection){
        String query = "SELECT SUM(biaya) AS total_biaya " +
                "FROM tb_datatransaksi WHERE " +
                "MONTH(tanggal) = MONTH(CURDATE()) AND " +
                "YEAR(tanggal) = YEAR(CURDATE()) " +
                "AND status = TRUE";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int total = rs.getInt("total_biaya");
                return "Rp. " + String.format("%,d", total).replace(",", ".");
            }
        } catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error menghitung total pendapatan: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return "Rp. 0";
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

    private void clearForm() {
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
        datapelangganTable.getSelectionModel().clearSelection();
    }

    private void lockField(JTextField field){
        field.setEditable(false);
        field.setFocusable(false);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setBorder(UIManager.getBorder("TextField.border"));
    }


    //Function untuk generate kodeTransaksi dari kodePelanggan dan tanggal pada tb_datapelanggan
    private String generateKodeTransaksi(Connection connection, String kodePelanggan, Date tanggal){
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

    private void prosesBayar() {
        if (datapelangganTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this,
                    "Silakan pilih data pelanggan dari tabel terlebih dahulu.",
                    "Pilih Pelanggan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String insertQuery = "INSERT INTO tb_datatransaksi " +
                "(kodetransaksi, kodepelanggan, nama, paket, tanggal, biaya, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String updateQuery = "UPDATE tb_datapelanggan " +
                "SET paket = ?, tanggal = ?, biaya = ?, status = true " +
                "WHERE kodepelanggan = ?";



        //Deklarasi variabel untuk insert
        String kodepelanggan = kodepelangganField.getText();
        String nama = namaField.getText();

        //Deklarasi variabel paket dari combobox paketBox
        String paket = (String) paketBox.getSelectedItem();

        //item combobox tanggal ke variabel sqlDate
        int day = (int) dayBox.getSelectedItem();
        int month = (int) monthBox.getSelectedItem();
        int year = (int) yearBox.getSelectedItem();

        LocalDate localDate;
        try {
            localDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(this,
                    "Tanggal yang dipilih tidak valid!",
                    "Tanggal Salah",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date tanggal = Date.valueOf(localDate);

        int biaya = Integer.parseInt(biayaField.getText());

        //deklarasi variabel status dari combobox statusBox
        String selectedStatus = (String) statusBox.getSelectedItem();
        boolean status = false;
        if (selectedStatus.equals("Sudah Bayar")) {
            status = true;
        } else if (selectedStatus.equals("Belum Bayar")) {
            status = false;
        }

        String kodeTransaksi = generateKodeTransaksi(connection, kodepelanggan, tanggal);

        if (kodepelangganField.getText().isEmpty() ||
                namaField.getText().isEmpty() ||
                alamatField.getText().isEmpty() ||
                telpField.getText().isEmpty() ||
                biayaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Data tidak lengkap! Mohon isi kembali",
                    "Data tidak lengkap",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else if (datapelangganModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tolong masukkan data terlebih dahulu!");
            return;
        }

        try {
            PreparedStatement insertPS = connection.prepareStatement(insertQuery);
            insertPS.setString(1, kodeTransaksi);
            insertPS.setString(2, kodepelanggan);
            insertPS.setString(3, nama);
            insertPS.setString(4, paket);
            insertPS.setDate(5, tanggal);
            insertPS.setInt(6, biaya);
            insertPS.setBoolean(7, status);

            insertPS.executeUpdate();

            if (status == true) {
                PreparedStatement updatePS = connection.prepareStatement(updateQuery);
                updatePS.setString(1, paket);
                updatePS.setDate(2, tanggal);
                updatePS.setInt(3, biaya);
                updatePS.setString(4, kodepelanggan);
                updatePS.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            clearForm();
            loadDataPelanggan();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format angka salah!:  " + e.getMessage());
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            clearForm();
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

}
