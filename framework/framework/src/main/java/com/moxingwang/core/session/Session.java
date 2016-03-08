package com.moxingwang.core.session;

import java.util.Date;

public abstract class Session {
	public static long expireAt = 60;
	public static long MINUTE = 1000 * 60;

	public abstract void setAccessToken(String accessToken, long expireAt);
	
	public abstract String getAccessToken();
	
	public abstract Date getExpireTime();
	
	public abstract Object get(String key);
	
	public abstract boolean set(String key, Object value);
	
	public Date calExpireDate() {
		long current = System.currentTimeMillis();
		Date expireTime = new Date();
		expireTime.setTime(current + expireAt * MINUTE);
		return expireTime;
	}
}
