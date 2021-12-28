package org.app.JakubsztubaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class JakubsztubaSpringBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JakubsztubaSpringBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JakubsztubaSpringBootApplication.class);
    }
}
