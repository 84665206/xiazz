 // 如果顶层页面不是登录页面，也就是说登录页面在框架里
if(window.parent.location != window.location) {
      window.parent.location = window.location;
}

$(function(){
	$("#login").click(function(event){
		var username = $.trim($('#username').val());
		var password = $.trim($('#password').val());
		var writeCode=$.trim($('#writeCode').val());
		if(username == ""){
			$('#errormessage').html("用户名不能为空！").css({visibility: 'visible'})
			return false;

		}else if(password  == ""){
			$('#errormessage').html("密码不能为空！").css({visibility: 'visible'});
			return false;
		}else if(writeCode  == ""){
			$('#errormessage').html("验证不能为空！").css({visibility: 'visible'});
			return false;
		}else{
		    $('#loginform').submit();
		}
	});
	$("#reset").click(function(event){
		$('#username').val('');
		$('#password').val('');
		$('#writeCode').val('');
	});
});

function enterNext(event){
	var keyCode = event.keyCode || event.which || event.charCode;
	if(keyCode == 13){
		document.getElementById('password').select();
	}
}

function enterImageCode(event){
	var keyCode = event.keyCode || event.which || event.charCode;
	if(keyCode == 13){
		document.getElementById('writeCode').select();
	}
}

function enterCommit(event){
	var keyCode = event.keyCode || event.which || event.charCode;
	if(keyCode == 13){
		document.getElementById('login').click();
	}
}

//获取验证码
function changeImageCode(obj) {
	var id = $(obj).attr("id");
	var timenow = new Date().getTime();
	var toSrc = ctx + '/getVerificationCode.action?d=' + timenow;
	document.getElementById(id).src = toSrc;
}