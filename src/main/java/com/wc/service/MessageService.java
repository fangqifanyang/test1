package com.wc.service;

import com.wc.Dto.messageDto;
import com.wc.entity.message;

import java.util.List;

public interface MessageService {

    //老师接受学生邀请
    void Accept(int Uid,int Gid,int Cid);
    List<Integer> findMyU(int Gid ,int Cid );
    Boolean findOther(int Cid,int Gid);

    //查看消息(邀请)
    List<messageDto> ListM(int Uid);

    //读消息并接受
    void readAccept(int Mid);

    //读消息并拒绝
    void readRefuse(int Mid);

    void readAA(int Mid);

    void read(int Mid);

    void returnMessage(int Sid,int Rid,int Gid,int Cid,String content);

    void AcceptNext(int Sid,int Cid,int Gid);


    List<messageDto> ListMR(int id);

    message findMessage(int Mid);



    //组长向学生发送邀请
    void sendInvite(message m);
}
