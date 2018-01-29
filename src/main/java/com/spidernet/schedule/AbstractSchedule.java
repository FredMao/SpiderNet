package com.spidernet.schedule;

import net.sf.json.JSONObject;

public abstract class AbstractSchedule {

	/**
	 * @see Access other's system via httpClict and return back
	 * @param url
	 * @return jsonObject
	 */
	public JSONObject getData(String url){
		JSONObject item = new JSONObject();
		return item;
	}
	
	public abstract void handle();
}
