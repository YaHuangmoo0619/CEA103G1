package com.member.model;

import jedis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandler {

	private static JedisPool pool = JedisUtil.getJedisPool();
	
	public static String randAuthCode() {
		StringBuffer code = new StringBuffer();
		String elements = "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 10; i++)
			code.append(elements.charAt((int) (Math.random() * elements.length())));
		return code.toString();
	}
	
	public String setAuthCode(String acc) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		String authCode = randAuthCode();
		jedis.select(1);
		jedis.set(acc, authCode);
		jedis.expire(acc, 86400);
		
		jedis.close();
		return authCode;
	}
	
	public static String getAuthCode(String acc) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		jedis.select(1);
		String savedCode = jedis.get(acc);
		
		jedis.close();	
		return savedCode;
	}
	
}