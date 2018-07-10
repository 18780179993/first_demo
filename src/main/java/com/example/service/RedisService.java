package com.example.service;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
@Service
public class RedisService {
	
	@Resource(name="userRedisTemplate")
	RedisTemplate<String, Object> userRedis;
	
	
	public void add() {
//		userRedis.opsForList().leftPushAll("test", 1,2,3,4,5);
		
//		userRedis.opsForValue().set("string", "first");
//		userRedis.opsForValue().append("string", "three");
		userRedis.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				RedisSerializer<String> serializer = (RedisSerializer<String>) userRedis.getKeySerializer();
				RedisSerializer<Object> serializer2 =  (RedisSerializer<Object>) userRedis.getValueSerializer();
                byte[] key  = serializer.serialize("xxx");
                byte[] name = serializer.serialize("xxx");
				connection.setNX(key, name);
				return true;
			}
		});
	}
	
	public void get() {
//		Object i=userRedis.opsForList().leftPop("test");
//		System.out.println(i);
//		System.out.println(userRedis.opsForValue().get("string"));
	}

}
