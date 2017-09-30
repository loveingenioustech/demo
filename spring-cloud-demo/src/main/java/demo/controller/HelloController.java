package demo.controller;

import demo.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2017-07-25.
 *
 * @RestController 等价于 @controller 和 @RequestBody
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return "hello-2";
    }

    @RequestMapping("/hello3")
    public String hello3() {
        return "hello-3";
    }

    @RequestMapping("/getPerson")
    public Person getPerson() {
        Person p = new Person();
        p.setId(1);
        p.setName("Robin");
        p.setCreateTime(new Date());

        return p;
    }


}
