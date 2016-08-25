package net.busonline.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisResultSet extends BaseJedis {

	@Autowired
	private JedisPool jedisPool;

	/*
	 * 通过redis连接池获取Jedis对象
	 */
	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	/*
	 * 释放占用的jedis资源
	 */
	public void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}
	
}
