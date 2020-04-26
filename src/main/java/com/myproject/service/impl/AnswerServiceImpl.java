package com.myproject.service.impl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myproject.dao.answerMapper;
import com.myproject.dao.userMapper;
import com.myproject.entiy.PageResult;
import com.myproject.pojo.answer;
import com.myproject.pojo.answerExample;
import com.myproject.pojo.user;
import com.myproject.pojo.answerExample.Criteria;
import com.myproject.service.AnswerService;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private answerMapper answerMapper;
	
	@Autowired
	private userMapper userMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<answer> findAll() {
		return answerMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<answer> page=   (Page<answer>) answerMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(answer answer) {
		answerMapper.insert(answer);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(answer answer){
		answerMapper.updateByPrimaryKey(answer);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public answer findOne(String id){
		return answerMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			answerMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(answer answer, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		answerExample example=new answerExample();
		Criteria criteria = example.createCriteria();
		
		if(answer!=null){			
			if(answer.getAnswerid()!=null && answer.getAnswerid().length()>0){
				criteria.andAnsweridEqualTo("%"+answer.getAnswerid()+"%");
			}
			if(answer.getUserid()!=null && answer.getUserid().length()>0){
				criteria.andUseridLike("%"+answer.getUserid()+"%");
			}
			if(answer.getOptionsid()!=null && answer.getOptionsid().length()>0){
				criteria.andOptionsidEqualTo("%"+answer.getOptionsid()+"%");
			}
	
		}
		
		Page<answer> page= (Page<answer>)answerMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public List<user> findByNum(String num) {
			answerExample answerExample = new answerExample();
			Criteria answercriteria = answerExample.createCriteria();
			answercriteria.andQuestionnairenumberEqualTo(num);
			List<answer> answerList = answerMapper.selectByExample(answerExample);
			
			HashSet<String> userIDlist = new HashSet<>();
			for(answer answer : answerList) {
				userIDlist.add(answer.getUserid());
			}
			List<user> userList = new ArrayList<>();
			for(String userID : userIDlist) {
				user user = userMapper.selectByPrimaryKey(userID);
				userList.add(user);
			}

			return userList;
		}

		@Override
		public List<Long> findByOptionsID(String[] optionsidList) {
				
			List<Long> countList = new ArrayList<>();
			for(String element : optionsidList) {
				answerExample answerExample = new answerExample();
				Criteria answercriteria = answerExample.createCriteria();
				answercriteria.andOptionsidEqualTo(element);
				Long count = answerMapper.countByExample(answerExample);
				countList.add(count);
			}
			return countList;
		}

		@Override
		public Long CountOptionID(String optionsid) {
			answerExample answerExample = new answerExample();
			Criteria answercriteria = answerExample.createCriteria();
			answercriteria.andOptionsidEqualTo(optionsid);
			Long count = answerMapper.countByExample(answerExample);	
			return count;	
		}

	@Override
	public Set<String> findByUserId(String userId) {
		answerExample answerExample = new answerExample();
		Criteria answercriteria = answerExample.createCriteria();
		answercriteria.andUseridEqualTo(userId);
		List<answer> answerList = answerMapper.selectByExample(answerExample);
		Set<String> quesNumSet = new HashSet<>();
		for (answer answer : answerList) {
			quesNumSet.add(answer.getQuestionnairenumber());
		}
		System.out.println("quesNumSet = " + quesNumSet);
		return quesNumSet;
	}


		@Override
		public List<answer> findOption(String optionsid) {
			answerExample answerExample = new answerExample();
			Criteria answercriteria = answerExample.createCriteria();
			answercriteria.andOptionsidEqualTo(optionsid);
			List<answer> answers = answerMapper.selectByExample(answerExample);
			return answers;
		}

}
