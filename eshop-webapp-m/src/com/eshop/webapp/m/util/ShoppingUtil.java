package com.eshop.webapp.m.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.eshop.webapp.m.customer.model.CustomerDelivery;
import com.eshop.webapp.m.model.Cart;
import com.eshop.webapp.m.model.CartItem;
import com.eshop.webapp.m.order.model.OrderGoods;
import com.eshop.webapp.m.order.model.OrderInfo;
/*import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;*/

/**
 * shopping 服务层工具类
 * 
 * @author william
 * 
 */
public class ShoppingUtil {

	

	/**
	 * 产生订单编码（2位前缀-6位时间串-随机码）--36进制 ??如何保证唯一
	 * 
	 * @param order
	 */
	public static String genOrderCode(Integer orderId) {
		String prefix = "ZX";
		
		java.util.Calendar now = Calendar.getInstance();
		// 获得年月日字符串
		SimpleDateFormat format1 = new SimpleDateFormat("yyMMdd");
		// SimpleDateFormat format2 = new SimpleDateFormat("HHmmssSSS");
		// 是不是严格按照指定的格式解析日期
		format1.setLenient(false);
		String datePart = format1.format(now.getTime());

		 Random random = new Random();
		 String randomS = "00" + random.nextInt(100);
		 String randomPart = randomS.substring(randomS.length() - 2);

		final long ALL = 36 * 36 * 36 * 36L;// 4位36进制可以支撑百万级/day数据
		final long ALL3 = 36 * 36 * 36L;
		long suffix=(Math.abs(orderId%(ALL-ALL3-1))+(ALL3+1))%ALL;//保证有且仅有4位
		String orderCode = prefix + datePart + Long.toString(suffix, 36).toUpperCase()+randomPart;
		return orderCode;
	}
	
	/**
	 * 产生发货编号（2位前缀-6位时间串-随机码）--36进制 ??如何保证唯一
	 * 
	 * @param order
	 */
	public static String genSendCode(long timestamp) {
		String prefix = "CK";
		
		java.util.Calendar now = Calendar.getInstance();
		// 获得年月日字符串
		SimpleDateFormat format1 = new SimpleDateFormat("yyMMdd");
		// SimpleDateFormat format2 = new SimpleDateFormat("HHmmssSSS");
		// 是不是严格按照指定的格式解析日期
		format1.setLenient(false);
		String datePart = format1.format(now.getTime());

		 Random random = new Random();
		 String randomS = "00" + random.nextInt(100);
		 String randomPart = randomS.substring(randomS.length() - 2);

		final long ALL = 36 * 36 * 36 * 36L;// 4位36进制可以支撑百万级/day数据
		final long ALL3 = 36 * 36 * 36L;
		long suffix=(Math.abs(timestamp%(ALL-ALL3-1))+(ALL3+1))%ALL;//保证有且仅有4位
		String sendCode = prefix + datePart + Long.toString(suffix, 36).toUpperCase()+randomPart;
		return sendCode;
	}

	/**
	 * 检查地址是否为空
	 */
	public static boolean checkOrderDelivery(CustomerDelivery customerDelivery) {
		if (customerDelivery == null
				|| StringUtils.isBlank(customerDelivery.getConsignee())
				|| StringUtils.isBlank(customerDelivery.getProvince())
				|| StringUtils.isBlank(customerDelivery.getCity())
				|| StringUtils.isBlank(customerDelivery.getDistrict())
				|| StringUtils.isBlank(customerDelivery.getAddress())
				|| (StringUtils.isBlank(customerDelivery.getMobile()) && StringUtils
						.isBlank(customerDelivery.getTel()))) {
			return false;
		}
		return true;
	}
	
	/**
	 * 订单来自购物车
	 * @param orderInfo
	 * @param cart
	 */
	public static void setOrderFromCart(OrderInfo orderInfo, Cart cart){
		List<OrderGoods> orderGoods=new ArrayList<OrderGoods>();
		
		BigDecimal realAllGoodsAmount=BigDecimal.ZERO;
		Integer goodsSum=0;
		BigDecimal goodsAmount=BigDecimal.ZERO;
		for (CartItem item : cart.getItems().values()) {
			OrderGoods g=new OrderGoods();
			g.setSku_id(item.getSku_id());
			g.setSku_name(item.getGoods_name());
			g.setSku_num(item.getBuy_num());
			g.setSale_price(item.getSale_price());
			g.setBuy_price(item.getBuy_price());
			g.setSku_pic(item.getMain_image_url());
			g.setGoods_code(item.getGoods_code());
			g.setGoods_origin(item.getGoods_origin());
			g.setGoods_origin_name(item.getGoods_origin_name());
			g.setGoods_shipping(item.getGoods_shipping());
			g.setGoods_shipping_name(item.getGoods_shipping_name());
			g.setGoods_spec(item.getGoods_spec());
			g.setGoods_spec_name(item.getGoods_spec_name());
			g.setGoods_varieties(item.getGoods_varieties());
			g.setGoods_varieties_name(item.getGoods_varieties_name());
			g.setInsert_user(cart.getSessionCustomer().getCustomer_name());
			
			BigDecimal realGoodsAmount=new BigDecimal(0);
			realGoodsAmount=new BigDecimal(g.getBuy_price()).multiply(new BigDecimal(g.getSku_num()));
			goodsAmount=goodsAmount.add(realGoodsAmount);
			realAllGoodsAmount=realAllGoodsAmount.add(realGoodsAmount);
			goodsSum=goodsSum+item.getBuy_num();
			orderGoods.add(g);
			
			orderInfo.setShipping_level(item.getGoods_shipping());
			orderInfo.setShipping_level_name(item.getGoods_shipping_name());
		}
		
		orderInfo.setShop_name(cart.getSessionCustomer().getShop_name());
		orderInfo.setShop_address(cart.getSessionCustomer().getShop_address());
		orderInfo.setRecruit_code(cart.getSessionCustomer().getRecruit_code());
		orderInfo.setSessionCustomer(cart.getSessionCustomer());
		orderInfo.setOrder_goodsList(orderGoods);
		orderInfo.setGoods_sum(goodsSum);
		orderInfo.setGoods_amount(goodsAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		orderInfo.setRealAllGoodsAmount(realAllGoodsAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
	}
	
}
