package com.microservice.backend.controller;

import com.microservice.backend.common.utils.ErrorResponseUtil;
import com.microservice.backend.entity.User;
import com.microservice.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String registering(HttpServletRequest request) {
        // 获取传递过来的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nick_name = request.getParameter("nick_name");
        User user = new User(username,nick_name,password,(long)1);
        try {
            userService.insert(user);
        }catch(Exception e){
            return ErrorResponseUtil.setResponse("400", e.getMessage());
        }
        return ErrorResponseUtil.setResponse("200", "registration success");
    }

    @RequestMapping(value="/login" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap map = new HashMap();
        User user;
        try {
            user = userService.findByUsernameAndPassword(username,password);
//            user = userService.findByUsername(username);
        }catch (Exception e){
            user = null;
            System.out.println("Exception:"+e.getMessage());
        }

        if(user == null){
            map = this.setResponse("error","passwrd error or user not exist",null);
            return  map;
        }

        map = this.setResponse("success",null,user);
        return map;
    }

    @RequestMapping(value="/password" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap changePassword(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        User user = userService.findById(id);
        HashMap map = null;
        if(user == null){
            map = this.setResponse("error","id error",null);
            return map;
        }
        if(user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            userService.insert(user);
        }else{
            map = this.setResponse("error","old password error",null);
            return map;
        }
        map = this.setResponse("success","",user);
        return map;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap changeBasicInformation(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        String newUsername = request.getParameter("newUsername");
        String newTel = request.getParameter("newTel");
        String newEmail = request.getParameter("newEmail");
        User user = userService.findById(id);
        HashMap map = null;
        if(user == null){
            map = this.setResponse("error","update information failure",null);
            return map;
        }else{
            user.setUsername(newUsername);
            user.setEmail(newEmail);
            user.setTel(newTel);
            userService.insert(user);
            map = this.setResponse("success","update information success",user);
        }

        return map;
    }

}
