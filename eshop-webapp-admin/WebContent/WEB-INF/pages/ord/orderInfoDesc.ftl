<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>虾找虾后台管理系统 - 订单详情页面</title>
	<style>
		table {
			border-collapse: collapse;
			border-spacing: 0;
			font-size:12px;
			color:#333;
			margin:auto;
			text-align: center;
		}
		table.stylized1 {
			border-collapse: collapse;
			margin-top: 1em;
			margin-bottom: 1em;
		}
		table.stylized1 th, table.stylized1 td {
			line-height: 18px;
			padding: 6px 3px;
		}
		table.stylized1 tr.high td {
			background-color: #ffa !important;
		}
		table.stylized1 tr.gray td {
			background-color: #E3E3E3 !important;
		}
		table.stylized1 tbody th, table.stylized1 tbody td, table.stylized1 tfoot th, table.stylized1 tfoot td {
			border: solid 1px #ccc;
		}
		table.stylized1 tfoot td {
			background-color: #f2f2f2 !important;
			border-bottom: 2px solid #ddd;
		}
	</style>
</head>

<body>
	
	<div style=" font-size:24px; color:#2A7AD2; text-align:center; line-height:30px; font-weight:bold;">订单详情页面</div>
	<table cellspacing="0" cellpadding="0"  class="stylized1 " style="width:900px;">
		<tr style="color:#333">
			<td style="border:solid 1px #ccc;" colspan="8">
				<div style=" font-size:16px; color:#2A7AD2; text-align:left; font-weight:bold;">【订单主体信息】</div>
			</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">订单编号</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.orderSn!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">订单状态</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.orderStatusDesc!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送级别</td>
			<td style="border:solid 1px #ccc; width:120px;">
				<#if orderInfo.shippingLevel=="4001"> 次日配送 </#if>
				<#if orderInfo.shippingLevel=="4002"> 当天配送 </#if>
			</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送状态</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.shippingStatusDesc!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">下单时间</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.addTimeDesc!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">下单客户</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.customerName!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">客户店招</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.shopName!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">推荐代码</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.recruitCode!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">商品数量</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.goodsSum!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">商品金额</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.goodsAmount!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">订单折让</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.orderDiscount!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">总金额</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.orderAmount!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">订单备注</td>
			<td style="border:solid 1px #ccc; " colspan="7">${orderInfo.orderMemo!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">支付方式</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.payWay!}-货到付款</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">支付状态</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.payStatusDesc!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">已付款金额</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.payAmount!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">待付款金额</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.needPayAmount!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货人</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.receiverName!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">手机</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.receiverMobile!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">座机</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.receiverPhone!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">&nbsp;</td>
			<td style="border:solid 1px #ccc; width:120px;">&nbsp;</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货省份</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.receiverProvince!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货城市</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.receiverCity!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货区县</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.receiverDistrict!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货街道</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderInfo.receiverStreet!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">详细地址</td>
			<td style="border:solid 1px #ccc; " colspan="7">${orderInfo.receiverAddr!}</td>
		</tr>
	</table>
	
	<table cellspacing="0" cellpadding="0"  class="stylized1 " style="width:900px;">
	<thead>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc;" colspan="8">
				<div style=" font-size:16px; color:#2A7AD2; text-align:left; font-weight:bold;">【订单商品信息】</div>
			</td>
		</tr>
		<tr valign="middle" style="color:#333">
		  	<td style="border:solid 1px #ccc; color:#2A7AD2;">序号</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">产品名称</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">品类</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">规格</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">数量(kg)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">单价(元)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">金额(元)</td>
		</tr>
	</thead>
	<tbody>
		<#if orderGoodsList??>
		<#list orderGoodsList as ele>
			<tr>
				<td style="border:solid 1px #ccc;">1</td>
				<td style="border:solid 1px #ccc;">${ele.skuName!}</td>
				<td style="border:solid 1px #ccc;">${ele.goodsVarietiesName!}</td>
				<td style="border:solid 1px #ccc;">${ele.goodsSpecName!}</td>
				<td style="border:solid 1px #ccc;">${ele.skuNum!}</td>
				<td style="border:solid 1px #ccc;">${ele.salePrice!}</td>
				<td style="border:solid 1px #ccc;">${ele.skuNum*ele.salePrice}</td>
			</tr>
		</#list>
		</#if>
	</tbody>
	</table>
	
	<div style=" font-size:16px; color:#2A7AD2; text-align:center; line-height:20px; font-weight:bold;">
		<input id="btn_close" type="button" value="【关闭页面】" onClick="btn_close()" />
		<#if orderInfo.orderStatus==10>
			<input id="btn_approve" type="button" value="【审核通过】" onClick="btn_approve(${orderInfo.id!})" style="margin-left:10px;color:green;"/>
			<input id="btn_cancel" type="button" value="【取消订单】" onClick="btn_cancel(${orderInfo.id!})" style="margin-left:10px;color:red;"/>
		</#if>
		<#if orderInfo.orderStatus==20>
			<input id="btn_complete" type="button" value="【完成订单】" onClick="btn_complete(${orderInfo.id!})" style="margin-left:10px;color:green;"/>
		</#if>
	</div>
	
	<table cellspacing="0" cellpadding="0"  class="stylized1 " style="width:900px;">
	<thead>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc;" colspan="13">
				<div style=" font-size:16px; color:#2A7AD2; text-align:left; font-weight:bold;">【订单配送信息】</div>
			</td>
		</tr>
		<tr valign="middle" style="color:#333">
			<td style="border:solid 1px #ccc; color:#2A7AD2;">配送批次</td>
		  	<td style="border:solid 1px #ccc; color:#2A7AD2;">配送单号</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">产品名称</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">品类</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">规格</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">数量(kg)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">单价(元)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">金额(元)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">支付状态</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">配送状态</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">配送人员</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">配送手机</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">配送时间</td>
		</tr>
	</thead>
	<tbody>
		<#if orderBatchSendList??>
		<#list orderBatchSendList as ele>
			<tr>							
				<td style="border:solid 1px #ccc;">${ele.sendNo!}</td>
				<td style="border:solid 1px #ccc;">${ele.batchSn!}</td>	
				<td style="border:solid 1px #ccc;">${ele.skuName!}</td>
				<td style="border:solid 1px #ccc;">${ele.goodsVarietiesName!}</td>
				<td style="border:solid 1px #ccc;">${ele.goodsSpecName!}</td>
				<td style="border:solid 1px #ccc;">${ele.skuNum!}</td>
				<td style="border:solid 1px #ccc;">${ele.salePrice!}</td>
				<td style="border:solid 1px #ccc;">${ele.skuNum*ele.salePrice}</td>
				<td style="border:solid 1px #ccc;">${ele.payStatusDesc!}</td>
				<td style="border:solid 1px #ccc;">${ele.shippingStatusDesc!}</td>
				<td style="border:solid 1px #ccc;">${ele.shippingPerson!}</td>
				<td style="border:solid 1px #ccc;">${ele.shippingPhone!}</td>
				<td style="border:solid 1px #ccc;">${ele.shippingTimeDesc!}</td>
			</tr>
		</#list>
		</#if>
	</tbody>
	</table>
	
	
	
	
	<script type="text/javascript">
		function btn_close(){
			window.opener=null;
			window.open('','_self');
			window.close();
		}
		
		function btn_approve(orderId){
		    if(confirm("您确定要【审核通过】吗？")){
		    	//alert("订单审核成功！");
				window.location = "approve.do?orderId="+orderId;
			}
		}
		
		function btn_cancel(orderId){
		    if(confirm("您确定要【取消订单】吗？")){
		    	//alert("订单取消成功！");
				window.location = "cancel.do?orderId="+orderId;
			}
		}
		
		function btn_complete(orderId){
		    if(confirm("请确认该订单下的配送单已经全部配送完成！您确定要【完成订单】吗？")){
		    	//alert("订单审核成功！");
				window.location = "complete.do?orderId="+orderId;
			}
		}
		
	</script>
</body>
</html>

