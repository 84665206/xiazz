package com.eshop.webapp.m.paycenter.handle;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.eshop.webapp.m.enums.WeixinPayTypeEnum;
import com.eshop.webapp.m.order.model.OrderInfo;
import com.eshop.webapp.m.paycenter.config.WeixinpayConfig;
import com.eshop.webapp.m.paycenter.util.HttpProtocolHandler;
import com.eshop.webapp.m.paycenter.util.HttpRequest;
import com.eshop.webapp.m.paycenter.util.HttpResponse;
import com.eshop.webapp.m.paycenter.util.HttpResultType;
import com.eshop.webapp.m.paycenter.util.WeixinPayRequestHandler;
import com.eshop.webapp.m.paycenter.util.WeixinPayResponse;
import com.eshop.webapp.m.paycenter.util.WeixinPayUtil;
/**
 * 
 * @ClassName: WeixinPayForH5Handle 
 * JS_API支付
 * ====================================================
 * 在微信浏览器里面打开H5网页中执行JS调起支付。接口输入输出数据格式为JSON。
 * 成功调起支付需要三个步骤：
 * 步骤1: 如果没有code跳转至微信获取code ，微信回跳到客户端传入code
 * 步骤2：网页授权获取用户openid
 * 步骤3：使用统一支付接口，获取prepay_id
 * 步骤4：使用jsapi调起支付
 * 
 * yangmeng
 */
@Component
public class WeixinPayForH5Handle {

	protected static Logger logger = LoggerFactory.getLogger(WeixinPayForH5Handle.class);
	
	public String getPayFormHtml(OrderInfo order, Map<String, Object> paras) {
		if (order != null) {

            String domainHost=(String)paras.get("domainHost");

          //商户号
			String mchId = WeixinpayConfig.mch_id;
			//appid
			String appId = WeixinpayConfig.app_id;
			//密钥
			String key = WeixinpayConfig.key;
            //主要为了获取用户的openid
            // String appsecret=WeixinpayConfig.getAppSecret(site);
            
            //订单支付后的通知地址
            String notifyUrl=WeixinpayConfig.notify_url;//测试  "http://52qq.wicp.net:51242/weixinpay/payNotify.php";// WeixinpayConfig.getNotify_url(domainHost,site);
            //用户ip,测试环境时不要加这个ip参数，正式环境再加此参数
            String clientIP = paras.get("clientIP").toString();
            String openId = paras.get("openId").toString();
            String nonceStr=WeixinPayUtil.generateRandom32();//不超过32位
            
            //发起预支付交易
            WeixinPayResponse weixinPayResponse=this.startPrePay(order, key, appId, mchId, notifyUrl, clientIP, openId,nonceStr);
            //返回html唤起微信支付
            return this.buildSubHtml(weixinPayResponse, key, nonceStr,order,domainHost);
         }
		return null;
	}
	
