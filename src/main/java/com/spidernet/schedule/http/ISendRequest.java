package com.spidernet.schedule.http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

public interface ISendRequest {

	String doGet(String url)  throws ClientProtocolException, IOException;
	String doGet(String url, Map<String, String> params) throws ClientProtocolException, IOException, URISyntaxException;
	HttpMessage doPost(String url) throws ClientProtocolException, IOException;
	HttpMessage doPost(String url, Map<String, String> params) throws ClientProtocolException, IOException ;
	HttpMessage doPostJson(String url, String json) throws ClientProtocolException, IOException;
}
