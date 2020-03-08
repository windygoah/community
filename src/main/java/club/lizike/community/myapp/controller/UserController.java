package club.lizike.community.myapp.controller;

import club.lizike.community.myapp.mapper.UserMapper;
import club.lizike.community.myapp.model.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/signUp")
    public String signUp(){
        return "signUp";
    }
    @GetMapping("/signIn")
    public String signIn(){
        return "signIn";
    }

    /**
     * 验证用户名
     * 0：格式不正确
     * 1：长度不够
     * 2：用户名已存在
     * 3:格式正确
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkName", method = RequestMethod.GET)
    public Integer checkName(String name) {
        System.out.println(userMapper.selectByName(name));
        return userMapper.selectByName(name);
    }
    /**
     * 验证密码格式
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkSignUp", method = RequestMethod.POST)
    public int checkRegister(String name,String password) {
        return userMapper.insert(name,password);
    }
    /**
     * 验证登录
     * 1、验证用户登录是否成功
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkSignIn", method = RequestMethod.GET)
    public Integer checkSignIn(String name, String password, HttpSession session) {
        Integer msg=0;
        System.out.println(userMapper.findByNameAndPassword(name,password));
        if(userMapper.findByNameAndPassword(name,password)!=null) {
            session.setAttribute("userName",name);
            msg=1;
        }
        return msg;
    }
    /**
     * 验证用户登录是否成功
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public User getUsername(HttpSession httpSession) {
        String name = (String) httpSession.getAttribute("userName");
        User param  = userMapper.findUserInfoByName(name);
        return param;
    }
}
