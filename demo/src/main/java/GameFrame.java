import java.io.IOException;

import javax.swing.JFrame;
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
    
        LocalPlayer=Client.joinGame(ipAddress);
        if (LocalPlayer.getPlayerID()==1){
            Client.startGame(IpAddress, LocalPlayer);
        }
        
        for (int i=0;i<game.getBoard();i++){

        }
        

    }
}
