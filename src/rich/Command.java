package rich;

public interface Command {

    Player.Status execute(Player player);

    Player.Status respond(Player player, Response response);
}
