package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.entiy.PageResult;
import com.myproject.entiy.Result;
import com.myproject.pojo.options;
import com.myproject.pojo.question;
import com.myproject.service.OptionsService;
import com.myproject.utils.CreatUUID;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/options")
public class OptionsController {

    @Autowired
    private OptionsService optionsService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<options> findAll() {
        return optionsService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return optionsService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param options
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody options[] options) {
        try {
            for (options element : options) {
                element.setOptionsid(CreatUUID.uuid());
                optionsService.add(element);
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
     * @param options
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody options[] options) {
        try {
            for (options element : options) {
                if (element.getOptionsid() == null) {
                    element.setOptionsid(CreatUUID.uuid());
                    optionsService.add(element);
                } else {
                    optionsService.update(element);
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
    public options findOne(String id) {
        return optionsService.findOne(id);
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
            optionsService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param options
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody options options, int page, int rows) {
        return optionsService.findPage(options, page, rows);
    }

}
