package rich;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    private Player player;
    private Command command;

    @Before
    public void before() {
        player = new Player();
        command = mock(Command.class);
    }

    @Test
    public void should_remain_in_waiting_for_command_after_execute_respondless_command() {
        when(command.execute(eq(player))).thenReturn(Player.Status.WAIT_FOR_COMMAND);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        player.execute(command);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_be_in_wait_for_response_after_execute_respondful_command() {
        when(command.execute(eq(player))).thenReturn(Player.Status.WAIT_FOR_RESPONSE);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        player.execute(command);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));

    }

    @Test
    public void should_be_end_turn_after_respond_to_command() {
        when(command.execute(eq(player))).thenReturn(Player.Status.WAIT_FOR_RESPONSE);
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        player.execute(command);

        Response response = mock(Response.class);

        when(command.respond(eq(player), eq(response))).thenReturn(Player.Status.END_TURN);

        player.respond(response);

        assertThat(player.getStatus(), is(Player.Status.END_TURN));
    }
}
