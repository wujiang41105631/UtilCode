package com.xcn.util;

import com.google.common.collect.Maps;
import net.sf.json.JSONUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE = "text/json";


    public static String sendHttpGet(String url) throws IOException {
        log.info("HttpUtils.sendHttpGet url:{}", url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String txt = "";
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            txt = EntityUtils.toString(entity);
        } else {
            txt = "";
        }
        return txt;
    }

    public static String sendHttpPost(String params, String url) throws IOException {
        log.info("HttpUtils.sendHttpPost url:{},params:{}", url, params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String txt = "";
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON);
        StringEntity se = new StringEntity(params, "utf-8");
        se.setContentType(CONTENT_TYPE);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON));
        httpPost.setEntity(se);

        CloseableHttpResponse response = httpclient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            txt = EntityUtils.toString(entity);
        } else {
            txt = "";
        }
        return txt;
    }

    public static String sendHttpPost(Map<String, Object> params, String url) throws IOException {
        log.info("HttpUtils.sendHttpPost url:{},params:{}", url, JSONUtils.valueToString(params));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String txt = "";
        HttpPost httpPost = new HttpPost();
        httpPost.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON);
        List<NameValuePair> nvps = new LinkedList<NameValuePair>();
        Set<Map.Entry<String, Object>> paramsSet = params.entrySet();
        for (Map.Entry<String, Object> paramEntry : paramsSet) {
            nvps.add(new BasicNameValuePair(paramEntry.getKey(), ObjectUtils.toString(paramEntry.getValue())));
        }
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(nvps);
            httpPost.setURI(builder.build());
            CloseableHttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                txt = EntityUtils.toString(entity);
            } else {
                txt = "";
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return txt;
    }


    /**
     * 发送http post 请求(多参数)
     *
     * @param params
     * @param url
     * @throws IOException
     * @throws ClientProtocolException
     * @throws Exception
     */
    public static Map<String, Object> sendHttpPostForParams(List<NameValuePair> params, String url) throws UnsupportedEncodingException, HttpHostConnectException, ConnectException, ClientProtocolException, Exception {
        Map<String, Object> statusCodeMap = Maps.newHashMap();
        //创建HttpClient对象
        CloseableHttpClient closeHttpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        //发送Post请求  
        HttpPost httpPost = new HttpPost(url);
        try {
            log.info("send http post params:{},url:{}", params.toString(), url);
            //转换参数并设置编码格式  
            httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            //执行Post请求 得到Response对象  
            httpResponse = closeHttpClient.execute(httpPost);
            String statusCode = String.valueOf(httpResponse.getStatusLine().getStatusCode());
            statusCodeMap.put("statusCode", statusCode);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String responseTxt = EntityUtils.toString(httpEntity);
                log.info("send http post responseTxt:{}", responseTxt);
                statusCodeMap.put("responseTxt", responseTxt);
            }
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (closeHttpClient != null) {
                try {
                    closeHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return statusCodeMap;
    }
}
