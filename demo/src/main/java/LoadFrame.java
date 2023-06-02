import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
public class LoadFrame extends JFrame
{
    public LoadFrame()
    {
        this.setVisible(true);
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        JLabel title = new JLabel("Clash of Pixels",SwingConstants.CENTER);
        title.setBounds(0, 70, 500, 30);
        title.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 30));
        title.setForeground(Color.getColor("4AC802"));
        
        
        this.add(title,SwingConstants.CENTER);


    }
}
