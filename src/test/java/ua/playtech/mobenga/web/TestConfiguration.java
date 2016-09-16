package ua.playtech.mobenga.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.playtech.mobenga.service.PlayersService;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfiguration {

    @Bean
    public Controller controller() {
        return new Controller();
    }

    @Bean
    public PlayersService playersService() {
        return mock(PlayersService.class);
    }
}