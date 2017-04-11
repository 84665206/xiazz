
<div class="col-xs-12">
	<#-- <h3 class="header smaller lighter blue">订单列表</h3> -->
	<div class="form-inline">
		<div class="col-xs-12">
		
	   		<label for="orderStatus">订单状态</label>
	   		<select class="form-control" name="orderStatus" id="orderStatus" style="width:auto;">
		      	<option value="-1">请选择...</option>
		      	<option value="10">初始</option>
				<option value="20">已审核</option> 
				<option value="30">已完成</option> 
				<option value="-99">取消</option> 
		    </select>
	    	
	   		<label for="shippingLevel" style="margin-left:10px;">配送级别</label>
	   		<select class="form-control" name="shippingLevel" id="shippingLevel" style="width:auto;">
		      	<option value="-1">请选择...</option>
		      	<option value="4001">次日配送</option>
				<option value="4002">当天配送</option> 
		    </select>
		
			<label for="orderSn" style="margin-left:10px;">订单编号</label>
	   		<input style="width:120px;" class="form-control" id="orderSn" type="text"  placeholder="订单编号">
	   		
	   		<label for="receiverName" style="margin-left:10px;">收货人</label>
	    	<input style="width:100px;" class="form-control" id="receiverName" type="text"  placeholder="收货人">
	    	
	   		<label for="receiverMobile" style="margin-left:10px;">收货人手机</label>
	    	<input style="width:100px;" class="form-control" id="receiverMobile" type="text"  placeholder="手机">
	    	
	    	<div class="input-append date">
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
		<label style="color:red;" id="searchSum">订单查询汇总数据 -- 总数量：0kg；总金额：0元。</label>
	</div>
	<div class="table-responsive">
		<table id="datainfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>操作</th>
					<th>订单编号</th>
					<th>订单状态</th>
					<th>配送级别</th>
					<th>下单时间</th>
					<th>收货人</th>
					<th>收货人手机</th>
					<th>数量(斤)</th>
					<th>金额</th>
					<th>店招</th>
					<th>店铺地址</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">	
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
            "url": "ord/orderInfo/list.do",
            "type": "POST",
            "data": {"orderStatus" : $("#orderStatus").val(),
            		"shippingLevel" : $("#shippingLevel").val(),
            		"orderSn" : $("#orderSn").val(),
            		"receiverName" : $("#receiverName").val(),
            		"receiverMobile" : $("#receiverMobile").val(),
            		"orderDateStart" : $("#orderDateStart").val(),
            		"orderDateEnd" : $("#orderDateEnd").val()
            		}
        },
        "columns": [
        			{ "data": "id" },
                    { "data": "orderSn" },
                    { "data": "orderStatus" },
                    { "data": "shippingLevel" },
                    { "data": "addTimeDesc" },
                    { "data": "receiverName" },
                    { "data": "receiverMobile" },
                    { "data": "goodsSum" },
                    { "data": "goodsAmount" },
                    { "data": "shopName" },
                    { "data": "shopAddress" }
                ],
         
		"columnDefs": [
                  	{
                      "targets": [0], // 目标列位置，下标从0开始
                      "data": "id", // 数据列名
                      "render": function(data, type, row, full) { // 返回自定义内容
                      	 if(row.orderStatus==10){
                          	return "<button class='btn btn-xs btn-primary' onclick='go_orderinfo("+data+")'>审核订单</button>";
                         }else{
                         	return "<button class='btn btn-xs btn-inverse' onclick='go_orderinfo("+data+")'>查看订单</button>";
                         }
                      }
                  	},
                  	{
                      "targets": [2], // 目标列位置，下标从0开始
                      "data": "orderStatus", // 数据列名
                      "render": function(data, type, full) { // 返回自定义内容
                      	  if(data==10){
                          	return "<span class='label label-danger arrowed'>初始</span>";
                          }else if(data==20){
                          	return "<span class='label label-yellow arrowed'>已审核</span>";
                          }else if(data==30){
                          	return "<span class='label label-success arrowed'>已完成</span>";
                          }else if(data==-99){
                          	return "<span class='label label-light arrowed'>取消</span>";
                          }else{
                          	return "<span class='label label-danger arrowed-in'>未知</span>";
                          }
                      }
                  	},
                  	{
                      "targets": [3], // 目标列位置，下标从0开始
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
	        "orderStatus" : $("#orderStatus").val(),
            "shippingLevel" : $("#shippingLevel").val(),
            "orderSn" : $("#orderSn").val(),
            "receiverName" : $("#receiverName").val(),
            "receiverMobile" : $("#receiverMobile").val(),
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
			url:'ord/orderInfo/showSum.do',
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
	
	function go_orderinfo(orderId){
		//alert(orderId);
		
		window.open('ord/orderInfo/detailpage.do?orderId='+orderId,'','width='+(window.screen.availWidth-400)+',height='+(window.screen.availHeight-30)+ ',top=0,left=200,resizable=yes,status=yes,menubar=no,scrollbars=yes');
	}
	
	
</script>