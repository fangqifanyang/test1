package com.wc.dao;

import com.wc.Dto.groupDto;
import com.wc.entity.GroupVo;
import com.wc.entity.User;
import com.wc.entity.group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupDao {

    //通过用户id查看所在的团队
    @Select("select * from group where id in (select group_id from member where user_id=#{uid})")
    List<group> ListGroup(Integer uid);

    //更新团队信息
    @Update("update group set name=#{name},intro=#{intro} where id=#{id}")
    void updateGroup(@Param("name")String name,@Param("intro")String intro,@Param("id")Integer id);

    //通过用户所在的团队id查看指定团队内成员
    @Select("select * from user where id in (select user_id from member where group_id=#{gid})")
    List<User> ListMember(Integer gid);

    //组长删除指定成员
    @Delete("delete from member where user_id=#{uid} and group_id=#{gid}")
    void deleteMember(@Param("UID") Integer uid,@Param("gid") Integer gid);

    //删除整个团队
    @Delete("delete from member where  group_id=#{gid}")
    void deletegroup1(Integer gid);
    @Delete("delete from `group` where  id=#{gid}")
    void deletegroup2(Integer gid);
    @Delete("delete from apply where  group_id=#{gid}")
    void deletegroup3(Integer gid);

    //成员接受邀请,加入团队/或组长创建团队时
    @Insert("insert into member (user_id,group_id) value (#{uid},#{gid})")
    void join(@Param("uid")Integer uid,@Param("gid")Integer gid);

    //创建团队
    @Insert("insert into group (name,intro) value(#{name},#{intro})")
    void createGroup(group g);


    //获取所有组员所参加的竞赛(用于判断一个人是否在同一竞赛中接受多个组)
    @Select("SELECT competition_id from apply INNER JOIN member ON apply.group_id=member.group_id WHERE member.user_id in (select user_id from member where group_id=#{gid}) and competition_id=#{cid}")
    List<Integer> ListMCompetition(@Param("gid")int gid,@Param("cid")int cid);


    //查询小组名
    @Select("select name from `group` where id=#{id}")
    String findname(Integer id);

    //我参与指导的团队(老师)
    @Select("SELECT `group`.id as group_id,`group`.`name` AS group_name,c.id AS competition_id,c.`name` AS competition_name FROM apply,c,`group` WHERE teacher_id=#{uid} AND competition_id=c.id AND group_id=`group`.id;")
    List<GroupVo> findGroup(Integer uid);


    //找出对应的小组名

    @Select("select name from `group` where id=#{id}")
    String findGname(@Param("id")int id);

    @Select("select id from `group` where name=#{name}")
    Integer findID(String name);

}
