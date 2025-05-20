package contentPanel;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel{
    private int cornerRadius = 20;

    public RoundedPanel(){
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(
                0, 0, new Color(224, 242, 255), getWidth(), getHeight(), Color.WHITE
        );
        g2.setPaint(gp);

        g2.fillRoundRect(
                0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius
        );

        g2.dispose();
        super.paintComponent(g);

    }
}
