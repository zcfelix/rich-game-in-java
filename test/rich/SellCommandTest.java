package rich;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SellCommandTest {

    @Test
    public void should_sell_item_from_player() {
        int FIRST = 0;
        Item item = mock(Item.class);
        SellCommand command = new SellCommand(FIRST);

        Player player = Player.createPlayerWithItems(item);

        player.execute(command);

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }
}
