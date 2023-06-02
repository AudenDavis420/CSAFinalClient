import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoadFrame extends JFrame
{
    public LoadFrame()
    {
        this.setVisible(true);
        this.setBounds(50, 50, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel();
        title.setBounds(50, 50, 100, 100);
        title.setText("Clash of Pixels");
        

        this.add(title);


    }
}
