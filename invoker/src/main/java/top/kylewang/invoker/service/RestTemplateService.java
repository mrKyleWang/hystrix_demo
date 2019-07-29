package top.kylewang.invoker.service;

import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.kylewang.invoker.hystrix.HystrixSetterFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用AsyncRestTemplate请求
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@Service
public class RestTemplateService {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateService.class);

	private Map<Integer, String> cache = new ConcurrentHashMap<>();

	@Autowired
	private RestTemplate restTemplate;

	public String queryData(int id) {
		if (cache.containsKey(id)) {
			logger.info("------------- get cache -------------");
			return cache.get(id);
		}

		HystrixCommand.Setter setter = HystrixSetterFactory.getSetter("queryData", 5000);
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(setter) {
			@Override
			protected String run() throws Exception {
				try {

					logger.info(Thread.currentThread().getName() + "------------- send request -------------");
					String result = restTemplate.getForObject("http://127.0.0.1:9001/queryData?id={id}", String.class,
							id);
					logger.info(Thread.currentThread().getName() + "------------- get response -------------");
					cache.put(id, result);
					return result;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}

			@Override
			protected String getFallback() {
				logger.info(Thread.currentThread().getName() + "------------- fallback -------------");
				return null;
			}
		};
		logger.info(Thread.currentThread().getName() + "------------- execute command -------------");
		return hystrixCommand.execute();
	}
}
