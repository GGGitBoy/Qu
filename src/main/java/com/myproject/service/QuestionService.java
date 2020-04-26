package com.myproject.service;
import java.util.List;

import com.myproject.entiy.PageResult;
import com.myproject.entiy.QuestionEntity;
import com.myproject.pojo.question;
import com.myproject.pojo.questionnaire;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface QuestionService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<question> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(question question);
	
	
	/**
	 * 修改
	 */
	public void update(question question);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public question findOne(String id);
	
	
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
	public PageResult findPage(question question, int pageNum,int pageSize);


	public List<question> findQuestions(String qid);


}
