package com.wc.controller;

import com.wc.entity.User;
import com.wc.service.UserService;
import com.wc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private UserService userService;

    //老师登录
    @PostMapping(value = "/Tlogin")
    private R Tlogin(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession session) {
        User user = userService.Tlogin(username, password);
        if(user==null){
            return R.error();
        }
        session.setAttribute("user",user);
        return R.ok().data("user", user);
    }

    //老师注册
    @PostMapping(value = "/sign")
    private R sign(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("name")String name, HttpSession session,@RequestParam("academy")String academy){
        userService.TSign(username, password,name,academy);
        User user=userService.Tlogin(username,password);
        if(user==null){
            return R.error();
        }
        session.setAttribute("user",user);
        return R.ok().data("user", user);
    }

    //查看个人信息
    @PostMapping(value = "/ListInfo")
    private R ListInfo(HttpSession session){
        User user=(User)session.getAttribute("user");
        int Uid=user.getId();
        user=userService.ListInfo(Uid);
        return R.ok().data("user",user);
    }

    //修改个人信息
    @PostMapping(value = "/UpdateInfo")
    private R UpdateInfo(@RequestParam("name")String name,@RequestParam("gender")String gender,@RequestParam("intro")String intro,@RequestParam("academy")String academy,@RequestParam("major")String major,@RequestParam("tel")String tel,@RequestParam("qq")String qq,HttpSession session ){
        User user=(User)session.getAttribute("user");
        int Uid=user.getId();
        if(gender==null) {
            gender=user.getGender();
        }
        if(intro==null) {
            intro=user.getIntro();
        }
        if(academy==null) {
            academy=user.getAcademy();
        }
        if(major==null){
            major=user.getMajor();
        }
        if(tel==null){
            tel=user.getTel();
        }
        if(qq==null){
            qq=user.getQq();
        }
        userService.UpdateInfo(name,gender,intro,academy,major,tel,qq,Uid);
        user=userService.ListInfo(Uid);
        session.setAttribute("user",user);
        return R.ok().data("user", user);
    }

    @PostMapping("/UpdatePass")
    private R UpdatePass(@RequestParam("newPass")String newPass,@RequestParam("oldPass")String oldPass,HttpSession session){
        User user=(User) session.getAttribute("user");
        int Uid=user.getId();
        userService.UpdatePass(newPass,oldPass,Uid);
        return R.ok();
    }



    @PostMapping("/findCompetitionPeople")
    private R findCP(@RequestParam("Cid")int Cid){
        int people=userService.findCompetitionPeople(Cid);
        return R.ok().data("people",people);
    }

    @PostMapping("/searchStu")
    private R searchStu(String key1,String key2){
        List<User> users=userService.searchStu(key1,key2);
        return R.ok().data("result",users);
    }
}
