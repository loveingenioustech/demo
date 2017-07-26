package demo.Controller;

import demo.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2017-07-25.
 *
 * @RestController 等价于 @Controller 和 @RequestBody
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
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
