<!DOCTYPE HTML>
<html>
<head>
	<title>虾找网</title>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<style>
.c-btn-oran {
border: 0;
display:block; margin:auto;
text-align: center;
text-decoration: none;
color: #fff;
font-size: 1.4em;
font-weight: 700;
-webkit-border-radius: 2px;
background: #042148;
width: 94%;
height: 2em;
line-height:2em;
}
</style>  
</head>
<body> 
 
<div class="landscape" id="J_wrap" style="display: block;">
		   <#include "/global/global_back.ftl" />
           <div class="main"> 
		          <div class="c-clild" id="J_orderDetail">
                    <div class="mainCont">
			                <section class="mb-dt">
			                   <div class="detaildz">
			                    <ul class="c-list">
			                    	<#if msg??>
										<li>
				                            <label> </label>
				                            <span>提交订单失败!</span> 
				                        </li>
				                        <li>
				                            <label> </label>
				                            <span style="color:red;font-weight">${msg}</span> 
			                        	</li>
			                        <#else>
										<li>
				                            <label> </label>
				                            <span>订单提交成功了!</span> 
				                        </li>
				                        <li>
				                            <label>订单号：</label>
				                            <span>${orderInfo?if_exists.order_sn?if_exists}</span>
				                        </li>
				                       <#-- <li>
				                            <label>支付方式：</label>
				                            <span>${orderInfo?if_exists.order_sn?if_exists}</span> 
				                        </li>
				                   -->
				                        <li>
				                            <label>应付金额：</label>
				                            <span>${orderInfo?if_exists.need_pay_amount?if_exists}元</span> 
				                        </li>
			                        </#if>
			                    </ul>
			                    </div>
			                    
			                </section> 
                            
                     </div>
                </div>  
		       
		  </div> 
</div>
<script type="text/javascript" src="/js/common/zepto.min.js"></script>

</body>
</html>
