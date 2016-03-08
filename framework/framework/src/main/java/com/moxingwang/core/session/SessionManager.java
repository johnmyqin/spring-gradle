package com.moxingwang.core.session;

public abstract class SessionManager {
	public long expire_time = 60;

	public abstract Session getSession(String accessToken);
	
	public abstract Session createSession();
	
	public String generateAccessToken() {
		return SessionUtil.generateShortUuid();
	}
	
	public void setExpireTime(long et) {
		expire_time = et;
	}
	

}
