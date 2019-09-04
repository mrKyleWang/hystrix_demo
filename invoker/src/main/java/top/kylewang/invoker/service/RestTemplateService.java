package top.kylewang.invoker.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateService.class);

	@Autowired
	private RestTemplate restTemplate;

	public String queryData(int id) {
		// 创建HystrixCommand.Setter
		int hystrixTimeOut = 4000;
		HystrixCommandProperties.Setter propSetter = HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true)
				.withExecutionTimeoutInMilliseconds(hystrixTimeOut)
				.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
				.withExecutionIsolationThreadInterruptOnTimeout(true);
		HystrixCommand.Setter setter = HystrixCommand.Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("queryData"))
				.andCommandPropertiesDefaults(propSetter);

		// 通过Setter创建创建HystrixCommand
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(setter) {
			@Override
			protected String run() throws Exception {
				logger.info(Thread.currentThread().getName() + "------------- send request -------------");
				String result = restTemplate.getForObject("http://127.0.0.1:9001/queryData?id={id}", String.class, id);
				logger.info(Thread.currentThread().getName() + "------------- get response -------------");
				return result;
			}

			@Override
			protected String getFallback() {
				logger.info(Thread.currentThread().getName() + "------------- fallback -------------");
				return null;
			}
		};
		logger.info(Thread.currentThread().getName() + "------------- execute command -------------");

		// 执行HystrixCommand
		return hystrixCommand.execute();
	}
}
