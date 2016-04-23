package top.latfat.mailcontrol.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 *
 * @author seanwg
 * 2016-4-24 上午02:51:52
 */
@Configuration
public class ThreadPoolConfiguration {
	
	@Value("${pool.corePoolSize}")
	private int corePoolSize;
	
	@Value("${pool.maxPoolSize}")
	private int maxPoolSize;
	
	@Value("${pool.keepAliveSeconds}")
	private int keepAliveSeconds;
	
	@Value("${pool.queueCapacity}")
	private int queueCapacity;

	@Bean
	ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
		threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
		threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
		threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
		return threadPoolTaskExecutor;
	}
}
