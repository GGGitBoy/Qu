package com.myproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.myproject.entiy.PageResult;
import com.myproject.entiy.Result;
import com.myproject.pojo.*;
import com.myproject.service.AnswerService;
import com.myproject.service.UserService;
import com.myproject.utils.CreatUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<answer> findAll() {
        return answerService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return answerService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param answer
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody answer answer) {
        try {
            answerService.add(answer);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }


    @RequestMapping("/addList")
    public Result addList(@RequestBody String answers, String questionnaireId) {
        try {
            answer answer = new answer();
            JSONObject root = JSONObject.parseObject(answers);
            for (Object ans : root.getJSONArray("answers")) {
                JSONObject obj = (JSONObject) ans;
                answer = JSONObject.toJavaObject(obj, answer.getClass());
                answer.setAnswerid(CreatUUID.uuid());
                answerService.add(answer);
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
     * @param answer
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody answer answer) {
        try {
            answerService.update(answer);
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
    public answer findOne(String id) {
        return answerService.findOne(id);
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
            answerService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param answer
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody answer answer, int page, int rows) {
        return answerService.findPage(answer, page, rows);
    }


    @RequestMapping("/findByNum")
    public List<user> findByNum(String num) {
        return answerService.findByNum(num);
    }

    @RequestMapping("/findByOptionsID")
    public List<Long> findByOptionsID(String[] optionsidList) {
        return answerService.findByOptionsID(optionsidList);
    }

    @Autowired
    QuestionnaireController questionnaireController;
    @Autowired
    UserService userService;

    @RequestMapping("/addTestData")
    public void addTestData() {
        String id = "eeb54f43b2fd4052815c97b24f9266e2";
        questionnaire questionnaire = questionnaireController.getDetail(id);
        List<user> userList = userService.findAll();
        for (question question : questionnaire.getQuestions()) {
            for (ques quess : question.getQuess()) {
                List<options> options = quess.getOptions();
                Object[] array = options.toArray();
                for (user user : userList) {
                    com.myproject.pojo.options options1 = (com.myproject.pojo.options) array[(int) (Math.random() * 5)];
                    answer answer = new answer();
                    answer.setOptionsid(options1.getOptionsid());
                    answer.setUserid(user.getUserid());
                    answer.setQuestionnairenumber(questionnaire.getQuestionnairenumber());
//                    answer.setValue(options1.getSite());
                    answer.setAnswerid(CreatUUID.uuid());
                    int i = options1.getSite() + 1;
                    answer.setValue(Integer.valueOf(i).toString());
                    answerService.add(answer);
                }
            }
        }


    }
}
