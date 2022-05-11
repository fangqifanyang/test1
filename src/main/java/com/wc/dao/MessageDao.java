package com.wc.dao;

import com.wc.entity.message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageDao {


    //接受小组的邀请
    @Update("update apply set teacher_id=#{Uid} where group_id=#{Gid} and competition_id=#{Cid}")
    void Accept(@Param("Uid")int Uid,@Param("Gid")int Gid,@Param("Cid")int Cid);
    //查询被我指导的人
    @Select("select user_id from apply where group_id=#{Gid} AND type='teacher' and competition_id=#{Cid}")
    List<Integer> findMyU(@Param("Gid")int Gid,@Param("Cid")int Cid);
    //查询是否已有其他老师接受该小组邀请
    @Select("select teacher_id from apply where competition_id=#{Cid} and group_id=#{Gid} and type='leader'")
    Integer findOther(@Param("Cid")int Cid,@Param("Gid")int Gid);

    //查看未读消息(邀请)

    @Select("select * from message  where received_id=#{id} and response='unread'")
    List<message> ListM(@Param("id")int id);

    //读消息并接受

    @Update("update message set response='Accept' where id=#{Mid}")
    void readAccept(@Param("Mid")int Mid);

    //读消息并拒绝

    @Update("update message set response='refuse' where id=#{Mid}")
    void readRefuse(@Param("Mid")int Mid);

    //接受发现太迟了

    @Update("update message set response='alreadly accepted' where id=#{Mid}")
    void readAA(@Param("Mid")int Mid);

    @Update("update message set response='read' where id=#{Mid}")
    void read(@Param("Mid")int Mid);

    //返回消息

    @Insert("insert into message(send_id,received_id,group_id,competition_id,type,content,response) value(#{Sid},#{Rid},#{Gid},#{Cid},'4',#{content},'unread')")
    void returnMessage(@Param("Sid")int Sid,@Param("Rid")int Rid,@Param("Gid")int Gid,@Param("Cid")int Cid,@Param("content")String content);

    //接受进入下一步

    @Update("update apply set steps=4 where user_id=#{Sid} and competition_id=#{Cid} and group_id=#{Gid}")
    void AcceptNext(@Param("Sid")int Sid,@Param("Cid")int Cid,@Param("Gid")int Gid);



    //查看已读消息(邀请)

    @Select("select * from message  where received_id=#{id} and response !='unread'")
    List<message> ListMR(@Param("id")int id);

    //找出对应的竞赛名

    @Select("select name from c where id=#{id}")
    String findCname(@Param("id")int id);

    //找出对应的小组名

    @Select("select name from `group` where id=#{id}")
    String findGname(@Param("id")int id);

    //找出对应的人名

    @Select("select name from user where id=#{id}")
    String findname(@Param("id")int id);

    //找出对应的消息

    @Select("select * from message where id=#{Mid}")
    message findMessage(@Param("Mid")int Mid);


    //组长向学生发送邀请
    @Insert("insert into message (send_id,received_id,group_id,type,content,response) value(#{sender_id},#{received_id},#{group_id},'1','邀请你加入我们小组','未读')")
    void sendInvite(message m);
}
