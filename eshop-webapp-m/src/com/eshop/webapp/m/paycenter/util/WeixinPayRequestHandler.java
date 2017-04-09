package com.eshop.webapp.m.paycenter.util;




public class WeixinPayRequestHandler extends WeixinPayBaseRequestHandler
{
	public WeixinPayRequestHandler()
	{
		super();
	} 
	
	/**
		* @Override
		* 初始化函数，默认给一些参数赋值，如cmdno,date等。
	*/
	public   void init() 
	{ 
		//公众账号ID
		this.setParameter("appid", "");
		//商户号
		this.setParameter("mch_id", "");
		//设备名称 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		this.setParameter("device_info", "");
		//随机字符串(不长于32位)
		this.setParameter("nonce_str", "");
		//签名
		this.setParameter("sign", "");
		//商品描述
		this.setParameter("body", "");
		//商品详情(商品名称明细列表)
		this.setParameter("detail", "");
		//说明(附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据)
		this.setParameter("attach", "");
		//商户订单号
		this.setParameter("out_trade_no", "");
		//货币类型
		this.setParameter("fee_type", "CNY");
		//总金额
		this.setParameter("total_fee", "");
		//用户ip
		this.setParameter("spbill_create_ip",  "");
		//交易起始时间
		this.setParameter("time_start",  "");
		//交易结束时间
		this.setParameter("time_expire",  "");
		//商品标记
		this.setParameter("goods_tag",  "");
		//回调url
		this.setParameter("notify_url",  "");
		//交易类型   JSAPI，NATIVE，APP 目前使用的属于NATIVE
		this.setParameter("trade_type",  "");
		//商品ID (trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。)
		this.setParameter("product_id",  "");
		//trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid
		this.setParameter("openid",  "");
		
		
	}



	/**
 * @Override
 * 创建签名
 
	protected   void createSign() 
	{
	
		//获取参数
		String appid = getParameter("appid");
		String mch_id = getParameter("mch_id");
		String device_info=getParameter("device_info");
		String nonce_str = getParameter("nonce_str");
		String body = getParameter("body");
		String detail=getParameter("detail");
		String attach=getParameter("attach");
		String out_trade_no=getParameter("out_trade_no");
		String fee_type=getParameter("fee_type");
		String total_fee=getParameter("total_fee");
		String spbill_create_ip = getParameter("spbill_create_ip");
		String time_start=getParameter("time_start");
		String time_expire=getParameter("time_expire");
		String goods_tag=getParameter("goods_tag");
		String notify_url=getParameter("notify_url");
		String trade_type=getParameter("trade_type");
		String product_id=getParameter("product_id");
		//JSAPI 必须
		String openid=getParameter("openid");
		
				
		
		
		
	
		//组织签名
		StringBuilder sb = new StringBuilder();
		sb.append("fee_type=" + fee_type + "&");
		sb.append("appid=" + appid + "&");
		sb.append("mch_id=" + mch_id + "&");
		sb.append("nonce_str=" + nonce_str + "&");
		sb.append("body=" + body + "&");
		sb.append("out_trade_no=" + out_trade_no + "&");
		sb.append("total_fee=" + total_fee + "&");
		sb.append("notify_url=" + notify_url + "&");
		sb.append("trade_type=" + trade_type + "&");
		sb.append("product_id=" + product_id + "&");
		
		if(StringUtils.isNotBlank(device_info)){
			sb.append("device_info=" + device_info + "&");
		}
		if(StringUtils.isNotBlank(detail)){
			sb.append("detail=" + detail + "&");
		}
		if(StringUtils.isNotBlank(attach)){
			sb.append("attach=" + attach + "&");
		}
		if(StringUtils.isNotBlank(spbill_create_ip) ) 
		{
			sb.append("spbill_create_ip=" + spbill_create_ip + "&");
		}
		if(StringUtils.isNotBlank(time_start)){
			sb.append("time_start=" + time_start + "&");
		}
		if(StringUtils.isNotBlank(time_expire)){
			sb.append("time_expire=" + time_expire + "&");
		}
		if(StringUtils.isNotBlank(openid)){
			sb.append("openid=" + openid + "&");
		}
		if(StringUtils.isNotBlank(goods_tag)){
			sb.append("goods_tag=" + goods_tag + "&");
		}
		
		
		
		
		
		sb.append("key=" + getKey());
	
		//算出摘要
		String sign = Md5Encrypt.md5(sb.toString(),getCharset()).toUpperCase();
			
		setParameter("sign", sign);

		//debug信息
		setDebugInfo(sb.toString() + " => sign:"  + sign);
	
	}*/
	

	

}
