package ua.playtech.mobenga;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.playtech.mobenga.service.CsvPlayerDao;
import ua.playtech.mobenga.service.PlayerDao;
import ua.playtech.mobenga.service.PlayersService;
import ua.playtech.mobenga.service.PlayersServiceImpl;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PlayersService playersService() {
        return new PlayersServiceImpl();
    }

    @Bean
    public PlayerDao dao() {
        return new CsvPlayerDao();
    }
}
