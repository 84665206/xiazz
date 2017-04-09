<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/metro.css" rel="stylesheet" type="text/css" />
<script src="js/ztree/jquery.ztree.all.min.js"  type="text/javascript"></scrip>
<script src="js/json-serialize.js"  type="text/javascript"></script>
<script src="js/browser.js"  type="text/javascript"></script>
<script type="text/javascript">

	function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_a");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button remove' id='removeBtn_" + treeNode.tId
              + "' title='删除' onclick='removeTreeNode();'></span>";

        addStr += "<span class=\"button add\" title=\"新增\" onclick=\"addTreeNode();\"  id=\"addBtn_" + treeNode.tId + "\"></span>";
        addStr += "<span class='button refresh' title='恢复树' onclick='resetTree();' id='editBtn_" + treeNode.tId + "'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
            //var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            //zTree.addNodes(treeNode, {id:(1000 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
            return false;
        });
    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
        $("#removeBtn_"+treeNode.tId).unbind().remove();
        $("#editBtn_"+treeNode.tId).unbind().remove();
    };

    var nodeNum = ${menus?size} + 1;
	var zNodes = [
	<#list menus as m > {
	id: "${m.id!}",
	parent_id: "${m.parent_id!0}",
	<#if ((m.menu_type??) && (m.menu_type=1) && (m.menu_level??))>font:{'color':'#2e8965'},</#if>
	nocheck: true,
	ch_name: "${m.ch_name!}",
	en_name: "${m.en_name!}",
	menu_uri: "${m.menu_uri!}",
	sort_index: "${m.sort_index!}",
	menu_type: "${m.menu_type!}",
	description: "${m.description!}",
	menu_level: "${m.menu_level!}"
	},</#list>];
    var setting = {
        view: {
			fontCss: getFont,
            dblClickExpand: false,
            showLine: true,
			addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true,
				idKey : "id",
				pIdKey : "parent_id",
				rootPId : 0
            },
			key : {
			    name : "ch_name",
			    title : "menu_uri"
			},
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {
                "Y": "ps",
                "N": "s"
            }
        },
        edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false
		},
        callback: {
    		onMouseDown: zTreeOnMouseDown,
            onClick: onClick,
			onRightClick: onRightClick,
            beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			onDrop: onDrop
        }
    };
			
    function getFont(treeId, node){
        return node.font ? node.font : {};
    }
    
    function zTreeOnMouseDown(event, treeId, treeNode) {
    	if(treeNode) fillMenuInfo(treeNode);
    };
    
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i < l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		return targetNode ? targetNode.drop !== false : true;
	}
	function onDrop(event, treeId, treeNodes, targetNode, moveType) {
		for(var i=0,len = treeNodes.length; i < len; i++) {
		    if(moveType === "inner")  {
				treeNodes[i].parent_id = targetNode.id;
			} else {
				treeNodes[i].parent_id = targetNode.parent_id;
			}
		    fillMenuInfo(treeNodes[i]);
		}
	};
    		
			
    function onClick(e, treeId, treeNode){
        var zTree = $.fn.zTree.getZTreeObj("menusTree");
        zTree.expandNode(treeNode);
        
        if(treeNode.id == 0) treeNode = {};
    }
    function fillMenuInfo(treeNode) {
		$('#menu_id').val(treeNode.id || treeNode.id4Dependence);
		$('#menu_ch_name').val(treeNode.ch_name);
		$('#menu_en_name').val(treeNode.en_name);
		$('#menu_menu_uri').val(treeNode.menu_uri); 
		$('#menu_parent_id').val(treeNode.parent_id || '');
		$('#menu_sort_index').val(treeNode.sort_index);
		$('#menu_type').val(treeNode.menu_type);
		$('#menu_description').val(treeNode.description);
		$('#menu_level').val(treeNode.menu_level);
        $("input[name=operate_type][value="+treeNode.operate_type+"]").prop("checked",true);
        $("input[name=is_public][value="+treeNode.is_public+"]").prop("checked",true);
    }

    function onRightClick(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
			zTree.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		}
	}

	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_add").hide();
			$("#m_del").hide();
		} else {
			$("#m_add").show();
			$("#m_del").show();
		}
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

		$("body").bind("mousedown", onBodyMouseDown);
	}
	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
	var addCount = 1;
	function addTreeNode() {
		hideRMenu();
		var selectedNode0 = zTree.getSelectedNodes()[0];
		if(!selectedNode0) {
			BootstrapDialog.alert("请选中一个节点，然后再添加！");
			return;
		}
		nodeNum++;
		var currentNode = {parent_id: selectedNode0.id,  ch_name:"(新节点)" };
		zTree.addNodes(selectedNode0, [currentNode]);
		fillMenuInfo(currentNode);
	}
	function removeTreeNode() {
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		if(nodes && nodes[0].id == 0){
			BootstrapDialog.alert("不能删除根节点");
			return;
		}
		if (nodes && nodes.length>0) {
			if (nodes[0].children && nodes[0].children.length > 0) {
				bootbox.alert("当前功能菜单下有子菜单，请先删除子菜单后再操作!");
			} else {
				deleteNode($('#menu_id').val().replace(/,/, ''));
			}
		}
	}
	function checkTreeNode(checked) {
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length>0) {
			zTree.checkNode(nodes[0], checked, true);
		}
		hideRMenu();
	}
	function resetTree() {
		hideRMenu();
		$.fn.zTree.init($("#menusTree"), setting, zNodes);
	}

	var zTree, rMenu;
    $(document).ready(function(){
        $.fn.zTree.init($("#menusTree"), setting, zNodes);
        
        zTree = $.fn.zTree.getZTreeObj("menusTree");
        bindDependence();
		zTree.expandAll(false); // 收缩菜单
		//zTree.expandNode(zTree.getNodeByTId("menusTree_1"), true); // 收缩菜单
        
		rMenu = $("#rMenu");
		
		$("input[name=operate_type][value=0]").prop("checked",true);
		$("input[name=is_public][value=0]").prop("checked",true);
		
		  	
	});
    

    function bindDependence(zNode) {
    	if(zNode == null) {
    		bindDependence(zTree.getNodes()[0]);
    		return;
    	}
        if(zNode.dependence && !zNode.id4Dependence) {
    		var deps = zNode.dependence.split(/,/);
     		for(var j = 0, l = deps.length - 1; j < l; j++ ) {
     			var tmp = jQuery.extend({}, zNode);
     			tmp.id4Dependence = tmp.id;
     			tmp.id = undefined;
     			tmp.font = {"color": "#aaa"};
     			var p = zTree.getNodeByParam("id", deps[j], null);
     			zTree.addNodes(p, tmp);
     			nodeNum++;
     		}
    	}
        
    	if(zNode.children && zNode.children.length) {
    		for(var i = 0, len = zNode.children.length; i < len; i++) {
    			if(zNode.children[i]) {
    				bindDependence(zNode.children[i]);
    			}
    		}
    	}
    	
    }
				
  //新增菜单节点
  function beforeSubmit(){
    if($("#menu_ch_name").val()==''){
       $("#errorMsg").html("<i class='icon-warning-sign'></i>功能名称不能为空");
       return false;
    }
    if($("#menu_sort_index").val()==''){
       $("#errorMsg").html("<i class='icon-warning-sign'></i>功能顺序不能为空");
       return false;
    }
  }
          
  function afterSubmit(responseText, statusText){
	 if(statusText=='success'){
	   if(responseText.code=='200'){
	     bootbox.alert(responseText.msg);
	     pageContentLoad('sys/menu/list.do');
	   }else{
	     bootbox.alert(responseText.msg);
	     pageContentLoad('sys/menu/list.do');
	   }
	 }else{
	   bootbox.alert(responseText.msg);
	 }
  }
		  
		 
  function deleteNode(menuId) {
  	bootbox.confirm('确定删除这个菜单吗？？', function(result){
    if(result) {
	     $.ajax({
				type:'get',
				url:'sys/menu/delete.do',
				data:{'id':menuId} ,
				success:function(data) {  
			    if(data.code =="200" ){  
			       bootbox.alert(data.msg); 
			       pageContentLoad('sys/menu/list.do');
				}else{  
				   bootbox.alert(data.msg); 
  				}  
				} ,
					dataType: 'json'
			  	}); 
				}else{
			}     
      });
   }
   
