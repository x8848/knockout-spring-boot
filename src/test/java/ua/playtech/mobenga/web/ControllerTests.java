package ua.playtech.mobenga.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.playtech.mobenga.domain.Player;
import ua.playtech.mobenga.service.PlayersService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class ControllerTests {

    private static final String JSON_UTF8 = "application/json;charset=UTF-8";
    private static final String PLAYER_ID = "playerId";
    private static final String PLAYER_ID_VALUE = "11119";
    private static final String NAME = "name";
    private static final String NAME_VALUE = "Lionel Messi";
    private static final String TEAM_NAME = "teamName";
    private static final String TEAM_NAME_VALUE = "Barcelona";
    private MockMvc mvc;

    @Autowired
    private Controller controller;

    @Autowired
    private PlayersService playersService;

    //private Player playerMock;
    private Map<String, String> content;

    @Before
    public void setUp() {
 /*       playerMock = mock(Player.class);
        when(playerMock.get(PLAYER_ID)).thenReturn(PLAYER_ID_VALUE);
        when(playerMock.get(NAME)).thenReturn(NAME_VALUE);
*/
        content = new HashMap<>();
        content.put(PLAYER_ID, PLAYER_ID_VALUE);
        content.put(NAME, NAME_VALUE);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllPlayers() throws Exception {
        when(playersService.getAllPlayers())
                //.thenReturn(Collections.singletonList(playerMock));
                .thenReturn(Collections.singletonList(new Player(content)));

        mvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]." .concat(PLAYER_ID), is(PLAYER_ID_VALUE)))
                .andExpect(jsonPath("$[0]." .concat(NAME), is(NAME_VALUE)));

        verify(playersService, times(1)).getAllPlayers();
        verifyNoMoreInteractions(playersService);
    }

    @Test
    public void getPlayerById() throws Exception {
        //when(playerMock.get(TEAM_NAME)).thenReturn(TEAM_NAME_VALUE);
        content.put(TEAM_NAME, TEAM_NAME_VALUE);

        when(playersService.getPlayerById(PLAYER_ID_VALUE))
                //.thenReturn(playerMock);
                .thenReturn(new Player(content));

        mvc.perform(get("/api/player/{playerId}", PLAYER_ID_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$." .concat(PLAYER_ID), is(PLAYER_ID_VALUE)))
                .andExpect(jsonPath("$." .concat(NAME), is(NAME_VALUE)))
                .andExpect(jsonPath("$." .concat(TEAM_NAME), is(TEAM_NAME_VALUE)));

        verify(playersService, times(1)).getPlayerById(PLAYER_ID_VALUE);
        verifyNoMoreInteractions(playersService);
    }
}
