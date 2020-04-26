package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.entiy.PageResult;
import com.myproject.entiy.QuestionEntity;
import com.myproject.entiy.Result;
import com.myproject.pojo.question;
import com.myproject.pojo.questionnaire;
import com.myproject.service.QuestionService;
import com.myproject.utils.CreatUUID;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<question> findAll() {
        return questionService.findAll();
    }

    @RequestMapping("/findQuestions")
    public List<question> findQuestions(String id) {
        return questionService.findQuestions(id);
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return questionService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param questionList
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody question[] questionList) {
        try {
            for (question element : questionList) {
                element.setQuestionid(CreatUUID.uuid());
                questionService.add(element);
            }
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param questionList
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody question[] questionList) {
        try {
            for (question element : questionList) {
                if (element.getQuestionid() == null) {
                    element.setQuestionid(CreatUUID.uuid());
                    questionService.add(element);
                } else {
                    questionService.update(element);
                }

            }
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public question findOne(String id) {
        return questionService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(String[] ids) {
        try {
            questionService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param question
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody question question, int page, int rows) {
        return questionService.findPage(question, page, rows);
    }

}
