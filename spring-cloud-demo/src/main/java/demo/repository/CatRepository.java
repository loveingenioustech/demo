package demo.repository;

import demo.domain.Cat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2017-09-11.
 */
public interface CatRepository extends CrudRepository<Cat, Integer> {

}
