$(document).ready(function() {
	AddressSelector.init(); 
});

function selectAddressClick(){
	window.location.href="/checkout/delivery/page.do";
}
function addDelivery(){ 
	$('#listAddress').hide();
	$('#newAddress').show(); 
}
function setDefaultDelivery(deliveryId){
	$.ajax({
		type : 'post',
		url : '/checkout/delivery/default.do',
		data : {
			"delivery_id" : deliveryId
		},
		dateType : 'json',
		success : function(data) {
			if (data.code == "200") {
				window.location.href="/checkout/view.do";
			} else {
				alert(data.msg);
				return false;
			}
		}
	});
}

function deleteDelivery(delivery_id){
	if(!confirm("确认删除？？？")){
		return false;
	}
	$.ajax({
		url: "/customer/delivery/delete.do",
		type: "post",
		data: {"delivery_id" : delivery_id},
		beforeSend:function(){
	    	$("#loading").show();
	    },
		success: function(data) {
			if (data.needlogin == true) {
				window.location.href="/passport/login.do?goingToURL="+encodeURI(location.href);
				return ;
			}
			if (data.code == "200") {
				window.location.href="/checkout/view.do";
			} else {
				alert(data.msg);
			}
		},
		complete:function(){$("#loading").hide();}
	});
}

var AddressSelector = { 
		init : function() { 
			$("#city,#county").hide();
			// 元素事件初始化
			$("#province").change(
					function() {
						var curObj = $(this);
						var id = curObj.val();
						$("#city,#county").empty().hide();
						if (id == 0) {
							alert("请输入收货省份");
						}else{
						  AddressSelector.refresh('city');
						}
					});
			$("#city").change(
					function() {
						var curObj = $(this);
						var id = curObj.val();
						$("#county").empty().hide();
						if (id == 0) {
							alert("请输入收货市/区");
						}else{
						  AddressSelector.refresh('county');
						}  
					});
			$("#county").change(
					function() {
						var curObj = $(this);
						var id = curObj.val();
						if (id == 0) {
							alert("请输入收货区/县");
						}  
					});
			this.refresh('province');
		},
		refresh : function(type) { 
			if(!type){
				return;
			}
			var url = "/customer/delivery/area.do";
			var data=null; 
			if (type == 'city') {
				data = {parent_id:$("#province").val(), area_type:2}; 
			} else if (type == 'county') {
				data = {parent_id:$("#city").val(), area_type:3}; 
			}else{
				data = {area_type:1}; 
			}
			$.post(url, data, function(rs) {
				if (rs != null && rs.code=="200"
					&&rs.data!=null&&rs.data.length>0) {
					AddressSelector.showUI(type,0,rs.data);
				}
			}, "json");

		},
		showUI : function(selectedType,selectedValue, data) {
			var defaultString = '请选择省';
			if (selectedType == 'city') {
				defaultString = '请选择市/区';
			} else if (selectedType == 'county') {
				defaultString = '请选择区/县';
			}

			if (data != null && data.length > 0) {
				$('#'+selectedType).empty();
				var html = "<option value=\"0\">" + defaultString + "</option>";
				$(data)
						.each(
								function(i) {
									if (this.name == selectedValue) {
										html += "<option value=\"" + this.id
												+ "\"  selected>" + this.area_name
												+ "</option>";
									} else {
										html += "<option value=\"" + this.id
												+ "\" >" + this.area_name + "</option>";
									}
								});
				$('#'+selectedType).html(html);
				$('#'+selectedType).show();
				if(selectedType=='province'){
					this.refresh('city');
				}else if(selectedType=='city'){
					this.refresh('county');
				}
			} else {
				$('#'+selectedType).empty().hide();

			}

		}
	};

var regMobile = /^([0\+]?\d{1,4}\-)?(1[2-9]{1}[0-9]{9}|\d{8,10})$/;
var regTel = /^([0\+]?\d{1,4}\-)?((\d{1,4})-)?(\d{7,8})(-(\d{1,6}))?$/;
var regZip = /^\d{5,6}$/g;
function addCustomerDelivery(){
	//判断是修改 还是 新增
	var modifyId = $("#modifyId").val();
	if (modifyId.trim() == "") {
		modifyId=0;
	}
	var consignee = $("#consignee").val();
	if (consignee == '') {
		alert("请输入收货人姓名");
		return false;
	}
	if (consignee.length > 12) {
		alert("收货人姓名 不能超过12个字符或6个汉字");
		return false;
	}
	var mobile = $("#mobile").val(); 
	var tel = $("#tel").val();
	if (mobile == '' && tel=="") {
		alert("请填写固话或手机号！");
		return false;
	}else{
		if (mobile != '' && !mobile.match(regMobile)) {
			alert("手机号码格式不正确，请输入11位数字");
			return false;
		}
		if (tel!="" && !tel.match(regTel)) {
			alert("请正确输入固话格式");
			return false;
		}
	}
	var provinceId = $("#province").val();
	var cityId =  $("#city").val();
	var countyId = $("#county").val();

	if (!provinceId ||provinceId == '' || provinceId == 0) {
		alert("请输入收货省份");
		return false;
	}
	 
	if (!cityId || cityId == '' || cityId == 0) {
		alert("请输入收货市/区");
		return false;
	}
	 
	if (!countyId || countyId == '' || countyId == 0) {
		alert("请输入收货区/县");
		return false;
	}
	var address = $("#address").val();
	if (address == '') {
		alert("请输入收货详细地址");
		return false;
	}
	if (address.length > 250) {
		alert("收货人姓名 不能超过125个汉字");
		return false;
	}
	
	var provinceTxt="";
	var cityTxt = "";
	var countyTxt = "";  
	$('#province option').each(function(){
		if( $(this).attr("selected")){
			provinceTxt=$(this).text();
		}
	}); 
	$('#city option').each(function(){
		if( $(this).attr("selected")){
			cityTxt=$(this).text();
		}
	}); 
	$('#county option').each(function(){
		if( $(this).attr("selected")){
			countyTxt=$(this).text();
		}
	});  
	
	$.ajax({
		url: "/customer/delivery/add.do",
		type: "post",
		data: {"consignee" : consignee,
				"mobile" : mobile,
				"tel" : tel,
				"province" : provinceTxt,
				"city" : cityTxt,
				"district" : countyTxt,
				"address" : address
				
		},
		beforeSend:function(){
	    	$("#loading").show();
	    },
		success: function(data) {
			if (data.needlogin == true) {
				window.location.href="/passport/login.do?goingToURL="+encodeURI(location.href);
				return ;
			}
			if (data.code == "200") {
				alert(data.msg);
				window.location.href="/checkout/view.do";
			} else {
				alert(data.msg);
			}
		},
		complete:function(){$("#loading").hide();}
	});
}

function submitOrderCheck() {  
	var message = $("#message").val();
	if (message && message.length > 1000) {
		alert("留言不能够超过500个汉字");
		return false;
	}
	$("#submitOrderBtn").html("订单处理中，请稍后...");
	$("#submitOrderForm").submit();
}

function clearOrder(){
	$.ajax({
		type : 'post',
		url : '/checkout/order/clear.do',
		data : {
			
		},
		dateType : 'json',
		success : function(data) {
			if (data.code == "200") {
				window.location.href="/checkout/view.do";
			} else {
				alert(data.msg);
				return false;
			}
		}
	});
}