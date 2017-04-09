package com.eshop.webapp.m.paycenter.util;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class WeixinPayResponse implements Serializable{
	
	protected static Logger logger = LoggerFactory.getLogger(WeixinPayResponse.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//返回结果响应结果和业务结果都用SUCCESS和FAIL标识
	public static String SUCCESS_CODE="SUCCESS";
	
	public static String FAIL_CODE="FAIL";
	
	private String returnCode;
	private String returnMsg;
	private String appid;
	private String mchId;
	private String nonceStr;
	private String sign;
	private String resultCode;
	private String prepayId;
	private String tradeType;
	private String codeUrl;
	private String timeStamp;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	
	
	/**
	 * 获取当前系统的时间戳
	 * @return
	 */
	public String getTimeStamp() {
		String currtenTime=String.valueOf(System.currentTimeMillis());
		currtenTime=currtenTime.substring(0,10);
		return currtenTime;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * 快速获取xml某个节点的值 
	 * @param xml
	 * @param key
	 * @return
	 */
	public static WeixinPayResponse getValueByNode(String xml,String nodeName){
		
	    	// step 1: 获得SAX解析器工厂实例
	        SAXParserFactory factory = SAXParserFactory.newInstance();

	        // step 2: 获得SAX解析器实例
	        SAXParser parser;
			try {
				parser = factory.newSAXParser();
				
	        	XmlForSaxHandler hand=new XmlForSaxHandler(nodeName);
	        	
				parser.parse(new InputSource(new StringReader(xml)),  hand);
				
				List<HashMap<String, String>>  list=hand.getList();
				
				if (list!=null&&list.size()>0) {
					HashMap<String, String> result=list.get(0);
					
					String returnCode=result.get("return_code");
					String resultCode=result.get("result_code");
					String codeUrl=result.get("code_url");
			        String returnMsg=result.get("return_msg");
			        String appid=result.get("appid");
			        String mchId=result.get("mch_id");
			        String nonceStr=result.get("nonce_str");
			        String sign=result.get("sign");
			        String prepayId=result.get("prepay_id");
			        String tradeType=result.get("trade_type");
			        
			        WeixinPayResponse response=new WeixinPayResponse();
			        
					response.setAppid(appid);
					response.setCodeUrl(codeUrl);
					response.setMchId(mchId);
					response.setNonceStr(nonceStr);
					response.setPrepayId(prepayId);
					response.setResultCode(resultCode);
					response.setReturnCode(returnCode);
					response.setReturnMsg(returnMsg);
					response.setSign(sign);
					response.setTradeType(tradeType);
					
					return response;
				}
				
			} catch (ParserConfigurationException e) {
				logger.error("微信支付解析返回结果ParserConfigurationException异常:"+e.getMessage());
			} catch (SAXException e) {
				logger.error("微信支付解析返回结果SAX异常:"+e.getMessage());
			} catch (IOException e) {
				logger.error("微信支付解析返回结果I/O异常:"+e.getMessage());
			}
			
		return null;

	}

}