</script>

 <div class="mainWrap">
            <table class="outter">
                <tr>
                    <td class="td_tree">
                        <ul id="menusTree" class="ztree"></ul>
                    </td>
                    <td class="td_tree right">
                    	<div class="col-md-offset-3 col-md-9">
                    		<h4 class="green">新增/修改功能信息操作<span class="help-button" data-rel="popover" data-trigger="hover" data-placement="left" data-content="More details." title="" data-original-title="Popover on hover">?</span></h4>
                    	</div>
                    	<form class="form-horizontal" id="sysMenu_form" action="sys/menu/save.do" method="post" ajaxForm="true" beforeSubmit="beforeSubmit" afterSubmit="afterSubmit">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"> 菜单ID </label>
								<div class="col-sm-9">
									<input type="text" readonly="" name="id" id="menu_id" placeholder="选中的功能的ID" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 父ID </label>
								<div class="col-sm-9">
									<input type="text" readonly="" name="parent_id" id="menu_parent_id" placeholder="选中的功能的父ID" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 功能名称 </label>
								<div class="col-sm-9">
									<input type="text" name="ch_name" id="menu_ch_name" placeholder="输入功能名称" class="form-control" />
								</div>
							</div>
                            <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 功能地址 </label>
								<div class="col-sm-9">
									<input type="text" name="menu_uri" id="menu_menu_uri" placeholder="输入功能地址的相对路劲" class="form-control" />
								</div>
							</div>
                            <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">功能类型</label>
							    <div class="col-sm-9">
							      <select class="form-control" name="menu_type" id="menu_type">
							      	<option value="-1">请选择...</option>
							      	<option value="1">菜单项</option>
									<option value="2">功能项</option> 
							      </select>
							    </div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">功能级别</label>
							    <div class="col-sm-9">
							      <select class="form-control" name="menu_level" id="menu_level">
							      	<option value="-1">请选择...</option>
							      	<option value="1">一级菜单</option>
									<option value="2">二级菜单</option>  
									<option value="3">三级菜单</option> 
							      </select>
							    </div>
							</div>
                            <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">显示顺序 </label>
								<div class="col-sm-9">
									<input type="text" name="sort_index" id="menu_sort_index" placeholder="功能在菜单栏显示的顺序" class="form-control" />
								</div>
							</div>
                            
							<#--<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">公共菜单</label>
								<div class="col-sm-7">
									<label>
										<input name="is_public" type="radio" value="0" class="ace" />
										<span class="lbl">否</span>
									</label>
									<label>
										<input name="is_public" type="radio" value="1" class="ace" />
										<span class="lbl">是</span>
									</label>
								</div>				
							</div>-->
							<#--<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">功能类型</label>
								<div class="col-sm-7">
									<label>
										<input name="operate_type" type="radio" value="0" class="ace" />
										<span class="lbl">查询</span>
									</label>
									<label>
										<input name="operate_type" type="radio" value="1" class="ace" />
										<span class="lbl">修改</span>
									</label>
								</div>				
							</div>-->
							
                            <div class="form-group">
                            	<label class="col-sm-3 control-label no-padding-right" for="form-field-1">功能描述</label>
                            	<div class="col-sm-9">
									<textarea name="description" id="menu_description" class="form-control" placeholder="输入功能描述及作用"></textarea>
								</div>
							</div>
							<div class="col-md-offset-3 col-md-9">
	                            <button class="btn btn-success" type="submit">
									<i class="icon-ok bigger-110"></i>
									保存
								</button>
								<ul class="list-unstyled spaced2">
									<li class="text-warning bigger-130 red" id="errorMsg"></li>
								</ul>								
							</div>
                      </form>
                    </td>
                </tr>
            </table>
        </div>
		 <div id="rMenu">
			<ul>
				<li id="m_add" onclick="addTreeNode();">增加节点</li>
				<li id="m_del" onclick="removeTreeNode();">删除节点</li>
				<li id="m_reset" onclick="resetTree();">恢复zTree</li>
			</ul>
		</div>
		
	   <form id="assignSuperUser_form" action="sys/menu/assign/role.do" method="post" ajaxForm="true">
	   </form>