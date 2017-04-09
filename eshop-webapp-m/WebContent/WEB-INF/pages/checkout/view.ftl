<!DOCTYPE HTML>
<html>
<head>
	<title>提交订单</title>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<link href="/css/buy.css" rel="stylesheet" />
	<style>
		.c-btn {
			display: inline-block !important;
			border-radius: 5px;
			height: 30px;
			line-height: 30px;
			color: #4e4e4e;
			background-color: #dedede;
			border: 1px #dedede solid;
			-webkit-tap-highlight-color: rgba(0,0,0,0);
			display: inline-block;
			min-width: 38px;
			padding: 0 6px;
			text-align: center;
			background: -webkit-gradient(linear, left top, left bottom, from(#fcfcfc), to(#ededed));
		}
		
		.c-btn-blue {
			background: #022046;
			width: 100%;
			height: 2em;
			line-height: 2em;
			display: block;
			margin: auto;
			font-size: 18px;
			clear: both
		}
		
		.c-list p {
			color: #aaaaaa;
			line-height: 1.5em;
			font-size: 12px;
			padding: 10px 0;
			width: 80%
		}
		
		.c-list p b {
			color: #3d3d3d;
			font-size: 14px;
		}
		
		.c-list input[type="radio" ] {
			border: 1px solid #909090;
			border-radius: 50%;
			width: 14px;
			height: 14px;
			margin: 15px;
		}
		
		.c-list input[type="radio"].on {
			background: #63738d
		}
		.c-btn-oran-big {
			border: 0;
			text-align: center;
			text-decoration: none;
			margin-top: 10px;
			color: #fff;
			font-size: 1.5em;
			font-weight: normal;
			-webkit-border-radius: 0px;
			background: #f00;
			height: 2.5m;
			line-height: 2.5em;
			font-weight:bold;
		}
	</style>
</head>
<body style="background: #F2EFEF;"> 
  <#include "/global/global_back.ftl" />
  <section class="content">
  <div class="active">
    <div class="content-buy">
      <div class="wrap order-buy">
        <form id="submitOrderForm" name="submitOrderForm" action="/checkout/submit.do" method="post">
          <div class="mainCont" id="checkMain">
            <section class="address " id="address7" onclick="selectAddressClick();">
              <div class="address-info" id="J_address">
                <p class="address-name">${orderInfo?if_exists.customer_delivery?if_exists.consignee}</p>
                <p class="address-phone"><#if (orderInfo.customer_delivery.mobile)?? && orderInfo.customer_delivery.mobile!="" >${orderInfo.customer_delivery.mobile?if_exists}<#else>${orderInfo.customer_delivery.tel?if_exists}</#if></p>
              </div>
              <div class="address-details">${orderInfo?if_exists.customer_delivery?if_exists.province} ${orderInfo?if_exists.customer_delivery?if_exists.city} ${orderInfo?if_exists.customer_delivery?if_exists.district} ${orderInfo?if_exists.customer_delivery?if_exists.address} </div>
            </section>
            <section class="order " id="order15">
              <section class="order-info">
                <div class="order-list">
                  <ul class="order-list-info">
                  	<#if orderInfo??&&orderInfo.order_goodsList?? >
					<#list orderInfo.order_goodsList as goods>
                    <li class="item " id="item14">
                      <div class="itemInfo list-info" id="itemInfo13">
                        <div class="list-cont">
                          <h3 class="goods-title">${goods?if_exists.sku_name?if_exists}<span style="float:right;font-weight:normal;">${goods?if_exists.buy_price?if_exists}元 / 斤</span></h3>
                          </div>
                        </div>
                      </div>
                    </li>
					</#list>
                    </#if>
                  </ul>
                  <div class="deliveryMethod ">
                    <div class="b-box">
                      <p class="title">合计金额：${orderInfo?if_exists.need_pay_amount?if_exists}元</p><p class="title" style="margin-left:80px;">合计数量：${orderInfo?if_exists.goods_sum?if_exists}</p>
                    </div>
                  </div>
                  <div class="deliveryMethod ">
                    <div class="b-box">
                      <p class="title">支付方式：</p>
                      <select name="pay_way" id="payWaySelect">
						<#if orderInfo??&&orderInfo.pay_methods??>
							<#list orderInfo.pay_methods as pay>
                        	<option value="${pay?if_exists.id}">${pay?if_exists.pay_name}<option>
                        	</#list>
						</#if>
                      </select>
                    </div>
                  </div>
                  <div class="deliveryMethod ">
                    <div class="b-box">
                      <p class="title">配送时间：${orderInfo?if_exists.shipping_level_name?if_exists}</p>
                    </div>
                  </div>
                  <div class="memo " id="memo9">
                    <div class="inputs">
                    	<span style="font-size:14px;">订单备注：</span>
                      	<input type="text" class="c-inputs c-form-txt-normal" maxlength="200" name="customer_memo" id="message" value="" placeholder="如有期望到货时间，请留言！">
                    </div>
                    <hr/>
                  </div>
                  
                  <div class="b-box" >
                    <p class="title">配送方式：
                    <#if selectValues??&&(selectValues?size>0)>	
                    	<select name="shipping_way">有
						<#list selectValues as se>
							<option value="${se?if_exists.value?if_exists}">${se?if_exists.name?if_exists}</option>
						</#list>
						</select>
					</#if>
                    </p>
                  </div>
                  
                  <div class="b-box">
                    <p class="title">定金：${orderInfo?if_exists.ding_amount?if_exists}元</p>
                    <div class="interpret multi-select">
                      
                    </div>
                  </div>
                  
                  <div class="b-box">
                    <div class="interpret multi-select">
                      	<input type="button" class="c-btn-oran-big" value="清空" style="width:40%;float:left;background: #EAE9E9; color:#080101;" onclick="clearOrder();">
  						<input type="button" class="c-btn-oran-big" value="确认" style="width:40%;float:right;" onclick="submitOrderCheck();">
                    </div>
                  </div>
                </div>
                
              </section>
            </section>
            
          </div>
        </form>
      </div>
    </div>
  </div>
  
  
  
  <div style="padding: 24px 0px 50px 10px;">  *订金=全款×20%  *订金金额将在最后一批货款抵扣   *最后一批货款不能低于订金</div>
</section>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
<script type="text/javascript" src="/js/checkout/checkout.js"></script>

</body>
</html>
