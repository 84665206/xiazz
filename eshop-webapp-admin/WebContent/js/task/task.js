var taskCmd={
		doCmd:function(opType,name,group){
			var param={};
			param.opType=opType;
			param.name=name;
			param.group=group;
			$.post(ctx+"/task/operatorTrigger.action",param,function(data){
					alert(data.msg);
					$("#allTriggers").datagrid('reload');  
			},"json");
		},
		TimeFormatter: function (value, rec, index) {
	        if (value == undefined) {
	            return "";
	        }
	        return new Date(parseInt(value)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");   
		},
		cmdFormatter:function(val,row){
			return '<input type="button" id="resume" value="开始"  onclick="taskCmd.doCmd(\'resume\',\''+row.name+'\',\''+row.group+'\')">&nbsp;<input type="button" id="pause" value="暂停"  onclick="taskCmd.doCmd(\'pause\',\''+row.name+'\',\''+row.group+'\')">&nbsp;<input type="button" id="remove" value="删除"  onclick="taskCmd.doCmd(\'remove\',\''+row.name+'\',\''+row.group+'\')">&nbsp;<input type="button" id="runNow" value="运行"  onclick="taskCmd.doCmd(\'runNow\',\''+row.name+'\',\''+row.group+'\')">';
		}
}