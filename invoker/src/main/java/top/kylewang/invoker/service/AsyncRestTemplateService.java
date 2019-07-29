package top.kylewang.invoker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import com.netflix.hystrix.HystrixCommand;

import top.kylewang.invoker.hystrix.HystrixSetterFactory;

/**
 * 使用最基础的RestTemplate请求
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
@Service
public class AsyncRestTemplateService  {

	private static final Logger logger = LoggerFactory.getLogger(AsyncRestTemplateService.class);

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	public String queryData(int id) {
		HystrixCommand.Setter setter = HystrixSetterFactory.getSetter("queryData", 5000);
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(setter) {

			private ListenableFuture<ResponseEntity<String>> future;

			@Override
			protected String run() throws Exception {
				try {
					logger.info(Thread.currentThread().getName() + "------------- send request -------------");
					future = asyncRestTemplate.getForEntity("http://127.0.0.1:9001/queryData?id={id}",
							String.class, id);
					logger.info(Thread.currentThread().getName() + "------------- get response future -------------");
					ResponseEntity<String> responseEntity = null;
					try {
						responseEntity = future.get();
					} catch (Exception e) {
						logger.error("get future error", e);
					}
					logger.info(Thread.currentThread().getName() + "------------- get response entity -------------");
					if (responseEntity != null) {
						return responseEntity.getBody();
					}
					logger.warn(
							Thread.currentThread().getName() + "------------- response entity is null -------------");
					return null;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}

			@Override
			protected String getFallback() {
				logger.info(Thread.currentThread().getName() + "------------- fallback -------------");
				if (future != null) {
					logger.info(Thread.currentThread().getName() + "------------- cancel future -------------");
					future.cancel(true);
					logger.info(
							Thread.currentThread().getName() + "------------- cancel future complete -------------");
				}
				return null;
			}
		};
		logger.info(Thread.currentThread().getName() + "------------- execute command -------------");
		return hystrixCommand.execute();
	}
}
