package top.kylewang.invoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InvokerApplication {

	public static void main(String[] args) {
		new SpringApplication(InvokerApplication.class).run(args);
	}

	@Bean
	public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		// 从连接池获取连接的超时时间
		httpRequestFactory.setConnectionRequestTimeout(1000);
		// 建立连接的超时时间
		httpRequestFactory.setConnectTimeout(2000);
		// 数据传输的超时时间
		httpRequestFactory.setReadTimeout(6000);
		return new RestTemplate(httpRequestFactory);
	}
}
