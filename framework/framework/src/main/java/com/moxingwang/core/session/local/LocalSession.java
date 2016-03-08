package com.moxingwang.core.session.local;

import java.util.Date;
import java.util.HashMap;

import com.moxingwang.core.session.Session;

public class LocalSession extends Session {
	private String accessToken;
	private Date expireTime;
	private HashMap<String, Object> params = new HashMap<String, Object>();

	@Override
	public Object get(String key) {
		return params.get(key);
	}

	@Override
	public boolean set(String key, Object value) {
		params.put(key, value);
		return false;
	}

	@Override
	public void setAccessToken(String accessToken, long expireAt) {
		this.accessToken = accessToken;
		this.expireAt = expireAt;
		this.expireTime = calExpireDate();
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public Date getExpireTime() {
		return expireTime;
	}

}
