package rich;

public class RollCommand implements Command {

    private final GameMap map;
    private final Dice dice;

    public RollCommand(GameMap map, Dice dice) {
        this.map = map;
        this.dice = dice;
    }

    @Override
    public Player.Status execute(Player player) {
        Land target = map.move(player.getCurrent(), dice.next());
        player.moveTo(target);
        return target.getOwner() == null ? Player.Status.WAIT_FOR_RESPONSE : Player.Status.END_TURN;
    }

    @Override
    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }

    public static Response YesToBuy = player -> {
        Land current = player.getCurrent();
        if (current.getOwner() == null) {
            player.buy();
        }
        return Player.Status.END_TURN;
    };
}
