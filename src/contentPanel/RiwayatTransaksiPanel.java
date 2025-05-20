package contentPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiwayatTransaksiPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField, dateFromField, dateToField;
    private JComboBox<String> statusFilterBox;
    private JLabel totalTransaksiLabel, totalLunasLabel, totalBelumLunasLabel;

    public RiwayatTransaksiPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();



        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter Transaksi"));
        GridBagConstraints gbcFilter = new GridBagConstraints();
        gbcFilter.insets = new Insets(5,5,5,5);
        gbcFilter.fill = GridBagConstraints.BOTH;


        dateFromField = new JTextField("2025-05-01");
        dateToField = new JTextField("2025-05-31");
        searchField = new JTextField();
        statusFilterBox = new JComboBox<>(new String[]{"Semua", "Lunas", "Belum Lunas"});
        JButton filterButton = new JButton("Terapkan Filter");

        gbcFilter.gridx = 0; gbcFilter.gridy = 0;
        gbcFilter.weightx = 0;
        filterPanel.add(new JLabel("Dari Tanggal:"), gbcFilter);
        gbcFilter.gridx = 1; gbcFilter.gridy = 0;
        gbcFilter.weightx = 1;
        filterPanel.add(dateFromField, gbcFilter);

        gbcFilter.gridx = 0; gbcFilter.gridy = 1;
        gbcFilter.weightx = 0;
        filterPanel.add(new JLabel("Sampai Tanggal:"), gbcFilter);
        gbcFilter.gridx = 1; gbcFilter.gridy = 1;
        filterPanel.add(dateToField, gbcFilter);

        gbcFilter.gridx = 2; gbcFilter.gridy = 0;
        gbcFilter.weightx = 0;
        filterPanel.add(new JLabel("Cari Nama/ID:"), gbcFilter);
        gbcFilter.gridx = 3; gbcFilter.gridy = 0;
        gbcFilter.weightx = 1;
        filterPanel.add(searchField, gbcFilter);

        gbcFilter.gridx = 2; gbcFilter.gridy = 1;
        gbcFilter.weightx = 0;
        filterPanel.add(new JLabel("Status:"), gbcFilter);
        gbcFilter.gridx = 3; gbcFilter.gridy = 1;
        gbcFilter.weightx = 1;
        filterPanel.add(statusFilterBox, gbcFilter);

        gbcFilter.gridx = 3; gbcFilter.gridy = 1;
        gbcFilter.weightx = 1;
        filterPanel.add(filterButton, gbcFilter);


        gbcPanel.gridx = 0; gbcPanel.gridy = 0;
        gbcPanel.weightx = 1; gbcPanel.weighty = 0;
        gbcPanel.anchor = GridBagConstraints.NORTH;
        gbcPanel.fill = GridBagConstraints.BOTH;
        add(filterPanel, gbcPanel);

        // === TABLE PANEL ===
        String[] columnNames = {"ID", "Nama", "Paket", "Tanggal", "Jumlah", "Status"};
        Object[][] data = {
                {"001", "Budi", "50 Mbps", "2025-05-20", "250000", "Belum Lunas"},
                {"002", "Siti", "30 Mbps", "2025-05-21", "200000", "Lunas"},
                {"003", "Andi", "100 Mbps", "2025-05-22", "500000", "Lunas"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        gbcPanel.gridx = 0; gbcPanel.gridy = 1;
        gbcPanel.weightx = 1; gbcPanel.weighty = 1;
        gbcPanel.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbcPanel);

        // === SUMMARY PANEL ===
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Ringkasan"));
        totalTransaksiLabel = new JLabel();
        totalLunasLabel = new JLabel();
        totalBelumLunasLabel = new JLabel();
        summaryPanel.add(totalTransaksiLabel);
        summaryPanel.add(totalLunasLabel);
        summaryPanel.add(totalBelumLunasLabel);

        gbcPanel.gridx = 0; gbcPanel.gridy = 2;
        gbcPanel.weightx = 1; gbcPanel.weighty = 0;
        gbcPanel.fill = GridBagConstraints.BOTH;
        add(summaryPanel, gbcPanel);

        // === FILTER ACTION ===
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilter();
            }
        });

        filterPanel.add(filterButton);
        updateSummary();
    }

    private void applyFilter() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        String keyword = searchField.getText().trim().toLowerCase();
        String status = statusFilterBox.getSelectedItem().toString();

        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                String id = entry.getStringValue(0).toLowerCase();
                String nama = entry.getStringValue(1).toLowerCase();
                String tanggal = entry.getStringValue(3);
                String statusEntry = entry.getStringValue(5);

                boolean matchSearch = id.contains(keyword) || nama.contains(keyword);
                boolean matchStatus = status.equals("Semua") || statusEntry.equals(status);

                // Optionally, add date filter logic here if implementing full date parser
                return matchSearch && matchStatus;
            }
        });

        updateSummary();
    }

    private void updateSummary() {
        int total = 0;
        int lunas = 0;
        int belumLunas = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String status = tableModel.getValueAt(i, 5).toString();
            total++;
            if (status.equals("Lunas")) {
                lunas++;
            } else if (status.equals("Belum Lunas")) {
                belumLunas++;
            }
        }

        totalTransaksiLabel.setText("Total: " + total);
        totalLunasLabel.setText("Lunas: " + lunas);
        totalBelumLunasLabel.setText("Belum Lunas: " + belumLunas);
    }
}
