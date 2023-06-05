import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import GameResources.*;

public class GameFrame extends JFrame
{
    String ipAddress;
    Player localPlayer;
    Game game;

    
    
    public GameFrame(Player LocalPlayer, String IpAddress) throws IOException, InterruptedException
    {
        localPlayer = LocalPlayer;
        ipAddress = IpAddress;
        game = Client.getMap(ipAddress, LocalPlayer);
        int count=0;
        GameSquare[][] board=game.getBoard();
        localPlayer=Client.joinGame(ipAddress);

        JFrame frame=new JFrame();
        JButton startGame=new JButton();
        frame.setBounds(50,50,200,200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(startGame);
        startGame.setBounds(100,100,100,50);
        startGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (localPlayer.getPlayerID()==1){
                    
                        try {
                            Client.startGame(ipAddress,localPlayer);
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



        }




    }
}
