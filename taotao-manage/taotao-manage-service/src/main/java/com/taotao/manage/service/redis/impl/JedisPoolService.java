package com.taotao.manage.service.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.manage.service.redis.JedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolService implements JedisService{
	
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();

		return jedis.set(key, value);
	}

	@Override
	public String setex(String key, int seconds, String value) {
		Jedis jedis = jedisPool.getResource();
		
		return jedis.setex(key, seconds, value);
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		return jedis.expire(key, seconds);
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.get(key);
	}

	@Override
	public Long delete(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.del(key);
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.incr(key);
	}

}
