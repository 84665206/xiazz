<!DOCTYPE HTML>
<html>
<head>
	<title>订单明细</title>
	<meta name="keywords" content="订单明细"/> 
	<meta name="description" content="订单明细"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<link href="/css/customer.css" rel="stylesheet" />
</head>
<body>
<#include "/global/global_back.ftl" />
<section class="content">
	  <div id="J_odetail_plugin">
	        <div class="mb-dtinfo">
	          <div class="mb-dtl">
	            <ul>
	            	<#if orderInfo??&&(orderInfo.order_goodsList?size>0)>
	            		<#list orderInfo.order_goodsList as goods>
			              <li data-orderid="" data-archive="false" data-snapshot="true">
			                <div class="ol-l">  </div>
			                <div class="ol-m"> <a class="toDetail">
			                  </a><h3><a class="toDetail"></a><a href="/pr-17447.htm" class="toDetail">${goods?if_exists.sku_name?if_exists} </a></h3>
			                  <p class="gray">
			                  	配送时间：${goods?if_exists.goods_shipping_name?if_exists}<br> 
								品种：${goods?if_exists.goods_varieties_name?if_exists}<br>
								产品规格：${goods?if_exists.goods_spec_name?if_exists}<br>
								养殖地：${goods?if_exists.goods_origin_name?if_exists}<br>
							  </p>
			                   </div>
			                <div class="ol-r">
			                  <p class="ol-m-price red">¥${goods?if_exists.sale_price?if_exists}<br>
			                    × ${goods?if_exists.sku_num?if_exists} </p>
			                </div>
			                <div id="myCommentDiv">
			        		</div>
			              </li>
		              	</#list>
		            </#if>
	            </ul>
	         </div>
	          <div class="mb-dt" data-orderid="929046762591169">
	            <p class="dt-p1"> 
	            	应付款<span class="lightgray"></span>：<strong class="red">¥ ${orderInfo?if_exists.need_pay_amount?if_exists}</strong> <br>	
	            	实付款<span class="lightgray"></span>：<strong class="red">¥ ${orderInfo?if_exists.pay_amount?if_exists}</strong> <br>
	              	<#--数量：<strong class="red">1</strong> -->
	            </p>
        		<#if (orderInfo.pay_status)??&&(orderInfo.pay_status==1)>
        			<p class="mb-dt-tip" style="margin-top:5px;"> <em class="orange">买家已付款</em> </p>
				<#else>
					<p class="mb-dt-tip" style="margin-top:5px;"> <em class="orange">买家未付款</em> </p>
				</#if>
	          </div>
	        </div>
	        <div class="mb-dti">
	          	<ul class="c-list">
		                        <li>
		                            <label> 订 单 号： </label>
		                            <span>${orderInfo?if_exists.order_sn?if_exists}</span> 
		                        </li>
		                        <li>
		                            <label> 收 件 人： </label>
		                            <span>${orderInfo?if_exists.receiver_name?if_exists}</span>
		                        </li>
		                        <li>
		                            <label> 联系电话： </label>
		                            <span><#if orderInfo??&&(orderInfo.receiver_mobile)??>${orderInfo?if_exists.receiver_mobile?if_exists}<#else>${orderInfo?if_exists.receiver_phone?if_exists}</#if></span>
		                        </li> 
		                        <li>
		                            <label> 收件地址： </label>
		                            <span>${orderInfo?if_exists.receiver_province?if_exists}&nbsp;${orderInfo?if_exists.receiver_city?if_exists}&nbsp;${orderInfo?if_exists.receiver_district?if_exists}&nbsp;${orderInfo?if_exists.receiver_addr?if_exists}</span>
		                        </li>
		                    </ul>
	        </div>
	      </div>
	</section>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
</body>
</html>