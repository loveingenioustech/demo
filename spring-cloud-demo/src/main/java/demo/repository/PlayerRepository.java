package demo.repository;

import demo.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Administrator on 2017-09-07.
 */
@RestResource(path = "playerRepo", rel = "player")
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
