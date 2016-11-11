package rich;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

public class RollCommandTest {

    private GameMap map;
    private Dice dice;
    private Land starting;
    private Land emptyLand;
    private Land othersLand;

    @Before
    public void before() {
        map = mock(GameMap.class);
        dice = () -> 1;
        starting = mock(Land.class);
        emptyLand = mock(Land.class);
        othersLand = mock(Land.class);
        when(emptyLand.getOwner()).thenReturn(null);
        when(othersLand.getOwner()).thenReturn(new Player());
    }

    @Test
    public void should_wait_for_response_if_reach_empty_land() {
        RollCommand roll = new RollCommand(map, dice);

        when(map.move(eq(starting), eq(1))).thenReturn(emptyLand);

        Player player = Player.createPlayerStartFrom(starting);

        player.execute(roll);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));
    }

    @Test
    public void should_end_turn_if_reach_others_land() {
        RollCommand roll = new RollCommand(map, dice);

        when(map.move(eq(starting), eq(1))).thenReturn(othersLand);

        Player player = Player.createPlayerStartFrom(starting);

        player.execute(roll);

        assertThat(player.getStatus(), is(Player.Status.END_TURN));
    }

    @Test
    public void should_buy_empty_land_if_user_respond_yes() {
        RollCommand roll = new RollCommand(map, dice);

        when(map.move(eq(starting), eq(1))).thenReturn(emptyLand);

        Player player = Player.createPlayerStartFrom(starting);

        player.execute(roll);
        player.respond(RollCommand.YesToBuy);

        assertThat(player.getLands().size(), is(1));
        assertThat(player.getStatus(), is(Player.Status.END_TURN));
    }

}

