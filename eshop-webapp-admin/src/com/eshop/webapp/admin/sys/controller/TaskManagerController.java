package com.eshop.webapp.admin.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.base.ResponseResult;
import com.eshop.webapp.admin.enums.ResponseResultEnum;
import com.eshop.webapp.admin.util.PageResponse;



/**
 * 定时任务管理（列表、启动、暂停）
 * @author yangmeng
 *
 */
@Controller
@RequestMapping("sys")
public class TaskManagerController extends BaseController {
	
	@SuppressWarnings("rawtypes")
	private List triggerList;
	@Autowired
	private Scheduler scheduler;
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/* 操作类型 */
	private String opType;
	/* 任务名称 */
	private String name;
	/* 组别 */
	private String group;
	
	private PageResponse<Trigger> triggerResultPage;
	
	/** 
	 * @Description: 进入定时任务监控页面
	 * @param @return    
	 * @return String 
	 * @throws 
	 */ 
	@RequestMapping("task/page")
	public String taskmgt(Model model)  throws SchedulerException{
		try {
			String[] triggerNames = this.scheduler
					.getTriggerNames(Scheduler.DEFAULT_GROUP);
			Arrays.sort(triggerNames);
			triggerList = new ArrayList();
			for (String name : triggerNames) {
				CronTrigger trig = (CronTrigger) this.scheduler.getTrigger(name,
						Scheduler.DEFAULT_GROUP);
				int status = this.scheduler.getTriggerState(name,
						Scheduler.DEFAULT_GROUP);
				String stat = null;
				switch (status) {
				case 0:
					stat = "运行中...";
					break;
				case 1:
					stat = "暂停";
					break;
				case 2:
					stat = "正在执行";
					break;
				default:
					stat = "" + status;
				}
				Map<String,Object> triggerMap=new HashMap<String,Object>();
				triggerMap.put("name", trig.getName());
				triggerMap.put("cronExpression", trig.getCronExpression());
				triggerMap.put("previousFireTime", trig.getPreviousFireTime());
				triggerMap.put("nextFireTime", trig.getNextFireTime());
				triggerMap.put("description", trig.getDescription());
				triggerMap.put("group", trig.getGroup());
				triggerMap.put("status",stat);
				triggerList.add(triggerMap);
			}
			
			model.addAttribute("triggerList", triggerList);
		} catch (Exception e) {
			
		}
		
		
		return "task/taskmgt";
	}

	/**
	 * @Description: 定时任务列表
	 * @param @return
	 * @param @throws SchedulerException
	 * @return String
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping("task/list")
	public ResponseResult list() throws SchedulerException {
		ResponseResult responseResult = new ResponseResult();
		String[] triggerNames = this.scheduler
				.getTriggerNames(Scheduler.DEFAULT_GROUP);
		Arrays.sort(triggerNames);
		triggerList = new ArrayList();
		for (String name : triggerNames) {
			CronTrigger trig = (CronTrigger) this.scheduler.getTrigger(name,
					Scheduler.DEFAULT_GROUP);
			int status = this.scheduler.getTriggerState(name,
					Scheduler.DEFAULT_GROUP);
			String stat = null;
			switch (status) {
			case 0:
				stat = "运行中...";
				break;
			case 1:
				stat = "暂停";
				break;
			case 2:
				stat = "正在执行";
				break;
			default:
				stat = "" + status;
			}
			Map<String,Object> triggerMap=new HashMap<String,Object>();
			triggerMap.put("name", trig.getName());
			triggerMap.put("cronExpression", trig.getCronExpression());
			triggerMap.put("previousFireTime", trig.getPreviousFireTime());
			triggerMap.put("nextFireTime", trig.getNextFireTime());
			triggerMap.put("description", trig.getDescription());
			triggerMap.put("group", trig.getGroup());
			triggerMap.put("status",stat);
			triggerList.add(triggerMap);
//			triggerResultPage=new Page<Trigger>();
//			triggerResultPage.setResult(triggerList);
//			triggerResultPage.setTotalItems(triggerList.size());
		}
		
//		responseResult.setCode(ResponseResultEnum.SUCCESS.code);
//		responseResult.setData(triggerResultPage);
		
		return responseResult;
	}

	/**
	 * 操作定时任务
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@ResponseBody
	@RequestMapping("task/oprate")
	public ResponseResult oprate()  {
		ResponseResult responseResult = new ResponseResult();
		try {
			if ("resume".equals(opType)) {
				this.resumeTrigger();
			} else if ("pause".equals(opType)) {
				this.pauseTrigger();
			} else if ("remove".equals(opType)) {
				this.removeTrigdger();
			} else {
				this.runNowTrigger();
			}
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
		} catch (SchedulerException e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.EXCEPTION.code);
			responseResult.setMsg("操作异常!");
		}
		return responseResult;
	}

	/**
	 * @Description: 根据名称和组别暂停Tigger
	 * @param @throws SchedulerException
	 * @return void
	 * @throws
	 */
	private void pauseTrigger() throws SchedulerException {
		this.scheduler.pauseTrigger(name, group);
		String msg = String.format("trigger(group=%s, name=%s)已暂停", group, name);
		System.out.println(msg);
	}

	/**
	 * @Description: 根据名称和组别开始Tigger
	 * @param @throws SchedulerException
	 * @return void
	 * @throws
	 */
	private void resumeTrigger() throws SchedulerException {
		this.scheduler.resumeTrigger(name, group);
		String msg = String.format("trigger(group=%s, name=%s)已开始", group, name);
		System.out.println(msg);
	}

	/**
	 * @Description: 根据名称和组别删除Tigger
	 * @param @throws SchedulerException
	 * @return void
	 * @throws
	 */
	private void removeTrigdger() throws SchedulerException {
		this.scheduler.pauseTrigger(name, group);// 停止触发器
		boolean rs = this.scheduler.unscheduleJob(name, group);// 移除触发器
		if (rs) {
			String msg = String.format("trigger(group=%s, name=%s)已删除", group, name);
			System.out.println(msg);
		} else {
			String msg = String.format("trigger(group=%s, name=%s)删除失败", group, name);
			System.out.println(msg);
		}
	}

	/**
	 * @Description: 根据名称立即执行
	 * @param @throws SchedulerException
	 * @return void
	 * @throws
	 */
	private void runNowTrigger()   {
		try {
			CronTrigger trig = (CronTrigger) this.scheduler.getTrigger(name,Scheduler.DEFAULT_GROUP);
			trig.getTriggerListenerNames();
			this.scheduler.triggerJob(trig.getJobName(), group);
			String msg = String.format("trigger(group=%s,name=%s)正在执行", group, name);
			System.out.println(msg);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
