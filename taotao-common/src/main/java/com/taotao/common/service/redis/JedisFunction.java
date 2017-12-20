package com.taotao.common.service.redis;

public interface JedisFunction<T,E> {

	public T callback(E jedis);
}
