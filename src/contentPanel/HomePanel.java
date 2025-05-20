package contentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BorderLayout());

        //Top Container
        JPanel topPanel = new JPanel(new GridBagLayout());
        //topPanel.setBorder(BorderFactory.createTitledBorder("Top Panel"));
        GridBagConstraints gbcTop = new GridBagConstraints();
        gbcTop.insets = new Insets(5,5,5,5);

        //Card Panel
        JPanel cardPanel = new JPanel(new GridBagLayout());
        //cardPanel.setBorder(BorderFactory.createTitledBorder("Card Panel"));
        GridBagConstraints gbcCard = new GridBagConstraints();
        gbcCard.insets = new Insets(5,5,5,5);

        gbcCard.gridx = 0; gbcCard.gridy = 0;
        JPanel card1 = new CreateCardPanel(
                "Jumlah Pelanggan Aktif", "250", "Pelanggan yang sudah membayar."
        );
        cardPanel.add(card1, gbcCard);

        gbcCard.gridx = 1; gbcCard.gridy = 0;
        JPanel card2 = new CreateCardPanel(
                "Pelanggan Nonaktif", "0", "Pelanggan yang belum membayar."
        );
        cardPanel.add(card2, gbcCard);

        gbcCard.gridx = 0; gbcCard.gridy = 1;
        JPanel card3 = new CreateCardPanel("Jumlah Pembayaran", "329", "Jumlah pembayaran bulan ini.");
        cardPanel.add(card3, gbcCard);

        gbcCard.gridx = 1; gbcCard.gridy = 1;
        JPanel card4 = new CreateCardPanel("Jumlah Pendapatan", "2500000", "Jumlah pendapatan bulan ini.");
        cardPanel.add(card4, gbcCard);

        //Right Panel
        JPanel rightPanel = new JPanel(new GridBagLayout());
        //rightPanel.setBorder(BorderFactory.createTitledBorder("Right Panel"));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5,5,5,5);
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;


        //Right Top Panel
        JPanel rightTopPanel = new JPanel(new GridBagLayout());
        //rightTopPanel.setBorder(BorderFactory.createTitledBorder("Right Top"));
        GridBagConstraints gbcRightTop = new GridBagConstraints();
        gbcRightTop.insets = new Insets(5,5,5,5);
        gbcRightTop.fill = GridBagConstraints.HORIZONTAL;

        //Right Bottom Panel
        JPanel rightBottomPanel = new JPanel(new GridBagLayout());
        //rightBottomPanel.setBorder(BorderFactory.createTitledBorder("Right Bottom"));
        GridBagConstraints gbcRightBottom = new GridBagConstraints();
        gbcRightBottom.insets = new Insets(5,5,5,5);
        gbcRightBottom.fill = GridBagConstraints.HORIZONTAL;

        //Right Label
        JPanel rightlabelPanel = new JPanel(new GridBagLayout());
        //rightlabelPanel.setBorder(BorderFactory.createTitledBorder("Right Label"));
        GridBagConstraints gbcRightLabel = new GridBagConstraints();
        gbcRightLabel.insets = new Insets(5,5,5,5);
        gbcRightLabel.anchor = GridBagConstraints.WEST;

        gbcRightLabel.gridx = 0; gbcRightLabel.gridy = 0;
        gbcRightLabel.weightx = 0;
        JLabel idLabel = new JLabel("ID Pelanggan");
        rightlabelPanel.add(idLabel, gbcRightLabel);

        gbcRightLabel.gridx = 1; gbcRightLabel.gridy = 0;
        gbcRightLabel.weightx = 1;
        gbcRightLabel.fill = GridBagConstraints.HORIZONTAL;
        JTextField idPelanggan = new JTextField("id");
        idPelanggan.setEditable(false);
        idPelanggan.setFocusable(false);
        rightlabelPanel.add(idPelanggan, gbcRightLabel);

        gbcRightLabel.gridx = 0; gbcRightLabel.gridy = 1;
        gbcRightLabel.weightx = 0;
        JLabel namaLabel = new JLabel("Nama Pelanggan");
        rightlabelPanel.add(namaLabel, gbcRightLabel);

        gbcRightLabel.gridx = 1; gbcRightLabel.gridy = 1;
        gbcRightLabel.weightx = 1;
        JTextField namaPelanggan = new JTextField("pelanggan");
        namaPelanggan.setEditable(false);
        namaPelanggan.setFocusable(false);
        rightlabelPanel.add(namaPelanggan, gbcRightLabel);

        gbcRightLabel.gridx = 0; gbcRightLabel.gridy = 2;
        gbcRightLabel.weightx = 0;
        JLabel alamatLabel = new JLabel("Alamat");
        rightlabelPanel.add(alamatLabel, gbcRightLabel);

        gbcRightLabel.gridx = 1; gbcRightLabel.gridy = 2;
        gbcRightLabel.weightx = 1;
        JTextField alamatPelanggan = new JTextField("alamat");
        alamatPelanggan.setEditable(false);
        alamatPelanggan.setFocusable(false);
        rightlabelPanel.add(alamatPelanggan, gbcRightLabel);

        gbcRightLabel.gridx = 2; gbcRightLabel.gridy = 0;
        gbcRightLabel.weightx = 0;
        JLabel jenisLabel = new JLabel("Jenis Langganan");
        rightlabelPanel.add(jenisLabel, gbcRightLabel);

        gbcRightLabel.gridx = 3; gbcRightLabel.gridy = 0;
        gbcRightLabel.weightx = 1;
        JTextField jenisLangganan = new JTextField("jenis");
        jenisLangganan.setEditable(false);
        jenisLangganan.setFocusable(false);
        rightlabelPanel.add(jenisLangganan, gbcRightLabel);

        gbcRightLabel.gridx = 2; gbcRightLabel.gridy = 1;
        gbcRightLabel.weightx = 0;
        JLabel tanggalLabel = new JLabel("Tanggal Langganan");
        rightlabelPanel.add(tanggalLabel, gbcRightLabel);

        gbcRightLabel.gridx = 3; gbcRightLabel.gridy = 1;
        gbcRightLabel.weightx = 1;
        JTextField tanggalLangganan = new JTextField("tanggal");
        tanggalLangganan.setEditable(false);
        tanggalLangganan.setFocusable(false);
        rightlabelPanel.add(tanggalLangganan, gbcRightLabel);

        gbcRightLabel.gridx = 2; gbcRightLabel.gridy = 2;
        gbcRightLabel.weightx = 0;
        JLabel statusLabel = new JLabel("Status Langganan");
        rightlabelPanel.add(statusLabel, gbcRightLabel);

        gbcRightLabel.gridx = 3; gbcRightLabel.gridy = 2;
        gbcRightLabel.weightx = 1;
        JTextField statusLangganan = new JTextField("status");
        statusLangganan.setEditable(false);
        statusLangganan.setFocusable(false);
        rightlabelPanel.add(statusLangganan, gbcRightLabel);


        //Right Jenis Langganan dan Tanggal

        JPanel comboPanel = new JPanel(new GridBagLayout());
        //comboPanel.setBorder(BorderFactory.createTitledBorder("Combo Panel"));
        GridBagConstraints gbcCombo = new GridBagConstraints();
        gbcCombo.insets = new Insets(5,5,5,5);
        gbcCombo.anchor = GridBagConstraints.WEST;

        JPanel jenisComboPanel = new JPanel(new GridBagLayout());
        //jenisComboPanel.setBorder(BorderFactory.createTitledBorder("Jenis Paket"));
        GridBagConstraints gbcJenisCombo = new GridBagConstraints();
        gbcJenisCombo.insets = new Insets(5,5,5,5);
        gbcJenisCombo.anchor = GridBagConstraints.WEST;

        JPanel dateComboPanel = new JPanel(new GridBagLayout());
        //dateComboPanel.setBorder(BorderFactory.createTitledBorder("Tanggal Langganan"));
        GridBagConstraints gbcDateCombo = new GridBagConstraints();
        gbcDateCombo.insets = new Insets(0,5,0,5);
        gbcDateCombo.anchor = GridBagConstraints.WEST;

        //Array untuk combobox

        String[] jenis = {
                "20 Mbps", "30 Mbps", "50 Mbps", "100 Mbps"
        };

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

        JComboBox<String> jenisBox = new JComboBox<>(jenis);
        JComboBox<Integer> dayBox = new JComboBox<>(days);
        JComboBox<Integer> monthBox = new JComboBox<>(months);
        JComboBox<Integer> yearBox = new JComboBox<>(years);


        gbcJenisCombo.gridx = 0; gbcJenisCombo.gridy = 0;
        gbcJenisCombo.weightx = 0;
        JLabel paketLabel = new JLabel("Jenis Paket:");
        jenisComboPanel.add(paketLabel, gbcJenisCombo);
        gbcJenisCombo.gridx = 1; gbcJenisCombo.gridy = 0;
        gbcJenisCombo.weightx = 1; gbcJenisCombo.fill = GridBagConstraints.HORIZONTAL;
        jenisComboPanel.add(jenisBox, gbcJenisCombo);


        gbcDateCombo.gridx = 0; gbcDateCombo.gridy = 0;
        JLabel dateLabel = new JLabel("Tanggal Langganan:");
        dateComboPanel.add(dateLabel, gbcDateCombo);
        gbcDateCombo.gridx = 1; gbcDateCombo.gridy = 0;
        gbcDateCombo.weightx = 1;
        gbcDateCombo.fill = GridBagConstraints.HORIZONTAL;
        dateComboPanel.add(dayBox, gbcDateCombo);
        gbcDateCombo.gridx = 2; gbcDateCombo.gridy = 0;
        gbcDateCombo.weightx = 1;
        gbcDateCombo.fill = GridBagConstraints.HORIZONTAL;
        dateComboPanel.add(monthBox, gbcDateCombo);
        gbcDateCombo.gridx = 3; gbcDateCombo.gridy = 0;
        gbcDateCombo.weightx = 1;
        gbcDateCombo.fill = GridBagConstraints.HORIZONTAL;
        dateComboPanel.add(yearBox, gbcDateCombo);


        //Right Button Panel
        JPanel rightbuttonPanel = new JPanel(new GridBagLayout());
        //rightbuttonPanel.setBorder(BorderFactory.createTitledBorder("Right Button panel"));
        GridBagConstraints gbcRightButton = new GridBagConstraints();
        gbcRightButton.insets = new Insets(5,5,5,5);
        gbcRightButton.anchor = GridBagConstraints.WEST;
        gbcRightButton.fill = GridBagConstraints.HORIZONTAL;

        JButton bayarButton = new JButton(" Proses Bayar");
        gbcRightButton.gridx = 0; gbcRightButton.gridy = 0;
        gbcRightButton.weightx = 0.5;
        rightbuttonPanel.add(bayarButton, gbcRightButton);

        JButton konfirmButton = new JButton("Konfirmasi Bayar");
        gbcRightButton.gridx = 0; gbcRightButton.gridy = 1;
        gbcRightButton.weightx = 0.5;
        rightbuttonPanel.add(konfirmButton, gbcRightButton);

        //Tambah rightlabelPanel ke rightTop
        gbcRightTop.gridx = 0; gbcRightTop.gridy = 0;
        gbcRightTop.weightx = 1; gbcRightTop.weighty = 0;
        gbcRightTop.fill = GridBagConstraints.BOTH;
        gbcRightTop.anchor = GridBagConstraints.NORTH;
        rightTopPanel.add(rightlabelPanel, gbcRightTop);

        //Tambah 2 combo ke combopanel
        gbcCombo.gridx = 0; gbcCombo.gridy = 0;
        gbcCombo.weightx = 1; gbcCombo.fill = GridBagConstraints.HORIZONTAL;
        comboPanel.add(jenisComboPanel, gbcCombo);
        gbcCombo.gridx = 0; gbcCombo.gridy = 1;
        gbcCombo.weightx = 1; gbcCombo.fill = GridBagConstraints.HORIZONTAL;
        comboPanel.add(dateComboPanel, gbcCombo);

        //Tambah comboPanel ke rightBottom
        gbcRightBottom.gridx = 0; gbcRightBottom.gridy = 0;
        gbcRightBottom.weightx = 1; gbcRightBottom.weighty = 1;
        rightBottomPanel.add(comboPanel, gbcRightBottom);

        //Tambah rightbuttonPanel ke rightBottom
        gbcRightBottom.gridx = 1; gbcRightBottom.gridy = 0;
        gbcRightBottom.weightx = 1; gbcRightBottom.weighty = 1;
        rightBottomPanel.add(rightbuttonPanel, gbcRightBottom);

        //Tambah rightTop & rightBottom ke rightPanel
        gbcRight.gridx = 0; gbcRight.gridy = 0;
        gbcRight.weightx = 1;
        rightPanel.add(rightTopPanel, gbcRight);

        gbcRight.gridx = 0; gbcRight.gridy = 1;
        gbcRight.weightx = 1;
        rightPanel.add(rightBottomPanel, gbcRight);


        //bottom panel
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        //bottomPanel.setBorder(BorderFactory.createTitledBorder("Bottom Panel"));
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.insets = new Insets(5,5,5,5);

        JPanel cariPanel = new JPanel(new GridBagLayout());
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



        //Table
        String[] columnNames = {"ID", "Nama", "Alamat", "Jenis", "Tanggal", "Status"};
        Object[][] data = {
                {1, "Bro", "Cipocok", "100 Mbps", "20-05-2025", "Belum Bayar"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));



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



        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

    }
}
