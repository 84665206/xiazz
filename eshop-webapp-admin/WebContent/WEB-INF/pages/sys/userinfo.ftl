
<div class="col-xs-12">
	<#--<h3 class="header smaller lighter blue">用户列表</h3>-->
	<div class="form-inline">
		<div class="col-sm-10">
			<label for="searchUserName">用户名</label>
	   		<input style="width:auto;" class="form-control" id="user_name" type="text"  placeholder="用户名">
	   		<label for="searchFullName" style="margin-left:10px;">真实姓名</label>
	    	<input style="width:auto;" class="form-control" id="full_name" type="text"  placeholder="真实姓名">
	    	<label for="searchPhone" style="margin-left:10px;">手机号</label>
	    	<input style="width:auto;" class="form-control" id="phone" type="text"  placeholder="手机号">
	    	<button class="btn btn-sm btn-primary" onclick="search();" style="margin-left:10px;"><i class="icon-search align-top bigger-125"></i> 搜索</button>
		</div>
		<div class="col-sm-10">
	    	<button class="btn btn-sm btn-primary" onclick="save();" style="margin-top:5px;margin-bottom:5px;"><i class="icon-plus align-top bigger-125"></i> 新增</button>
		</div>
	</div>
	<div class="table-responsive">
		<table id="userinfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>手机号码</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">				
	var table=$('#userinfo-list').DataTable( {
    	"searching":false,  //去除搜索框
        "processing": true, //隐藏加载提示
        "serverSide": true, //启用服务器端分页
        "order":[],   //取消初始化排序
        "ordering":false,  //取消所有列排序按钮
        //paging: false,  //禁止分页
        //"stripeClasses": ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
        "ajax": {
            "url": "sys/user/list.do",
            "type": "POST",
            "data": {"user_name" : $("#user_name").val(),
            		"full_name" : $("#full_name").val(),
            		"phone" : $("#phone").val()
            		}
        },
        "columns": [
        			{ "data": "id" },
                    { "data": "user_name" },
                    { "data": "full_name" },
                    { "data": "phone" },
                    { "data": "is_valid" }
                ],
         
		"columnDefs": [
                  	{
                      "targets": [4], // 目标列位置，下标从0开始
                      "data": "is_valid", // 数据列名
                      "render": function(data, type, full) { // 返回自定义内容
                      	  if(data==0){	
                          	return "<span class='label label-danger arrowed-in'>无效</span>";
                          }else{
                          	return "<span class='label label-success arrowed'>有效</span>";
                          }
                      }
                  	},
                  	{
                      "targets": [5], // 目标列位置，下标从0开始
                      "data": "id", // 数据列名
                      "render": function(data, type, full) { // 返回自定义内容
                          return "<button class='btn btn-xs btn-inverse' onclick='allot_role("+data+")'><i class='icon-lock bigger-120'></i> 分配角色</button>";
                      }
                  	}
                  ]
    } );

	function search(){
	    var param = {
	        "user_name" : $("#user_name").val(),
            "full_name" : $("#full_name").val(),
            "phone" : $("#phone").val()
	    };
	    table.settings()[0].ajax.data = param;
	    table.ajax.reload();
	}
	
	function allot_role(id){
		$.ajax({
			type:"post",
			dataType:"json",
			url:'sys/role/getmy.do',
			data:{'user_id' : id},
			success:function(data){
				if(!data||data.code!="200"){
					bootbox.alert(data.msg);
					return false;
				}
				data=data.data;
				var html = '';
				$.each(data,function(i,v){
					if(v.selected){
						html += '<option value="'+v.id+'" selected="selected">'+v.role_name+'</option>'
					}else{
						html += '<option value="'+v.id+'">'+v.role_name+'</option>';
					}
				})
				var dialog = bootbox.confirm({
		    		title: '分配角色',
		    		message: '正在获取角色信息',
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
				    		//提交选择
				    		var roleValues = $('[name="duallistbox_demo1[]"]').val()==null?'':$('[name="duallistbox_demo1[]"]').val().toString();
							$.ajax({
								url : 'sys/role/savemy.do',
								async:false,
								dataType : 'json',
								data : {role_id_str:roleValues,user_id:id},
								type : 'post',
								success : function(data){
									bootbox.alert(data.msg);
									return false;
								}
							});
							
				    	}
				    }
				});
				
				setTimeout(function(){
		        	dialog.find('.bootbox-body').html('<form class="form-group" id="dualSelectForm"><div><select multiple="multiple" size="10" name="duallistbox_demo1[]" id="duallist">'+html+'</select></div></form>');
					var demo1 = $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox({infoText:false,nonSelectedListLabel:'<font style="color:red;font-weight:bold;">待分配角色</font>',selectedListLabel:'<font style="color:green;font-weight:bold;">已分配角色</font>'});
					
		    	}, 1000);
			}
		});
	}
	
	function save(){
		bootbox.dialog({  
                message: '<form id="fm" method="post"><div class="widget-main"><div><label for="form-field-8">用户名称</label><input type="text" class="form-control" id="user_name_dlg" placeholder="请输入用户名称"></div><div><label for="form-field-8">真实姓名</label><input type="text" class="form-control" id="full_name_dlg" placeholder="请输入真实姓名"></div><div><label for="form-field-8">手机号码</label><input type="text" class="form-control" id="phone_dlg" placeholder="请输入手机号码"></div><div><label for="form-field-8">邮箱地址</label><input type="text" class="form-control" id="mail_dlg" placeholder="请输入邮箱地址"></div><div><label for="form-field-8">是否激活</label><div><label><input name="is_valid" type="radio" value="1" class="ace" /><span class="lbl">是</span></label><label><input name="is_valid" type="radio" value="0" class="ace" /><span class="lbl">否</span></label></div></div></div></form>',  
                title: "新增用户",  
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
							url:'sys/user/create.do',
							data:{"user_name":$("#user_name_dlg").val(), "full_name":$("#full_name_dlg").val(), "phone":$("#phone_dlg").val(), "mail":$("#mail_dlg").val(), "is_valid":$("input[name='is_valid']").val()}, 
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