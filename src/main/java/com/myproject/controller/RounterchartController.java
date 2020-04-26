package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.entiy.PageResult;
import com.myproject.entiy.Result;
import com.myproject.pojo.rounterchart;
import com.myproject.service.RounterchartService;
import com.myproject.utils.CreatUUID;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/rounterchart")
public class RounterchartController {

    @Autowired
    private RounterchartService rounterchartService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<rounterchart> findAll(String rounterChart) {
        return rounterchartService.findAll(rounterChart);
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return rounterchartService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param rounterchart
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody rounterchart rounterchart) {
        try {
            rounterchart.setRounterchartid(CreatUUID.uuid());
            rounterchartService.add(rounterchart);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param rounterchart
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody rounterchart rounterchart) {
        try {
            rounterchartService.update(rounterchart);
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
    public rounterchart findOne(String id) {
        return rounterchartService.findOne(id);
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
            rounterchartService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param rounterchart
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody rounterchart rounterchart, int page, int rows) {
        return rounterchartService.findPage(rounterchart, page, rows);
    }

}
