<div class="col-xs-12">
	
	<div id="stockinDirectTaskDlg" style="width:400px;height:250px;padding:10px 20px;left:10px;top:10px;" closed="false" closable="false" class="easyui-dialog" buttons="#dlg-buttons" title="修改密码">
			<form id="modifypassword" method="post" novalidate>
				<div class="fitem">
				    <label>
				        原密码
				    </label>
				    <input name="oldPassword" id="oldPassword" type="password">
				</div>
				<div class="fitem" style="margin-top:20px;">
				    <label>
				        新密码
				    </label>
				    <input name="newPassword" id="newPassword" type="password" onKeyUp="modifypassword.authPasswd();">(数字和字母组合)
				</div>
				<div>
					 <p id="weakWordError" style="float:left;color:red;font-weight:bold;text-align:center;width:230px;" class="hide"></p>
					 <p id="weakWord" style="float:left;margin-left:8px;" class="hide">密码强度：</p>
					 <p id="weakSmall" class="hide" style="width:100px;float:left;color: white; text-align: center; background-color: rgb(221, 0, 0);">弱</p>
					 <p id="weakMiddle" class="hide" style="width:130px;float:left;color: white; text-align: center; background-color: rgb(255, 204, 51);">中</p>
					 <p id="weakBig" class="hide" style="width:150px;float:left;color: white; text-align: center; background-color: rgb(0, 153, 0);">强</p>
			    </div>
				<br/>	  
				<div class="fitem" style="margin-top:3px;">
				    <label>
				        重复密码
				    </label>
				    <input name="rePassword" id="rePassword" type="password">
				</div>
				<p id ="errormessage" style="visibility:visible;font-size:15px;height:20px;font-weight:bold;color:red;width:330px;text-align:center;"></p>
				<div class="fitem" style="margin-top:5px;margin-left:100px;">
				    <a href="#" class="easyui-linkbutton" iconcls="icon-ok" id="save">保存</a>
				</div>
			</form>
		</div>
	
</div>

<script type="text/javascript">

	$("#save").click(function(event){
		var newPassword = $('#newPassword').val();
		var rePassword = $('#rePassword').val();
		var oldPassword = $.trim($('#oldPassword').val());

		if(!oldPassword){
			$("#oldPassword").select();
			$("#oldPassword").focus();
			modifypassword.showMessage("请输入原始密码");
			return false;
		}
		if(!newPassword){
			$("#newPassword").select();
			$("#newPassword").focus();
			modifypassword.showMessage("请输入新密码");
			return false;
		}
		if(!rePassword){
			$("#rePassword").select();
			$("#rePassword").focus();
			modifypassword.showMessage("请输入确认密码");
			return false;
		}
		if(newPassword != rePassword){
			modifypassword.showMessage("输入的两次新密码不一致").css({visibility: 'visible'});
			return false;
		}
		if(newPassword.length<6){
			$("#newPassword").val("");
			$("#newPassword").focus();
			$("#rePassword").val("");
			modifypassword.showMessage("设置的密码长度需大于6");
			return false;
		}
		if(!(/[a-zA-Z]+/.test(newPassword) && /[0-9]+/.test(newPassword))){
			$("#newPassword").val("");
			$("#newPassword").focus();
			$("#rePassword").val("");
			modifypassword.showMessage("设置的密码必须包含数字和字母");
			return false;
		}
		$.ajax({
			url: ctx + "/sys/modifyUserPassword.do",
			type:"post",
			data:{newPassword:newPassword,rePassword:rePassword,oldPassword:oldPassword,anticache:Math.floor(Math.random()*1000)},
			dataType:"html",
			success:function(map){
				$("#newPassword").val("");
				$("#oldPassword").val("");
				$("#rePassword").val("");
				$("#weakWord").hide();
				$("#weakBig").hide();
				$("#weakSmall").hide();
				$("#weakMiddle").hide();
				modifypassword.showMessage(map);
			}
		});
	});

var modifypassword={
	//检验密码的长度
	authPasswd:function(){  
		var string=$("#newPassword").val();
		var containSpecial=RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);
		if(string.length>=6&&/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string)) {
			if(/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string)&&containSpecial.test(string)) {  
				modifypassword.noticeAssign(1);  
			}else if(/[a-zA-Z]+/.test(string) || /[0-9]+/.test(string) || /\W+\D+/.test(string)) {  
				if(/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string)) {  
					modifypassword.noticeAssign(0); 
				}else if(/\[a-zA-Z]+/.test(string)&&containSpecial.test(string)) {  
					modifypassword.noticeAssign(0);
				}else if(/\[a-zA-Z]+/.test(string)) {  
					modifypassword.noticeAssign(-1);
				}else if(/[0-9]+/.test(string)&&containSpecial.test(string)) {  
					modifypassword.noticeAssign(0);	
				}else if(/[0-9]+/.test(string)) {
					modifypassword.noticeAssign(-1);
				}
			}  
		}else if(string.length>=6&&(/[a-zA-Z]+/.test(string) || /[0-9]+/.test(string))){  
			modifypassword.noticeAssign(-2);   
		}else{
			modifypassword.noticeAssign(-3);   
		}
	},

	//显示密码条强度
	 noticeAssign:function(num){
		if(num == 1) {  
			$("#weakWordError").hide();
			$("#weakWord").show();
			$("#weakBig").show();
			$("#weakSmall").hide();
			$("#weakMiddle").hide();
		}else if(num == -1){  
			$("#weakWordError").hide();
			$("#weakWord").show();
			$("#weakSmall").show();
			$("#weakBig").hide();
			$("#weakMiddle").hide();
		}else if(num ==0) {
			$("#weakWordError").hide();
			$("#weakWord").show();
			$("#weakMiddle").show();
			$("#weakBig").hide();
			$("#weakSmall").hide();
		}else if(num ==-2){
			$("#weakWordError").show();
			$("#weakWordError").html("设置的密码需包含数字和字母");
			$("#weakWord").hide();
			$("#weakMiddle").hide();
			$("#weakBig").hide();
			$("#weakSmall").hide();
		}else if(num ==-3){
			$("#weakWordError").show();
			$("#weakWordError").html("设置的密码需长度大于6位");
			$("#weakWord").hide();
			$("#weakMiddle").hide();
			$("#weakBig").hide();
			$("#weakSmall").hide();
		}
	},
	
	//显示message
	showMessage:function(message){
		$("#errormessage").html(message);	
		window.setTimeout("modifypassword.clearMessage()",10000);
	},
	//清空message(跳转)
	clearMessage:function(){
		$("#errormessage").html("");
	},
};
	
</script>



