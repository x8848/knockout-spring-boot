package ua.playtech.mobenga.service;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ua.playtech.mobenga.domain.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CsvPlayerDao implements PlayerDao {

    private final List<Map<String, String>> dataSource = new LinkedList<>();

    public CsvPlayerDao() {
        try {
            Reader input = new InputStreamReader(new FileInputStream
                    ((getClass().getResource("/players.csv").getFile())), "UTF8");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(input);
            for (CSVRecord record : records) {
                dataSource.add(record.toMap());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> findAll() {
        return FluentIterable.from(dataSource).transform(new Function<Map<String, String>, Player>() {
            @Override
            public Player apply(Map<String, String> input) {
                Player player = new Player(input);
                player.keySet().retainAll(Arrays.asList("playerId", "name"));
                return player;
            }
        }).toList();
    }

    @Override
    public Player findById(final String id) {
        return FluentIterable.from(dataSource).filter(new Predicate<Map<String, String>>() {
            @Override
            public boolean apply(Map<String, String> input) {
                return input.get("playerId").equals(id);
            }
        }).transform(new Function<Map<String, String>, Player>() {
            @Override
            public Player apply(Map<String, String> input) {
                return new Player(input);
            }
        }).first().get();
    }
}
