package ua.playtech.mobenga.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.playtech.mobenga.domain.Player;

import java.util.List;

public class PlayersServiceImpl implements PlayersService {

    @Autowired
    private PlayerDao dao;

    @Override
    public List<Player> getAllPlayers() {
        return dao.findAll();
    }

    @Override
    public Player getPlayerById(final String id) {
        return dao.findById(id);
    }
}
