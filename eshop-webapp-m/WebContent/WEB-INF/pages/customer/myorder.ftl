<!DOCTYPE HTML>
<html>
<head>
	<title>我的订单</title>
	<meta name="keywords" content="我的订单"/> 
	<meta name="description" content="我的订单"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<link href="/css/customer.css" rel="stylesheet" />
</head>
<body>
<#include "/global/global_back.ftl" />
<section class="content" >
	<div id="J_olist_plugin">
	     
	      <div class="orderCont mb-ol">
	        <ul class="moreMessage">
	        	<#if orderList??&&(orderList?size>0)>
	        		<#list orderList as o>
	               <li>
		            <div class="ol-t"> <a href="/member/toOrderDetail.php?orderId=740780">订单号：${o?if_exists.order_sn?if_exists}</a> </div>
		            <div class="mb-ollr">
		              <div class="ol-r"> <a class="fragment">
		                <p class="r-time"> 订单时间：<strong>${o?if_exists.add_time?if_exists?string('yyyy-MM-dd HH:mm:ss')}</strong> </p>
		                <p class="r-price"> 订单金额：<strong>
								            	¥${o?if_exists.need_pay_amount?if_exists}
								            	</strong> </p>
		                <p class="d-total"> 收货人：<strong>${o?if_exists.receiver_name?if_exists}</strong> </p>
		                <p class="r-status">微信支付： <strong class="orange">${o?if_exists.pay_status_name?if_exists}</strong> </p>
		               
		                 <p class="r-btn">
	             			<a href="/customer/order/detail.do?order_id=${o?if_exists.id?if_exists}" class="c-btn hotHref ">查看详情</a> 
	                     </p>
		              </div>
		            </div>
		          </li>
		          </#list>
				</#if>
	    </div>
	  </div>

</section>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
</body>
</html>