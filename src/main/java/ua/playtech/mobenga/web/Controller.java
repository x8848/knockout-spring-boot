package ua.playtech.mobenga.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.playtech.mobenga.domain.Player;
import ua.playtech.mobenga.service.PlayersService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private PlayersService playersService;

    @RequestMapping("/players")
    public List<Player> getPlayers() {
        return playersService.getAllPlayers();
    }

    @RequestMapping("/player/{id}")
    public Player getPlayerById(@PathVariable("id") String id) {
        return playersService.getPlayerById(id);
    }
}
