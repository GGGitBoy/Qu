package com.myproject.service;
import java.util.List;

import com.myproject.entiy.PageResult;
import com.myproject.entiy.Result;
import com.myproject.pojo.user;
import org.springframework.security.core.userdetails.User;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<user> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(user user);
	
	
	/**
	 * 修改
	 */
	public void update(user user);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public user findOne(String id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(user user, int pageNum,int pageSize);

    user findByUserName(String username);

	public void addExcel(user user);

}
