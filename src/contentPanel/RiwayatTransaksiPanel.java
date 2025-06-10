package contentPanel;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class RiwayatTransaksiPanel extends JPanel {
    //deklarasi variable
    private JPanel filterPanel, dariTanggalPanel, sampaiTanggalPanel, tablePanel;
    private JLabel dariTanggalLabel, sampaiTanggalLabel, cariLabel, statusLabel;
    private JTextField cariField;
    private JComboBox<Integer> dariDayBox, dariMonthBox, dariYearBox, sampaiDayBox, sampaiMonthBox, sampaiYearBox;
    private JComboBox<String> statusBox;
    private JButton filterButton, refreshButton;
    private DefaultTableModel datatransaksiModel;
    private JTable datatransaksiTable;

    private Connection connection;
    public RiwayatTransaksiPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        connection = DatabaseConnection.getConnection();

        //filterPanel pada top layout
        filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter"));
        GridBagConstraints gbcFilterPanel = new GridBagConstraints();
        gbcFilterPanel.insets = new Insets(5,5,5,5);
        gbcFilterPanel.fill = GridBagConstraints.BOTH;

        //Deklarasi array untuk combo box tanggal
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

        //Combo box dari Tanggal
        dariTanggalPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDariTanggal = new GridBagConstraints();

        dariDayBox = new JComboBox<>(days);
        dariMonthBox = new JComboBox<>(months);
        dariYearBox = new JComboBox<>(years);

        gbcDariTanggal.gridx = 0; gbcDariTanggal.weightx = 1;
        gbcDariTanggal.fill = GridBagConstraints.HORIZONTAL;
        dariTanggalPanel.add(dariDayBox, gbcDariTanggal);

        gbcDariTanggal.gridx = 1; gbcDariTanggal.weightx = 1;
        gbcDariTanggal.fill = GridBagConstraints.HORIZONTAL;
        dariTanggalPanel.add(dariMonthBox, gbcDariTanggal);

        gbcDariTanggal.gridx = 2; gbcDariTanggal.weightx = 1;
        gbcDariTanggal.fill = GridBagConstraints.HORIZONTAL;
        dariTanggalPanel.add(dariYearBox, gbcDariTanggal);

        //Combo box ke Tanggal
        sampaiTanggalPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcSampaitanggal = new GridBagConstraints();

        sampaiDayBox = new JComboBox<>(days);
        sampaiMonthBox = new JComboBox<>(months);
        sampaiYearBox = new JComboBox<>(years);

        gbcSampaitanggal.gridx = 0; gbcSampaitanggal.weightx = 1;
        gbcSampaitanggal.fill = GridBagConstraints.HORIZONTAL;
        sampaiTanggalPanel.add(sampaiDayBox, gbcSampaitanggal);

        gbcSampaitanggal.gridx = 1; gbcSampaitanggal.weightx = 1;
        gbcSampaitanggal.fill = GridBagConstraints.HORIZONTAL;
        sampaiTanggalPanel.add(sampaiMonthBox, gbcSampaitanggal);

        gbcSampaitanggal.gridx = 2; gbcSampaitanggal.weightx = 1;
        gbcSampaitanggal.fill = GridBagConstraints.HORIZONTAL;
        sampaiTanggalPanel.add(sampaiYearBox, gbcSampaitanggal);


        //add label, field, combobox ke filterPanel
        gbcFilterPanel.gridx = 0; gbcFilterPanel.gridy = 0;
        gbcFilterPanel.weightx = 0;
        dariTanggalLabel = new JLabel("Dari Tanggal:");
        filterPanel.add(dariTanggalLabel, gbcFilterPanel);

        gbcFilterPanel.gridx = 1; gbcFilterPanel.gridy = 0;
        gbcFilterPanel.weightx = 1;
        filterPanel.add(dariTanggalPanel, gbcFilterPanel);

        gbcFilterPanel.gridx = 2; gbcFilterPanel.gridy = 0;
        gbcFilterPanel.weightx = 0;
        sampaiTanggalLabel = new JLabel("Sampai Tanggal:");
        filterPanel.add(sampaiTanggalLabel, gbcFilterPanel);

        gbcFilterPanel.gridx = 3; gbcFilterPanel.gridy = 0;
        gbcFilterPanel.weightx = 1;
        filterPanel.add(sampaiTanggalPanel, gbcFilterPanel);

        gbcFilterPanel.gridx = 4; gbcFilterPanel.gridy = 0;
        gbcFilterPanel.weightx = 1;
        filterButton = new JButton("Terapkan Filter");
        filterButton.addActionListener(e -> filterData());
        filterPanel.add(filterButton, gbcFilterPanel);

        gbcFilterPanel.gridx = 0; gbcFilterPanel.gridy = 1;
        gbcFilterPanel.weightx = 0;
        cariLabel = new JLabel("ID/Kode/Nama");
        filterPanel.add(cariLabel, gbcFilterPanel);

        gbcFilterPanel.gridx = 1; gbcFilterPanel.gridy = 1;
        gbcFilterPanel.weightx = 1;
        cariField = new JTextField();
        filterPanel.add(cariField, gbcFilterPanel);

        gbcFilterPanel.gridx = 2; gbcFilterPanel.gridy = 1;
        gbcFilterPanel.weightx = 0;
        statusLabel = new JLabel("Status");
        filterPanel.add(statusLabel, gbcFilterPanel);

        gbcFilterPanel.gridx = 3; gbcFilterPanel.gridy = 1;
        gbcFilterPanel.weightx = 1;
        String status[] = {"Semua", "Sudah Bayar", "Belum Bayar"};
        statusBox = new JComboBox<>(status);
        filterPanel.add(statusBox, gbcFilterPanel);

        gbcFilterPanel.gridx = 4; gbcFilterPanel.gridy = 1;
        gbcFilterPanel.weightx = 1;
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadDataTransaksi());
        filterPanel.add(refreshButton, gbcFilterPanel);

        //panel tablePanel
        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Data Transaksi"));
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.insets = new Insets(5,5,5,5);
        gbcTable.fill = GridBagConstraints.BOTH;

        //tabel data pelanggan
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

        gbcTable.gridx = 0; gbcPanel.gridy = 0;
        gbcTable.weightx = 1; gbcTable.weighty = 1;
        tablePanel.add(tableScroll, gbcTable);

        //add panel ke panel utama
        gbcPanel.gridx = 0; gbcPanel.gridy = 0;
        gbcPanel.weightx = 1; gbcPanel.weighty = 0;
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.anchor = GridBagConstraints.NORTH;
        add(filterPanel, gbcPanel);

        gbcPanel.gridx = 0; gbcPanel.gridy = 1;
        gbcPanel.weightx = 1; gbcPanel.weighty = 1;
        gbcPanel.fill = GridBagConstraints.BOTH;
        add(tablePanel, gbcPanel);

        loadDataTransaksi();
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

    private void filterData(){
        Date dariTanggal = null;
        Date sampaiTanggal = null;
        String cariText = cariField.getText().trim();
        String statusTerpilih = (String) statusBox.getSelectedItem();

        try{
            //get dariTanggal
            int dariDay = (int) dariDayBox.getSelectedItem();
            int dariMonth = (int) dariMonthBox.getSelectedItem() - 1; // Calendar months are 0-indexed
            int dariYear = (int) dariYearBox.getSelectedItem();
            Calendar dariCalendar = Calendar.getInstance();
            dariCalendar.set(dariYear, dariMonth, dariDay, 0, 0, 0);
            dariCalendar.set(Calendar.MILLISECOND, 0);
            dariTanggal = dariCalendar.getTime();

            //get sampaiTangal
            int sampaiDay = (int) sampaiDayBox.getSelectedItem();
            int sampaiMonth = (int) sampaiMonthBox.getSelectedItem() - 1;
            int sampaiYear = (int) sampaiYearBox.getSelectedItem();
            Calendar sampaiCalendar = Calendar.getInstance();
            sampaiCalendar.set(sampaiYear, sampaiMonth, sampaiDay, 23, 59, 59);
            sampaiCalendar.set(Calendar.MILLISECOND, 999);
            sampaiTanggal = sampaiCalendar.getTime();

            if (dariTanggal.after(sampaiTanggal)){
                JOptionPane.showMessageDialog(this, "Tanggal 'Dari' tidak boleh lebih dari tanggal 'Sampai'.", "Kesalahan Input Tanggal", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Format tanggal tidak valid. Silakan periksa pilihan tanggal Anda.", "Kesalahan Tanggal", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        loadDataTransaksi(dariTanggal, sampaiTanggal, cariText, statusTerpilih);
    }

    private void loadDataTransaksi(Date dariTanggal, Date sampaiTanggal, String cariText, String statusTerpilih){
        PreparedStatement loadState = null;
        ResultSet resultSet = null;
        datatransaksiModel.setRowCount(0);
        int rowCount = 0;

        try{
            StringBuilder loadQuery = new StringBuilder("SELECT kodetransaksi, kodepelanggan, nama, paket, tanggal, biaya, status FROM tb_datatransaksi");
            boolean filterditambahkan = false;

            //filter tanggal
            if (dariTanggal != null && sampaiTanggal != null){
                loadQuery.append(" WHERE tanggal BETWEEN ? AND ?");
                filterditambahkan = true;
            }

            //filter cariText
            if (cariText != null && !cariText.isEmpty()){
                if (!filterditambahkan){
                    loadQuery.append(" WHERE ");
                    filterditambahkan = true;
                } else {
                    loadQuery.append(" AND ");
                }
                loadQuery.append("(kodetransaksi LIKE ? OR kodepelanggan LIKE ? OR nama LIKE ?)");
            }

            //filter status
            if (statusTerpilih != null && !statusTerpilih.equals("Semua")) {
                if (!filterditambahkan){
                    loadQuery.append(" WHERE ");
                    filterditambahkan = true;
                } else {
                    loadQuery.append(" AND ");
                }
                loadQuery.append("status = ?");
            }

            loadState = connection.prepareStatement(loadQuery.toString());
            int parameterIndex = 1;

            //Set parameter untuk tanggal
            if (dariTanggal != null && sampaiTanggal != null){
                loadState.setDate(parameterIndex++, new java.sql.Date(dariTanggal.getTime()));
                loadState.setDate(parameterIndex++, new java.sql.Date(sampaiTanggal.getTime()));
            }

            //Set parameter untuk cariText
            if (cariText != null && !cariText.isEmpty()){
                loadState.setString(parameterIndex++, "%" + cariText + "%");
                loadState.setString(parameterIndex++, "%" + cariText + "%");
                loadState.setString(parameterIndex++, "%" + cariText + "%");
            }

            //Set parameter untuk statusTerpilih
            if (statusTerpilih != null && !statusTerpilih.equals("Semua")){
                boolean nilaiStatus = statusTerpilih.equals("Sudah Bayar");
                loadState.setBoolean(parameterIndex++, nilaiStatus);
            }

            resultSet = loadState.executeQuery();
            while(resultSet.next()) {
                Object[] row = {
                        resultSet.getString("kodetransaksi"),
                        resultSet.getString("kodepelanggan"),
                        resultSet.getString("nama"),
                        resultSet.getString("paket"),
                        resultSet.getDate("tanggal"),
                        resultSet.getInt("biaya"),
                        resultSet.getBoolean("status"), // Fetch as boolean
                };
                datatransaksiModel.addRow(row);
                rowCount++;
            }
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Filter berhasil diterapkan. Ditemukan " + rowCount + " data transaksi.", "Filter Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Filter berhasil diterapkan. Tidak ada data transaksi yang ditemukan.", "Filter Berhasil", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data dengan filter: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



}
