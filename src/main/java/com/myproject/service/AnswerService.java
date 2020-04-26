package com.myproject.service;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.User;

import com.myproject.entiy.PageResult;
import com.myproject.pojo.answer;
import com.myproject.pojo.user;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface AnswerService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<answer> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(answer answer);
	
	
	/**
	 * 修改
	 */
	public void update(answer answer);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public answer findOne(String id);
	
	
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
	public PageResult findPage(answer answer, int pageNum,int pageSize);


	public List<user> findByNum(String num);


	public List<Long> findByOptionsID(String[] optionsidList);


	public Long CountOptionID(String optionsid);

	public Set<String> findByUserId(String userId);


	public List<answer> findOption(String optionsid);

}
