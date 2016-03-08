package com.moxingwang.core.session.local;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.moxingwang.core.session.Session;
import com.moxingwang.core.session.SessionManager;

public class LocalSessionManager extends SessionManager {
	private static long lastClear = 0;
	private static final long CLEAR_INTERVAL = 10 * Session.MINUTE;
	private Map<String, LocalSession> sessionMap = new ConcurrentHashMap<String, LocalSession>();

	@Override
	public Session getSession(String accessToken) {
		if (accessToken == null || "".equals(accessToken))
			return null;
		LocalSession session = sessionMap.get(accessToken);		
		if (session != null) {
			//check expire time
			long current = System.currentTimeMillis();
			Date expireTime = session.getExpireTime();
			long expire = expireTime.getTime();
			if (current <= expire) {
				//update expire time
				session.setAccessToken(accessToken, expire_time);

				return session;
			}
		}

		//expired
		if (session != null) {
			//delete session
			sessionMap.remove(session.getAccessToken());
			clearOldSession();
		}
		return null;
	}

	@Override
	synchronized public Session createSession() {
		clearOldSession();
		
		LocalSession session = new LocalSession();
		String accessToken = generateAccessToken();
		session.setAccessToken(accessToken, expire_time);
		sessionMap.put(accessToken, session);
		return session;
	}

	
	private void clearOldSession() {
		long current = System.currentTimeMillis();
		if (current - lastClear > CLEAR_INTERVAL) {
			Date expireTime;
			for (Entry<String, LocalSession> entry : sessionMap.entrySet()) {
				LocalSession session = entry.getValue();		
				if (session != null) {
					//check expire time
					expireTime = session.getExpireTime();
					long expire = expireTime.getTime();
					if (current > expire) {
						//delete session
						sessionMap.remove(entry.getKey());
					}
				}
			}
			lastClear = current;
		}
	}
}
