package com.myproject.service;
import java.util.List;

import com.myproject.pojo.ques;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface QuesService {

	
	/**
	 * 增加
	*/
	public void add(ques ques);

	public List<ques> findQuess(String questionid);

	public void update(ques ques);


}
