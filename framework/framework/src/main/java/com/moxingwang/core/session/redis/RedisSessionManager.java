package com.moxingwang.core.session.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.moxingwang.core.session.Session;
import com.moxingwang.core.session.SessionManager;

public class RedisSessionManager extends SessionManager {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Session getSession(String accessToken) {
		if (accessToken == null || "".equals(accessToken))
			return null;
		RedisSession session = new RedisSession(accessToken, redisTemplate);
		if (session.isEmpty()) {
			return null;
		} else {
			return session;
		}
	}

	@Override
	public Session createSession() {
		RedisSession session = new RedisSession(redisTemplate);
		String accessToken = generateAccessToken();
		session.setAccessToken(accessToken, expire_time);
		return session;
	}

	
}
