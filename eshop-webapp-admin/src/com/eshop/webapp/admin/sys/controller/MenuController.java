package com.eshop.webapp.admin.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.base.ResponseResult;
import com.eshop.webapp.admin.enums.ResponseResultEnum;
import com.eshop.webapp.admin.sys.model.Menu;
import com.eshop.webapp.admin.sys.model.Role;
import com.eshop.webapp.admin.sys.service.IMenuService;
import com.eshop.webapp.admin.sys.service.IRoleService;

/**
 * 菜单管理
 */
@Controller
@RequestMapping("sys")
public class MenuController extends BaseController {
	
	@Autowired
	private IMenuService menuService;

	@Autowired
	private IRoleService roleService;
	
	
	/**
	 * 进入菜单管理页
	 * @param model
	 * @return
	 */
	@RequestMapping("menu/list")
	public String listMenu(Model model) {
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = menuService.getAllMenuList();
		
		model.addAttribute("menus", menuList);
		return "sys/menumgt";
	}
	
	/**
	 * 新增、修改菜单
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("menu/save")
	public ResponseResult saveMenu(@RequestParam(value="id", required=false)Integer id ,
						   @RequestParam(value="parent_id", required=false)Integer parent_id ,
						   @RequestParam(value="menu_uri", required=false)String menu_uri ,
						   @RequestParam(value="ch_name", required=false)String ch_name ,
						   @RequestParam(value="menu_type", required=false)Integer menu_type ,
						   @RequestParam(value="menu_level", required=false)Integer menu_level ,
						   @RequestParam(value="sort_index", required=false)Integer sort_index ,
						   @RequestParam(value="description", required=false)String description ,
						   Model model
			){
		ResponseResult responseResult =new ResponseResult();
		try {
			Menu menu = new Menu();
//			if(StringUtils.isEmpty(menu_uri)){
//				responseResult.setCode(ResponseResultEnum.FAIL.code);
//				responseResult.setMsg("功能地址不能为空");
//				return responseResult;
//			}
			menu.setId(id);
			menu.setMenu_uri(menu_uri);
			menu.setEn_name(menu_uri);
			menu.setCreate_user(this.getCurrentUsername());
			
			if(StringUtils.isEmpty(ch_name)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("功能名称不能为空");
				return responseResult;
			}
			menu.setParent_id(parent_id);
			menu.setCh_name(ch_name);
			menu.setMenu_type(menu_type);
			menu.setMenu_level(menu_level);
			menu.setCreate_user(getCurrentUsername());
			menu.setUpdate_date(new Date());
			menu.setUpdate_user(getCurrentUsername());
			menu.setSort_index(sort_index);
			menu.setDescription(description);
			menu.setIs_delete(0);
			String result = menuService.addMenu(menu,null);
			
			if("SUCCESS".equals(result)){
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("保存成功");
				return responseResult;
			}else{
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("保存失败");
				return responseResult;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("操作失败，请稍后再试!");
			return responseResult;
		}
	}
	
	/**
	 * 删除菜单
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("menu/delete")
	public ResponseResult deleteMenu(@RequestParam(value="id", required=false)Integer id){
		ResponseResult responseResult = new ResponseResult();
		try {
			String result = menuService.deleteMenuById(id);
			if("SUCCESS".equals(result)){
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("删除成功！");
				return responseResult;
			}else{
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("删除失败！");
				return responseResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("删除异常，稍后再试！");
			return responseResult;
		}
	}
	
	//获取所有角色信息列表
	@ResponseBody
	@RequestMapping("searchAllRoles")
	public Map<String,Object> searchAllRoles(){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Role> allRoleList = roleService.listAllRoles();
		if(allRoleList == null){
			allRoleList = new ArrayList<Role>();
		}
		resultMap.put("status", "success");
		resultMap.put("msg", "角色信息查询成功！");
		resultMap.put("data", allRoleList);
		return resultMap;
	}
}
