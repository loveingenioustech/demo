package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Administrator on 2017-09-12.
 */
@Controller
public class FreemarkerController {
    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String,Object> map){
        map.put("hello","from FreemarkerController.helloFtl");
        return "/freemarker";
    }



}
