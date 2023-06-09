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

//@Author David 
/**
 * the following is the GameFrame
 * which loads after the game is started
 * and runs until the game is over. 
 */
public class GameFrame extends JFrame
{
    String ipAddress; //ip that gets sent to client
    Player localPlayer; //player object
    Game game; //game object
    int count; //# of units in the game

    
    
    public GameFrame(Player LocalPlayer, String IpAddress) throws IOException, InterruptedException
    {
        //initialize variables
        game = Client.getMap(ipAddress, LocalPlayer);
        count=8;
        GameSquare[][] board=game.getBoard(); //game board
        localPlayer=Client.joinGame(ipAddress);
      
        //creates the frame and start game button and sets the bounds
        JFrame frame=new JFrame(); //frame of game
        JButton startGame=new JButton("Start Game?"); //button that starts the game
        frame.setBounds(50,50,500,600);
        frame.add(startGame);
        startGame.setBounds(100,100,100,50);
        //if clicked, game starts and send request to server
        startGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (localPlayer.getPlayerID()==1){ //only player 1 can start the game
                    
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
    
        while(count>1) //continues the game when there's more than 1 unit on the board
        {
            startGame.setVisible(false);
           
            count=0; //resets the number of units
             //determines the number of units in the game 
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
       //adds the terrain in the game
        for (int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                JPanel landscape  = new JPanel(); //individual terrain

                    //determines what type of terrain
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
                //adds the terrain
                area.setBounds(50+i, 50+j, 25,25);
                area.add(landscape);
                frame.add(area);
                area.setVisible(true);
            }
        }
        //adds the move types for players to choose
        JButton move=new JButton("Move"); //move character
        JButton attack=new JButton("attack");//attack enemy
        JButton end=new JButton("end"); //end turn
        move.setBounds(100,500,130,100);
        attack.setBounds(235,500,130,100);
        end.setBounds(370,500,130,100);
        //adds text areas for player to enter which character to perform the move
        JTextArea sX=new JTextArea("enter start X");
        JTextArea sY=new JTextArea("enter start Y");
        JTextArea gX=new JTextArea("enter goal X");
        JTextArea gY=new JTextArea("enter goal Y");
      
    
        frame.add(move);
        frame.add(attack);
        frame.add(end);
        //determines what to do when choosen move type
        move.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               frame.add(sX);
               frame.add(sY);
               frame.add(gX);
               frame.add(gY);
            
                //converts the text the player entered into int and sends to server
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

                 //converts the text the player entered into int and sends to server
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
                 //sends to server
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
