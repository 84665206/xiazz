<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "common/taglib.ftl"> 
	<title>罗莱O2O后台支撑系统</title>
	<script type="text/javascript" src="js/tabs.js"></script>
</head>

<body class="easyui-layout" >
<div region="north" style="height:90px;">
	<div class="topbanner">
		<img src="images/bsui_01.png" class="left"/>
		<img src="images/bsui_05.png" class="right"/>
	</div>
	<ul class="topnav">
		<#list myMenuList as menu><#if menu.level == 1 >
		<li id="${menu.id!}">
			<a>${menu.chName!}</a>
		</li>
		</#if></#list>
		<#--
		<p class="right welcome">
			<span> 欢迎您，${current_user.fullName!}(${current_user.userName!})  </span>
			<a href="${ctx}/logout.action"> <img src="${staticHost}/images/bsui_14.png"> 注销 </a>
		</p>
		-->
	</ul>
</div>

<div region="west" id="navWest" split="true" style="width:230px;overflow-x:hidden;overflow-y:auto;" title='<label style="width:95px;display:inline-block;">欢迎您，${userName!}</label><a href="sys/enterModifyPasswordPage.do" target="_blank" style="margin-right:5px;">[修改密码]</a><a href="logout.do">[注销]</a>'>
    <div id="aside"></div>
</div>

<div region="center" split="true" >
	<div region="center" id="tabs" class="easyui-tabs" fit="true" border="false"  >
        <div title="欢迎使用">
        	<div style="margin:10px 10px">
        		<h3>欢迎使用罗莱O2O支撑系统</h3>
        	</div>
        </div>
	</div>
</div>

<div id="menu" class="easyui-menu" style="width:150px;">
    <div id="m-refresh">刷新</div>
    <div class="menu-sep"></div>
    <div id="m-close">关闭本窗口</div>
    <div class="menu-sep"></div>
    <div id="m-closeother">关闭其他窗口</div>
    <div id="m-closeall">关闭所有窗口</div>
</div>

<script type="text/javascript">
	var menus = [{}
	    <#list myMenuList! as m >
	    ,{
	    	id: ${m.id},
	    	parentId: ${m.parentId!'undefined'},
	    	chName: "${m.chName!'(no name)'}",
	    	status: ${m.status!'undefined'},
	    	level: ${m.level!'undefined'},
	    	menuUri: <#if m.menuUri??>"${m.menuUri}"<#else>undefined</#if>,
	    	sortIndex : ${m.sortIndex!1000000000}
	    }
	    </#list>
	];
	menus.splice(0, 1);
	
	/* 填充右侧二级菜单 */
	$(function(){
		for(var i=0; i < menus.length; i++) {
			if(menus[i].status == 1){
				switch(menus[i].level) {
				case 1:	
					$('<ul class="" style="display:none;" />').attr("id", menus[i].id+"_sub").appendTo($('#aside'));
					break;
				case 2:
					$('#' + menus[i].parentId + "_sub").append(
						$('<li class="outli" ></li>').attr("id", menus[i].id).append(
							$('<span/>').text(menus[i].chName)
						).append(
							$('<ul/>').attr("id", menus[i].id + "_sub")
						)
					);
					break;
				case 3:
					$('#' + menus[i].parentId + "_sub").append(
						$('<li />').attr("id", "li_" + menus[i].id).append(
							$('<a iframeFlg="true" href="#" />').attr("rel", "/" + menus[i].menuUri).text(menus[i].chName)
						)
					);
					break;
				default:
				}
			}
		}
	});
	
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
	
	/*左侧菜单slide*/
	$(function(){
		//设置第一个二级菜单可见
		$("div ul:first-child").css({display:"block"});
		$(".topnav li:first-child").addClass("active")
		
	    //手风琴效果
		$("#aside .outli span").toggle(function(){
		       $(this).addClass("off");
			   $(this).siblings("ul").slideUp("fast");
				},function(){
				$(this).removeClass("off");
				$(this).siblings("ul").slideDown("fast");});
	
		$("#aside .outli li").click(function(){
		       $(this).addClass("active").siblings().removeClass("active");
		});
	
	    //上部菜单和左侧菜单联动
		$(".topnav li").click(function(){
				var li$ = $(this);
				var selectId = li$.attr("id");
		        li$.addClass("active").siblings().removeClass("active");
		        var selectWestNavId="#"+selectId+"_sub";
		        //alert(selectWestNavId);
		        $(selectWestNavId).closest("div").find(">ul").each(function(){$(this).hide();});
				$("#"+selectId+"_sub").show();
		});
	});
</script>
</body>
</html>
