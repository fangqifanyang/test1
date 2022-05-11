package com.wc.dao;

import com.wc.entity.Reward;
import com.wc.entity.RewardVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardDao {

    //查询奖项是否存在
    @Select("select * from Reward where group_id=#{group_id} and competition_id=#{competition_id}")
    List<Reward> checkR(Reward reward);

    //插入新奖项
    @Insert("insert into Reward (competition_id,group_id,cup) values(#{competition_id},#{group_id},#{cup})")
    void insertR(Reward reward);

    //插入指定表
    @Insert("insert into ${tablename} (group_id,group_name,cup) values(#{group_id},#{group_name},#{cup})")
    void insert(@Param("tablename")String tablename,@Param("group_id")Integer group_id,@Param("group_name")String group_name,@Param("cup")String cup);
    //更新奖项
    @Update("update Reward set cup=#{cup} where competition_id=#{competition_id} and group_id=#{group_id}")
    void updateR(Reward reward);

//    @Select("select * from Reward where id=#{id}")
//    Reward check(Integer id);

    //查看我的竞赛获奖信息

    @Select("select c.`name` AS competition_name,reward.cup AS cup from reward,c where (group_id in(SELECT group_id from member where user_id=2) or user_id=2) AND reward.competition_id=c.id;")
    List<Reward> ListReward(@Param("Uid")int Uid);

    //找出对应的竞赛名

    @Select("select name from c where id=#{id}")
    String findCname(@Param("id")int id);

    //找出对应的小组名

    @Select("select name from `group` where id=#{id}")
    String findGname(@Param("id")int id);


    //删除奖项表
    @Update("DROP TABLE IF EXISTS c${cid};")
    void drop(Integer cid);

    //创建奖项表
    @Update("create table c${cid} (  `id` int(11) NOT NULL AUTO_INCREMENT,`group_id` int(11),`group_name` varchar(255),`cup` varchar(255),PRIMARY KEY (`id`))")
    void create(Integer cid);


    @Update("create table c${cid} (  `id` int(11) NOT NULL AUTO_INCREMENT,`user_id` int(11),`user_name` varchar(255),`cup` varchar(255),PRIMARY KEY (`id`))")
    void createONE(Integer cid);
    @Insert("insert into ${tablename} (user_id,user_name,cup) values(#{user_id},#{user_name},#{cup})")
    void insertONE(@Param("tablename")String tablename,@Param("user_id")Integer user_id,@Param("user_name")String user_name,@Param("cup")String cup);

    @Select("select * from Reward where user_id=#{user_id} and competition_id=#{competition_id}")
    List<Reward> checkRONE(Reward r);
    @Insert("insert into Reward (competition_id,user_id,cup) values(#{competition_id},#{user_id},#{cup})")
    void insertRONE(Reward r);
    @Update("update Reward set cup=#{cup} where competition_id=#{competition_id} and user_id=#{user_id}")
    void updateRONE(Reward r);




    @Select("select c.`name` AS competition_name,`group`.`name` AS group_name,reward.cup AS cup,c.max_people AS type  from reward,c,`group` where reward.group_id in(SELECT group_id from member where user_id=#{uid}) AND reward.competition_id=c.id AND `group`.id=reward.group_id AND IFNULL(c.`name`,'') LIKE '%${keyword}%';")
    List<RewardVo> findTeamReward(@Param("uid") Integer uid,@Param("keyword")String keyword);

    @Select("SELECT c.`name` AS competition_name,reward.cup AS cup,c.max_people AS type FROM reward,c WHERE reward.user_id=#{uid} AND reward.competition_id=c.id AND IFNULL(c.`name`,'') LIKE '%${keyword}%';")
    List<RewardVo> findOneReward(@Param("uid") Integer uid,@Param("keyword")String keyword);

    @Select("select c.`name` AS competition_name,`group`.`name` AS group_name,reward.cup AS cup  from reward,c,apply,`group` where apply.teacher_id=#{uid} AND reward.competition_id=c.id AND apply.group_id=reward.group_id AND apply.competition_id=reward.competition_id AND `group`.id=reward.group_id AND IFNULL(c.`name`,'') LIKE '%${keyword}%';")
    List<RewardVo> findTeacherReward(@Param("uid") Integer uid,@Param("keyword")String keyword);
}
