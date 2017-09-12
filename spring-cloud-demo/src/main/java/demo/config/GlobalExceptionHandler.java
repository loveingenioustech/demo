package demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017-09-12.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest req, Exception e){
        //返回 String.

        //ModelAndView
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName(viewName);

        return "Sorry, the server is busy, please try later!";
    }

}
