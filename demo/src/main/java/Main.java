import java.io.IOException;
import GameResources.*;

public class Main 
{
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        Player player = new Player(1);
        
        MoveRequest request = new MoveRequest(1,0 , 1, 1, "move");
        System.out.println(Client.makeMove("localhost", player,request));
    }
}
