package top.kylewang.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		new SpringApplication(ServiceApplication.class).run(args);
	}
}
