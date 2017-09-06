package demo.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by Administrator on 2017-09-06.
 * JAXB
 *
 */
@XmlRootElement
public class Team {
    String name;
    String location;
    String mascot;
    Set<Player> players;

    public Team() {
        super();
    }

    public Team(String name, String location, String mascot, Set<Player> players) {
        this();
        this.name = name;
        this.location = location;
        this.mascot = mascot;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
