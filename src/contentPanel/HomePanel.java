package contentPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;


public class HomePanel extends JPanel {
    private JPanel topPanel, cardPanel, rightPanel, formPanel, buttonPanel, tanggalPanel, bottomPanel, cariPanel;
    private JLabel kodePelangganLabel, namaLabel, alamatLabel, telpLabel, tanggalLabel, paketLabel, biayaLabel, statusLabel;
    private JTextField kodepelangganField, namaField, alamatField, telpField, biayaField;
    private JComboBox<Integer> dayBox, monthBox, yearBox;
    private JComboBox<String> paketBox, statusBox;
    private JButton bayarButton, refreshButton;
    private DefaultTableModel datapelangganModel;
    private JTable datapelangganTable;

    public HomePanel() {
        setLayout(new BorderLayout());

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
        JPanel card1 = new CreateCardPanel("Jumlah Pelanggan Aktif", "250", "Pelanggan yang sudah membayar.");
        cardPanel.add(card1, gbcCard);

        gbcCard.gridx = 1; gbcCard.gridy = 0;
        JPanel card2 = new CreateCardPanel("Pelanggan Nonaktif", "0", "Pelanggan yang belum membayar.");
        cardPanel.add(card2, gbcCard);

        gbcCard.gridx = 0; gbcCard.gridy = 1;
        JPanel card3 = new CreateCardPanel("Jumlah Pembayaran", "329", "Jumlah pembayaran bulan ini.");
        cardPanel.add(card3, gbcCard);

        gbcCard.gridx = 1; gbcCard.gridy = 1;
        JPanel card4 = new CreateCardPanel("Jumlah Pendapatan", "800000", "Jumlah pendapatan bulan ini.");
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
        biayaField = new JTextField();
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
        gbcRightButton.gridx = 0; gbcRightButton.gridy = 0;
        gbcRightButton.weightx = 0.5;
        buttonPanel.add(bayarButton, gbcRightButton);

        refreshButton = new JButton("Refresh");
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
        JTextField cariField = new JTextField();
        cariPanel.add(cariField, gbcCari);

        gbcCari.gridx = 1; gbcCari.gridy = 0;
        gbcCari.weightx = 0;
        JButton cariButton = new JButton("Cari");
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
        JScrollPane tableScroll = new JScrollPane(datapelangganTable);
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

}
