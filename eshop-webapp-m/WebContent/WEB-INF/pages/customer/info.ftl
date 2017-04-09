<!DOCTYPE HTML>
<html>
<head>
	<title>我的资料</title>
	<meta name="keywords" content="我的资料"/> 
	<meta name="description" content="我的资料"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<link href="/css/customer.css" rel="stylesheet" />
</head>
<body>
<#include "/global/global_back.ftl" />
<section class="content">
	  <div id="J_odetail_plugin">
	        <div class="mb-dtinfo"></div>
	        <div class="mb-dti">
	        	<ul class="c-list" id="showInfo">
		            <li>
		                <label> 用户名： </label>
		                <span style="word-wrap:break-word;">${customer?if_exists.customer_name?if_exists}</span>
		            </li>
		            <li>
		                <label> 店主： </label>
		                <span>${customer?if_exists.customer_true_name?if_exists}</span>
		            </li>
					
		            <li>
		                <label>店铺名称： </label>
		                <span>${customer?if_exists.shop_name?if_exists}</span>
		            </li>
					<li>
		                <label>店铺地址： </label>
		                <span>${customer?if_exists.shop_address?if_exists}</span>
		            </li>
		        </ul>
			</div>
		</div>
</section>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
</body>
</html>