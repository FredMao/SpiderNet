package com.spidernet.schedule.http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

@Service("iSendRequest")
public class SendRequest implements ISendRequest, BeanFactoryAware {

//	@Resource
	private RequestConfig requestConfig;

    private BeanFactory beanFactory;
    
//	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.beanFactory = arg0;
	}

	private CloseableHttpClient getHttpClient() {
        return this.beanFactory.getBean(CloseableHttpClient.class);
    }
	
	/**
     * Simple Get
     * 
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	@Override
    public String doGet(String url) throws ClientProtocolException, IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * @param url
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
	@Override
    public String doGet(String url, Map<String, String> params)
            throws ClientProtocolException, IOException, URISyntaxException {

        if (null == params)
            return this.doGet(url);

        URIBuilder uriBuilder = new URIBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }

        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	@Override
    public HttpMessage doPost(String url) throws ClientProtocolException, IOException {
        return doPost(url, null);
    }

    /**
     * 
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	@Override
    public HttpMessage doPost(String url, Map<String, String> params)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);

        if (null != params) {
            ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
            httpPost.setEntity(entity);
        }

        httpPost.setConfig(this.requestConfig);
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");

        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(httpPost);
            return new HttpMessage(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

	@Override
    public HttpMessage doPostJson(String url, String json) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);

        if (json != null) {
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            response = this.getHttpClient().execute(httpPost);
            return new HttpMessage(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
