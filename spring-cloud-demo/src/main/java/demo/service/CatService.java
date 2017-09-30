package demo.service;

import demo.domain.Cat;
import demo.repository.Cat2Repository;
import demo.repository.CatDao;
import demo.repository.CatRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017-09-11.
 */
@Service
public class CatService {

    @Resource
    private CatRepository catRepository;

    @Resource
    private Cat2Repository cat2Repository;

    @Resource
    private CatDao catDao;

    /**
     * save,update ,delete 方法需要绑定事务.
     *
     * 使用@Transactional进行事务的绑定.
     *
     * @param cat
     */
    @Transactional
    public void save(Cat cat){
        catRepository.save(cat);
    }

    @Transactional
    public void delete(int id){
        catRepository.delete(id);
    }

    public Iterable<Cat> getAll(){
        return catRepository.findAll();
    }

    //--- 使用cat2Repository
    public Cat findByCatName(String catName){
        return cat2Repository.findByCatName(catName);
    }

    public Cat findByCatName2(String catName){
        return cat2Repository.findMyCatName(catName);
    }

    //--- 使用 catDao
    public Cat selectByCatName(String catName){
        return catDao.selectByCatName(catName);
    }
}
