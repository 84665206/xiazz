<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "/WEB-INF/pages/common/taglib.ftl" />
	<title>罗莱家纺电子商务新版仓库管理系统</title>
	<script type="text/javascript" src="${staticHost}/js/tabs.js"></script>
</head>

<body class="easyui-layout" >
<div region="north" style="height:90px;">
	 <div class="topbanner">
                   <img src="${staticHost}/images/bsui_01.png" class="left"/>
                   <img src="${staticHost}/images/bsui_05.png" class="right"/>
               </div>
               <ul class="topnav">
<!--	               <li id="userMng"> <a> 订单管理 </a> </li>   -->
<!--	               <li id="deliveryMng"> <a> 配发货 </a> </li>  -->
<!--	               <li id="importMng"> <a> 入库管理 </a> </li>  -->
<!--	               <li id="stockMng"> <a> 库存管理 </a> </li>  -->
<!--	               <li id="reportMng"> <a> 报表管理 </a> </li>  -->
<!--	               <li id="interfaceMng"> <a> 接口管理 </a> </li>  -->
	               <li id="baseDataMng"> <a> 主数据管理 </a> </li>  
	               <li id="systemMng"> <a> 系统管理 </a> </li> 
	               <li id="otherMng" class="active"> <a> 扩展功能 </a> </li>
				   <p class="right welcome"><span> 欢迎您，${current_user.fullName!}(${current_user.userName!})  </span> <a href="${ctx}/logout.action"> <img src="${staticHost}/images/bsui_14.png">修改密码 </a> <a href="${ctx}/logout.action"> <img src="${staticHost}/images/bsui_14.png"> 注销 </a>  </p>
               </ul>
</div>

