
<div class="col-xs-12">
	<#-- <h3 class="header smaller lighter blue">客户会员列表</h3> -->
	<div class="form-inline">
		<div class="col-sm-10">
			<label for="searchUserName">客户编码</label>
	   		<input style="width:auto;" class="form-control" id="customerName" type="text"  placeholder="客户编码">
	   		
	   		<label for="searchFullName" style="margin-left:10px;">客户名称</label>
	    	<input style="width:auto;" class="form-control" id="customerTrueName" type="text"  placeholder="客户名称">
	    	
	    	<button class="btn btn-sm btn-primary" onclick="search();" style="margin-left:10px;"><i class="icon-search align-top bigger-125"></i> 搜索</button>
		</div>
	</div>
	<div class="table-responsive">
		<table id="datainfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>客户编码</th>
					<th>是否有效</th>
					<th>客户名称</th>
					<th>店招</th>
					<th>店铺地址</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="dlg_addOne" style="display:none;">
	<form id="fm" method="post">
		<div class="widget-main">
		  <div><label>客户编码</label><input type="text" class="form-control" id="customerName_dlg2" placeholder="请输入客户编码"></div>
		  <div><label>客户名称</label><input type="text" class="form-control" id="customerTrueName_dlg2" placeholder="请输入客户名称"></div>
		  <div><label>店招</label><input type="text" class="form-control" id="shopName_dlg2" placeholder="请输入店招"></div>
		  <div><label>店铺地址</label><input type="text" class="form-control" id="shopAddress_dlg2" placeholder="请输入店铺地址"></div>
		  <div>
		    <label>是否有效</label>
		    <label><input name="isValid_dlg2" type="radio" value="1" class="ace" /><span class="lbl">是</span></label>
		    <label><input name="isValid_dlg2" type="radio" value="0" class="ace" /><span class="lbl">否</span></label>
		  </div>
		</div>
	</form>
</div>

<script type="text/javascript">				
	var table=$('#datainfo-list').DataTable( {
    	"searching":false,  //去除搜索框
        "processing": true, //隐藏加载提示
        "serverSide": true, //启用服务器端分页
        "order":[],   //取消初始化排序
        "ordering":false,  //取消所有列排序按钮
        //paging: false,  //禁止分页
        //"stripeClasses": ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
        "ajax": {
            "url": "crm/customer/list.do",
            "type": "POST",
            "data": {"customerName" : $("#customerName").val(),
            		"customerTrueName" : $("#customerTrueName").val()
            		}
        },
        "columns": [
        			{ "data": "id" },
                    { "data": "customerName" },
                    { "data": "isValid" },
                    { "data": "customerTrueName" },
                    { "data": "shopName" },
                    { "data": "shopAddress" }
                ],
         
		"columnDefs": [
                  	{
                      "targets": [2], // 目标列位置，下标从0开始
                      "data": "isValid", // 数据列名
                      "render": function(data, type, full) { // 返回自定义内容
                      	  if(data==0){	
                          	return "<span class='label label-danger arrowed-in'>无效</span>";
                          }else{
                          	return "<span class='label label-success arrowed'>有效</span>";
                          }
                      }
                  	},
                  	{
                      "targets": [6], // 目标列位置，下标从0开始
                      "data": "id", // 数据列名
                      "render": function(data, type, row, full) { // 返回自定义内容          
                      		return "<button class='btn btn-xs btn-warning' onclick='editOne("+data+", &apos;"+row.customerName+"&apos;, &apos;"+row.customerTrueName+"&apos;, &apos;"+row.shopName+"&apos;, &apos;"+row.shopAddress+"&apos;, "+row.isValid+")'><i class='icon-edit bigger-120'></i> 修改</button>";
                      }
                  	}
                  ]
    } );

	function search(){
	    var param = {
	        "customerName" : $("#customerName").val(),
            "customerTrueName" : $("#customerTrueName").val()
	    };
	    table.settings()[0].ajax.data = param;
	    table.ajax.reload();
	}
	
	//打开新增窗口
	function addOne(){
		var dlg_msg = $("#dlg_addOne").html();
		//alert(dlg_msg);
		bootbox.dialog({  
                message: dlg_msg,  
                title: "新增客户",  
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
                        	parm.customerName = $("#customerName_dlg2").val();
                        	parm.customerTrueName = $("#customerTrueName_dlg2").val();
                        	parm.shopName = $("#shopName_dlg2").val();
                        	parm.shopAddress = $("#shopAddress_dlg2").val();
                        	parm.isValid = $('input[name="isValid_dlg2"]:checked').val();
                        	
                            $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'crm/customer/create.do',
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
	
	//打开修改窗口
	function editOne(id,customerName,customerTrueName,shopName,shopAddress,isValid){
		var dlg_msg = '';
		dlg_msg += '<form id="fm" method="post">';
		dlg_msg += '  <div class="widget-main">';
		dlg_msg += '    <div><label>客户编码</label><input type="text" class="form-control" id="customerName_dlg" value="'+customerName+'"></div>';
		dlg_msg += '    <div><label>客户名称</label><input type="text" class="form-control" id="customerTrueName_dlg" value="'+customerTrueName+'"></div>';
		dlg_msg += '    <div><label>店招</label><input type="text" class="form-control" id="shopName_dlg" value="'+shopName+'"></div>';
		dlg_msg += '    <div><label>店铺地址</label><input type="text" class="form-control" id="shopAddress_dlg" value="'+shopAddress+'"></div>';
		dlg_msg += '    <div>';
		dlg_msg += '      <label>是否有效</label>';
		
		if(isValid==1){
			dlg_msg += '      <label><input name="isValid_dlg" type="radio" value="1" class="ace" checked="checked"/><span class="lbl">是</span></label>';
			dlg_msg += '      <label><input name="isValid_dlg" type="radio" value="0" class="ace" /><span class="lbl">否</span></label>';
		}else{
			dlg_msg += '      <label><input name="isValid_dlg" type="radio" value="1" class="ace" /><span class="lbl">是</span></label>';
			dlg_msg += '      <label><input name="isValid_dlg" type="radio" value="0" class="ace"  checked="checked"/><span class="lbl">否</span></label>';
		}
		
		dlg_msg += '    </div>';
		dlg_msg += '  </div>';
		dlg_msg += '</form>';
		
		//alert(dlg_msg);
		bootbox.dialog({  
                message: dlg_msg,  
                title: "修改客户",  
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
                        	parm.id = id;
                        	parm.customerName = $("#customerName_dlg").val();
                        	parm.customerTrueName = $("#customerTrueName_dlg").val();
                        	parm.shopName = $("#shopName_dlg").val();
                        	parm.shopAddress = $("#shopAddress_dlg").val();
                        	parm.isValid = $('input[name="isValid_dlg"]:checked').val();
                        	
                            $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'crm/customer/edit.do',
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