package contentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class DataPelangganPanel extends JPanel {
    public DataPelangganPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        //left container
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("leftPanel"));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5,5,5,5);

        //formPanel
        JPanel formPanel = new JPanel(new GridBagLayout());
        //formPanel.setBorder(BorderFactory.createTitledBorder("formPanel"));
        GridBagConstraints gbcFormPanel = new GridBagConstraints();
        gbcFormPanel.insets = new Insets(5,5,5,5);
        gbcFormPanel.anchor = GridBagConstraints.WEST;

        //tambah label & textfield di formPanel
        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 0;
        JLabel idLabel = new JLabel("ID");
        formPanel.add(idLabel, gbcFormPanel);


        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        JTextField idField = new JTextField();
        formPanel.add(idField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 0;
        JLabel namaLabel = new JLabel("Nama Pelanggan");
        formPanel.add(namaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        JTextField namaField = new JTextField();
        formPanel.add(namaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0;
        JLabel alamatLabel = new JLabel("Alamat");
        formPanel.add(alamatLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        JTextField alamatField = new JTextField();
        formPanel.add(alamatField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 0;
        JLabel jenisLabel = new JLabel("Jenis Paket");
        formPanel.add(jenisLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 1;
        String[] jenis = {
                "20 Mbps", "30 Mbps", "50 Mbps", "100 Mbps"
        };
        JComboBox<String> jenisBox = new JComboBox<>(jenis);
        formPanel.add(jenisBox, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 0;
        JLabel tanggalLabel = new JLabel("Tanggal");
        formPanel.add(tanggalLabel, gbcFormPanel);

        //Combobox tanggal
        JPanel tanggalPanel = new JPanel(new GridBagLayout());
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
        JComboBox<Integer> dayBox = new JComboBox<>(days);
        JComboBox<Integer> monthBox = new JComboBox<>(months);
        JComboBox<Integer> yearBox = new JComboBox<>(years);

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
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(tanggalPanel, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 0;
        JLabel biayaLabel = new JLabel("Biaya");
        formPanel.add(biayaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        JTextField biayaField = new JTextField();
        formPanel.add(biayaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 6;
        gbcFormPanel.weightx = 0;
        JLabel statusLabel = new JLabel("Status");
        formPanel.add(statusLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 6;
        gbcFormPanel.weightx = 1;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;
        String status[] = {"Sudah Bayar", "Belum Bayar"};
        JComboBox<String> statusBox = new JComboBox<>(status);
        formPanel.add(statusBox, gbcFormPanel);

        //left button container
        JPanel leftbuttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcLeftButton = new GridBagConstraints();
        gbcLeftButton.insets = new Insets(0, 5, 0, 5);
        gbcLeftButton.fill = GridBagConstraints.HORIZONTAL;

        gbcLeftButton.gridx = 0;
        gbcLeftButton.weightx = 1;
        JButton tambahButton = new JButton("Tambah");
        leftbuttonPanel.add(tambahButton, gbcLeftButton);

        gbcLeftButton.gridx = 1;
        gbcLeftButton.weightx = 1;
        JButton editButton = new JButton("Edit");
        leftbuttonPanel.add(editButton, gbcLeftButton);

        gbcLeftButton.gridx = 2;
        gbcLeftButton.weightx = 1;
        JButton hapusButton = new JButton("Hapus");
        leftbuttonPanel.add(hapusButton, gbcLeftButton);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 7;
        gbcFormPanel.weightx = 1;
        formPanel.add(leftbuttonPanel, gbcFormPanel);





        //Tambah formPanel ke leftPanel
        gbcLeft.gridx = 0; gbcLeft.gridy = 0;
        gbcLeft.weightx =  1; gbcLeft.weighty = 1;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(formPanel, gbcLeft);


        //right container
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("rightPanel"));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5,5,5,5);

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

        gbcCari.gridx = 2; gbcCari.gridy = 0;
        gbcCari.weightx = 0;
        JButton refreshButton = new JButton("Refresh");
        cariPanel.add(refreshButton, gbcCari);

        String[] columnNames = {"ID", "Nama Pelangan", "Alamat", "Jenis", "Tanggal", "Biaya", "Status"};
        Object[][] data = {
                {1, "Bro", "Luar Angkasa", "100 Mbps", "20-05-2025", "250000", "Belum Bayar"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));

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





    }

}
