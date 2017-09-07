package demo;

import demo.domain.Player;
import demo.domain.Team;
import demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017-09-07.
 */
@SpringBootApplication
public class TeamApp {
    @Autowired
    TeamRepository teamRepository;

    public static void main(String[] args) {
        SpringApplication.run(TeamApp.class, args);
    }

    @PostConstruct
    public void init() {
        List<Team> list = new ArrayList<>();

        Set<Player> set = new HashSet<>();
        set.add(new Player("Big Easy", "Showman"));
        set.add(new Player("Buckets", "Guard"));
        set.add(new Player("Dizzy", "Guard"));

        list.add(new Team("Harlem", "Globetrotters", "We are the champion", set));
        list.add(new Team("Washington","Generals","Be No.1", null));

        teamRepository.save(list);
    }

}
