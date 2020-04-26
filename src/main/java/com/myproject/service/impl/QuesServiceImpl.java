package com.myproject.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dao.quesMapper;
import com.myproject.pojo.ques;
import com.myproject.pojo.quesExample;
import com.myproject.pojo.quesExample.Criteria;
import com.myproject.service.QuesService;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class QuesServiceImpl implements QuesService {

	@Autowired
	private quesMapper quesMapper;
	
	/**
	 * 增加
	 */
	@Override
	public void add(ques ques) {
		quesMapper.insert(ques);		
	}

	@Override
	public List<ques> findQuess(String questionid) {
		quesExample example = new quesExample();
		example.setOrderByClause("`site` ASC");
		Criteria criteria = example.createCriteria();
		criteria.andQuestionidEqualTo(questionid);
		return quesMapper.selectByExample(example);
	}

	@Override
	public void update(ques ques) {
		quesMapper.updateByPrimaryKey(ques);
		
	}

}
