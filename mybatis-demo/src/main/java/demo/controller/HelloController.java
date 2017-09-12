package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Administrator on 2017-09-12.
 */
@Controller
public class HelloController {
    private String hello;

    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String,Object> map){
        System.out.println("HelloController.helloJsp().hello=hello");
        map.put("hello", hello);
        return "helloJsp";
    }

}
