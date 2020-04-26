package com.myproject.service.impl;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.myproject.entiy.Result;
import com.myproject.utils.AlSmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myproject.dao.userMapper;
import com.myproject.entiy.PageResult;
import com.myproject.pojo.user;
import com.myproject.pojo.userExample;
import com.myproject.pojo.userExample.Criteria;
import com.myproject.service.UserService;
import com.myproject.utils.CreatUUID;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private userMapper userMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<user> findAll() {
		return userMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<user> page  = (Page<user>) userMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(user user) {
		userMapper.insert(user);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(user user){
		userMapper.updateByPrimaryKey(user);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public user findOne(String id){
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			userMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(user user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		userExample example=new userExample();
		Criteria criteria = example.createCriteria();
		
		if(user!=null){			
			if(user.getUserid()!=null && user.getUserid().length()>0){
				criteria.andUseridLike("%"+user.getUserid()+"%");
			}
			if(user.getIcon()!=null && user.getIcon().length()>0){
				criteria.andIconLike("%"+user.getIcon()+"%");
			}
			if(user.getUsername()!=null && user.getUsername().length()>0){
				criteria.andUsernameLike("%"+user.getUsername()+"%");
			}
			if(user.getPassword()!=null && user.getPassword().length()>0){
				criteria.andPasswordLike("%"+user.getPassword()+"%");
			}
			if(user.getNickname()!=null && user.getNickname().length()>0){
				criteria.andNicknameLike("%"+user.getNickname()+"%");
			}
			if(user.getStudentnumber()!=null && user.getStudentnumber().length()>0){
				criteria.andStudentnumberLike("%"+user.getStudentnumber()+"%");
			}
			if(user.getMajor()!=null && user.getMajor().length()>0){
				criteria.andMajorLike("%"+user.getMajor()+"%");
			}
			if(user.getGraduationyear()!=null && user.getGraduationyear().length()>0){
				criteria.andGraduationyearLike("%"+user.getGraduationyear()+"%");
			}
			if(user.getName()!=null && user.getName().length()>0){
				criteria.andNameLike("%"+user.getName()+"%");
			}
			if(user.getCorrespondenceaddress()!=null && user.getCorrespondenceaddress().length()>0){
				criteria.andCorrespondenceaddressLike("%"+user.getCorrespondenceaddress()+"%");
			}
			if(user.getWechat()!=null && user.getWechat().length()>0){
				criteria.andWechatLike("%"+user.getWechat()+"%");
			}
			if(user.getPhone()!=null && user.getPhone().length()>0){
				criteria.andPhoneLike("%"+user.getPhone()+"%");
			}
			if(user.getEmail()!=null && user.getEmail().length()>0){
				criteria.andEmailLike("%"+user.getEmail()+"%");
			}
			if(user.getQqnumber()!=null && user.getQqnumber().length()>0){
				criteria.andQqnumberLike("%"+user.getQqnumber()+"%");
			}
			if(user.getGraduationplane()!=null && user.getGraduationplane().length()>0){
				criteria.andGraduationplaneLike("%"+user.getGraduationplane()+"%");
			}
			if(user.getGraduationplanesituation()!=null && user.getGraduationplanesituation().length()>0){
				criteria.andGraduationplanesituationLike("%"+user.getGraduationplanesituation()+"%");
			}
			if(user.getRemark()!=null && user.getRemark().length()>0){
				criteria.andRemarkLike("%"+user.getRemark()+"%");
			}
	
		}
		
		Page<user> page= (Page<user>)userMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public void addExcel(user user) {
			//密码加密
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setUserid(CreatUUID.uuid());	
			userMapper.insert(user);		
			
		}

    @Override
    public user findByUserName(String username) {
        return userMapper.findByUserName(username);
    }


}
