package ua.playtech.mobenga.web;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import ua.playtech.mobenga.ApplicationConfiguration;
import ua.playtech.mobenga.domain.Player;
import ua.playtech.mobenga.service.PlayerDao;
import ua.playtech.mobenga.service.PlayersServiceImpl;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class PlayerServiceTests {

    private static final String PLAYER_ID = "playerId";
    private static final String PLAYER_ID_VALUE = "11119";

    @Mock
    private PlayerDao dao;

    @InjectMocks
    private PlayersServiceImpl playersService = new PlayersServiceImpl();

    @Before
    public void setUp() throws Exception {
        Player playerWithId = mock(Player.class);
        when(playerWithId.get(PLAYER_ID)).thenReturn(PLAYER_ID_VALUE);

        when(dao.findAll()).
                thenReturn(Lists.newArrayList(playerWithId, mock(Player.class)));

        when(dao.findById(PLAYER_ID_VALUE)).thenReturn(playerWithId);
    }

    @Test
    public void getAllPlayers() {
        List<Player> allPlayers = playersService.getAllPlayers();
        assertThat("Number of players", allPlayers, hasSize(2));
    }

    @Test
    public void getPlayerById() {
        Player player = playersService.getPlayerById(PLAYER_ID_VALUE);
        assertThat("Player id", player.get(PLAYER_ID), is(PLAYER_ID_VALUE));

    }
}
