import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import GameResources.Player;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Font;

//@Author Auden
public class LoadFrame extends JFrame
{
    
    GameFrame game;


    /**
    *Contructor for load frame
    * makes a frame, titles, text area for ip, and button
    * has action preformer that creates the game frame from the ip
    * */
    public LoadFrame()
    {
        //makes frame
        LoadFrame frame = this;
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        //creates title
        JLabel title = new JLabel("Clash of Pixels",SwingConstants.CENTER);
        title.setBounds(0, 70, 500, 30);
        
        //creats ip label
        JLabel textTitle = new JLabel("IP Address:",SwingConstants.LEFT);
        textTitle.setBounds(100, 100, 300, 30);
        


        //create text area
        JTextArea textArea = new JTextArea();
        textArea.setBounds(100, 125, 300, 30);
        textArea.setVisible(true);
    
        //creates error label
        JLabel response = new JLabel("",SwingConstants.CENTER);
        response.setVisible(true);
        response.setBounds(100, 250, 300, 30);
        
        
        
        // creates join button
        JButton joinButton = new JButton();
        joinButton.setBounds(200, 200, 100, 40);
        joinButton.setText("Join Game");

        //creates action listener
        joinButton.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                Boolean isGood = true;
                response.setText("");
                Player player = null;
                try {
                    
                    player = Client.joinGame(textArea.getText());//makes joinCall to the server
                } 
                catch (Exception exception) // returns error message
                {
                    
                    response.setText("invalid ip");
                    System.out.println("invalid ip");
                    isGood = false;
                    
                }
                
                if (isGood){frame.setVisible(false); try {
                    game = new GameFrame(player, response.getText()); //creates game
                } catch (IOException | InterruptedException e1) {
                    
                    e1.printStackTrace();
                }}
                System.out.println(player);
            }
            
        });

        this.add(response); //sets up Jframe
        this.add(textTitle);
        this.add(textArea);
        this.add(title);
        this.add(joinButton);




        this.setVisible(true);

        
    }
}
