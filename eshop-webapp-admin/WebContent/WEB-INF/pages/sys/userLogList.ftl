
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
	</div>
	<div class="table-responsive">
		<table id="userinfo-list" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>用户名</th>
					<th>登录IP</th>
					<th>登录时间</th>
					<th>状态</th>
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
            "url": "sys/log/list.do",
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
					var demo1 = $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox({infoText:false,nonSelectedListLabel:'<font style="color:red">未选中的角色</font>',selectedListLabel:'<font style="color:green">已选择的角色</font>'});
					
		    	}, 1000);
			}
		});
	}
</script>