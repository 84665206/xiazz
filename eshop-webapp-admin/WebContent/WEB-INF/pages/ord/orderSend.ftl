
<div class="col-xs-12">
	<#-- <h3 class="header smaller lighter blue">订单配送列表</h3> -->
	<div class="form-inline">
		<div class="col-xs-12">
		
	   		<label for="shippingStatus">配送状态</label>
	   		<select class="form-control" name="shippingStatus" id="shippingStatus" style="width:auto;">
		      	<option value="-1">请选择...</option>
		      	<option value="1">待配送</option>
				<option value="2">已配送</option> 
				<option value="3">已签收</option> 
				<option value="9">客户拒收</option> 
				<option value="8">卖家作废</option> 
		    </select>
	    	
			<label for="batchSn" style="margin-left:10px;">配送单号</label>
	   		<input style="width:120px;" class="form-control" id="batchSn" type="text"  placeholder="配送单号">
	   		
			<label for="orderSn" style="margin-left:10px;">订单编号</label>
	   		<input style="width:120px;" class="form-control" id="orderSn" type="text"  placeholder="订单编号">
	   		
	   		<label for="receiverName" style="margin-left:10px;">收货人</label>
	    	<input style="width:100px;" class="form-control" id="receiverName" type="text"  placeholder="收货人">
	    	
	   		<label for="receiverMobile" style="margin-left:10px;">收货人手机</label>
	    	<input style="width:100px;" class="form-control" id="receiverMobile" type="text"  placeholder="手机">
	    	
	    	<div class="input-append date">
	    		<label for="needSendStart">配送要求</label>
				<span class="input-append date form_date">
					<input size="16" type="text" value="" id="needSendStart">
					<span class="add-on"><i class="icon-calendar"></i></span>
				</span>
			    <label for="needSendEnd">至</label>
			    <span class="input-append date form_date">
				    <input size="16" type="text" value="" id="needSendEnd">
				    <span class="add-on"><i class="icon-calendar"></i></span>
			    </span>
	    		
	    		
				<label for="orderDateStart">下单时间</label>
				<span class="input-append date form_datetime">
				    <input size="16" type="text" value="" id="orderDateStart" >
				    <span class="add-on"><i class="icon-calendar"></i></span>
			    </span>
			    <label for="orderDateEnd">至</label>
			    <span class="input-append date form_datetime">
				    <input size="16" type="text" value="" id="orderDateEnd">
				    <span class="add-on"><i class="icon-calendar"></i></span>
			    </span>
			    
			    <button class="btn btn-sm btn-primary" onclick="search();" style="margin-left:10px;"><i class="icon-search align-top bigger-125"></i> 搜索</button>
			</div>
	    	
		</div>
	</div>
	
	<div>
		<label style="color:red;" id="searchSum">查询汇总数据 -- 总数量：0kg；总金额：0元。</label>
	</div>
	<div class="table-responsive">
		<table id="datainfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>操作</th>
					<th>配送单号</th>
					<th>配送状态</th>
					<th>订单编号</th>
					<th>配送级别</th>
					<th>配送期望</th>
					<th>批次</th>
					<th>产品名称</th>
					<th>品类</th>
					<th>规格</th>
					<th>数量(KG)</th>
					<th>收货人</th>
					<th>手机</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">
	$(".form_date").datetimepicker({
		language: 'zh-CN',
	    format: "yyyy-mm-dd",
	    minView:2,
	    autoclose: true,
	    todayBtn: true,
	    todayHighlight:true,
	    pickerPosition: "bottom-left"
	});   
	$(".form_datetime").datetimepicker({
		language: 'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: true,
	    todayBtn: true,
	    todayHighlight:true,
	    pickerPosition: "bottom-left"
	});   
	     
	var table=$('#datainfo-list').DataTable( {
    	"searching":false,  //去除搜索框
        "processing": true, //隐藏加载提示
        "serverSide": true, //启用服务器端分页
        "order":[],   //取消初始化排序
        "ordering":false,  //取消所有列排序按钮
        //paging: false,  //禁止分页
        //"stripeClasses": ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
        "ajax": {
            "url": "ord/orderSend/list.do",
            "type": "POST",
            "data": {"shippingStatus" : $("#shippingStatus").val(),
            		"shippingLevel" : $("#shippingLevel").val(),
            		"orderSn" : $("#orderSn").val(),
            		"receiverName" : $("#receiverName").val(),
            		"receiverMobile" : $("#receiverMobile").val(),
            		"needSendStart" : $("#needSendStart").val(),
            		"needSendEnd" : $("#needSendEnd").val(),
            		"orderDateStart" : $("#orderDateStart").val(),
            		"orderDateEnd" : $("#orderDateEnd").val()
            		}
        },
        "columns": [
                    { "data": "batchId" },
                    { "data": "batchSn" },
        			{ "data": "shippingStatus" },
                    { "data": "orderSn" },
                    { "data": "shippingLevel" },
                    { "data": "needSendTimeDesc" },
                    { "data": "sendNo" },
                    { "data": "skuName" },
                    { "data": "goodsVarietiesName" },
                    { "data": "goodsSpecName" },
                    { "data": "skuNum" },
                    { "data": "receiverName" },
                    { "data": "receiverMobile" }
                ],
         
		"columnDefs": [
					{
                      "targets": [0], // 目标列位置，下标从0开始
                      "data": "batchSn", // 数据列名
                      "render": function(data, type, row, full) { // 返回自定义内容
                      	 var result = "<button class='btn btn-xs btn-primary' onclick='go_orderinfo("+row.batchId+")'>查看</button> ";
                      	 if(row.shippingStatus==1){
                      	 	result += " <button class='btn btn-xs btn-danger' onclick='go_ordersend("+row.batchId+")'>发货</button>";
                      	 }
                      	 if(row.shippingStatus==2){
                      	 	result += " <button class='btn btn-xs btn-success' onclick='go_sendComplete("+row.batchId+","+row.needPayAmount+")'>完成</button>";
                      	 }
                      	 return result;
                      }
                  	},
                  	{
                      "targets": [2], // 目标列位置，下标从0开始
                      "data": "shippingStatus", // 数据列名
                      "render": function(data, type, full) { // 返回自定义内容
                      	  if(data==1){
                          	return "<span class='label label-danger arrowed'>待配送</span>";
                          }else if(data==2){
                          	return "<span class='label label-yellow arrowed'>已配送</span>";
                          }else if(data==3){
                          	return "<span class='label label-success arrowed'>已签收</span>";
                          }else if(data==9){
                          	return "<span class='label label-light arrowed'>客户拒收</span>";
                          }else if(data==8){
                          	return "<span class='label label-light arrowed'>卖家作废</span>";
                          }else{
                          	return "<span class='label label-danger arrowed-in'>未知</span>";
                          }
                      }
                  	},
                  	{
                      "targets": [4], // 目标列位置，下标从0开始
                      "data": "shippingLevel", // 数据列名
                      "render": function(data, type, full) { // 返回自定义内容
                      	  if(data==4001){
                          	return "<span class='label label-success'>次日配送</span>";
                          }else if(data==4002){
                          	return "<span class='label label-yellow'>当天配送</span>";
                          }else{
                          	return "<span class='label label-danger'>未知</span>";
                          }
                      }
                  	}
                  ]
    } );

    //页面载入时，初始查询
    searchSum({});

	function search(){
	    var param = {
	        "shippingStatus" : $("#shippingStatus").val(),
            "batchSn" : $("#batchSn").val(),
            "orderSn" : $("#orderSn").val(),
            "receiverName" : $("#receiverName").val(),
            "receiverMobile" : $("#receiverMobile").val(),
    		"needSendStart" : $("#needSendStart").val(),
    		"needSendEnd" : $("#needSendEnd").val(),
    		"orderDateStart" : $("#orderDateStart").val(),
    		"orderDateEnd" : $("#orderDateEnd").val()
	    };
	    table.settings()[0].ajax.data = param;
	    table.ajax.reload();
	    
	    //查询汇总
	    searchSum(param);
	}
	
	function searchSum(param){
		$.ajax({
			type:"post",
			async:false,
			dataType:"json",
			url:'ord/orderSend/showSum.do',
			data:param, 
			success:function(data){
				if(data.code=="200"){
					$("#searchSum").html(data.msg);
				}else{
					$("#searchSum").html("");
				}
			}
		});
	}
	
	function go_orderinfo(batchId){
		//alert(orderId);
		
		window.open('ord/orderSend/detailpage.do?batchId='+batchId,'','width='+(window.screen.availWidth-400)+',height='+(window.screen.availHeight-30)+ ',top=0,left=200,resizable=yes,status=yes,menubar=no,scrollbars=yes');
	}
	
	//打开发货窗口
	function go_ordersend(batchId){
		var dlg_msg = '';
		dlg_msg += '<form id="fm" method="post">';
		dlg_msg += '  <div class="widget-main">';
		dlg_msg += '    <div><label style="color:red;">请确认该 配送单 确实已经发出，并记录配送人员相关信息</label></div>';
		dlg_msg += '    <div><label>配送人姓名：</label><input type="text" class="form-control" id="shippingPerson"></div>';
		dlg_msg += '    <div><label>联系人电话：</label><input type="text" class="form-control" id="shippingPhone"></div>';
		dlg_msg += '    <div><label>配送方式：</label><input type="text" class="form-control" id="shippingWay"></div>';
		dlg_msg += '    <div><label>配送单号：</label><input type="text" class="form-control" id="shippingNo"></div>';
		dlg_msg += '  </div>';
		dlg_msg += '</form>';
		
		//alert(dlg_msg);
		bootbox.dialog({
            message: dlg_msg,  
            title: "保存配送人员信息",  
            buttons: {
                Cancel: {  
                    label: "取消",  
                    className: "btn-default",
                    callback: function () {  
                        
                    }  
                }  
                , OK: {  
                    label: "保存",  
                    className: "btn-primary",
                    callback: function () {
                    	var parm = {};
                    	parm.batchId = batchId;
                    	parm.shippingPerson = $("#shippingPerson").val();
                    	parm.shippingPhone = $("#shippingPhone").val();
                    	parm.shippingWay = $("#shippingWay").val();
                    	parm.shippingNo = $("#shippingNo").val();
                    	
                        $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'ord/orderSend/saveSendPerson.do',
							data:parm, 
							success:function(data){
								if(data.code=="200"){
									bootbox.alert(data.msg);
									search();
								}else{
									bootbox.alert(data.msg);
								}
							}
						});
                    }  
                }  
            }  
        });  
	}
	
	
	//打开完成窗口
	function go_sendComplete(batchId,needPayAmount){
		var dlg_msg = '';
		dlg_msg += '<form id="fm" method="post">';
		dlg_msg += '  <div class="widget-main">';
		dlg_msg += '    <div><label style="color:red;">请确认该 配送单 客户确实已经签收或者拒收，并配送成功的货款已经上交财务</label></div>';
		
		dlg_msg += '    <div>';
		dlg_msg += '      <label>请选择配送结果：</label>';
		dlg_msg += '      <label><input name="sendResult" type="radio" value="1" class="ace" checked="checked"/><span class="lbl">配送完成</span></label>';
		dlg_msg += '      <label><input name="sendResult" type="radio" value="0" class="ace" /><span class="lbl">客户拒收</span></label>';
		dlg_msg += '    </div>';
		dlg_msg += '    <div><label style="color:red;">应收货款：' + needPayAmount + '元</label></div>';
		dlg_msg += '    <div><label>实收货款：</label><input type="text" class="form-control" id="payAmount"></div>';
		
		dlg_msg += '  </div>';
		dlg_msg += '</form>';
		
		//alert(dlg_msg);
		bootbox.dialog({
            message: dlg_msg,  
            title: "完成配送单",  
            buttons: {
                Cancel: {  
                    label: "取消",  
                    className: "btn-default",
                    callback: function () {  
                        
                    }  
                }  
                , OK: {  
                    label: "保存",  
                    className: "btn-primary",
                    callback: function () {
                    	var parm = {};
                    	parm.batchId = batchId;
                        parm.sendResult = $('input[name="sendResult"]:checked').val();
                        parm.payAmount = $("#payAmount").val();
                        	
                        $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'ord/orderSend/saveSendResult.do',
							data:parm, 
							success:function(data){
								if(data.code=="200"){
									bootbox.alert(data.msg);
									search();
								}else{
									bootbox.alert(data.msg);
								}
							}
						});
                    }  
                }  
            }  
        });  
	}
	
	
</script>