	/**
	 * 发起预支付交易
	 * @param order
	 * @param key
	 * @param appId
	 * @param mchId
	 * @param notifyUrl
	 * @param clientIp
	 * @param openId
	 * @param nonce_str
	 * @return
	 */
	private WeixinPayResponse startPrePay(OrderInfo order,String key,String 
			appId,String mchId,String notifyUrl,String clientIp,String openId,String nonce_str){
		 //商户订单号，不超过32位，需要保证唯一性
        String out_trade_no = order.getOrder_sn();
        String orderAmount = "0"; 
        orderAmount = BigDecimal.valueOf(order.getNeed_pay_amount())
					.multiply(BigDecimal.valueOf(100)).intValue()+""; 
        //创建PayRequestHandler实例
       WeixinPayRequestHandler reqHandler = new WeixinPayRequestHandler(); 
        //设置密钥
       reqHandler.setKey(key); 
       reqHandler.setCharset(WeixinpayConfig.charset);
        //初始化
       reqHandler.init(); 
        //-----------------------------
        //设置支付参数
        //-----------------------------
      
       reqHandler.setParameter("appid", appId);	 //appid
       reqHandler.setParameter("mch_id", mchId);			//商户号
       reqHandler.setParameter("out_trade_no", out_trade_no+"_p_H5");				//商家订单号
       reqHandler.setParameter("notify_url", notifyUrl);				//支付通知url
       reqHandler.setParameter("nonce_str", nonce_str);	  //随机字符串
       reqHandler.setParameter("body", "LOVO商城订单：" + out_trade_no);	//商品描述
       reqHandler.setParameter("total_fee", orderAmount);						//商品金额,以分为单位
        
        
       reqHandler.setParameter("spbill_create_ip", clientIp);
       reqHandler.setParameter("trade_type", WeixinPayTypeEnum.JSAPI.type);
       reqHandler.setParameter("openid",openId );//必传
       
        
        //post实现方式
       String requestUrl=reqHandler.getGateUrl();
       //把请求参数转成xml
       String requestXML=reqHandler.getRequestXML();
       
       HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

       HttpRequest request = new HttpRequest(HttpResultType.BYTES);
       //设置编码集
       request.setCharset(WeixinpayConfig.charset);

       request.setUrl(requestUrl);
       request.setQueryString(requestXML);

       HttpResponse response = httpProtocolHandler.execute(request);
       
       if (response == null) {
           return null;
       }
       
       String responseContext;
       
		try {
			responseContext = response.getStringResult();
			//println(responseContext);
			
			if (StringUtils.isNotBlank(responseContext)) {
				
				WeixinPayResponse weixinPayResponse = WeixinPayResponse.getValueByNode(responseContext, "xml");
				return weixinPayResponse;
				//timeStamp
				//校验响应结果和业务结果是否成功
//				if (weixinPayResponse!=null&& WeixinPayResponse.SUCCESS_CODE.equals(
//								weixinPayResponse.getReturnCode())&&
//								WeixinPayResponse.SUCCESS_CODE.equals(weixinPayResponse.getResultCode())) {
//					return weixinPayResponse;
//					 
//				} else {
//					//记录错误日志
//					logger.debug("微信预生成订单失败,原因:"+weixinPayResponse.getResultCode());
//					return weixinPayResponse;
//				}
				
			} else {
				logger.debug("从微信获未获取到返回结果!!!");
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error("微信支付请求编码异常:"+e.getMessage());
		}
		return null;
	}
	
	public String buildJsPayResultJson(WeixinPayResponse response,String key,String nonceStr){
		
		WeixinPayRequestHandler reqHandler = new WeixinPayRequestHandler();
		reqHandler.setKey(key);
		
		String appId=response.getAppid();
		String timeStamp=WeixinPayUtil.getCurrentTimeStamp();
		String packages="prepay_id="+response.getPrepayId();
		String signType="MD5";
		
		//签名包数据
		reqHandler.setParameter("appId", appId);
		reqHandler.setParameter("timeStamp", timeStamp);
		reqHandler.setParameter("nonceStr", nonceStr);
		reqHandler.setParameter("package", packages);
		reqHandler.setParameter("signType", signType);
		String paySign=reqHandler.createPaySign();
		reqHandler.setParameter("paySign", paySign);
		reqHandler.setParameter("resultType", "success");
		String result=JSONObject.toJSONString(reqHandler.getAllParameters());
		return result;
	}

	/**
	 * 构造前台展示html
	 * @param response
	 * @param key
	 * @param nonceStr
	 * @param order
	 * @param domainHost
	 * @param site
	 * @return
	 */
	public String buildSubHtml(WeixinPayResponse response,String key,String nonceStr,OrderInfo order,String domainHost){
		
		String strpayHtml = "";
		
		strpayHtml += "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head>";
		strpayHtml += " <meta http-equiv=\"content-type\" content=\"text/html; charset="+ WeixinpayConfig.charset + "\" >";
		strpayHtml += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0\">";
		strpayHtml += "<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">";
		strpayHtml += "<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">"; 
		
		if (response==null ||!WeixinPayResponse.SUCCESS_CODE.equals(response.getReturnCode())
				||!WeixinPayResponse.SUCCESS_CODE.equals(response.getResultCode())) {
			strpayHtml +="<body>";
			strpayHtml +="<p style=\"font-size:15px;font-weight:bold;\">";
			strpayHtml +="微信支付发起失败，商家订单号重复";
			strpayHtml +="</p>";
			strpayHtml +="</body>";
			strpayHtml +="</html>";
			//println("杨孟测试微信支付,状态码：ReturnCode="+response.getReturnCode()+",ResultCode="+response.getResultCode());
			return strpayHtml;
		}
		
		WeixinPayRequestHandler reqHandler = new WeixinPayRequestHandler();
		reqHandler.setKey(key);
		
		String appId=response.getAppid();
		String timeStamp=WeixinPayUtil.getCurrentTimeStamp();
		String packages="prepay_id="+response.getPrepayId();
		String signType="MD5";
		
		//签名包数据
		reqHandler.setParameter("appId", appId);
		reqHandler.setParameter("timeStamp", timeStamp);
		reqHandler.setParameter("nonceStr", nonceStr);
		reqHandler.setParameter("package", packages);
		reqHandler.setParameter("signType", signType);
		String paySign=reqHandler.createPaySign();
		
		
		//样式
		strpayHtml +="<style type=\"text/css\">\r\n";
		strpayHtml +=".wxOrderSucc {width: 90%;max-width: 600px;height: auto;margin: 0 auto;}\r\n";
		strpayHtml +=".succInfo{padding-bottom:5px;border-bottom:1px solid#ddd;margin-bottom:35px}\r\n";
		strpayHtml +=".wxOrderSucc h2{margin:20px auto;font-size:20px;text-align:center;color:#333;font-weight:bold}\r\n";
		strpayHtml +=".wxOrderSucc p{color:#333;margin-bottom:10px}\r\n";
		strpayHtml +=".clearfix{}\r\n";
		strpayHtml +=".totalPrice .totalNum{color:#e4393c;font-size:20px}\r\n";
		strpayHtml +=".totalPrice span{float:right;display:inline-block;font-weight:bold}\r\n";
		strpayHtml +=".totalPrice .priceTit{color:#999;font-size:18px;margin-top:2px;margin-right:2px}\r\n";
		strpayHtml +=".clearfix:after{clear:both;display:block;content:\"...\";visibility:hidden;height:0;font-size:0}\r\n";
		strpayHtml +=".payNow{background-color:#43c01d;color:#fff;display:block;width:150px;height:35px;line-height:35px;text-align:center;border-radius:5px;margin:0 auto 20px;font-size:16px}\r\n";
		strpayHtml +=".contact{text-align: center;}";
		strpayHtml +="</style>\r\n";
		
		strpayHtml +="<script language=\"javascript\">\r\n";
				//js支付方法
				strpayHtml +="function onBridgeReady(){";
						 if (response==null||StringUtils.isBlank(response.getPrepayId())) {
							 strpayHtml +="alert('发起支付失败:"+response.getReturnMsg()+"请刷新后重试');return;";	
						 }
						 
						String returnUrl=  "window.location.href='"
							+ WeixinpayConfig.return_url+"?orderCode="+order.getOrder_sn()
							+ "';\r\n";
							
						 strpayHtml +="WeixinJSBridge.invoke(";
						 strpayHtml +="'getBrandWCPayRequest', {";
										 strpayHtml +="\"appId\":\""+appId+"\",";
										 strpayHtml +="\"timeStamp\":\""+timeStamp+"\",";
										 strpayHtml +="\"nonceStr\":\""+nonceStr+"\",";
										 strpayHtml +="\"package\":\""+packages+"\", ";
										 strpayHtml +="\"signType\":\""+signType+"\",";
										 strpayHtml +="\"paySign\":\""+paySign+"\"";
										 strpayHtml+="},";
										 strpayHtml+=" function(res){  ";
										 	strpayHtml+="if(res.err_msg == \"get_brand_wcpay_request:ok\" ) {"+returnUrl+"} ";
										 	strpayHtml+="else if(res.err_msg == \"get_brand_wcpay_request:cancel\" ) {} ";
										 	strpayHtml+="else { alert('error:'+res.err_msg)}";
										 strpayHtml+="}";
										 strpayHtml+="); ";
						strpayHtml +="}";
				//页面渲染判断是否在有微信服务端JS
				strpayHtml += "if (typeof WeixinJSBridge == \"undefined\"){";
					strpayHtml += "if( document.addEventListener ){";
					strpayHtml += "document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);";
					strpayHtml += "}else if (document.attachEvent){";
					strpayHtml += "document.attachEvent('WeixinJSBridgeReady', onBridgeReady);";
					strpayHtml += "document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);";
					strpayHtml += "}";
				strpayHtml += "}else{";
					strpayHtml += "onBridgeReady();";
				strpayHtml += "}";
				strpayHtml += "</script>";
				strpayHtml += "</head>";
				
				
				strpayHtml +="<body>";
				
					strpayHtml +="<div class=\"wxOrderSucc\">";
					strpayHtml +="<div class=\"succInfo\">";
					strpayHtml +="<h2>订单信息</h2>";
					strpayHtml +="<p class=\"clearfix\"><span class=\"itemTit\">订单号：</span><span class=\"itemDetial\">"+order.getOrder_sn()+"</span></p>";
					strpayHtml +="<p class=\"clearfix\"><span class=\"itemTit\">配送地址：</span><span class=\"itemDetial\">"+order.getReceiver_addr()+"</span></p>";
					strpayHtml +="<p class=\"clearfix\"><span class=\"itemTit\">支付方式：</span><span class=\"itemDetial\">微信支付</span></p>";
					strpayHtml +="<p class=\"totalPrice clearfix\"><span class=\"totalNum\">"+order.getNeed_pay_amount()+"元</span><span class=\"priceTit\">总价:</span></p>";
					strpayHtml +="</div>";
					strpayHtml +="<a class=\"payNow\" href=\"javascript:;\" onclick=\"onBridgeReady()\">立即支付</a>";
					strpayHtml +="<div class='contact'><span>LOVO商城客服热线：4006-965-165</span></div>";
					strpayHtml +="</div>";
					
				strpayHtml += "</body>";
				strpayHtml += "</html>";
		return strpayHtml;
	}

	/**
	 * 获取code(暂时不用,通过联合登录获取openid)
	 * @param appId
	 * @param redirectUrl
	 * @param orderId
	 * @return
	 */
	private String createOauthUrlForCode(String appId, String redirectUrl,
			Integer orderId) {

		Map<String, String> urlObj = new HashMap<String, String>();

		urlObj.put("appid", appId);
		try {
			urlObj.put("redirect_uri", URLEncoder.encode(redirectUrl, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("redirect_uri encode失败");
		}
		urlObj.put("response_type", WeixinpayConfig.RESPONSE_TYPE);
		urlObj.put("scope", WeixinpayConfig.SCOPE);
		urlObj.put("state", orderId + WeixinpayConfig.STATE);

		String requestUrl = WeixinPayUtil.createRequestUrl(
				WeixinpayConfig.AUTHORIZE_URL, urlObj);

		return requestUrl;
	}
	
	/**
	 * 获取openId(暂时不用,通过联合登录获取openid)
	 * @param appId
	 * @param appsecret
	 * @param code
	 * @return
	 */
	private String getOpenId(String appId,String appsecret,String code){
		Map<String,String> urlObj=new HashMap<String, String>();
		urlObj.put("appid", appId);
		urlObj.put("secret", appsecret);
		urlObj.put("code", code);
		urlObj.put("grant_type", WeixinpayConfig.AUTH_GRANT_TYPE);
		
		String requestUrl=WeixinPayUtil.createRequestUrl(WeixinpayConfig.ACCESS_TOKEN_URL,urlObj);
		
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
		
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(WeixinpayConfig.charset);
        request.setUrl(requestUrl);
        request.setMethod(HttpRequest.METHOD_GET);//只能是get请求
        HttpResponse response = httpProtocolHandler.execute(request);
        
        try {
        	//处理结果
			if (response != null&&StringUtils.isNotBlank(response.getStringResult())) {
				
				JSONObject reulst=JSONObject.parseObject(response.getStringResult());
				
				String openid=reulst.getString("openid");
				
				logger.debug("微信返回的用户openid:"+openid);
				
				return openid;
				
			}
		} catch (UnsupportedEncodingException e) {
			
			logger.error("微信支付请求编码异常:"+e.getMessage());
		}
		return null;
	}
	
	



}
