package demo.Controller;

import demo.domain.Person;
import demo.domain.Player;
import demo.domain.Team;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017-09-06.
 */
@RestController
public class TeamController {

    private Team team;

    @PostConstruct
    public void init(){
        Set<Player> players = new HashSet<>();
        players.add(new Player("Robin", "pitcher"));
        players.add(new Player("Sean", "forward"));

        team = new Team("Great", "Shenzhen", "Dragon", players);
    }


    /**
     * http://localhost:8080/getTeam.json
     * http://localhost:8080/getTeam.xml got error: error on line 1 at column 1: Document is empty
     * @return
     */
    @RequestMapping("/getTeam")
    public Team getTeam() {
        return team;
    }
}
