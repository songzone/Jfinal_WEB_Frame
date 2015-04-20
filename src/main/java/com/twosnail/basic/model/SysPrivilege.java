package com.twosnail.basic.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**   
 * @Title: SysInfoUser.java
 * @Description: TODO 
 * @author 两只蜗牛   
 * @date 2015年4月17日 下午1:02:01 
 * @version V1.0   
 */

@SuppressWarnings("serial")
public class SysPrivilege extends Model<SysPrivilege>{
	public static final SysPrivilege me = new SysPrivilege() ; 
	
	
	public List<SysPrivilege> getPrivilegeByUserId( long id ) {
		return me.find( 
				"SELECT a.* FROM sys_privilege a  LEFT JOIN sys_user b  ON a.roleId = b.roleId WHERE b.id = ?" , id ) ;
	}
	
	/**
     * 获取角色授权信息
     * @param roleId
     */
    public List<SysPrivilege> getPrivilegeByRoleId( long roleId ){
    	return  me.find( "SELECT a.* FROM sys_privilege a WHERE a.roleId = ?" , roleId ) ;
    }
	
}
