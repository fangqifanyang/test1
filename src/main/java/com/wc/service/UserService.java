package com.wc.service;

import com.wc.entity.User;

import java.util.List;

public interface UserService {

    //教师登录
    User Tlogin(String username, String password);

    //教师注册
    void Sign(String username,String password,String name,String academy);

    //查看个人信息
    User ListInfo(int Uid);

    //修改个人信息
    void UpdateInfo(String name,String gender,String intro,String academy,String major,String tel,String qq,int Uid);

    //修改密码
    void UpdatePass(String newPass,String oldPass,int Uid);


    int findCompetitionPeople(int Cid);

    List<User> searchStu(String key1,String key2);

    Integer findID(String user_name);
}
