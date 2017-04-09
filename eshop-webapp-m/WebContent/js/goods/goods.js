
define(function(require){
	var $ = require('zepto'),
	slider = require('/js/common/slider'); 
	$('#dt-slider').slider({
      trigger:'.dt-slider-status',
      loop:true,
      play:true,
      steps:320
	});
	
	$("#decCount").click(function(){
		changeCount(-1);
	});

	$("#addCount").click(function(){
		changeCount(1);
	});
	
	 //更改购买数量
	function changeCount(count){
	   	if($("#goodsCount").val()==''){
	   		$("#goodsCount").val(min_num);
	   	}else{
	       	var goodsCount = parseInt($("#goodsCount").val()) + count;
	       	if(goodsCount >= min_num){
	       		$("#goodsCount").val(goodsCount);
	       	}
	   	}
	}
	
	//立即购买
	$("#go_buy").bind("click",function(){
    	var sku_num = $("#goodsCount").val();
    	var sku_id = $("#buy_sku_id").val();
    	goodsToCart(sku_id, sku_num);
    	
    });
	
	function goodsToCart(sku_id, sku_num) {
		$.ajax({
			url: "/cart/add.do",
			type: "post",
			data: {"sku_id" : sku_id,"sku_num" : sku_num},
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
	
	function getGoodsById(sku_id) {
		$.ajax({
			url: "/goods/ajax/info.do",
			type: "post",
			data: {"sku_id" : sku_id},
			beforeSend:function(){
		    	$("#loading").show();
		    },
			success: function(data) {
				if (data.needlogin == true) {
					window.location.href="/passport/login.do?goingToURL="+encodeURI(location.href);
					return ;
				}
				if (data.code == "200") {
					if(data.data){
						var goods=data.data;
						$("#buy_sku_id").val(goods.main_goods.id);
						$("#goods_price_span").html("￥"+goods.main_goods.sale_price+"/斤");
					}else{
						$("#buy_sku_id").val("");
					}
				} else {
					alert(data.msg);
				}
			},
			complete:function(){$("#loading").hide();}
		});
	}
	
	
	$("#goods_shipping i").click(function(){
		var select_goods_shipping=$(this).attr("dataId");
		var buy_goods_shipping = $("#buy_goods_shipping").val();
    	if(select_goods_shipping == buy_goods_shipping){
    		return;
    	} 	
    	var buy_goods_varieties = $("#buy_goods_varieties").val(); 
    	var buy_goods_spec = $("#buy_goods_spec").val(); 
    	var buy_goods_origin = $("#buy_goods_origin").val(); 
    	$("#buy_goods_shipping").val(select_goods_shipping); 
    	if(buy_goods_varieties&&buy_goods_spec&&buy_goods_origin){
    		var goodsId = $("#buy_goods_"+select_goods_shipping+"_"+buy_goods_varieties+"_"+buy_goods_spec+"_"+buy_goods_origin).val(); 
        	if(goodsId){
        		getGoodsById(goodsId);
        		$("#goods_shipping .spec").removeClass("sel");
        		$(this).addClass("sel");
        	}else{
        		alert("产品不存在，请重新选择");
        		$("#buy_sku_id").val(""); 
        	}
    	}
	});
	
	$("#goods_varieties i").click(function(){
		var select_goods_varieties=$(this).attr("dataId");
		var buy_goods_varieties = $("#buy_goods_varieties").val();
    	if(select_goods_varieties == buy_goods_varieties){
    		return;
    	} 	
    	var buy_goods_shipping = $("#buy_goods_shipping").val(); 
    	var buy_goods_spec = $("#buy_goods_spec").val(); 
    	var buy_goods_origin = $("#buy_goods_origin").val(); 
    	$("#buy_goods_varieties").val(select_goods_varieties); 
    	if(buy_goods_shipping&&buy_goods_spec&&buy_goods_origin){
    		var goodsId = $("#buy_goods_"+buy_goods_shipping+"_"+select_goods_varieties+"_"+buy_goods_spec+"_"+buy_goods_origin).val(); 
        	if(goodsId){
        		getGoodsById(goodsId);
        		$("#goods_varieties i").removeClass("sel");
        		$(this).addClass("sel");
        	}else{
        		alert("产品不存在，请重新选择");
        		$("#buy_sku_id").val(""); 
        	}
    	}
	});
	
	$("#goods_spec i").click(function(){
		var select_goods_spec=$(this).attr("dataId");
		var buy_goods_spec = $("#buy_goods_spec").val();
    	if(select_goods_spec == buy_goods_spec){
    		return;
    	} 	
    	var buy_goods_shipping = $("#buy_goods_shipping").val(); 
    	var buy_goods_varieties = $("#buy_goods_varieties").val(); 
    	var buy_goods_origin = $("#buy_goods_origin").val(); 
    	$("#buy_goods_spec").val(select_goods_spec); 
    	if(buy_goods_shipping&&buy_goods_varieties&&buy_goods_origin){
    		var goodsId = $("#buy_goods_"+buy_goods_shipping+"_"+buy_goods_varieties+"_"+select_goods_spec+"_"+buy_goods_origin).val(); 
        	if(goodsId){
        		getGoodsById(goodsId);
        		$("#goods_spec i").removeClass("sel");
        		$(this).addClass("sel");
        	}else{
        		alert("产品不存在，请重新选择");
        		$("#buy_sku_id").val(""); 
        	}
    	}
	});
	
	$("#goods_origin i").click(function(){
		var select_goods_origin=$(this).attr("dataId");
		var buy_goods_origin = $("#buy_goods_origin").val();
    	if(select_goods_origin == buy_goods_origin){
    		return;
    	} 	
    	var buy_goods_shipping = $("#buy_goods_shipping").val(); 
    	var buy_goods_varieties = $("#buy_goods_varieties").val(); 
    	var buy_goods_spec = $("#buy_goods_spec").val(); 
    	$("#buy_goods_origin").val(select_goods_origin); 
    	if(buy_goods_shipping&&buy_goods_varieties&&buy_goods_spec){
    		var goodsId = $("#buy_goods_"+buy_goods_shipping+"_"+buy_goods_varieties+"_"+buy_goods_spec+"_"+select_goods_origin).val(); 
        	if(goodsId){
        		getGoodsById(goodsId);
        		$("#goods_origin i").removeClass("sel");
        		$(this).addClass("sel");
        	}else{
        		alert("产品不存在，请重新选择");
        		$("#buy_sku_id").val(""); 
        	}
    	}
	});
});