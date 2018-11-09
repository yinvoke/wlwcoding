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


@RestController
@RequestMapping("/api/user")
public class UserController {

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
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        try {
            user = userService.findByUsernameAndPassword(username,password);
//            user = userService.findByUsername(username);
        }catch (Exception e){
            user = null;
            System.out.println("Exception:"+e.getMessage());
        }

        if(user != null){
            return ErrorResponseUtil.setResponse("200", "login successful "+user.getNick_name());
        }

        return ErrorResponseUtil.setResponse("400", "Login fail ");
    }


}
