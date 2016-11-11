package rich;

import com.sun.xml.internal.ws.server.UnsupportedMediaException;

public class SellCommand implements Command {
    private int index;

    public SellCommand(int index) {

        this.index = index;
    }

    @Override
    public Player.Status execute(Player player) {
        player.sellItem(index);
        return Player.Status.WAIT_FOR_COMMAND;
    }

    @Override
    public Player.Status respond(Player player, Response response) {
        throw new UnsupportedMediaException();
    }
}
