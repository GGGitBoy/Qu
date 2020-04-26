package com.myproject.service;
import java.util.List;

import com.myproject.entiy.PageResult;
import com.myproject.entiy.QuestionnaireNum;
import com.myproject.pojo.questionnaire;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface QuestionnaireService {

	/**
	 * 返回全部列表
	 * @param status 
	 * @return
	 */
	public List<questionnaire> findAll(String status);
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(questionnaire questionnaire);
	
	
	/**
	 * 修改
	 */
	public void update(questionnaire questionnaire);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public questionnaire findOne(String id);
	
	
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
	public PageResult findPage(questionnaire questionnaire, int pageNum,int pageSize);


	public void changeStatus(questionnaire questionnaire);


	List<questionnaire> findAllNum(String filtersStatus);


	questionnaire findByQuestionnaireNumber(String s);
}
