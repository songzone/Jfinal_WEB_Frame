package com.twosnail.basic.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.twosnail.basic.util.exception.BusiException;
import com.twosnail.basic.util.tree.TreeList;
import com.twosnail.basic.util.tree.TreeNode;

/**   
 * @Title: SysMenu.java
 * @Description: 菜单 
 * @author 两只蜗牛   
 * @date 2015年4月17日 下午1:02:01 
 * @version V1.0   
 */
@SuppressWarnings("serial")
public class SysMenu extends Model<SysMenu> {
	public static final SysMenu me = new SysMenu() ; 
	
	/**
     * 获取菜单列表信息Tree
     * @return
     */
    public List<TreeNode<SysMenu>> getMenuTree(){
    	List<SysMenu> menu = this.getMenuList() ;
    	List<TreeNode<SysMenu>> tree =  TreeList.sort( menu, new TreeList.SortHandler<SysMenu>() {
			public int getId(SysMenu t){
				return t.getInt("id");
			}
			public int getParentId(SysMenu t){
				return t.getInt("parentId");
			}
    	} );
    	return tree ;
    }
    
    /**
     * 获取菜单列表信息List
     * @return
     */
    public List<SysMenu> getMenuList(){
    	return me.find( "SELECT * FROM sys_menu " ) ;
    }
    
    /**
	 * 通过id查询菜单信息
	 * @param id
	 * @return
	 */
	public SysMenu getMenuById( int id ){
		return me.findById( id ) ;
	}
	
	public String getMenuName(  int id  ) {
		if( id == -1 ) {
			return null ;
		}
		return this.getMenuById(id).getStr("name") ;
	}
	
	/**
	 * 添加菜单信息
	 * @param SysMenu
	 * @throws BusiException
	 */
	public int addMenu( SysMenu menu) throws BusiException {
		menu.set( "createTime" , System.currentTimeMillis() );
		if( !menu.save() ) {
            throw new BusiException( "添加信息失败!" );
        }
		return menu.getInt("id") ;
	}
    
	/**
	 * 修改菜单信息
	 * @param SysMenu
	 * @throws BusiException
	 */
	public void updMenu( SysMenu menu ) throws BusiException {
		if( !menu.update() ) {
            throw new BusiException( "修改菜单信息失败!" );
        }
	}
	
	/**
     * 修改菜单信息状态
     * @param SysMenu
     * @throws BusiException
     */
    public void updMenuStasus( SysMenu menu ) throws BusiException {
    	if(  !menu.update() ) {
            throw new BusiException( "修改信息失败" );
        }
    }   
    
    /**
     * 删除 菜单
     * @param id
     * @throws BusiException
     */
    public void delMenuTx( int id ) throws BusiException{
       if( !me.deleteById( id ) ) {
           throw new BusiException( "修改信息失败" );
       }
    }
	
}