<div region="west" id="navWest" split="true" style="width:220px;" title="导航菜单">
    <div id="aside">

        <ul class="" id="userMng_sub" style="display:none;">
            <li class="outli"><span>订单管理 </span>
                <ul>
                    <li class="active" ><a href="#" rel="${ctx}/order/enterOrderAddPage.action" iframeFlg="true">  新增发货订单</a></li>
                    <li><a href="#" rel="/login.action"> 待处理发货订单</a></li>
                    <li><a> 发货订单列表</a></li>
                </ul>
            </li>
        </ul>

        <ul class="" id="deliveryMng_sub" style="display:none;">
            <li class="outli"><span>配发货 </span>
                <ul>
                    <li><a>待配货订单</a></li>
                    <li><a>拣货单管理</a></li>
                    <li><a>分拣</a></li>
                    <li><a>包装复核</a></li>
                    <li><a>称重出库</a></li>
                    <li><a>出库记录查询</a></li>
                </ul>
            </li>
        </ul>

        <ul class="" id="importMng_sub" style="display:none;">
            <li class="outli"><span>入库单管理 </span>
                <ul>
                    <li><a>采购订单</a></li>
                    <li><a>商品调拨单</a></li>
                    <li><a>客户退换货</a></li>
                    <li><a>入库计划单</a></li>
                    <li><a>收货入库</a></li>
                    <li><a>商品上架</a></li>
                </ul>
            </li>
        </ul>


        <ul class="" id="stockMng_sub" style="display:none;">
            <li class="outli"><span>库存管理 </span>
                <ul>
                    <li><a>库存查询</a></li>
                    <li><a>库存调整</a></li>
                    <li><a>库存冻结</a></li>
                    <li><a>库存盘点</a></li>
                    <li><a>商品调拨</a></li>
                </ul>
            </li>
        </ul>

        <ul class="" id="reportMng_sub" style="display:none;">
            <li class="outli"><span>报表管理 </span>
                <ul>
                    <li><a>发货订单分析</a></li>
                    <li><a>销售日报制定</a></li>
                </ul>
            </li>
        </ul>

        <ul class="" id="interfaceMng_sub" style="display:none;">
            <li class="outli"><span>接口管理 </span>
                <ul>
                    <li><a>同步官网销售订单</a></li>
                    <li><a>同步商品主数据</a></li>
                    <li><a>上报销售日报</a></li>
                </ul>
            </li>
        </ul>

        <ul class="" id="baseDataMng_sub" style="display:none;">
            <li class="outli" style="display:none;"><span>商品相关信息 </span>
                <ul>
                    <li><a href="#" rel="${ctx}/maindata/listGoods.action">商品分类</a></li>
                    <li><a href="#" rel="${ctx}/maindata/listColors.action">商品颜色</a></li>
                    <li><a href="#" rel="${ctx}/maindata/listSizes.action">商品规格</a></li>
                    <li><a href="#" rel="${ctx}/maindata/listBrands.action">商品品牌</a></li>
                    <li><a href="#" rel="${ctx}/maindata/listGoodsdata.action">商品主数据</a></li>
                </ul>
            </li>
            <li class="outli" style="display:none;"><span>业务代码信息 </span>
                <ul>
                    <li><a href="#" rel="${ctx}/maindata/listBsCodeType.action">代码类型信息</a></li>
                    <li><a href="#" rel="${ctx}/maindata/listBsCode.action">代码信息</a></li>
                </ul>
            </li>
            <li class="outli"><span>仓储相关信息 </span>
                <ul>
                    <li><a href="#" rel="${ctx}/warehouse/enterWarehouse.action">仓库信息</a></li>
                    <li><a href="#" rel="${ctx}/warehouse/enterWarehouseArea.action">库区信息</a></li>
                    <li><a href="#" rel="${ctx}/warehouse/enterLocation.action">货位信息</a></li>
                </ul>
            </li>
        </ul>

        <ul class="" id="systemMng_sub" style="display:none;">
            <li class="outli"><span>用户信息管理 </span>
                <ul>
                    <li><a href="#" rel="${ctx}/sys/enterUserInfo.action" iframeFlg="false">用户信息</a></li>
                    <li><a href="#" rel="${ctx}/sys/enterUserGroup.action">用户组信息</a></li>
                    <li><a href="#" rel="${ctx}/sys/listRole.action">角色信息</a></li>
                    <li><a>权限信息</a></li>
                </ul>
            </li>
            <li class="outli"><span>安全信息管理 </span>
                <ul>
                    <li><a href="#" rel="${ctx}/sys/enterModifyPasswordPage.action">修改密码</a></li>
                    <li><a href="#" rel="${ctx}/sys/enterUserLogPage.action">登陆日志</a></li>
                </ul>
            </li>
            <li class="outli"><span>系统配置信息管理 </span>
                <ul>
                    <li><a>系统代码信息</a></li>
                    <li><a>系统设置</a></li>
                    <li><a>系统帮助</a></li>
                </ul>
            </li>
        </ul>

        <ul class="" id="otherMng_sub" style="display:none;">
        	<li class="outli"><span>商品货位维护</span>
                <ul>
                    <li><a href="#" rel="${ctx}/storage/enterGoodsInStore.action" iframeFlg="true">商品上架维护</a></li>
                    <li><a href="#" rel="${ctx}/storage/enterGoodsUnboxing.action" iframeFlg="true">商品拆箱补货</a></li>
                    <li><a href="#" rel="${ctx}/storage/getGoodsInStoreAndUnboxLog.action" iframeFlg="true">商品上架补货日志</a></li>
                </ul>
            </li>
            <li class="outli"><span>商品库存查询</span>
                <ul>
                    <li><a href="#" rel="${ctx}/storage/enterGoodsStockQtySearch.action" iframeFlg="true">按商品查询库存</a></li>
                    <li><a href="#" rel="${ctx}/storage/enterLocationStockQtySearch.action" iframeFlg="true">按货位查询库存</a></li>
                    <li><a href="#" rel="${ctx}/storage/enterGoodsQtySapCompare.action" iframeFlg="true">与SAP库存比对</a></li>
                </ul>
            </li>
            <li class="outli"><span>其他工具</span>
                <ul>
                    <li><a href="#" rel="${ctx}/efastGoodsAllocation/enterGoodsAllocationSearch.action" iframeFlg="true">商品货位扫描查询</a></li>
                    <li><a href="#" rel="${ctx}/efast/enterReviewJob.action" iframeFlg="true">发货计件统计查询</a></li>
                    <li><a href="#" rel="${ctx}/efast/enterListDeliverGoodsDetail.action" iframeFlg="true">发货明细查询</a></li>
                 	<li><a href="#" rel="${ctx}/report/salesDailyReport.action" iframeFlg="true">销售日报</a></li>
                 	<li><a href="#" rel="${ctx}/efast/listShippedOrder.action" iframeFlg="true">已发货订单查询</a></li>
                 	<li><a href="#" rel="${ctx}/taobao/taobaoSalesAnalysisUserList.action" iframeFlg="true">淘宝会员分析</a></li>
                </ul>
            </li>
        </ul>

    </div>
