import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import GameResources.*;

public class GameFrame extends JFrame
{
    String ipAddress;
    Player localPlayer;
    Game game;
    int count;

    
    
    public GameFrame(Player LocalPlayer, String IpAddress) throws IOException, InterruptedException
    {
        localPlayer = LocalPlayer;
        ipAddress = IpAddress;
        game = Client.getMap(ipAddress, LocalPlayer);
        count=0;
        GameSquare[][] board=game.getBoard();
        localPlayer=Client.joinGame(ipAddress);
      

        JFrame frame=new JFrame();
        JButton startGame=new JButton("Start Game?");
        frame.setBounds(50,50,500,500);
        frame.add(startGame);
        startGame.setBounds(100,100,100,50);
      
        startGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (localPlayer.getPlayerID()==1){
                    
                        try {
                            Client.startGame(ipAddress,localPlayer);
                            count=2;
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (InterruptedException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
               
                }
            }
            
        });
    
        while(count>1)
        {
            startGame.setVisible(false);
            
            count=0;
            for (int i=0;i<board.length;i++)
            {
                for(int j=0;j<board[0].length;j++)
                {
                    if(board[i][j].getUnit()!=null)
                    {
                        count+=1;
                    }
                }
            }
        
        
        frame.setVisible(true);
        JPanel area=new JPanel();
       
        for (int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                JComponent landscape=new JComponent(); 
                    if (board[i][j].getTerrain.equals("grass")){
                        landscape.setVisible(true);
                        landscape.setBackground(Color.GREEN);
                    }
                    if (board[i][j].getTerrain=="water"){
                        landscape.setVisible(true);
                        landscape.setBackground(Color.BLUE);
                    }
                    if (board[i][j].getTerrain=="sand"){
                        landscape.setVisible(true);
                        landscape.setBackground(Color.YELLOW);
                    }
                
                area.add(landscape);
            }
        }




      


        }
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);



    }
}
