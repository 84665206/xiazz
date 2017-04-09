$(function(){
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