package net.busonline.core.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public abstract class BaseJedis implements JedisAchieve {
	
	public String rpop(int dbIndex, String key) {
		String value = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			value = jedis.rpop(key);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(int dbIndex, byte[] key) {
		byte[] value = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			value = jedis.get(key);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public String get(int dbIndex, String key) {
		String value = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			value = jedis.get(key);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(int dbIndex, byte[] key, byte[] value) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.set(key, value);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(int dbIndex, byte[] key, byte[] value, int expire) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public String set(int dbIndex, String key, String value) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.set(key, value);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public String set(int dbIndex, String key, String value, int expire) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] hset(int dbIndex, byte[] key, byte[] field, byte[] value) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.hset(key, field, value);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] hset(int dbIndex, byte[] key, byte[] field, byte[] value, int expire) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.hset(key, field, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public String hset(int dbIndex, String key, String field, String value, int expire) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.hset(key, field, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public String hset(int dbIndex, String key, String field, String value) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.hset(key, field, value);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public byte[] hget(int dbIndex, byte[] key, byte[] field) {
		byte [] value = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			value = jedis.hget(key, field);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public String hget(int dbIndex, String key, String field) {
		String value = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			value = jedis.hget(key, field);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	public List<String> hget2(int dbIndex, String key, String sign) {

		Jedis jedis = getJedis();
		List<String> d = null;
		try {
			jedis.select(dbIndex);
			d = jedis.hmget(key, sign);
		} finally {
			returnResource(jedis);
		}
		return d;
	}
	public Map<byte [], byte []> hmset(int dbIndex, byte [] key, Map<byte [], byte []> hash) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.hmset(key, hash);
		} finally {
			returnResource(jedis);
		}
		return hash;
	}
	
	public Map<byte [], byte []> hmset(int dbIndex, byte [] key, Map<byte [], byte []> hash, int expire) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			jedis.hmset(key, hash);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			returnResource(jedis);
		}
		return hash;
	}

	public List<byte []> hmget(int dbIndex, byte [] key, byte []... fields) {
		List<byte []> hash = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			hash = jedis.hmget(key, fields);
		} finally {
			returnResource(jedis);
		}
		return hash;
	}
	
	/**
	 * del
	 * 
	 * @param key
	 */
	public void del(int dbIndex, byte[] key) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			byte[] sb = jedis.get(key);
			if (sb != null) {
				jedis.select(dbIndex);
				jedis.del(key);
			}
		} finally {
			returnResource(jedis);
		}
	}
	
	public void del(int dbIndex, String key) {
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			String sb = jedis.get(key);
			if (sb != null) {
				jedis.select(dbIndex);
				jedis.del(key);
			}
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * flush
	 */
	public void flushDB(int dbIndex) {
		Jedis jedis = getJedis();
		try {
			// jedis.select(dbIndex);
			jedis.flushDB();
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * size
	 */
	public Long dbSize(int dbIndex) {
		Long dbSize = 0L;
		Jedis jedis = getJedis();
		try {
			// jedis.select(dbIndex);
			dbSize = jedis.dbSize();
		} finally {
			returnResource(jedis);
		}
		return dbSize;
	}
	
	/**
	 * keys
	 * 
	 * @param regex
	 * @return
	 */
	public List<String> hvals(int dbIndex, String key) {
		List<String> vals = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			vals = jedis.hvals(key);
		} finally {
			returnResource(jedis);
		}
		return vals;
	}

	/**
	 * keys
	 * 
	 * @param regex
	 * @return
	 */
	public Set<byte[]> keys(int dbIndex, byte[] pattern) {
		Set<byte[]> keys = null;
		Jedis jedis = getJedis();
		try {
			// jedis.select(dbIndex);
			keys = jedis.keys(pattern);
		} finally {
			returnResource(jedis);
		}
		return keys;
	}
	
	/**
	 * keys
	 * 
	 * @param regex
	 * @return
	 */
	public Set<byte[]> hkeys(int dbIndex, byte[] pattern) {
		Set<byte[]> keys = null;
		Jedis jedis = getJedis();
		try {
			jedis.select(dbIndex);
			keys = jedis.hkeys(pattern);
		} finally {
			returnResource(jedis);
		}
		return keys;
	}

	public Boolean isExists(String key) {
		Jedis jedis=null;
		try {
			jedis = getJedis();
			return	jedis.exists(key);
		} finally {
			returnResource(jedis);
		}
	}
	/**
	 * 设置key过期时间
	 */
	public void expire(String key){
		Jedis jedis = getJedis();
		try {
			jedis.expire(key,1800);
		} finally {
			returnResource(jedis);
		}
	}
	
	
	
	
}
