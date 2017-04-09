
package com.eshop.webapp.admin.util.session;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author shangxuejin
 * @date Nov 30, 2011 10:23:34 AM
 */
public class SessionData implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * session元数据
	 */
	private SessionMetaData sessionMetaData = null;
	/**
	 * session数据
	 */
	private final Map<String, Object> dataMap = new LinkedHashMap<String, Object>();;

	public SessionData(String sessionId, int maxIdle) {
		this.sessionMetaData = new SessionMetaData(sessionId, maxIdle);
	}

	public SessionMetaData getSessionMetaData() {
		return sessionMetaData;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	@Override
	public String toString() {
		return String.format("{sessionMetaData=%s, dataMap=%s}", sessionMetaData, dataMap.toString());
	}
}
