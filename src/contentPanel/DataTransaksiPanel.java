package contentPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class DataTransaksiPanel extends JPanel {
    public DataTransaksiPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        // Left Panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Data Transaksi"));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5, 5, 5, 5);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcFormPanel = new GridBagConstraints();
        gbcFormPanel.insets = new Insets(5, 5, 5, 5);
        gbcFormPanel.anchor = GridBagConstraints.WEST;
        gbcFormPanel.fill = GridBagConstraints.HORIZONTAL;

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 0;
        JLabel idLabel = new JLabel("ID");
        formPanel.add(idLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 0;
        gbcFormPanel.weightx = 1;
        JTextField idField = new JTextField();
        formPanel.add(idField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 0;
        JLabel namaLabel = new JLabel("Nama");
        formPanel.add(namaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 1;
        gbcFormPanel.weightx = 1;
        JTextField namaField = new JTextField();
        formPanel.add(namaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 0;
        JLabel jenisLabel = new JLabel("Jenis Paket");
        formPanel.add(jenisLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 2;
        gbcFormPanel.weightx = 1;
        String[] jenis = {"20 Mbps", "30 Mbps", "50 Mbps", "100 Mbps"};
        JComboBox<String> jenisBox = new JComboBox<>(jenis);
        formPanel.add(jenisBox, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 0;
        JLabel tanggalLabel = new JLabel("Tanggal");
        formPanel.add(tanggalLabel, gbcFormPanel);

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

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 3;
        gbcFormPanel.weightx = 1;
        formPanel.add(tanggalPanel, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 0;
        JLabel biayaLabel = new JLabel("Biaya");
        formPanel.add(biayaLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 4;
        gbcFormPanel.weightx = 1;
        JTextField biayaField = new JTextField();
        formPanel.add(biayaField, gbcFormPanel);

        gbcFormPanel.gridx = 0; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 0;
        JLabel statusLabel = new JLabel("Status");
        formPanel.add(statusLabel, gbcFormPanel);

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 5;
        gbcFormPanel.weightx = 1;
        String status[] = {"Sudah Bayar", "Belum Bayar"};
        JComboBox<String> statusBox = new JComboBox<>(status);
        formPanel.add(statusBox, gbcFormPanel);

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

        gbcFormPanel.gridx = 1; gbcFormPanel.gridy = 6;
        gbcFormPanel.weightx = 1;
        formPanel.add(leftbuttonPanel, gbcFormPanel);

        gbcLeft.gridx = 0; gbcLeft.gridy = 0;
        gbcLeft.weightx =  1;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(formPanel, gbcLeft);

        // Table Panel
        String[] columnNames = {"ID", "Nama", "Paket", "Tanggal", "Jumlah", "Status"};
        Object[][] data = {
                {"001", "Budi", "50 Mbps", "20-05-2025", "250000", "Belum Lunas"},
                {"002", "Siti", "30 Mbps", "21-05-2025", "200000", "Lunas"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane tableScroll = new JScrollPane(table);



        gbcLeft.gridx = 0; gbcLeft.gridy = 2;
        gbcLeft.fill = GridBagConstraints.BOTH;
        gbcLeft.weightx = 1;
        gbcLeft.weighty = 1;
        leftPanel.add(tableScroll, gbcLeft);

        // Right Panel
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Detail & Invoice"));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5, 5, 5, 5);
        gbcRight.fill = GridBagConstraints.HORIZONTAL;


        // Detail Panel
        JPanel detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBorder(BorderFactory.createTitledBorder("Detail Transaksi"));
        GridBagConstraints gbcDetail = new GridBagConstraints();
        gbcDetail.insets = new Insets(5, 5, 5, 5);
        gbcDetail.anchor = GridBagConstraints.WEST;

        JLabel idValueLabel = new JLabel();
        JLabel namaValueLabel = new JLabel();
        JLabel jenisValueLabel = new JLabel();
        JLabel tanggalValueLabel = new JLabel();
        JLabel biayaValueLabel = new JLabel();
        JLabel statusValueLabel = new JLabel();

        gbcDetail.gridx = 0; gbcDetail.gridy = 0;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("ID:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(idValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 1;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Nama:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(namaValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 2;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Paket:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(jenisValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 3;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Tanggal:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(tanggalValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 4;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Biaya:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(biayaValueLabel, gbcDetail);

        gbcDetail.gridx = 0; gbcDetail.gridy = 5;
        gbcDetail.weightx = 0;
        gbcDetail.fill = GridBagConstraints.NONE;
        detailPanel.add(new JLabel("Status:"), gbcDetail);

        gbcDetail.gridx = 1;
        gbcDetail.weightx = 1;
        gbcDetail.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(statusValueLabel, gbcDetail);


        // Invoice Panel
        JPanel invoicePanel = new JPanel(new GridBagLayout());
        invoicePanel.setBorder(BorderFactory.createTitledBorder("Invoice Text"));
        GridBagConstraints gbcInvoice = new GridBagConstraints();
        gbcInvoice.insets = new Insets(5, 5, 5, 5);

        JTextArea invoiceArea = new JTextArea(10, 20);
        invoiceArea.setLineWrap(true);
        invoiceArea.setWrapStyleWord(true);
        JScrollPane invoiceScroll = new JScrollPane(invoiceArea);

        JButton generateButton = new JButton("Generate Invoice");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String invoiceText =
                        "=== INVOICE TRANSAKSI ===\n" +
                                "ID Transaksi : " + idValueLabel.getText() + "\n" +
                                "Nama Pelanggan : " + namaValueLabel.getText() + "\n" +
                                "Paket : " + jenisValueLabel.getText() + "\n" +
                                "Tanggal : " + tanggalValueLabel.getText() + "\n" +
                                "Jumlah Bayar : Rp" + biayaValueLabel.getText() + "\n" +
                                "Status : " + statusValueLabel.getText() + "\n\n" +
                                "Silakan segera melakukan pembayaran. Terima kasih.";
                invoiceArea.setText(invoiceText);
            }
        });

        JButton copyButton = new JButton("Copy Invoice");
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

        // Table selection listener
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    idValueLabel.setText(table.getValueAt(selectedRow, 0).toString());
                    namaValueLabel.setText(table.getValueAt(selectedRow, 1).toString());
                    jenisValueLabel.setText(table.getValueAt(selectedRow, 2).toString());
                    tanggalValueLabel.setText(table.getValueAt(selectedRow, 3).toString());
                    biayaValueLabel.setText(table.getValueAt(selectedRow, 4).toString());
                    statusValueLabel.setText(table.getValueAt(selectedRow, 5).toString());
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
    }
}
