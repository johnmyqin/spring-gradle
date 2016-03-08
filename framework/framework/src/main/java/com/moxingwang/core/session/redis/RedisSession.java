package com.moxingwang.core.session.redis;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.moxingwang.core.session.Session;

public class RedisSession extends Session {
	private static Logger logger =Logger.getLogger(RedisSession.class);
	
	private String accessToken;
	private Date expireTime;
	private Map<Object, Object> params;
	
	private static TimeUnit TIMEUNIT = TimeUnit.MINUTES;
	
	private static final String PREFIX = "session:";
	
	private RedisTemplate<String, Object> redisTemplate;
	
	private BoundHashOperations<String, Object, Object> hashOp;
	
	public RedisSession(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		params = null;
	}

	public RedisSession(String accessToken, RedisTemplate<String, Object> redisTemplate) {
		if (accessToken == null || "".equals(accessToken)) {
			params = null;
		} else {
			//get session
			this.redisTemplate = redisTemplate;
			this.accessToken = accessToken;
			hashOp = redisTemplate.boundHashOps(PREFIX + accessToken);		
			logger.debug("Get redis session:" + accessToken + ",    hash:" + hashOp);
			if (hashOp != null) {
				params = hashOp.entries();
				if (params != null) {
					hashOp.expire(expireAt, TIMEUNIT);
					expireTime = new Date(System.currentTimeMillis() + hashOp.getExpire()*1000);
				}
			}
		}
	}

	@Override
	public Object get(String key) {
		return params.get(key);
	}

	@Override
	public boolean set(String key, Object value) {
		if (hashOp != null) {
			params.put(key, value);
			hashOp.put(key, value);
			return true;
		}
		return false;
	}

	@Override
	public void setAccessToken(String accessToken, long expireAt) {
		this.accessToken = accessToken;
		this.expireAt = expireAt;
		this.expireTime = calExpireDate();
		//create a new session
		hashOp = redisTemplate.boundHashOps(PREFIX + accessToken);
		if (hashOp != null) {
			hashOp.expire(expireAt, TIMEUNIT);
			hashOp.put("access_token", accessToken);
			params = hashOp.entries();
		}
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public Date getExpireTime() {
		return expireTime;
	}
	
	public boolean isEmpty() {
		if (params == null || params.isEmpty())
			return true;
		else
			return false;
	}

}
