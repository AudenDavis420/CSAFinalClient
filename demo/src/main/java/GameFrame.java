import java.io.IOException;

import javax.swing.JFrame;
import GameResources.*;

public class GameFrame extends JFrame
{
    String ipAddress;
    Player localPlayer;
    Game game;
    
    public GameFrame(Player LocalPlayer) throws IOException, InterruptedException
    {
        localPlayer = LocalPlayer;
        game = Client.getMap(ipAddress, LocalPlayer);

    }
}
