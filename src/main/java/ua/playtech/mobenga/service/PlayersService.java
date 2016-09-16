package ua.playtech.mobenga.service;

import ua.playtech.mobenga.domain.Player;

import java.util.List;

public interface PlayersService {

    List<Player> getAllPlayers();

    Player getPlayerById(String id);
}
