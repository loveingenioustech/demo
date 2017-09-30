package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Administrator on 2017-09-06.
 */
@SpringBootApplication
public class WarApp extends SpringBootServletInitializer {

    /**
     * Used when run as Jar
     */
    public static void main(String[] args) {
        // 在main 方法里启动应用程序
        SpringApplication.run(WarApp.class, args);
    }

    /**
     * Used when run as War
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WarApp.class);
    }

}
