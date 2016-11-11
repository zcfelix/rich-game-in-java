package rich;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Player {
    private  Status status;
    private Command lastExecuted;
    private List<Item> items;
    private List<Land> lands;
    private Land current;

    public Player() {
        this.status = Status.WAIT_FOR_COMMAND;
        this.items = new ArrayList<>();
        this.lands = new ArrayList<>();
    }

    public Status getStatus() {
        return status;
    }

    public void execute(Command command) {
        status = command.execute(this);
        lastExecuted = command;

    }

    public void respond(Response response) {
        status = lastExecuted.respond(this, response);
    }


    public List<Item> getItems() {
        return items;
    }

    public void sellItem(int index) {
        items.remove(index);
    }

    static Player createPlayerStartFrom(Land starting) {
        Player player = new Player();
        player.current = starting;
        return player;
    }

    static Player createPlayerWithItems(Item... items) {
        Player player = new Player();
        player.items.addAll(asList(items));
        return player;
    }

    public Land getCurrent() {
        return current;
    }

    public void moveTo(Land target) {
        current = target;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void buy() {
        lands.add(current);
    }


    public enum Status {WAIT_FOR_RESPONSE, END_TURN, WAIT_FOR_COMMAND}
}
