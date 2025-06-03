package contentPanel;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.Calendar;

public class RiwayatTransaksiPanel extends JPanel {
    //deklarasi variable
    private JPanel filterPanel, dariTanggalPanel, sampaiTanggalPanel, tablePanel;
    private JLabel dariTanggalLabel, sampaiTanggalLabel, cariLabel, statusLabel;
    private JTextField cariField, statusField;
    private JButton filterButton;
    private DefaultTableModel datatransaksiModel;
    private JTable datatransaksiTable;

    public RiwayatTransaksiPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();


        //filterPanel pada top layout
        filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createTitledBorder("filterPanel"));
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

        JComboBox<Integer> dariDayBox = new JComboBox<>(days);
        JComboBox<Integer> dariMonthBox = new JComboBox<>(months);
        JComboBox<Integer> dariYearBox = new JComboBox<>(years);

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

        JComboBox<Integer> sampaiDayBox = new JComboBox<>(days);
        JComboBox<Integer> sampaiMonthBox = new JComboBox<>(months);
        JComboBox<Integer> sampaiYearBox = new JComboBox<>(years);

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
        JComboBox<String> statusBox = new JComboBox<>(status);
        filterPanel.add(statusBox, gbcFilterPanel);

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

    }


}
