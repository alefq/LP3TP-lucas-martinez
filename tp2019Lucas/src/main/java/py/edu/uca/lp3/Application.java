package py.edu.uca.lp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { 
		"py.edu.uca.lp3.rest.controller", 
		"py.uca.edu.lp3.repository", "py.edu.uca.lp3.service",
		"py.edu.uca.lp3.service.impl"})
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
