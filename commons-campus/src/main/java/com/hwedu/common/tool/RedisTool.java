package com.hwedu.common.tool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hwedu.common.constants.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
/**
 * 
 * @author htcgg 
 * 实现了redis的基本操作，以及发布订阅功能
 * 静态类初始化构建连接池，并分配一个连接
 * 使用该工具类之前先调用allocate方法判断
 * 是否当前连接已被回收，如果已回收自动分配一个
 */
public class RedisTool  {

	private static Jedis jedis = null;
	private static JedisPool pool = null;
	private static Logger logger = LogManager.getLogger();

	static {
		if (pool == null) {
			pool = new JedisPool(new JedisPoolConfig(), Constants.REDIS_IP, Constants.REDIS_PORT);// 使用连接池
			// jedis = new Jedis(Constants.REDIS_IP, Constants.REDIS_PORT);// 直接连接
			jedis = pool.getResource();
		}
	}
	
	public static Jedis getJedis() {
		return jedis;
	}

	public static void setJedis(Jedis jedis) {
		RedisTool.jedis = jedis;
	}

	public static JedisPool getPool() {
		return pool;
	}

	public static void setPool(JedisPool pool) {
		RedisTool.pool = pool;
	}

	/** 
     * 关闭一个连接 
     */  
    public static void close() {  
        if (jedis != null) {  
            jedis.close();
        }  
    }  
    
    /**
     * 摧毁连接池
     */
    public static void destoryPool(){
    	if(pool != null){
    		pool.destroy();
    		logger.info("连接池被摧毁");
    	}
    }
    
    public static void allocate(){
    	if(jedis==null){
			jedis = pool.getResource();
		}
    }

	public static void setValue(String key, String value) {
		jedis.set(key, value);
	}

	public static String getValue(String key) {
		return jedis.get(key);
	}

	/**
	 * 给某个key追加value
	 * 
	 * @param key
	 * @param value
	 */
	public static void appendValue(String key, String value) {
		jedis.append(key, value);
	}

	/**
	 * 给一个Set集合添加数据
	 * 
	 * @param lKey
	 *            集合的key
	 * @param sort
	 *            排序值
	 * @param lValue
	 *            数据
	 */
	public static void setSetValue(String lKey, double sort, String lValue) {
		jedis.zadd(lKey, sort, lValue);
	}

	/**
	 *
	 * @param lKey
	 *            Set集合的key
	 * @param start
	 *            集合的起始位置
	 * @param end
	 *            集合的末位，如何end=-1取全部数据
	 * @return 根据key值返回一个集合
	 */
	public static Set<String> getSetValue(String lKey, long start, long end) {
		return jedis.zrange(lKey, start, end);
	}

	/**
	 * list集合添加数据
	 * 
	 * @param lKey
	 * @param lValue
	 */
	public static void setListValue(String lKey, String lValue) {
		jedis.lpush(lKey, lValue);
	}

	/**
	 * 获取一个list集合
	 * 
	 * @param lKey
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getListValue(String lKey, long start, long end) {
		return jedis.lrange(lKey, start, end);
	}

	/**
	 * 获取集合长度
	 * 
	 * @param key
	 * @return
	 */
	public static long getlen(String key) {
		return jedis.llen(key);
	}

	public static void remove(String key) {
		jedis.del(key);
	}

	public static void renameKey(String key, String newKey) {
		jedis.rename(key, newKey);
	}

	/**
	 * 类似于hashmap功能
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public static void hsetValue(String key, String field, String value) {
		jedis.hset(key, field, value);
	}

	/**
	 * 获取hashmap中某个值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static String hgetValue(String key, String field) {
		return jedis.hget(key, field);
	}

	/**
	 * 通过key值返回一个map
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, String> hgetAllValue(String key) {
		return jedis.hgetAll(key);
	}

	/**
	 * 向某个通道发布一条消息
	 */
	public static void publishMessage(String channel, String message) {
		jedis.publish(channel, message);
	}

	/**
	 * 订阅多个通道的消息
	 * @param channel
	 */
	
	public static void subScriptMessage(String[] channel) {
		JedisPubSub pubJedis = new JedisPubSub() {
			public void onMessage(String s, String s1) {
				System.out.println("channel=" + s + ",message=" + s1);
			}
		};
		jedis.subscribe(pubJedis, channel); // 可以同时订阅多个通道
	}

	/**
	 * 删除所有的数据库
	 */
	public static void flushAll() {
		jedis.flushAll();
	}

	public static void main(String args[]) {
		RedisTool.setSetValue("subjects", 0, "v0");
		RedisTool.setSetValue("subjects", 1, "v1");
		RedisTool.setSetValue("subjects", 2, "v2");
		RedisTool.setSetValue("subjects", 4, "v3");
		RedisTool.setSetValue("subjects", 3, "v4");
		RedisTool.setSetValue("subjects", 5, "v5");
		Set<String> subs = RedisTool.getSetValue("subjects", 0, -1);
		Iterator<String> iters = subs.iterator();
		StringBuffer vals = new StringBuffer();
		while (iters.hasNext()) {
			String val = iters.next();
			vals.append(val);
		}
		System.out.println(vals);
		/////////////////////////////////////
		System.out.println(RedisTool.getValue("name"));
	}
}
