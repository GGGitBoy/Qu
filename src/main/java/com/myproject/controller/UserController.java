package com.myproject.controller;

import com.myproject.entiy.PageResult;
import com.myproject.entiy.Result;
import com.myproject.pojo.user;
import com.myproject.service.UserService;
import com.myproject.utils.AlSmsHelper;
import com.myproject.utils.CreatUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserService userService;


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<user> findAll() {
        return userService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return userService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody user user) {
        try {
            System.out.println(user.getPassword());
            //密码加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            System.out.println(password);

            user.setUserid(CreatUUID.uuid());
            userService.add(user);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody user user) {
        try {
            System.out.println("user.toString() = " + user.toString());
            userService.update(user);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }


    @RequestMapping("/password")
    public Result password(@RequestBody user user) {
        try {
            System.out.println(user.getPassword());
            //密码加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userService.update(user);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }


    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public user findOne(String id) {
        return userService.findOne(id);
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
            userService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param filtersName
     * @param filtersMajor
     * @param filtersGraduationyear
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(String filtersName, String filtersMajor, String filtersGraduationyear, int page, int rows) {
        user user = new user();
        user.setName(filtersName);
        user.setMajor(filtersMajor);
        user.setGraduationyear(filtersGraduationyear);
        return userService.findPage(user, page, rows);
    }


    /**
     * 发送手机验证码
     *
     * @param phone 手机号
     * @throws Exception
     */
    @RequestMapping("/sendCaptcha")
    public void sendCaptcha(String phone) throws Exception {
        String captcha = AlSmsHelper.generate6BitDigital();
        System.out.println("captcha = " + captcha);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("captcha:" + phone, captcha, 5L, TimeUnit.MINUTES);
//        AlSmsHelper.sendUpdatePasswordCaptcha(phone, captcha);

    }

    /**
     * 修改用户密码
     *
     * @param
     */
    @RequestMapping("/updatePassword")
    public Result updatePassword(String userId, String phone, String password, String captcha) {

        try {
            user user = findOne(userId);
            ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
            String captchaRedis = (String) opsForValue.get("captcha:" + phone);
            System.out.println("captchaRedis = " + captchaRedis);
            if (captchaRedis == null) {
                return new Result(false, "验证码未发送");
            } else {
                if (!captchaRedis.equals(captcha)) {
                    return new Result(false, "验证码错误");
                }
            }
            //密码加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setPhone(phone);
            update(user);
            return new Result(true, "修改密码成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String username, String password) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        user user = userService.findByUserName(username);
        System.out.println("user = " + user.toString());
        if (user != null) {
            //密码加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());
            if (matches) {
                return new Result(true, user.getUserid());
            }
        }
        return new Result(false, "账号或者密码错误");
    }


}
