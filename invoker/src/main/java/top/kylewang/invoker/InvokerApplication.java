package top.kylewang.invoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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

	@Bean
	public RestTemplate customRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(1000);
		httpRequestFactory.setConnectTimeout(2000);
		httpRequestFactory.setReadTimeout(2000);
		return new RestTemplate(httpRequestFactory);
	}
}
