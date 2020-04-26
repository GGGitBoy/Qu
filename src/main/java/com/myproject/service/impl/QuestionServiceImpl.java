package com.myproject.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myproject.dao.optionsMapper;
import com.myproject.dao.questionMapper;
import com.myproject.entiy.PageResult;
import com.myproject.entiy.QuestionEntity;
import com.myproject.pojo.answer;
import com.myproject.pojo.answerExample;
import com.myproject.pojo.options;
import com.myproject.pojo.optionsExample;
import com.myproject.pojo.question;
import com.myproject.pojo.questionExample;
import com.myproject.pojo.questionExample.Criteria;
import com.myproject.pojo.questionnaire;
import com.myproject.pojo.questionnaireExample;
import com.myproject.pojo.user;
import com.myproject.service.QuestionService;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private questionMapper questionMapper;
	
	@Autowired
	private optionsMapper optionsMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<question> findAll() {
		return questionMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<question> page=   (Page<question>) questionMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(question question) {
		questionMapper.insert(question);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(question question){
		questionMapper.updateByPrimaryKey(question);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public question findOne(String id){
		return questionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			questionMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(question question, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		questionExample example=new questionExample();
		Criteria criteria = example.createCriteria();
		
		if(question!=null){			
			if(question.getQuestionid()!=null && question.getQuestionid().length()>0){
				criteria.andQuestionidLike("%"+question.getQuestionid()+"%");
			}
//			if(question.getQuestionnumber()!=null && question.getQuestionnumber().length()>0){
//				criteria.andQuestionnumberLike("%"+question.getQuestionnumber()+"%");
//			}
//			if(question.getContent()!=null && question.getContent().length()>0){
//				criteria.andContentLike("%"+question.getContent()+"%");
//			}
//			if(question.getDigest()!=null && question.getDigest().length()>0){
//				criteria.andDigestLike("%"+question.getDigest()+"%");
//			}
//			if(question.getQuestiontypenumber()!=null && question.getQuestiontypenumber().length()>0){
//				criteria.andQuestionnumberLike("%"+question.getQuestiontypenumber()+"%");
//			}
	
		}
		
		Page<question> page= (Page<question>)questionMapper.selectByExample(example);		
			return new PageResult(page.getTotal(), page.getResult());
		}

		@Override
		public List<question> findQuestions(String Qid) {
			questionExample example=new questionExample();
			example.setOrderByClause("`site` ASC");
			Criteria criteria = example.createCriteria();
			criteria.andNaireidEqualTo(Qid);
			
			return questionMapper.selectByExample(example);
		}

}
