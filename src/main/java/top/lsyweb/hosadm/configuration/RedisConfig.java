package top.lsyweb.hosadm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-31
 */
@Configuration
@PropertySource("classpath:application-online.yml")
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private int maxWaitMillis;

//	@Value("${redis.blockWhenExhausted}")
//	private Boolean blockWhenExhausted;
//
//	@Value("${redis.JmxEnabled}")
//	private Boolean JmxEnabled;

	@Bean
	public JedisPool jedisPoolFactory() {
//		System.out.println("JedisPool注入开始...");
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
//		jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		// 是否启用pool的jmx管理功能, 默认tru
//		jedisPoolConfig.setJmxEnabled(JmxEnabled);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//		System.out.println("JedisPool注入成功...");
		return jedisPool;
	}
}