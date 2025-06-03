package contentPanel;

import javax.swing.*;
import java.awt.*;

public class CreateCardPanel extends RoundedPanel {
    public CreateCardPanel(String title, String value, String subtitle) {
        super(20);

        setPreferredSize(new Dimension(240, 120));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(Color.BLACK);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 28));
        valueLabel.setForeground(Color.BLACK);

        JTextArea subtitleLabel = new JTextArea(subtitle);
        subtitleLabel.setLineWrap(true);
        subtitleLabel.setWrapStyleWord(true);
        subtitleLabel.setEditable(false);
        subtitleLabel.setFocusable(false);
        subtitleLabel.setOpaque(false);
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        subtitleLabel.setForeground(Color.BLACK);


        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.insets = new Insets(2, 2, 2, 2);
        gbcContent.anchor = GridBagConstraints.WEST;
        gbcContent.fill = GridBagConstraints.HORIZONTAL;
        gbcContent.gridx = 0;
        gbcContent.weightx = 1;

        gbcContent.gridy = 0;
        contentPanel.add(titleLabel, gbcContent);

        gbcContent.gridy = 1;
        contentPanel.add(valueLabel, gbcContent);

        gbcContent.gridy = 2;
        gbcContent.weighty = 1;
        contentPanel.add(subtitleLabel, gbcContent);

        add(contentPanel, BorderLayout.CENTER);
    }
}
