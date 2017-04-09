
<div class="col-xs-12">
	<#-- <h3 class="header smaller lighter blue">商品SKU列表</h3> -->
	<div class="form-inline">
		<div class="col-sm-10">
			<label for="searchUserName">sku编码</label>
	   		<input style="width:auto;" class="form-control" id="skuCode" type="text"  placeholder="sku编码">
	   		
	   		<label for="searchFullName" style="margin-left:10px;">商品名称</label>
	    	<input style="width:auto;" class="form-control" id="goodsName" type="text"  placeholder="商品名称">
	    	
	    	<button class="btn btn-sm btn-primary" onclick="search();" style="margin-left:10px;"><i class="icon-search align-top bigger-125"></i> 搜索</button>
		</div>
		<div class="col-sm-10">
	    	<button class="btn btn-sm btn-primary" onclick="addOne();" style="margin-top:5px;margin-bottom:5px;"><i class="icon-plus align-top bigger-125"></i> 新增</button>
		</div>
	</div>
	<div class="table-responsive">
		<table id="datainfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>sku编码</th>
					<th>是否有效</th>
					<th>商品名称</th>
					<th>商品品种</th>
					<th>商品规格</th>
					<th>养殖地</th>
					<th>基本单位</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="dlg_addOne" style="display:none;">
	<form id="fm" method="post">
		<div class="widget-main">
		  <div><label>SKU编码</label><input type="text" class="form-control" id="skuCode_dlg" placeholder="请输入SKU编码"></div>
		  <div><label>商品名称</label><input type="text" class="form-control" id="goodsName_dlg" placeholder="请输入商品名称"></div>
		  <div><label>商品品种</label><input type="text" class="form-control" id="goodsVarieties_dlg" placeholder="请输入商品品种"></div>
		  <div><label>商品规格</label><input type="text" class="form-control" id="goodsSpec_dlg" placeholder="请输入商品规格"></div>
		  <div><label>产地/养殖地</label><input type="text" class="form-control" id="goodsOrigin_dlg" placeholder="请输入产地/养殖地"></div>
		  <div><label>基本单位</label><input type="text" class="form-control" id="goodsUnit_dlg" placeholder="请输入基本单位"></div>
		  <div><label>其他说明</label><input type="text" class="form-control" id="otherExplain_dlg" placeholder="请输入其他说明"></div>
		  <div>
		    <label>是否有效</label>
		    <label><input name="isValid_dlg" type="radio" value="1" class="ace" /><span class="lbl">是</span></label>
		    <label><input name="isValid_dlg" type="radio" value="0" class="ace" /><span class="lbl">否</span></label>
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
            "url": "goods/goodsSku/list.do",
            "type": "POST",
            "data": {"skuCode" : $("#skuCode").val(),
            		"goodsName" : $("#goodsName").val()
            		}
        },
        "columns": [
        			{ "data": "id" },
                    { "data": "skuCode" },
                    { "data": "isValid" },
                    { "data": "goodsName" },
                    { "data": "goodsVarieties" },
                    { "data": "goodsSpec" },
                    { "data": "goodsOrigin" },
                    { "data": "goodsUnit" }
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
                      "targets": [8], // 目标列位置，下标从0开始
                      "data": "id", // 数据列名
                      "render": function(data, type, row, full) { // 返回自定义内容          
                      		return "<button class='btn btn-xs btn-warning' onclick='editOne("+data+", &apos;"+row.skuCode+"&apos;, &apos;"+row.goodsName+"&apos;, &apos;"+row.goodsVarieties+"&apos;, &apos;"+row.goodsSpec+"&apos;, &apos;"+row.goodsOrigin+"&apos;, &apos;"+row.goodsUnit+"&apos;, &apos;"+row.otherExplain+"&apos;, "+row.isValid+")'><i class='icon-edit bigger-120'></i> 修改</button>";
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
	
	//打开新增窗口
	function addOne(){
		var dlg_msg = $("#dlg_addOne").html();
		//alert(dlg_msg);
		bootbox.dialog({  
                message: dlg_msg,  
                title: "新增商品SKU",  
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
                        	parm.skuCode = $("#skuCode_dlg").val();
                        	parm.goodsName = $("#goodsName_dlg").val();
                        	parm.goodsVarieties = $("#goodsVarieties_dlg").val();
                        	parm.goodsSpec = $("#goodsSpec_dlg").val();
                        	parm.goodsOrigin = $("#goodsOrigin_dlg").val();
                        	parm.goodsUnit = $("#goodsUnit_dlg").val();
                        	parm.otherExplain = $("#otherExplain_dlg").val();
                        	parm.isValid = $("input[name='isValid_dlg']").val();
                        	
                            $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'goods/goodsSku/create.do',
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
	function editOne(id,skuCode,goodsName,goodsVarieties,goodsSpec,goodsOrigin,goodsUnit,otherExplain,isValid){
		var dlg_msg = '';
		dlg_msg += '<form id="fm" method="post">';
		dlg_msg += '  <div class="widget-main">';
		dlg_msg += '    <div><label>SKU编码</label><input type="text" class="form-control" id="skuCode_dlg2" value="'+skuCode+'"></div>';
		dlg_msg += '    <div><label>商品名称</label><input type="text" class="form-control" id="goodsName_dlg2" value="'+goodsName+'"></div>';
		dlg_msg += '    <div><label>商品品种</label><input type="text" class="form-control" id="goodsVarieties_dlg2" value="'+goodsVarieties+'"></div>';
		dlg_msg += '    <div><label>商品规格</label><input type="text" class="form-control" id="goodsSpec_dlg2" value="'+goodsSpec+'"></div>';
		dlg_msg += '    <div><label>产地/养殖地</label><input type="text" class="form-control" id="goodsOrigin_dlg2" value="'+goodsOrigin+'"></div>';
		dlg_msg += '    <div><label>基本单位</label><input type="text" class="form-control" id="goodsUnit_dlg2" value="'+goodsUnit+'"></div>';
		dlg_msg += '    <div><label>其他说明</label><input type="text" class="form-control" id="otherExplain_dlg2" value="'+otherExplain+'"></div>';
		dlg_msg += '    <div>';
		dlg_msg += '      <label>是否有效</label>';
		
		if(isValid==1){
			dlg_msg += '      <label><input name="isValid_dlg2" type="radio" value="1" class="ace" checked="checked"/><span class="lbl">是</span></label>';
			dlg_msg += '      <label><input name="isValid_dlg2" type="radio" value="0" class="ace" /><span class="lbl">否</span></label>';
		}else{
			dlg_msg += '      <label><input name="isValid_dlg2" type="radio" value="1" class="ace" /><span class="lbl">是</span></label>';
			dlg_msg += '      <label><input name="isValid_dlg2" type="radio" value="0" class="ace"  checked="checked"/><span class="lbl">否</span></label>';
		}
		
		dlg_msg += '    </div>';
		dlg_msg += '  </div>';
		dlg_msg += '</form>';
		
		//alert(dlg_msg);
		bootbox.dialog({  
                message: dlg_msg,  
                title: "修改商品SKU",  
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
                        	parm.skuCode = $("#skuCode_dlg2").val();
                        	parm.goodsName = $("#goodsName_dlg2").val();
                        	parm.goodsVarieties = $("#goodsVarieties_dlg2").val();
                        	parm.goodsSpec = $("#goodsSpec_dlg2").val();
                        	parm.goodsOrigin = $("#goodsOrigin_dlg2").val();
                        	parm.goodsUnit = $("#goodsUnit_dlg2").val();
                        	parm.otherExplain = $("#otherExplain_dlg2").val();
                        	parm.isValid = $('input[name="isValid_dlg2"]:checked').val();
                        	
                            $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'goods/goodsSku/edit.do',
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