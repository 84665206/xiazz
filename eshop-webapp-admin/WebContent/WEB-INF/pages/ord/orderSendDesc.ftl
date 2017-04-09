<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>虾找虾后台管理系统 - 订单配送页面</title>
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
	
	<div style=" font-size:24px; color:#2A7AD2; text-align:center; line-height:30px; font-weight:bold;">订单配送详情</div>
	<table cellspacing="0" cellpadding="0"  class="stylized1 " style="width:900px;">
		<tr style="color:#333">
			<td style="border:solid 1px #ccc;" colspan="8">
				<div style=" font-size:16px; color:#2A7AD2; text-align:left; font-weight:bold;">【订单主体信息】</div>
			</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送单号</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.batchSn!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">订单编号</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.orderSn!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送级别</td>
			<td style="border:solid 1px #ccc; width:120px;">
				<#if orderBatchSend.shippingLevel=="4001"> 次日配送 </#if>
				<#if orderBatchSend.shippingLevel=="4002"> 当天配送 </#if>
			</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送要求</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.needSendTimeDesc!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">下单客户</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.customerName!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">客户店招</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.shopName!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">下单时间</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.addTimeDesc!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">下单数量</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.goodsSum!}kg</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">支付方式</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.payWay!}-货到付款</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货人</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.receiverName!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">手机</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.receiverMobile!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">座机</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.receiverPhone!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货省份</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.receiverProvince!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货城市</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.receiverCity!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货区县</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.receiverDistrict!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">收货街道</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.receiverStreet!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">详细地址</td>
			<td style="border:solid 1px #ccc; " colspan="7">${orderBatchSend.receiverAddr!}</td>
		</tr>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送状态</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.shippingStatusDesc!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送人员</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.shippingPerson!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送手机</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.shippingPhone!}</td>
			<td style="border:solid 1px #ccc; width:80px; color:#2A7AD2;">配送时间</td>
			<td style="border:solid 1px #ccc; width:120px;">${orderBatchSend.shippingTimeDesc!}</td>
		</tr>
	</table>
	
	<table cellspacing="0" cellpadding="0"  class="stylized1 " style="width:900px;">
	<thead>
		<tr style="color:#333">
			<td style="border:solid 1px #ccc;" colspan="13">
				<div style=" font-size:16px; color:#2A7AD2; text-align:left; font-weight:bold;">【本次配送信息】</div>
			</td>
		</tr>
		<tr valign="middle" style="color:#333">
			<td style="border:solid 1px #ccc; color:#2A7AD2;">配送批次</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">产品名称</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">品类</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">规格</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">数量(kg)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">单价(元)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">金额(元)</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">支付状态</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">已付金额</td>
			<td style="border:solid 1px #ccc; color:#2A7AD2;">待付金额</td>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td style="border:solid 1px #ccc;">第 ${orderBatchSend.sendNo!} 批</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.skuName!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.goodsVarietiesName!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.goodsSpecName!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.skuNum!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.salePrice!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.batchAmount!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.payStatusDesc!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.payAmount!}</td>
				<td style="border:solid 1px #ccc;">${orderBatchSend.needPayAmount!}</td>
			</tr>
	</tbody>
	</table>
	
	<div style=" font-size:16px; color:#2A7AD2; text-align:center; line-height:20px; font-weight:bold;">
		<input id="btn_close" type="button" value="【关闭本页】" onClick="btn_close()" />
		<input id="btn_print" type="button" value="【打印配送单】" onClick="btn_print(${orderBatchSend.batchId!})" style="margin-left:10px;"/>
	</div>
	
	
	
	
	<script type="text/javascript">
		function btn_close(){
			window.opener=null;
			window.open('','_self');
			window.close();
		}
		
		function btn_print(batchId){
		    window.open('detailprint.do?batchId='+batchId,'','width='+(window.screen.availWidth-400)+',height='+(window.screen.availHeight-30)+ ',top=0,left=200,resizable=yes,status=yes,menubar=no,scrollbars=yes');
		}
		
		
	</script>
</body>
</html>

