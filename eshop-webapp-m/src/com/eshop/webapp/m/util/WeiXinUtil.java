package com.eshop.webapp.m.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class WeiXinUtil {
	
	
	
	/**
	 * 生成 ticket 请求地址 要求post 请求格式 post 永久格式：{"action_name": "QR_LIMIT_SCENE",
	 * "action_info": {"scene": {"scene_id": 123}}} post
	 * 临时格式：{"expire_seconds":604800, "action_name": "QR_SCENE", "action_info":
	 * {"scene": {"scene_id": 123}}} (expire_seconds 临时过期时间最大7天)
	 * 
	 * https://api.weixin.qq.com/cgi-bin/qrcode/create + "?access_token=TOKEN"
	 */
	private static final String GETEWAY_ticket_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	/**
	 * 根据ticket 生成 二维码 请求地址 TICKET记得进行UrlEncode
	 * https://mp.weixin.qq.com/cgi-bin/showqrcode + "?ticket=TICKET"
	 */
	private static final String GETEWAY_img_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	
	/**
	 * 生成店铺唯一二维码（永久）
	 * @return
	 */
	public String getTicketInfo(int sceneId) {
		try {
			HttpClient client = new DefaultHttpClient();
			String url = GETEWAY_ticket_URL + "?access_token="+ this.getAccessToken();
			HttpPost post = new HttpPost(url);
			JSONObject params = new JSONObject();
			JSONObject params2 = new JSONObject();
			JSONObject params3 = new JSONObject();
			params.put("action_name", "QR_LIMIT_SCENE");
			params3.put("scene_id", sceneId);// 1~10万
			params2.put("scene", params3);
			params.put("action_info", params2);
			StringEntity para = new StringEntity(params.toString());
			para.setContentEncoding("UTF-8");
			para.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(para);
			HttpResponse response = client.execute(post);
			JSONObject responseJson = null;
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("响应成功");
				HttpEntity httpEntity = response.getEntity();
				String result = EntityUtils.toString(httpEntity);
				responseJson = JSONObject.fromObject(result);
				Object errcode = responseJson.get("errcode");
				if (null != errcode) {
					System.out.println("请求结果错误");
				}
				System.out.println("ticket KEY："
						+ responseJson.getString("ticket").toString());
				String ticket = responseJson.getString("ticket").toString();
				if (StringUtils.isNotBlank(ticket)) {
					return GETEWAY_img_URL+ "?ticket="+ticket;
				}
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 生成临时二维码
	 * @param sceneId
	 * @return
	 */
	public String getTempQrCode(int sceneId,int limitSeconds) {
		try {
			HttpClient client = new DefaultHttpClient();
			String url = GETEWAY_ticket_URL + "?access_token="+ this.getAccessToken();
			HttpPost post = new HttpPost(url);
			JSONObject params = new JSONObject();
			JSONObject params2 = new JSONObject();
			JSONObject params3 = new JSONObject();
			params.put("action_name", "QR_SCENE");
			params.put("expire_seconds", limitSeconds);
			params3.put("scene_id", sceneId);// 1~10万
			params2.put("scene", params3);
			params.put("action_info", params2);
			StringEntity para = new StringEntity(params.toString());
			para.setContentEncoding("UTF-8");
			para.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(para);
			HttpResponse response = client.execute(post);
			JSONObject responseJson = null;
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("响应成功");
				HttpEntity httpEntity = response.getEntity();
				String result = EntityUtils.toString(httpEntity);
				responseJson = JSONObject.fromObject(result);
				Object errcode = responseJson.get("errcode");
				if (null != errcode) {
					System.out.println("请求结果错误");
				}
				System.out.println("ticket KEY："
						+ responseJson.getString("ticket").toString());
				String ticket = responseJson.getString("ticket").toString();
				if (StringUtils.isNotBlank(ticket)) {
					return GETEWAY_img_URL+ "?ticket="+ticket;
				}
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 后去AccessToken
	 * @return
	 * @throws Exception
	 */
	public String getAccessToken() throws Exception {
		String access_token = null;
		//access_token = (String) MemcachedClientSupport.getFromMemCached(MemcacheKeyEnum.WEIXIN_ACCESS_TOKEN);
		//if(access_token == null){
			String url1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx29b5cd93f26b8f14&secret=94546a94e6c02a7552950fac7cdd0cd6";
			URL url = new URL(url1);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			InputStream in = connection.getInputStream(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
			String str = reader.readLine();
			JSONObject json = JSONObject.fromObject(str);
			access_token= json.getString("access_token");
			//MemcachedClientSupport.setIntoMemCachedWithMinute(MemcacheKeyEnum.WEIXIN_ACCESS_TOKEN, access_token, 5);
		//}else{
			//System.out.println("直接就从memmerchache拿到值");
		//}
		return access_token;
	}

}
