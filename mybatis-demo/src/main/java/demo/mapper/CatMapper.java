package demo.mapper;

import demo.model.Cat;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017-09-13.
 */
public interface CatMapper {
    @Select("select * from cat where cat_name = #{name}")
    @Results({
            @Result(property = "catName", column = "cat_name"),
            @Result(property = "catAge", column = "cat_age")
    })
    public List<Cat> likeName(String name);

    @Select("select * from cat where id = #{id}")
    @Results({
            @Result(property = "catName", column = "cat_name"),
            @Result(property = "catAge", column = "cat_age")
    })
    public Cat getById(long id);

    @Select("select cat_name from cat where id = #{id}")
    @Results({
            @Result(property = "catName", column = "cat_name")
    })
    public String getNameById(long id);

    @Insert("insert into Cat(cat_name, cat_age) values (#{catName}, #{catAge})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    public void save(Cat cat);
}
