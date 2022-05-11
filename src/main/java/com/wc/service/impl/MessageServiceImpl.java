package com.wc.service.impl;

import com.wc.Dto.messageDto;
import com.wc.dao.MessageDao;
import com.wc.dao.UserDao;
import com.wc.entity.message;
import com.wc.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;


    @Override
    public void Accept(int Gid, int Uid, int Cid) {
        messageDao.Accept(Uid,Gid,Cid);
    }


    @Override
    public List<Integer> findMyU(int Gid,int Cid) {
        List<Integer> UList=messageDao.findMyU(Gid,Cid);
        return UList;
    }

    @Override
    public Boolean findOther(int Cid, int Gid) {
        Integer other=messageDao.findOther(Cid, Gid);
        if(other==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<messageDto> ListM(int Uid) {

        List<messageDto> messageList=e2d(messageDao.ListM(Uid));
        for(messageDto r:messageList){
            r.setCompetition_name(messageDao.findCname(r.getCompetition_id()));
            r.setGroup_name(messageDao.findGname(r.getGroup_id()));
            r.setSender_name(messageDao.findname(r.getSend_id()));
        }

        return messageList;
    }

    private List<messageDto> e2d(List<message> userList) {
        if(userList==null||userList.size()==0){
            return null;
        }
        List<messageDto> dtoList=new ArrayList<>();
        for(message u:userList){
            messageDto dto=new messageDto();
            BeanUtils.copyProperties(u,dto);
            dtoList.add(dto);
        }
        return dtoList;
    }



    @Override
    public void readAccept(int Mid) {
        messageDao.readAccept(Mid);
    }

    @Override
    public void readRefuse(int Mid) {
        messageDao.readRefuse(Mid);
    }

    @Override
    public void readAA(int Mid) {
        messageDao.readAA(Mid);
    }

    @Override
    public void read(int Mid) {
        messageDao.read(Mid);
    }

    @Override
    public void returnMessage(int Sid, int Rid, int Gid, int Cid,  String content ) {
        messageDao.returnMessage(Sid, Rid, Gid, Cid, content);
    }

    @Override
    public void AcceptNext(int Sid, int Cid, int Gid) {
        messageDao.AcceptNext(Sid, Cid, Gid);
    }


    @Override
    public List<messageDto> ListMR(int id) {
        List<messageDto> messageList=e2d(messageDao.ListMR(id));
        for(messageDto r:messageList){
            r.setCompetition_name(messageDao.findCname(r.getCompetition_id()));
            r.setGroup_name(messageDao.findGname(r.getGroup_id()));
            r.setSender_name(messageDao.findname(r.getSend_id()));
        }

        return messageList;
    }

    @Override
    public message findMessage(int Mid) {
        message messageList=messageDao.findMessage(Mid);
        return messageList;
    }

    @Override
    public void sendInvite(message m) {
        messageDao.sendInvite(m);
    }


}
