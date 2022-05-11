package com.wc.dao;

import com.wc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /*
    教师个人相关
     */

    //登录

    @Select("select * from user where username=#{username} and password=#{password} and type='teacher'")
    User Teacherlogin(@Param("username") String username,@Param("password") String password);

    //注册

    @Insert("insert into user (username,password,name,,academy,type) value(#{username},#{password},#{name},#{academy},'teacher')")
    void Sign(@Param("username") String username,@Param("password") String password,@Param("name") String name,@Param("academy")String academy);

    //查询个人信息

    @Select("select id,name,gender,intro,academy,major,tel,qq,type from user where id=#{Uid}")
    User ListInfo(@Param("Uid")int Uid);

    //修改个人信息

    @Update("update user set name=#{name},gender=#{gender},intro=#{intro},academy=#{academy},major=#{major},tel=#{tel},qq=#{qq} where id=#{Uid}")
    void UpdateInfo(@Param("name")String name,@Param("gender")String gender,@Param("intro")String intro,@Param("academy")String academy,@Param("major")String major,@Param("tel")String tel,@Param("qq")String qq,@Param("Uid")int Uid);

    //修改密码

    @Update("update user set password=#{newpass} where password=#{oldpass} and id=#{Uid}")
    void UpdatePass(@Param("newpass")String newpass,@Param("oldpass")String oldpass,@Param("Uid")int Uid);





    //-------------------------------------



    @Select("select people from c where id=#{Cid}")
    int findCompetitionPeople(@Param("Cid")int Cid);



    //--------------------------------------------

    //模糊查询(学生)

    @Select("SELECT * FROM user WHERE CONCAT(IFNULL(`name`,''),IFNULL(`id`,'')) LIKE '%${key1}%' and type='学生' AND IFNULL(`academy`,'') LIKE '%${key2}%'")
    List<User> searchStu(@Param("key1")String key1,@Param("key2")String key2);

    //
    @Select("select id from user where name=#{name}")
    Integer findID(String name);

}
