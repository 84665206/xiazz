package com.eshop.webapp.m.paycenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.webapp.m.base.BaseController;
import com.eshop.webapp.m.order.service.IShoppingService;
import com.eshop.webapp.m.pay.model.PayLog;
import com.eshop.webapp.m.paycenter.handle.WeixinPayForH5Handle;
import com.eshop.webapp.m.util.IPUtil;



/** 
 * 支付服务
 * 
 * @author yangmeng
 * @version 创建时间：2016年6月2日 下午2:17:24 
 * 
 */
@Controller
@RequestMapping("/pay")
public class PaycenterController extends BaseController{
	
	@Autowired
	private WeixinPayForH5Handle weixinPayForH5Handle;
	
	@Autowired
	private IShoppingService shoppingService;
	
	
	@RequestMapping(value="/weixin",method=RequestMethod.POST)
	public String weixin(@RequestParam("order_id")Integer order_id, 
			HttpServletRequest request, HttpServletResponse response){
		PayLog plog = new PayLog();
		try {
			if(order_id==null){
				return null;
			}
			
//			Order order=oldShoppingService.getOrderNeedPay(order_id, getUserId().toString(),
//					SiteEnum.LOVO);
//			if(order==null){
//				responseResult.setCode(ResponseResultEnum.FAIL.code);
//				responseResult.setMsg("没有需要支付的订单");
//				return responseResult;
//			}
			
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("clientIP", IPUtil.getIpAddr(request));
			paras.put("openId",this.getCurrentCustomer().getOpen_id());
			paras.put("domainHost", "");
			
			
			
			plog.setPay_handle_type(PayLog.LogType.PAY_REQUEST.getValue());
			plog.setPay_url("");
			plog.setPay_ip(IPUtil.getIpAddr(request));
			plog.setCustomer_id(getCurrentCustomerId());
			plog.setDeal_code("");
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			//oldShoppingService.insertPayLog(plog, SiteEnum.LOVO);
		}  

		return null;
	}
	
}
