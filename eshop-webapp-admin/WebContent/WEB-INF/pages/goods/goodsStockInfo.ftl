
<div class="col-xs-12">
	<#-- <h3 class="header smaller lighter blue">商品库存列表</h3> -->
	<div class="form-inline">
		<div class="col-sm-10">
			<label for="searchUserName">sku编码</label>
	   		<input style="width:auto;" class="form-control" id="skuCode" type="text"  placeholder="sku编码">
	   		
	   		<label for="searchFullName" style="margin-left:10px;">商品名称</label>
	    	<input style="width:auto;" class="form-control" id="goodsName" type="text"  placeholder="商品名称">
	    	
	    	<button class="btn btn-sm btn-primary" onclick="search();" style="margin-left:10px;"><i class="icon-search align-top bigger-125"></i> 搜索</button>
		</div>
	</div>
	<div class="table-responsive">
		<table id="datainfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>skuID</th>
					<th>sku编码</th>
					<th>商品名称</th>
					<th>商品品种</th>
					<th>商品规格</th>
					<th>养殖地</th>
					<th>配送级别</th>
					<th>可用库存</th>
					<th>基本单位</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
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
            "url": "goods/goodsStock/list.do",
            "type": "POST",
            "data": {"skuCode" : $("#skuCode").val(),
            		"goodsName" : $("#goodsName").val()
            		}
        },
        "columns": [
        			{ "data": "skuId" },
                    { "data": "skuCode" },
                    { "data": "goodsName" },
                    { "data": "goodsVarietiesDesc" },
                    { "data": "goodsSpecDesc" },
                    { "data": "goodsOriginDesc" },
                    { "data": "goodsShippingDesc" },
                    { "data": "stockNum" },
                    { "data": "goodsUnit" }
                ],
         
		"columnDefs": [
                  	{
                      "targets": [9], // 目标列位置，下标从0开始
                      "data": "skuId", // 数据列名
                      "render": function(data, type, row, full) { // 返回自定义内容          
                      		return "<button class='btn btn-xs btn-warning' onclick='editOne("+data+", &apos;"+row.skuCode+"&apos;, &apos;"+row.goodsName+"&apos;, &apos;"+row.goodsVarietiesDesc+"&apos;, &apos;"+row.goodsSpecDesc+"&apos;, &apos;"+row.goodsOriginDesc+"&apos;, &apos;"+row.goodsShippingDesc+"&apos;, &apos;"+row.goodsUnit+"&apos;, "+row.stockNum+")'><i class='icon-edit bigger-120'></i> 调整库存</button>";
                      }
                  	}
                  ]
    } );

	function search(){
	    var param = {
	        "skuCode" : $("#skuCode").val(),
            "goodsName" : $("#goodsName").val()
	    };
	    table.settings()[0].ajax.data = param;
	    table.ajax.reload();
	}
	
	//打开修改窗口
	function editOne(id,skuCode,goodsName,goodsVarieties,goodsSpec,goodsOrigin,goodsShipping,goodsUnit,stockNum){
		var dlg_msg = '';
		dlg_msg += '<form id="fm" method="post">';
		dlg_msg += '  <div class="widget-main">';
		dlg_msg += '    <div><label>SKU编码：'+skuCode+'</label></div>';
		dlg_msg += '    <div><label>商品名称：'+goodsName+'</label></div>';
		dlg_msg += '    <div><label>商品品种：'+goodsVarieties+'</label></div>';
		dlg_msg += '    <div><label>商品规格：'+goodsSpec+'</label></div>';
		dlg_msg += '    <div><label>产地/养殖地：'+goodsOrigin+'</label></div>';
		dlg_msg += '    <div><label>配送级别：'+goodsShipping+'</label></div>';
		dlg_msg += '    <div><label>基本单位：'+goodsUnit+'</label></div>';
		dlg_msg += '    <div><label>当前库存：'+stockNum+'</label></div>';
		dlg_msg += '    <div><label>调整：</label><input type="text" class="form-control" id="stockNum_dlg"></div>';
		dlg_msg += '  </div>';
		dlg_msg += '</form>';
		
		//alert(dlg_msg);
		bootbox.dialog({  
                message: dlg_msg,  
                title: "修改商品SKU库存",  
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
                        	parm.stockNum = $("#stockNum_dlg").val();
                        	
                            $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'goods/goodsStock/edit.do',
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