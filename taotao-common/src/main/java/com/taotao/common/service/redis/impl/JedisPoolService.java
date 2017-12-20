package com.taotao.common.service.redis.impl;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.service.redis.JedisFunction;
import com.taotao.common.service.redis.JedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolService implements JedisService {
	
	@Autowired
	private JedisPool jedisPool;
	
	private <T> T execute(JedisFunction<T,Jedis> fun) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return fun.callback(jedis);
		} finally {
			jedis.close();
		}
	}

	@Override
	public String set(final String key, final String value) {
		return execute(new JedisFunction<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	@Override
	public String setex(final String key, final int seconds, final String value) {
		return execute(new JedisFunction<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		});
	}

	@Override
	public Long expire(final String key, final int seconds) {
		return execute(new JedisFunction<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	@Override
	public String get(final String key) {
		return execute(new JedisFunction<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	@Override
	public Long del(final String key) {
		return execute(new JedisFunction<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.del(key);
			}
		});
	}

	@Override
	public Long incr(final String key) {
		return execute(new JedisFunction<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}

}
