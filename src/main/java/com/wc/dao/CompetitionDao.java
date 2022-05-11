package com.wc.dao;

import com.wc.entity.Competition;
import com.wc.entity.Reward;
import com.wc.entity.stauts;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompetitionDao {
        /*
    竞赛相关
     */

    //发布竞赛
    @Insert("INSERT INTO c (`name`,intro,academy,people,max_people,start_time,end_time,tag,signed_people)  value(#{name},#{intro},#{academy},#{people},#{max_people},#{start_time},#{end_time},#{tag},0)")
    void publish(Competition competition);

    //判读竞赛是否存在
    @Select("select * from c where name=#{name} and academy=#{academy}")
    Competition checkPublish(Competition competition);

    //删除竞赛
    @Delete("delete from c where id=#{cid}")
    void deleteCompetition1(Integer cid);
    @Delete("delete from apply where competition_id=#{ci亟d}")
    void deleteCompetition2(Integer cid);

    //更新竞赛信息
    @Update("update c set name=#{name},intro=#{intro},academy=#{academy},people=#{people},max_people=#{max_people},start_time=#{start_time},end_time=#{endtime},tag=#{tag}")
    void updateCompetition(Competition competition);



    //查看所有竞赛

    @Select("select * from c ")
    List<Competition> SListC( );



    //查看所有竞赛(老师)

    @Select("select * from c where academy=#{academy} ")
    List<Competition> ListC(String academy);


    //竞赛模糊查询(老师)

    @Select("SELECT * FROM c WHERE CONCAT(IFNULL(`name`,''),IFNULL(`academy`,''),IFNULL(`tag`,''),IFNULL(intro,'')) LIKE '%${keyword}%'and academy=#{academy} and start_time>IFNULL(#{Stime},'0000-1-1') and end_time<IFNULL(#{Etime},'9999-12-31')")
    List<Competition> TsearchC(@Param("keyword")String keyword, @Param("academy")String academy,@Param("Stime")Date Stime,@Param("Etime")Date Etime);

    //模糊查询可报名竞赛(老师)

    @Select("SELECT * FROM c WHERE CONCAT(IFNULL(`name`,''),IFNULL(`academy`,''),IFNULL(`tag`,''),IFNULL(intro,'')) LIKE '%${keyword}%'and academy=#{academy}")
    List<Competition> TsearchAC(@Param("keyword")String keyword, @Param("academy")String academy);

    //竞赛模糊查询

    @Select("SELECT * FROM c WHERE CONCAT(IFNULL(`name`,''),IFNULL(`academy`,''),IFNULL(`tag`,''),IFNULL(intro,'')) LIKE '%${keyword}%' and start_time>IFNULL(#{Stime},'0000-1-1') and end_time<IFNULL(#{Etime},'9999-12-31')")
    List<Competition> SsearchC(@Param("keyword")String keyword,@Param("Stime")Date Stime,@Param("Etime")Date Etime);

    //模糊查询可报名竞赛(学生)
    @Select("SELECT * FROM c WHERE CONCAT(IFNULL(`name`,''),IFNULL(`academy`,''),IFNULL(`tag`,''),IFNULL(intro,'')) LIKE '%${keyword}%' and start_time<#{currenttime}  AND end_time>#{currenttime} and people>signed_people and id not in (SELECT competition_id FROM apply WHERE user_id=#{Uid})")
    List<Competition> SsearchAC(@Param("keyword")String keyword,@Param("currenttime") Date currenttime,@Param("Uid")int Uid);

    //查看可报名竞赛(学生)

    @Select("select * from c where start_time<#{currenttime}  AND end_time>#{currenttime} and people>signed_people")
    List<Competition> ListAttendCompetition(@Param("currenttime") Date currenttime);

    //查看可报名竞赛(老师)

    @Select("select * from c where start_time<#{currenttime}  AND end_time>#{currenttime} and academy=#{academy} and id not in (SELECT competition_id FROM apply WHERE user_id=#{Uid})")
    List<Competition> TListAttendCompetition(@Param("currenttime")Date currenttime, @Param("academy")String academy,@Param("Uid")int Uid);


    //查看我参加的竞赛

    @Select("select * from c where id in(select competition_id from apply where group_id in(SELECT group_id from member where user_id=#{uid})) or id in(select competiton_id from apply where user_id=#{uid} and steps=4)")
    List<Competition> findMyC(@Param("uid")int uid);


    //查看我的竞赛获奖信息

    @Select("SELECT * FROM Reward WHERE group_id IN(SELECT group_id FROM member WHERE user_id=#{Uid})")
    List<Reward> ListReward(@Param("Uid")int Uid);


    //竞赛获奖信息模糊查询

    @Select("SELECT * FROM Reward WHERE CONCAT(IFNULL(`comprtition_name`,''),IFNULL(`group_name`,''),IFNULL(`cup`,''),IFNULL(intro,'')) LIKE '%${keyword}%' and group_id IN(SELECT group_id FROM member WHERE user_id=#{Uid})")
    List<Reward> searchR(@Param("keyword")String keyword, @Param("Uid")int Uid);


    //老师选择指导哪些竞赛

    @Insert("insert into apply(competition_id,user_id,type) value(#{Cid},#{Uid},'teacher')")
    void chooseToGuide(@Param("Cid")int Cid,@Param("Uid")int Uid);

    //找出对应的竞赛名

    @Select("select name from c where id=#{id}")
    String findCname(@Param("id")int id);

    //找出对应的小组名

    @Select("select name from `group` where id=#{id}")
    String findGname(@Param("id")int id);



    //统计竞赛参加人数
    @Select("SELECT COUNT(id) from member WHERE group_id IN(SELECT group_id FROM apply WHERE steps=4 AND competition_id=#{cid})")
    Integer countPeople(@Param("cid")int cid);

    //修改参赛人数
    @Update("update c set signed_people=#{count} where id=#{cid}")
    void  updatepeople(@Param("count")Integer count,@Param("cid")Integer cid);

    //统计竞赛参加人数(个人)
    @Select("SELECT COUNT(user_id) from apply WHERE steps=4 AND competition_id=#{cid}")
    Integer countONEPeople(@Param("cid")int cid);

    //修改参赛人数
    @Update("update c set signed_people=#{count} where id=#{cid}")
    void  updateONEpeople(@Param("count")Integer count,@Param("cid")Integer cid);


    //查询竞赛id
    @Select("select id from c where name=#{name}")
    Integer findID(String name);


    //查询报名状态
    @Select("SELECT c.`name`,apply.steps from apply,c WHERE user_id=#{uid} AND c.id=apply.competition_id;")
    List<stauts> check(Integer uid);

    @Update("update c set signed_people=-1 where id=#{cid}")
    void updateSigned_people(Integer cid);

    //判断竞赛类型
    @Select("select max_people from c where id=#{cid}")
    Integer findCType(Integer cid);
}
