package top.kylewang.invoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@SpringBootApplication
public class InvokerApplication {

	public static void main(String[] args) {
		new SpringApplication(InvokerApplication.class).run(args);
	}
}
