package top.kylewang.invoker.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
public class HystrixSetterFactory {

	public static HystrixCommand.Setter getSetter(String key, int timeOutMilliseconds) {
		HystrixCommandProperties.Setter propSetter = HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true)
				.withExecutionTimeoutInMilliseconds(timeOutMilliseconds)
				.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
				.withExecutionIsolationThreadInterruptOnTimeout(true);
		return HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(key))
				.andCommandPropertiesDefaults(propSetter);
	}
}
