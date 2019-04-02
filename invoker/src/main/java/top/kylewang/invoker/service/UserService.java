package top.kylewang.invoker.service;

import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.kylewang.invoker.entity.HystrixSetterFactory;
import top.kylewang.invoker.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private Map<Long, String> cache = new ConcurrentHashMap<>();

	@Autowired
	private RestTemplate restTemplate;

	public User queryUser(long userid) {
		User user = new User();
		user.setUserid(userid);
		user.setName("张三");
		user.setCard(queryBindCardByHystrix(userid));
		return user;
	}

	private String queryBindCardByHystrix(long userid) {
		if (cache.containsKey(userid)) {
			logger.info("------------- get cache -------------");
			return cache.get(userid);
		}

		HystrixCommand.Setter setter = HystrixSetterFactory.getSetter("queryBindCard", 100);
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(setter) {
			@Override
			protected String run() throws Exception {
				try {

					logger.info(Thread.currentThread().getName() + "------------- send request -------------");
					String result = restTemplate.getForObject("http://127.0.0.1:9001/queryBindCard?userid={userid}",
							String.class, userid);
					logger.info(Thread.currentThread().getName() + "------------- get response -------------");
					cache.put(userid, result);
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
