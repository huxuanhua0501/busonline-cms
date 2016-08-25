package net.busonline.core.redis;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.busonline.core.util.ProFileUtil;
import net.busonline.core.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("unchecked")
public class RedisCache {
	static JedisPool pool;
	public static final String REDIS_CHARSET="utf-8";
	public static void returnJedis(Jedis  redis){
		pool.returnResource(redis);
	}

	
	public static Set<String> getSet(String key){
		Jedis  redis=pool.getResource();
		Set<String> mems1=redis.smembers(key);
		returnJedis(redis);
		return mems1;
	}
	
	
	public static void setIntoSet(String key,String ...values){
		Jedis  redis=pool.getResource();
		redis.sadd(key, values);
		returnJedis(redis);
	}
	
	
	public static <T> T getHash(String domin,String key){
		Jedis  redis=pool.getResource();
		byte[] bytes=redis.hget(domin.getBytes(Charset.forName("utf8")), key.getBytes(Charset.forName("utf8")));
		T ret=(T) SerializeUtil.unserialize(bytes);
		returnJedis(redis);
		return ret;
	}
	
	
	public static <T> Map<String,T> getHashAll(String domin){
		Jedis  redis=pool.getResource();
		Map<byte[],byte[]> retMap=redis.hgetAll(domin.getBytes(Charset.forName("utf8")));
		Map<String,T> ret=new HashMap<String,T>();
		Iterator<byte[]> iter = retMap.keySet().iterator();
		while (iter.hasNext()) {
			byte[] key = iter.next();
			byte[] value = retMap.get(key);
			ret.put(new String(key,Charset.forName("utf8")), (T)SerializeUtil.unserialize(value));
		}
		returnJedis(redis);
		return ret;
	}
	
	
	
	/**
	 * 入-尾部
	 * @param key
	 * @param value
	 */
	public static void lPush(String key,Object value){
		Jedis  redis=pool.getResource();
		redis.rpush(key.getBytes(Charset.forName("utf-8")), SerializeUtil.serialize(value));
		returnJedis(redis);
	}
	
	
	/**
	 * 出-头部
	 * @param key
	 * @return
	 */
	public static <T> T lpop(String key){
		Jedis  redis=pool.getResource();
		byte[] data=redis.lpop(key.getBytes(Charset.forName("utf-8")));
		T t=(T) SerializeUtil.unserialize(data);
		returnJedis(redis);
		return t;
	}
	
 
	
	public static  void setHash(String domin,String key, Object object){
		Jedis  redis=pool.getResource();
		redis.hset(domin.getBytes(Charset.forName(REDIS_CHARSET)), key.getBytes(Charset.forName(REDIS_CHARSET)), SerializeUtil.serialize(object));
		returnJedis(redis);
	}

	
	public static  void setHashAll(String domin,Map<String,Object> hash){
		Jedis  redis=pool.getResource();
		Iterator<String> iter = hash.keySet().iterator();
		while (iter.hasNext()) {
			String key=iter.next();
			byte[] item=SerializeUtil.serialize(hash.get(key));
			redis.hset(domin.getBytes(Charset.forName(REDIS_CHARSET)), key.getBytes(Charset.forName(REDIS_CHARSET)), item);
		}
		returnJedis(redis);
	}
	
	
	/**
	 * 赋值
	 * @param key
	 * @param value
	 */
	public static void setObject(String key,Object value){
		Jedis  redis=pool.getResource();
		 redis.set(key.getBytes(Charset.forName(REDIS_CHARSET)), SerializeUtil.serialize(value));
		 returnJedis(redis);
	}
	
	
	/**
	 * 修改时间
	 * @param key
	 * @param seconds
	 */
	public static void setExpire(String key,int seconds){
		Jedis  redis=pool.getResource();
		redis.expire(key, seconds);
		returnJedis(redis);
	}
	
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	public static <T> T getObject(String key){
		Jedis  redis=pool.getResource();
		byte[] obytes=redis.get(key.getBytes(Charset.forName(REDIS_CHARSET)));
		T ret=(T) SerializeUtil.unserialize(obytes);
		returnJedis(redis);
		return ret;
	}
	
	
	public static boolean existKey(String key){
		Jedis  redis=pool.getResource();
		boolean ret=redis.exists(key);
		returnJedis(redis);
		return ret;
	}
	
	/**
	 * 判断是否存在
	 * @param domin
	 * @param key
	 * @return
	 */
	public static boolean existMapKey(String domin,String key){
		Jedis  redis=pool.getResource();
		boolean ret =redis.hexists(domin, key);
		returnJedis(redis);
		return ret;
	}
	
	
	/**
	 * 根据key 删除数据
	 * @param key
	 */
	public static void del(String key){
		Jedis  redis=pool.getResource();
//		long delNum=redis.del(key.getBytes(Charset.forName(REDIS_CHARSET)));
		redis.del(key.getBytes(Charset.forName(REDIS_CHARSET)));
		returnJedis(redis);
	}
	
	
	public static  byte[] getByte(Object value){
		byte[] midbytes = null;
		try {
			midbytes = value.toString().getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return midbytes;
	}
	
	
	public static <T> T getObject(Class<T> clzz,String domin,String field) throws UnsupportedEncodingException{
		 Jedis  redis=pool.getResource();
		List<byte[]> b=redis.hmget(domin.getBytes(REDIS_CHARSET), field.getBytes(REDIS_CHARSET));
		returnJedis(redis);
		if(b!=null&&!b.isEmpty())
		return (T) SerializeUtil.unserialize(b.get(0));
		return null;
	}
	
	
	public static void setObject(String domin,Map<byte[],byte[]> map) throws UnsupportedEncodingException{
		 Jedis  redis=pool.getResource();
		redis.hmset(domin.getBytes(REDIS_CHARSET), map);
		returnJedis(redis);
	}
	
	
	public static void setObject(String domin,String field,Object o) throws UnsupportedEncodingException{
		 Jedis  redis=pool.getResource();
		redis.hset(domin.getBytes(REDIS_CHARSET), field.getBytes(REDIS_CHARSET), SerializeUtil.serialize(o));
		returnJedis(redis);
	}
	
	
	
	public static  Jedis open(){
		 Jedis  redis=pool.getResource();
		 if (redis != null){
			 pool.returnBrokenResource(redis);
		 }
		return redis;
	}
	
	
/*	public static void main(String[] args) throws UnsupportedEncodingException {
		 Jedis  redis=pool.getResource();
		 setObject(CACHE_KEY.令牌缓存域.getVal(), "aaaa");
		 setObject(CACHE_KEY.图片缓存域.getVal(), "bbbb");
		 setObject(CACHE_KEY.头像缓存.getVal(), "ccccc");
		 setObject("aaaaaa", "ccccc");
		 
          Set<String> s = redis.keys("APPCLIENT*");//模糊查询
		  Iterator it = s.iterator();
          System.out.println("删除前"); 
    	  System.out.println(redis.dbSize());
		  while (it.hasNext()) {
			   String key = (String) it.next();
			   Object value = getObject(key);
			   del(key);
			   System.out.println(key );
			   System.out.println(value );
		  }
		  
	
		  //del("APPCLIENT.*");
		 
		  Set<String> s1 = redis.keys("APPCLIENT*");
		  Iterator it1 = s1.iterator();
          System.out.println("删除后"); 
          System.out.println(redis.dbSize());
		  while (it1.hasNext()) {
			   String key = (String) it1.next();
			   Object value = redis.get(key);
			   System.out.println(key );
		  }
		 
		 returnJedis(redis);
	}*/
	
	
}
