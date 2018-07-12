package com.velvol.salary.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Nan
 * 2015-11
 */
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init(){
        if(cm == null){
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);//整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);//每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     * @return
     */
    private static CloseableHttpClient getHttpClient(){
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     *
     * @param url
     * @return
     */
    public static String httpGetRequest(String url){
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException{
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> headers,
                                        Map<String, Object> params) throws URISyntaxException{
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param: headers.entrySet()) {
            httpGet.addHeader(param.getKey(), param.getValue()+"");
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url){
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException{
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers,
                                         Map<String, Object> params) throws UnsupportedEncodingException{
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param: headers.entrySet()) {
            httpPost.addHeader(param.getKey(), param.getValue()+"");
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPost);
    }
    public static String httpPutRequest(String url, Map<String, Object> headers,
                                        Map<String, Object> params) throws UnsupportedEncodingException{
        HttpPut httpPut = new HttpPut(url);
        for (Map.Entry<String, Object> param: headers.entrySet()) {
            httpPut.addHeader(param.getKey(), param.getValue()+"");
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPut.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPut);
    }
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params){
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param: params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey()+"", param.getValue()+""));
        }

        return pairs;
    }


    /**
     * 处理Http请求
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request){
        //CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        try{
            CloseableHttpResponse response = httpClient.execute(request);
            //response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                //long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity);
                response.close();
                //httpClient.close();
                return result;
            }
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{

        }
        return EMPTY_STR;
    }
//   public static  void main(String [] aa){
//       Map<String, Object> headers=new HashMap<String, Object>();
////       headers.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxNjEwMDE1NCIsImV4cCI6MTUxNTY1ODM5NzcyMH0.dnp8l51nqMyMpUxNLg2YZ7o3kTWkAULJw_pHT0JfME8");
//       Map<String, Object> params=new HashMap<String, Object>();
//       try{
//           String obj=httpPostRequest("http://10.184.1.8/api/eck/test/users/validate", headers,params);
//           ObjectMapper mapper = new ObjectMapper();//转换器
////           String json=mapper.writeValueAsString(obj);//将对象转换成json
////           Result r = mapper.readValue(obj, Result.class); //json转换成map
////           System.out.println(r.getErrmsg()+"-----Errmsg");
////           System.out.println(r.getErrcode()+"--Errcode");
////           System.out.println(r.getUser().get("name"));
//       }catch(Exception e){
//           e.printStackTrace();
//       }
//
//   }
}  