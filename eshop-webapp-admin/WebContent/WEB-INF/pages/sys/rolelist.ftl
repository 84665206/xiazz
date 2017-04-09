
<div class="col-xs-12">
	<#--<h3 class="header smaller lighter blue">用户列表</h3>-->
	<div class="form-inline">
		<div class="col-sm-10">
			<label for="searchRoleName">角色名称</label>
	   		<input style="width:auto;" class="form-control" id="role_name" type="text"  placeholder="角色名称">
	    	<button class="btn btn-sm btn-primary" onclick="search();" style="margin-left:10px;"><i class="icon-search align-top bigger-125"></i> 搜索</button>
		</div>
	</div>
	<div class="table-responsive">
		<table id="roleinfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>角色名称</th>
					<th>角色描述</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">				
	var table=$('#roleinfo-list').DataTable( {
    	"searching":false,  //去除搜索框
        "processing": true, //隐藏加载提示
        "serverSide": true, //启用服务器端分页
        "order":[],   //取消初始化排序
        "ordering":false,  //取消所有列排序按钮
        //paging: false,  //禁止分页
        //"stripeClasses": ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
        "ajax": {
            "url": "sys/role/list.do",
            "type": "POST",
            "data": {"role_name" : $("#role_name").val()}
        },
        "columns": [
        			{ "data": "id" },
                    { "data": "role_name" },
                    { "data": "description" }
                ],
         
		"columnDefs": [
                  	{
                      "targets": [3], // 目标列位置，下标从0开始
                      "data": "id", // 数据列名
                      "render": function(data, type, row, full) { // 返回自定义内容
                          return "<button class='btn btn-xs btn-primary' onclick='save()'><i class='icon-plus bigger-120'></i> 新增</button> <button class='btn btn-xs btn-warning' onclick=\"edit_role("+data+", '"+row.role_name+"', '"+row.description+"')\"><i class='icon-edit bigger-120'></i> 修改</button> <button class='btn btn-xs btn-inverse' onclick='allot_role("+data+")'><i class='icon-lock bigger-120'></i> 权限</button> <button class='btn btn-xs btn-danger' onclick='delete_role("+data+")'><i class='icon-trash bigger-120'></i> 删除</button>";
                      }
                  	}
                  ]
    } );

	function search(){
	    var param = {
	        "role_name": $("#role_name").val()
	    };
	    table.settings()[0].ajax.data = param;
	    table.ajax.reload();
	}
	
	function save(){
		bootbox.dialog({  
                message: '<form id="fm" method="post"><div class="widget-main"><div><label for="form-field-8">角色名称</label><input type="text" class="form-control" id="role_name_dlg" placeholder="请输入角色名称"></div><hr><div><label for="form-field-8">描述</label><textarea class="form-control" id="description_dlg" placeholder="请输入角色描述"></textarea></div></div></form>',  
                title: "新增角色",  
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
                            $.ajax({
							type:"post",
							async:false,
							dataType:"json",
							url:'sys/role/save.do',
							data:{"role_name":$("#role_name_dlg").val(), "description":$("#description_dlg").val()}, 
							success:function(data){
								if(data.code=="200"){
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
	
	function delete_role(id){
		bootbox.confirm({
		    title: "删除角色",
		    message: "确定需要删除当前角色？？？",
		    buttons: {
		        cancel: {
		            label: '<i class="fa fa-times"></i> 取消'
		        },
		        confirm: {
		            label: '<i class="fa fa-check"></i> 确定'
		        }
		    },
		    callback: function (result) {
		        if(true){
		        	$.ajax({
						url:"sys/role/delete.do",
						type:"post",
						data:{"id": id},
						dataType:"json",
						success:function(data){
							if(data.code =="200"){
								search();
							}else{
								bootbox.alert(data.msg);
							}
						}
					});
		        }
		    }
		});
	}
	
	function edit_role(id, role_name, description){
		bootbox.dialog({
		    title: "编辑角色",
		    message: '<form id="fm" method="post"><div class="widget-main"><div><label for="form-field-8">角色名称</label><input type="text" value="'+role_name+'" class="form-control" id="role_name_dlg" placeholder="请输入角色名称"></div><hr><div><label for="form-field-8">描述</label><textarea class="form-control" id="description_dlg"  placeholder="请输入角色描述">'+description+'</textarea></div></div></form>',
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
                        $.ajax({
							type:"post",
							dataType:"json",
							url:"sys/role/edit.do",
							data:{"role_name":$("#role_name_dlg").val(), "description":$("#description_dlg").val(), "id" : id}, 
							success:function(data){
								if(data.code=='200'){
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
	
	//分配权限
	function allot_role (role_id){
			var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					},
					key:{
						name:'ch_name'
					}
				}
			};
			var dialog = bootbox.confirm({
			    title: "角色赋权限",
			    message: '<p><i class="fa fa-spin fa-spinner"></i> 正在获取信息...</p></ul>',
			    buttons: {
			        cancel: {
			            label: '<i class="fa fa-times"></i> 取消'
			        },
			        confirm: {
			            label: '<i class="fa fa-check"></i> 提交'
			        }
			    },
			    callback: function (result) {
			        if(result){
						var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            			nodes=treeObj.getCheckedNodes(true);
            			if(nodes.length==0){
							$('<div id="myAlert" class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>警告！</strong>请选择菜单。</div>').prependTo(".page-content").delay(2000).hide(0);
							return;
						}
						var menuIds = new Array();
						menuIds.push(role_id);//角色id
            			for(var i=0;i<nodes.length;i++){
			            	menuIds.push(nodes[i].id);
			            }
			            
						$.ajax({
							url : 'sys/saveGrantMenus.do',
							data : $.param({menuIds:menuIds,anticache:Math.floor(Math.random()*1000)},true),
							dataType : 'json',
							type : 'post',
							success : function(data){
								if(data.status =="success"){
									$('<div id="myAlert" class="alert alert-success"><a href="#" class="close" data-dismiss="alert">&times;</a>修改成功。</div>').prependTo(".page-content").delay(2000).hide(0);
								}else{
									$('<div id="myAlert" class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>修改失败。</div>').prependTo(".page-content").delay(2000).hide(0);

								}
							}
						});
			        }
			    }
			});
			
			
			
			$.ajax({
				url: 'sys/listRolsMenu.do?roleId='+role_id,
				dataType : 'json',
				type : 'post',
				success : function(data){
					var zNodes = eval(data);
					setTimeout(function(){
			        	dialog.find('.bootbox-body').html('<ul id="treeDemo" class="ztree">');
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			    	}, 1000);
					
				}
			});
		}
</script>