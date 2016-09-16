package ua.playtech.mobenga.service;

import ua.playtech.mobenga.domain.Player;

import java.util.List;

public interface PlayerDao {

    List<Player> findAll();

    Player findById(String id);
}
