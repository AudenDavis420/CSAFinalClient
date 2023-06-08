import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
        
        game = Client.getMap(ipAddress, LocalPlayer);
        count=0;
        GameSquare[][] board=game.getBoard();
        localPlayer=Client.joinGame(ipAddress);
      

        JFrame frame=new JFrame();
        JButton startGame=new JButton("Start Game?");
        frame.setBounds(50,50,500,600);
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
                JPanel landscape  = new JPanel();
                    if (board[i][j].getTerrain().equals("grass")){
                        landscape.setVisible(true);
                        landscape.setBackground(Color.GREEN);
                    }
                    if (board[i][j].getTerrain().equals("water")){
                        landscape.setVisible(true);

                        landscape.setBackground(Color.BLUE);
                    }
                    if (board[i][j].getTerrain().equals("sand")){
                        landscape.setVisible(true);
                        landscape.setBackground(Color.YELLOW);
                    }
                
                area.setBounds(50+i, 50+j, 25,25);
                area.add(landscape);
                frame.add(area);
                area.setVisible(true);
            }
        }
        JButton move=new JButton("Move");
        JButton attack=new JButton("attack");
        JButton end=new JButton("end");
        move.setBounds(100,500,130,100);
        attack.setBounds(235,500,130,100);
        end.setBounds(370,500,130,100);
        JTextArea sX=new JTextArea("enter start X");
        JTextArea sY=new JTextArea("enter start Y");
        JTextArea gX=new JTextArea("enter goal X");
        JTextArea gY=new JTextArea("enter goal Y");
      
        frame.add(sX);
        frame.add(move);
        frame.add(attack);
        frame.add(end);
        
        move.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               frame.add(sX);
               frame.add(sY);
               frame.add(gX);
               frame.add(gY);
            

               int sx=Integer.parseInt(sX.getText());
               int sy=Integer.parseInt(sY.getText());
               int gx=Integer.parseInt(gX.getText());
               int gy=Integer.parseInt(gY.getText());
               String movetype="move";
               MoveRequest move=new MoveRequest(sx,sy,gx,gy,movetype);

               try {
                Client.makeMove(IpAddress, LocalPlayer, move);
            } catch (IOException | InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            }
            
        });


        attack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               frame.add(sX);
               frame.add(sY);
               frame.add(gX);
               frame.add(gY);


               int sx=Integer.parseInt(sX.getText());
               int sy=Integer.parseInt(sY.getText());
               int gx=Integer.parseInt(gX.getText());
               int gy=Integer.parseInt(gY.getText());
               String movetype="attack";
               MoveRequest move=new MoveRequest(sx,sy,gx,gy,movetype);

               try {
                Client.makeMove(IpAddress, LocalPlayer, move);
            } catch (IOException | InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            }
            
        });


        end.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int sx=1  ;
                int sy=0;
                int gx=0;
                int gy=0;
                String movetype="end";
                MoveRequest move=new MoveRequest(sx, sy, gx, gy, movetype);
                try {
                    Client.makeMove(IpAddress, LocalPlayer, move);
                } catch (IOException | InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });

        }
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);



    }
}
