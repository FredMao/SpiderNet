package com.spidernet.schedule.http;

public class HttpMessage {

	private int code;
    private String body;
    
    public HttpMessage(){
    	
    }
    
	public HttpMessage(int code, String body) {
		this.code = code;
		this.body = body;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
    
    
}
