package demo.controller;

import com.github.pagehelper.PageHelper;
import demo.model.Cat;
import demo.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017-09-13.
 */
@RestController
@RequestMapping("/cat")
public class CatController {
    @Autowired
    private CatService catService;

    @RequestMapping("/likeName")
    public List<Cat> likeName(String name){
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
        PageHelper.startPage(1, 2);
        return catService.likeName(name);
    }

    @RequestMapping("/findCat")
    public Cat findCatById(int catId){
        return catService.getById(catId);
    }

    @RequestMapping("/findName")
    public String findCatName(int catId){
        return catService.getNameById(catId);
    }

    @RequestMapping("/save")
    public Cat save(){
        Cat cat = new Cat();
        cat.setCatName("KK");
        cat.setCatAge(2);
        catService.save(cat);
        return cat;
    }

}
