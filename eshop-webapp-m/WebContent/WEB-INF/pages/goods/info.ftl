<!DOCTYPE HTML>
<html>
<head>
	<title>商品详情页</title>
	<meta name="keywords" content="商品详情页"/> 
	<meta name="description" content="商品详情页"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<link href="/css/goods.css" rel="stylesheet" />
</head>
<body style="background: #F2EFEF;" >
<#include "/global/global_back.ftl" />
<section class="content" >
  <div class="active" id="h5v0">
    <div id="J_detail">
      <div id="J_detail_main">
		    <div id="dt-slider" class="dt-slider">
	          <div class="dt-slider-cont" style="width: 319px; -webkit-transform: translateZ(0px);">
	            <ul class="dt-slct-ul" style="-webkit-transform: translate3d(0px, 0px, 0px); -webkit-backface-visibility: hidden; width: 750px;">
	            	<#--<li><img original="${imageHost}/${goodsImage.imagePath!}${goodsImage.picListImage!}" class="lazyimg picListImageTag"></li>-->
					<li><img src="/images/goods/xia1.jpg" class="lazyimg picListImageTag"></li>
					<li><img src="/images/goods/xia2.jpg" class="lazyimg picListImageTag"></li>
					<#-- <li><img src="/images/goods/xia3.jpg" class="lazyimg picListImageTag"></li> -->
	            </ul>
	            <div class="dt-slider-mask"></div>
	          </div>
	          <div class="dt-slider-status">
	          	<i class="sel"></i>
	          	<i></i>
	          </div>
	        </div>
	          
        <div class="dt-info" data-spm="info">
          <h1 id="goodsNameStrong" class="dtif-h">
		${goodsInfoVo?if_exists.main_goods?if_exists.goods_name?if_exists}
		<span style="float:right;color:#f00;" id="goods_price_span" >
			￥${goodsInfoVo?if_exists.main_goods?if_exists.sale_price?if_exists}/斤
		</span>
	</h1>
          
          <ul class="d-cul">
            <li>
              <label>配送时间</label>
              <#--<span style="float:right;">${goodsInfoVo?if_exists.main_goods?if_exists.goods_shipping_name?if_exists}</span>-->
           	</li>
           	<hr/>
           	<#if (goodsInfoVo.goods_shipping)??&&(goodsInfoVo.goods_shipping?size>0)>
           	<p class="dgsc-pc" id="goods_shipping">
           		<#list goodsInfoVo.goods_shipping as shipping>
           			<i class="d <#if goodsInfoVo.main_goods.goods_shipping = shipping.attr_code>sel</#if> shipping" dataid="${shipping?if_exists.attr_code?if_exists}">${shipping?if_exists.attr_name?if_exists}</i>
           		</#list>
           	</p>
			</#if>
           	
            <li class="dic-fli">
              <label>品类</label>
              <#--<span style="float:right;">${goodsInfoVo?if_exists.main_goods?if_exists.goods_varieties_name?if_exists}</span>-->
            </li>
            <hr/>
            <#if (goodsInfoVo.goods_varieties)??&&(goodsInfoVo.goods_varieties?size>0)>
           	<p class="dgsc-pc" id="goods_varieties">
           		<#list goodsInfoVo.goods_varieties as varieties>
           			<i class="d <#if goodsInfoVo.main_goods.goods_varieties = varieties.attr_code>sel</#if> varieties" dataid="${varieties?if_exists.attr_code?if_exists}">${varieties?if_exists.attr_name?if_exists}</i>
           		</#list>
           	</p>
			</#if>

            <li class="dic-fli">
              <label>产品规格</label>
              <#--<span style="float:right;">${goodsInfoVo?if_exists.main_goods?if_exists.goods_spec_name?if_exists}</span>-->
  			</li>  
            <hr/>
            <#if (goodsInfoVo.goods_spec)??&&(goodsInfoVo.goods_spec?size>0)>
           	<p class="dgsc-pc" id="goods_spec">
           		<#list goodsInfoVo.goods_spec as spec>
           			<i class="d <#if goodsInfoVo.main_goods.goods_spec = spec.attr_code>sel</#if> spec" dataid="${spec?if_exists.attr_code?if_exists}">${spec?if_exists.attr_name?if_exists}</i>
           		</#list>
           	</p>
			</#if>


           
          </ul>
        </div>
        <div class="dgsc-pr">
          <div class="dgscp-c">
            <span class="bd" >购买数量</span>
            <div class="nums" style="right:24px;"> <span id="decCount" class="minus J_updateQuantity">-</span>
            	<input type="hidden" value="${goodsInfoVo?if_exists.main_goods?if_exists.id?if_exists}" id="buy_sku_id"/>
				<input type="hidden" value="${goodsInfoVo?if_exists.main_goods?if_exists.goods_shipping?if_exists}" id="buy_goods_shipping"/>
				<input type="hidden" value="${goodsInfoVo?if_exists.main_goods?if_exists.goods_varieties?if_exists}" id="buy_goods_varieties"/>
				<input type="hidden" value="${goodsInfoVo?if_exists.main_goods?if_exists.goods_spec?if_exists}" id="buy_goods_spec"/>
				<input type="hidden" value="${goodsInfoVo?if_exists.main_goods?if_exists.goods_origin?if_exists}" id="buy_goods_origin"/>
				<#if (goodsInfoVo.relate_goods)??&&(goodsInfoVo.relate_goods?size>0)>
					<#list goodsInfoVo.relate_goods as relate>
						<input type="hidden" value="${relate?if_exists.id?if_exists}" id="buy_goods_${relate?if_exists.goods_shipping?if_exists}_${relate?if_exists.goods_varieties?if_exists}_${relate?if_exists.goods_spec?if_exists}_${relate?if_exists.goods_origin?if_exists}"/>
					</#list>
				</#if>
              	<input id="goodsCount" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')" type="text" data-default="20" value="${min_num}" class="input-nums c-form-txt-normal J_quantity"/>
              	<span id="addCount" class="plus  J_updateQuantity">+</span> 
            </div>
            <div style=" position: absolute;right: 5px;top: 12px;">${goodsInfoVo?if_exists.main_goods?if_exists.goods_unit?if_exists}</div>
          </div>
			
        </div>
        
        <div class="dt-content">
          <ul class="dtct-ul">
            <li class="liChange" index="0" style="color:#ffffff;" id="go_buy">立即购买</li>
            <#--<li  class="liChange" index="1" style="opacity: 1;">产品参数</li>
            <li class="liChange" index="2">产品评价</li>-->
          </ul>
        </div>

      </div>
    </div>
  </div>
</section>
<div class="mask" id="loading"><img src="/images/loading-grey.gif" style="position: fixed;_position:absolute;margin-left:-20px;left:50%;top:45%;"></div>
<script type="text/javascript">var min_num = ${min_num} </script>
<script type="text/javascript" src="/js/common/zepto.min.js"></script>
<script type="text/javascript" src="/js/sea/sea.js"></script>
<script type="text/javascript" src="/js/common/slider.js"></script>
<script type="text/javascript" src="/js/goods/goods.js"></script>
<script type="text/javascript">
	seajs.config({ 
	  plugins: ['shim'], 
	  alias: {
	    'zepto': {
	      src: '/js/common/zepto.min.js',
	      exports: '$'
	    }
	  }
	}); 
	seajs.use('/js/goods/goods.js');
</script>
</body>
</html>
