package com.eshop.webapp.admin.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.base.ResponseResult;
import com.eshop.webapp.admin.enums.ResponseResultEnum;
import com.eshop.webapp.admin.sys.model.Menu;
import com.eshop.webapp.admin.sys.model.Role;
import com.eshop.webapp.admin.sys.model.RoleMenu;
import com.eshop.webapp.admin.sys.model.RoleUser;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.sys.service.IMenuService;
import com.eshop.webapp.admin.sys.service.IRoleService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;
@Controller
@RequestMapping("sys")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IMenuService menuService; 

	/**
	 * 列出所有角色，已经选择的标记为已选
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping("role/getmy")
	public ResponseResult getMyRoles(Integer user_id){
		ResponseResult responseResult=new ResponseResult();
		try{
			if(user_id==null){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("用户信息未空，不能分配角色!");
				return responseResult;
			}
			List<Role> roleList = roleService.listAllRoles();
			List<RoleUser> roleUserList = roleService.listUsersRole(user_id);
			List<Integer> roleIdList = new ArrayList<Integer>(); 
			if(roleUserList != null && roleUserList.size() > 0){
				for(RoleUser sysRoleUser : roleUserList){
					if(sysRoleUser != null){
						roleIdList.add(sysRoleUser.getRoleId());
					}
				}
			}
			List<Role> rightRoleList = roleService.listRightRole(roleIdList);
			if(rightRoleList!=null){
				for(Role role : roleList){
					for(Role srole : rightRoleList){
						if(srole.getId().equals(role.getId())){
							role.setSelected(true);
							break;
						}
					}
				}
				
			}
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setData(roleList);
		}catch(Exception ex){
			ex.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("系统出现错误，请稍后再试!");
		}
		
		return responseResult;
	}
	

	/**
	 * 保存给用户赋的角色
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("role/savemy")
	public ResponseResult saveMyRole(Integer user_id,String role_id_str){
		ResponseResult responseResult = new ResponseResult();
		try{
			if(user_id==null){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("分配角色的用户不能为空");
				return responseResult;
			}
			if(StringUtils.isBlank(role_id_str)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("分配的角色不能为空");
				return responseResult;
			}
			
			List<Integer> roleIdList = new ArrayList<Integer>();
			String[] temArr = role_id_str.split(",");
			for(int i=0;i<temArr.length;i++){
				roleIdList.add(Integer.valueOf(temArr[i]));
			}
			int itotal=roleService.createUserRole(user_id, roleIdList);
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("分配角色失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("分配角色成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("系统出现错误，请稍后再试!");
		}
		return responseResult;
	}
	
	/**
	 * 角色列表页
	 * @return
	 */
	@RequestMapping("role/page")
	public String rolePage(){
		return "sys/rolelist";
	}
	
	/**
	 * 根据roleid列出该角色所有的权限
	 * @return
	 */
	@ResponseBody
	@RequestMapping("listRolsMenu")
	public List<Menu> listRolsMenu(int roleId){
		List<Menu> menuList = menuService.getAllMenuList();
		List<RoleMenu> roleMenuList = roleService.listRolesMenus(roleId);
		if(menuList != null && menuList.size() > 0 && roleMenuList != null && roleMenuList.size()>0){
			for(RoleMenu sysRoleMenu : roleMenuList){
				int sysRoleMenuId = sysRoleMenu.getMenuId();
				for(Menu menu : menuList){
					int menuId = menu.getId();
					if(sysRoleMenuId == menuId){
						menu.setIsCheck(1);
						menu.setChecked(true);
					}
				}
			}
		}
		List<Menu> nodeList = new ArrayList<Menu>();
		HashMap<Integer, Menu> mapNode = new HashMap<Integer, Menu>();
		// 根据结果集构造节点列表（存入哈希表）
		// dataList必须按照pid升序排列，才能这样建造树形结构。
		for (Menu menu : menuList) {
			mapNode.put(menu.getId(), menu);
			if(mapNode.containsKey(menu.getParent_id())){
				// 应为是引用类型的，所以该结点也在nodeList里。
				mapNode.get(menu.getParent_id()).getChildren().add(menu);
			}else{
				nodeList.add(menu);
			}
		}
		return nodeList;
	}
	
	
	/**
	 * 将菜单赋给角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveGrantMenus")
	public Map<String,Object> saveGrantMenus(Integer[] menuIds){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Integer> menuIdList = new ArrayList<Integer>(Arrays.asList(menuIds));
		if(menuIdList != null && menuIdList.size() > 1){
			try {
				int roleId = (Integer) menuIdList.get(0); //角色id
				menuIdList.remove(0);
				roleService.grantMenusForRol(roleId, menuIdList);
				resultMap.put("status", "success");
				resultMap.put("msg", "分配角色成功");
			} catch (Exception e) {
				resultMap.put("status", "error");
				resultMap.put("msg", "分配权限失败");
			}
			
		}else{
			resultMap.put("status", "error");
			resultMap.put("msg", "分配权限失败");
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("role/list")
	public PageResponse<Role> roleList(PageRequest  pageRequest,String role_name) {
		if(StringUtils.isBlank(role_name)){
			role_name = null;
		}
		PageResponse<Role> pageResponse = roleService.listRolePage(pageRequest, role_name);
		
		return pageResponse;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("role/save")
	public ResponseResult roleSave(String role_name, String description) {
		ResponseResult responseResult = new ResponseResult();
		try {
			if (StringUtils.isBlank(role_name)) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("请输入角色名称");
				return responseResult;
			}
			if (StringUtils.isBlank(description)) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("请输入角色描述");
				return responseResult;
			}
			
			Role role=new Role();
			role.setRole_name(role_name);
			role.setDescription(description);
			role.setCreate_date(new Date()); 
			role.setCreate_user(this.getCurrentUsername());
			roleService.addRole(role);
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("系统出现错误，请稍后再试!");
		}
		
		return responseResult;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("role/edit")
	public ResponseResult roleEdit(String role_name, String description, Integer id) {
		ResponseResult responseResult = new ResponseResult();
		if(id==null){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("角色信息不能为空");
			return responseResult;
		}
		if (StringUtils.isBlank(role_name)) {
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("请输入角色名称");
			return responseResult;
		}
		if (StringUtils.isBlank(description)) {
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("请输入角色描述");
			return responseResult;
		}
		try {
			Role role= new Role();
			role.setRole_name(role_name);
			role.setId(id);
			role.setDescription(description);
			role.setUpdate_user(getCurrentUsername());
			roleService.modifyRole(role);
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("系统出现错误，请稍后再试!");
		}
		return responseResult;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("role/delete")
	public ResponseResult roleDelete(Integer id) {
		ResponseResult responseResult = new ResponseResult();
		try {
			if(id==null){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("角色信息不能为空");
				return responseResult;
			}
			int[] ids=new int[]{id}; 
			roleService.deleteRoles(ids);
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("系统出现错误，请稍后再试!");
		}
		return responseResult;
	}
	
	@ResponseBody
	@RequestMapping("selectRoleUser")
	public Map<String,Object> selectRoleUser(int roleId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			List<User> roleUser=roleService.selectRoleUser(roleId);
			if(roleUser!=null&&roleUser.size()>0){
				resultMap.put("roleUserTotal", roleUser.size());
				resultMap.put("roleUser", roleUser);
			}else{
				resultMap.put("roleUserTotal", 0);
				resultMap.put("roleUser", roleUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

}
