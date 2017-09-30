package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Administrator on 2017-09-06.
 */
@Controller
public class ThymeleafController {
    @RequestMapping("/hi/{name}")
    public String hi(Map model, @PathVariable String name) {
        model.put("name", name);
        return "hello";
    }

    /**
     * 返回html模板.
     */
    @RequestMapping("/helloThymeleaf")
    public String helloHtml(Map<String,Object> map){
        map.put("hello","from TemplateController.helloHtml");
        return "/thymeleaf";
    }

}
