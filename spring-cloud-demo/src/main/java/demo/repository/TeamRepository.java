package demo.repository;

import demo.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by Administrator on 2017-09-07.
 */
@RestResource(path = "teamRepo", rel="team")
public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findAll();

    Team findByName(String name);
}