</div>

<div region="center" split="true" >
	<div region="center" id="tabs" class="easyui-tabs" fit="true" border="false"  >
        <div title="欢迎使用">
        	<div style="margin:10px 10px">
        		<span>对系统使用的问题可以联系如下人员寻求帮助：</span>
        	<ol>
	        	<li>1.罗文 QQ：84665206   电话：18019353818  邮箱：luowen@luolai.com.cn</li>
	        	<li>2.孙晓波 QQ：275297025   电话：13681620658  邮箱：sunxiaobo@luolai.com.cn</li>
        	</ol>
        	<span>问题请尽量在工作日内提出，非工作日只处理紧急问题！</span>
        	<ol>
        		<li>以上会随同人员变化有所调整，请持续关注。</li>
        	</ol>
        	</div>
        </div>
	</div>
</div>

<div id="menu" class="easyui-menu" style="width:150px;">
    <div id="m-refresh">刷新</div>
    <div class="menu-sep"></div>
    <div id="m-closeall">全部关闭</div>
    <div id="m-closeother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="m-close">关闭</div>
</div>




<script type="text/javascript">

	$(function(){
	 	/*为选项卡绑定右键*/
		$(".tabs li").live('contextmenu',function(e){

			/* 选中当前触发事件的选项卡 */
			var subtitle =$(this).text();
			$('#tabs').tabs('select',subtitle);

			//显示快捷菜单
			$('#menu').menu('show', {
				left: e.pageX,
				top: e.pageY
			});

			return false;
		});
	});

	/*打开tab传值*/
	$(function(){
	$("#aside a","#navWest").live("click",function(event){
              //alert($(event.target))
		      var a =this;
			  var title=$(a).text();
			  var url=$(a).attr("rel");
			  var icon=$(a).attr("icon");
			  var iframeFlg = $(a).attr("iframeFlg");
			  if(iframeFlg == "true"){
			  	OpenIFrameTab(title,url,icon);
			  }else{
			  	OpenTab(title,url,icon);
			  }
	  });
	});

    /*初始化左侧默认菜单*/
	function initActiveWestMenu(){
			var selectId = $(".topnav li.active").attr("id");
			var selectWestNav = $("#aside>ul#otherMng_sub");
			selectWestNav.css({display:"block"});
	};


	/*左侧菜单slide*/
	$(function(){

		initActiveWestMenu();
        //手风琴效果
		$("#aside .outli span").toggle(function(){
		       $(this).addClass("off");
			   $(this).siblings("ul").slideUp("fast");
				},function(){
				$(this).removeClass("off");
				$(this).siblings("ul").slideDown("fast");});

		$("#aside .outli li").click(function(){
		       $(this).addClass("active").siblings().removeClass("active");
		})

        //上部菜单和左侧菜单联动
		$(".topnav li").click(function(){
				var li$ = $(this);
				var selectId = li$.attr("id");
		        li$.addClass("active").siblings().removeClass("active");
		        var selectWestNavId="#"+selectId+"_sub";
		        //alert(selectWestNavId);
		        $(selectWestNavId).closest("div").find(">ul").each(function(){$(this).hide()});
				$("#"+selectId+"_sub").show();
		})
	});


</script>
</body>
</html>
