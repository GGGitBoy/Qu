package com.myproject.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList.Member2.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myproject.dao.questionnaireMapper;
import com.myproject.entiy.PageResult;
import com.myproject.entiy.QuestionnaireNum;
import com.myproject.pojo.questionnaireExample.Criteria;
import com.myproject.pojo.questionnaire;
import com.myproject.pojo.questionnaireExample;
import com.myproject.service.QuestionnaireService;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	private questionnaireMapper questionnaireMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<questionnaire> findAll(String status) {
		List<questionnaire> questionnairelist;
		if(status == null ) {
			questionnairelist = questionnaireMapper.selectByExample(null);
		}else {
			questionnaireExample example=new questionnaireExample();
			
			Criteria criteria = example.createCriteria();
			
			criteria.andStatusEqualTo(status);
			
			questionnairelist = questionnaireMapper.selectByExample(example);
		}
		return questionnairelist;
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<questionnaire> page=   (Page<questionnaire>) questionnaireMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(questionnaire questionnaire) {
		questionnaireMapper.insert(questionnaire);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(questionnaire questionnaire){
		questionnaireMapper.updateByPrimaryKey(questionnaire);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public questionnaire findOne(String id){
		return questionnaireMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			questionnaireMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(questionnaire questionnaire, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		questionnaireExample example=new questionnaireExample();
		Criteria criteria = example.createCriteria();
		
		if(questionnaire!=null){	
			if(questionnaire.getStatus()!=null && questionnaire.getStatus().length()>0){
				criteria.andStatusEqualTo(questionnaire.getStatus());
			}
			if(questionnaire.getQuestionnaireid()!=null && questionnaire.getQuestionnaireid().length()>0){
				criteria.andQuestionnaireidLike("%"+questionnaire.getQuestionnaireid()+"%");
			}
			if(questionnaire.getQuestionnairenumber()!=null && questionnaire.getQuestionnairenumber().length()>0){
				criteria.andQuestionnairenumberLike("%"+questionnaire.getQuestionnairenumber()+"%");
			}
			if(questionnaire.getTitle()!=null && questionnaire.getTitle().length()>0){
				criteria.andTitleLike("%"+questionnaire.getTitle()+"%");
			}
			if(questionnaire.getSecondtitle()!=null && questionnaire.getSecondtitle().length()>0){
				criteria.andSecondtitleLike("%"+questionnaire.getSecondtitle()+"%");
			}
			if(questionnaire.getImage()!=null && questionnaire.getImage().length()>0){
				criteria.andImageLike("%"+questionnaire.getImage()+"%");
			}
			if(questionnaire.getAuthor()!=null && questionnaire.getAuthor().length()>0){
				criteria.andAuthorLike("%"+questionnaire.getAuthor()+"%");
			}
			if(questionnaire.getDigest()!=null && questionnaire.getDigest().length()>0){
				criteria.andDigestLike("%"+questionnaire.getDigest()+"%");
			}
	
		}
		
		Page<questionnaire> page= (Page<questionnaire>)questionnaireMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public void changeStatus(questionnaire questionnaire) {
			questionnaireMapper.updateByPrimaryKeySelective(questionnaire);
		}
		
		//查找问卷号 标题等信息
		@Override
		public List<questionnaire> findAllNum(String filtersStatus) {
			
			questionnaireExample example=new questionnaireExample();
			Criteria criteria = example.createCriteria();
			
			criteria.andStatusEqualTo(filtersStatus);
			
			List<questionnaire> questionnairelist = questionnaireMapper.selectByExample(example);
	
			return questionnairelist;
		}

	@Override
	public questionnaire findByQuestionnaireNumber(String s) {
		questionnaireExample example = new questionnaireExample();
		Criteria criteria = example.createCriteria();
		criteria.andQuestionnairenumberEqualTo(s);
		List<questionnaire> questionnaireList = questionnaireMapper.selectByExample(example);
		return questionnaireList.get(0);
	}


}
