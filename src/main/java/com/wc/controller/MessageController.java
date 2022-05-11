package com.wc.controller;

import com.wc.Dto.messageDto;
import com.wc.entity.User;
import com.wc.entity.message;
import com.wc.service.MessageService;
import com.wc.service.UserService;
import com.wc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/Accept")
    private R Accept(@RequestParam("Mid")int Mid, HttpSession session){
        User user=(User)session.getAttribute("user");
        int Uid=user.getId();
        message m=messageService.findMessage(Mid);
        int Cid=m.getCompetition_id();
        int Gid=m.getGroup_id();
        int Sid=m.getSend_id();

        //判断该小组是否已有指导老师
        if(messageService.findOther(Cid,Gid)){
            messageService.readAA(Mid);
            return R.NTR();
        }

        List<Integer> UList=messageService.findMyU(Gid,Cid);
        for(Integer uid:UList){
                messageService.Accept(uid,Gid,Cid);
        }

        //设消息已读并接受
        messageService.AcceptNext(Sid,Cid,Gid);
        messageService.readAccept(Mid);
        messageService.returnMessage(Uid,Sid,Gid,Cid,"邀请已接受");
        return R.Acceptok();
    }

    @PostMapping("/Refuse")
    private R Refuse(@RequestParam("Mid")int Mid, HttpSession session){

        User user=(User)session.getAttribute("user");
        int Uid=user.getId();

        message m=messageService.findMessage(Mid);
        int Cid=m.getCompetition_id();
        int Gid=m.getGroup_id();
        int Sid=m.getSend_id();

        messageService.readRefuse(Mid);
        messageService.returnMessage(Uid,Sid,Gid,Cid,"邀请已拒绝");
        return R.refuse();
    }

    @PostMapping("/ListMessage")
    private R ListMessage(HttpSession session){
        User user=(User)session.getAttribute("user");
        int Uid=user.getId();
        List<messageDto> messageList=messageService.ListM(Uid);
        return R.ok().data("messageList",messageList);
    }

    @PostMapping("/sendInvite")
    private R sendInvite(message m){
        messageService.sendInvite(m);
        return R.ok();
    }

}
