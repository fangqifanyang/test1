package com.wc.service.impl;

import com.wc.dao.UserDao;
import com.wc.entity.User;
import com.wc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User Tlogin(String username, String password) {
        User user=userDao.Teacherlogin(username, password);
        return user;
    }


    @Override
    public void Sign(String username, String password, String name,String academy) {
        userDao.Sign(username, password, name,academy);
    }

    @Override
    public User ListInfo(int Uid) {
        User user=userDao.ListInfo(Uid);
        return user;
    }

    @Override
    public void UpdateInfo(String name,String gender,String intro,String academy,String major,String tel,String qq,int Uid) {
        userDao.UpdateInfo(name,gender,intro,academy,major,tel,qq,Uid);
    }

    @Override
    public void UpdatePass(String newPass, String oldPass, int Uid) {
        userDao.UpdatePass(newPass,oldPass,Uid);
    }

    @Override
    public int findCompetitionPeople(int Cid) {
        return userDao.findCompetitionPeople(Cid);
    }

    @Override
    public List<User> searchStu(String key1,String key2) {
        return userDao.searchStu(key1,key2);
    }

    @Override
    public Integer findID(String user_name) {
        return userDao.findID(user_name);
    }


}
