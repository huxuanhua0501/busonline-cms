package net.busonline.core.redis;

import redis.clients.jedis.Jedis;

public interface JedisAchieve {
	
	Jedis getJedis();
	
	void returnResource(Jedis jedis);
}
