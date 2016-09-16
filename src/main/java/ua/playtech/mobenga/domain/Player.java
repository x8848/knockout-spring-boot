package ua.playtech.mobenga.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Player extends LinkedHashMap<String, String> {

    public Player(Map<String, String> content) {
        super(content);
    }
}
