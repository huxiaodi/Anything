/**
 * @Title: HttpInteractionUtils.java
 * @Package com.dllg.ams.common
 * @Description: TODO(用一句话描述该文件做什么)
 * @author duyixin
 * @date 2016年5月4日 下午4:35:51
 * @version V1.0
 */


package com.dllg.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: HttpInteractionUtils
 * @Description: HTTP协议调用子系统的工具类
 * @author duyixin
 * @date 2016年5月4日 下午4:35:51
 *
 */
@Component
public class HttpInteractionUtils {

//	@Autowired
//	private RedisUtils redisUtils;
	
	@Autowired
	private RestTemplate restTemplate;
    /**
     *
     * @Title: generatorToken
     * @Description: 生产调用子系统API的校验码UUID
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String generatorToken() {
        return CommonUtil.uuid();
    }

    /**
     *
     * @Title: saveToken
     * @Description: 把产生的校验码保存在redis里 key-value都为token值,并且设置5秒超时
     * @param @param token    校验码
     * @param @param timeout    超时时间
     * * @param @param timeUnit    时间单位
     * @return void    返回类型
     * @throws
     */
//    public void saveToken(String token, Long timeout, TimeUnit timeUnit) {
//    	redisUtils.set(token, token);
//        redisUtils.expire(token, timeout, timeUnit);
//    }

    /**
     * 
    * @Title: setHttpEntity 
    * @Description: 设置请求
    * @param @param body
    * @param @param headerKey
    * @param @param headerValue
    * @param @return    设定文件 
    * @return HttpEntity<Map<String,String>>    返回类型 
    * @throws
     */
    public HttpEntity setHttpEntity(Object body, String headerKey, String headerValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(headerKey!=null&&!headerKey.isEmpty()) {        	
        	headers.set(headerKey, headerValue);
        }
        HttpEntity httpEntity = null;
        if(body!=null) {
        	httpEntity = new HttpEntity(body, headers);
        } else {
        	httpEntity = new HttpEntity(headers);
        }
        return httpEntity;
    }

    /**
     * 
    * @Title: exchange 
    * @Description: 通用请求
    * @param @param url
    * @param @param method
    * @param @param requestEntity
    * @param @param responseType
    * @param @param uriVariables
    * @param @return    设定文件 
    * @return ResponseEntity<?>    返回类型 
    * @throws
     */
    public ResponseEntity<?> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<?> responseType, Map<String,Object> uriVariables) {
        ResponseEntity<?> response = restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
        return response;
    }

    /**
     *
     * @Title: pastToken
     * @Description: 将redis中的该token过期
     * @param @param token    设定文件
     * @return void    返回类型
     * @throws
     */
//    public void pastToken(String token) {
//        redisUtils.del(token);
//    }
    
    /**
     * post 简易请求
     */
    public <T> T post(String url,Class<T> responseType, Map<String, String> map) {
    	String url_ = repaire(url, map);
        return post(url_,null,responseType,map);
    }
    /**
     * 直接传参数
     */
    public <T> T post(String url,Class<T> responseType, MultiValueMap<String, String> map) {
    	return post(url,map,responseType,new HashMap<String,String>());
    }
    public <T> T post(String url,Object request,Class<T> responseType, Map<String, String> map) {
    	return restTemplate.postForObject(url, request, responseType, map);
    }
    public <T> T posto(String url,Object request,Class<T> responseType, Map<String, Object> map) {
    	return restTemplate.postForObject(url, request, responseType, map);
    }
    /**
     * post 简易请求json
     */
    public <T> T post(String url,Class<T> responseType, Map<String, String> body,Map<String,String> params) {
    	MappingJacksonValue value = new MappingJacksonValue(body);
    	HttpEntity<MappingJacksonValue> httpEntity = new HttpEntity<>(value, httpHeaders(null));
    	String url_ = repaire(url, params);
    	return restTemplate.postForObject(url_, httpEntity, responseType, params);
    }
    /**
     * get 简易请求
     */
    public <T> T get(String url,Class<T> responseType, Map<String, String> map) {
        String url_ = repaire(url, map);
        System.out.println(url_);
        return restTemplate.getForObject(url_, responseType, map);
    }
    /**
     * get 简易请求
     */
    public <T> T getr(String url,Class<T> responseType, Map<String, Object> map) {
    	String url_ = repairere(url, map);
    	System.out.println(url_);
    	return restTemplate.getForObject(url_, responseType, map);
    }
    /**
     * post 简易请求
     */
    public <T> T postr(String url,Class<T> responseType, Map<String, Object> map) {
    	String url_ = repairere(url, map);
        return posto(url_,null,responseType,map);
    }
    
    public String repaire(String url, Map<String,String> map){
    	if(map == null)
    		return url;
    	StringBuilder sb = new StringBuilder();
    	map.keySet().stream().forEach(p -> {
    		String value = map.get(p);
    		if(value == null){
    			sb.append(p +"=&");
    		}else{
    			sb.append(p +"="+map.get(p)+"&");
    		}
    		});
    	if(url.indexOf("?")>0){
    		if(url.endsWith("&")){
    			sb.insert(0, url);
    		}else{
    			sb.insert(0, url+"&");
    		}
    	}else{
    		sb.insert(0, url+"?");
    	}
    	return sb.toString();
    }
    public String repairere(String url, Map<String,Object> map){
    	if(map == null)
    		return url;
    	StringBuilder sb = new StringBuilder();
    	map.keySet().stream().forEach(p -> {
    		Object value = map.get(p);
    		if(value == null){
    			sb.append(p +"=&");
    		}else{
    			sb.append(p +"="+map.get(p)+"&");
    		}
    	});
    	if(url.indexOf("?")>0){
    		if(url.endsWith("&")){
    			sb.insert(0, url);
    		}else{
    			sb.insert(0, url+"&");
    		}
    	}else{
    		sb.insert(0, url+"?");
    	}
    	return sb.toString();
    }
    private HttpHeaders httpHeaders(String authentication) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if (StringUtils.isNotBlank(authentication)) {
            httpHeaders.set("Authentication", authentication);
        }
        return httpHeaders;
    }
}
