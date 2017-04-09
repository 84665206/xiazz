	<script type="text/javascript">
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
	</script>
	
	<div class="page-header">
		<h1>
			定时任务列表
			<small>
				<i class="icon-double-angle-right"></i>
				暂停&运行&删除
			</small>
		</h1>
	</div><!-- /.page-header -->

	<div class="row">
	<div class="col-xs-12">
	<!-- PAGE CONTENT BEGINS -->
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>任务名称</th>
							<th>任务描述</th>
							<th>Cron表达式</th>
							<th>
								<i class="icon-time bigger-110 hidden-480"></i>
								上次执行时间
							</th>
							<th>
								<i class="icon-time bigger-110 hidden-480"></i>
								下次执行时间
							</th>
							<th>状态</th>
							<th>操作项目</th>
						</tr>
					</thead>

					<tbody>
						<#if triggerList??&&(triggerList?size>0)>
						<#list triggerList as trigger>
						<tr>
							<td>${trigger?if_exists.name?if_exists}</td>
							<td>${trigger?if_exists.description?if_exists}</td>
							<td>${trigger?if_exists.cronExpression?if_exists}</td>
							<td>${trigger?if_exists.previousFireTime?if_exists}</td>
							<td>${trigger?if_exists.nextFireTime?if_exists}</td>
							<td>${trigger?if_exists.status?if_exists}</td>
							<td>
								<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
									<button class="btn btn-xs btn-success">
										<i class="icon-ok bigger-120"></i>开始
									</button>

									<button class="btn btn-xs btn-info">
										<i class="icon-edit bigger-120"></i>
									</button>

									<button class="btn btn-xs btn-danger">
										<i class="icon-trash bigger-120"></i>删除
									</button>

									<button class="btn btn-xs btn-warning">
										<i class="icon-flag bigger-120"></i>立即运行
									</button>
								</div>
							</td>
						</tr>
						</#list>
						</#if>
					</tbody>
				</table>
			</div><!-- /.table-responsive -->
		</div><!-- /span -->
	</div><!-- /row -->