package demo.service;

import demo.mapper.CatMapper;
import demo.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017-09-13.
 */
@Service
public class CatService {
    @Autowired
    private CatMapper catMapper;

    public List<Cat> likeName(String name){
        return catMapper.likeName(name);
    }

    public Cat getById(int id){
        return catMapper.getById(id);
    }

    public String getNameById(int id){
        return catMapper.getNameById(id);
    }

    @Transactional//添加事务.
    public void save(Cat cat){
        catMapper.save(cat);
    }

}
