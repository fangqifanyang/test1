package com.wc.controller;


import com.wc.entity.Competition;
import com.wc.entity.Reward;
import com.wc.entity.User;
import com.wc.service.CompetitionService;
import com.wc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/ListCompetition")
    private R ListC(HttpSession session){
        User user=(User) session.getAttribute("user");
        List<Competition> competitionList =competitionService.LISTC(user.getAcademy());
        return R.ok().data("ListCompetition", competitionList);
    }

    @PostMapping("/TsearchCompetition")
    private R Tsearch(@RequestParam("keyword") String keyword, HttpSession session){
        User user=(User) session.getAttribute("user");
        String academy=user.getAcademy();
        List<Competition> searchResult=competitionService.TsearchC(keyword,academy);
        return R.ok().data("searchResult",searchResult);
    }

    @PostMapping("/SsearchCompetition")
    private R Ssearch(@RequestParam("keyword") String keyword){
        //List<Competition> searchResult=competitionService.SsearchC(keyword);
        return R.ok();//.data("searchResult",searchResult);
    }

    @PostMapping("/SsearchAttendCompetition")
    private R SsearchAC(@RequestParam("keyword") String keyword,@RequestParam("Uid") int Uid){
        Date date = new Date();
        List<Competition> searchResult=competitionService.SsearchAC(keyword,date, Uid);
        return R.ok().data("searchResult",searchResult);
    }



    @PostMapping("/ListAC")
    private R ListAC(){

        Date date = new Date();
        competitionService.updatepeople();
        List<Competition> result=competitionService.ListAttendCompetition(date);

        System.out.println(date);

        return R.ok().data("ListAC",result);
    }

    @PostMapping("/TListAC")
    private R TListAC(HttpSession session){
        Date date =new Date();
        competitionService.updatepeople();
        User user=(User) session.getAttribute("user");
        List<Competition> result=competitionService.TListAttendCompetition(date,user.getAcademy(),user.getId());
        return R.ok().data("TListAC",result);
    }

    @PostMapping("/ListReward")
    private R ListR(HttpSession session){
        User user=(User) session.getAttribute("user");
        List<Reward> rewardList=competitionService.ListReward(user.getId());
        return R.ok().data("rewardList",rewardList);
    }

    @PostMapping("/searchReward")
    private R searcR(@RequestParam("keyword") String keyword,@RequestParam("Uid")Integer Uid){
        List<Reward> searchResult=competitionService.searchR(keyword,Uid);
        return R.ok().data("searchResult",searchResult);
    }


    @PostMapping("/findMyC")
    private R findMyC(HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Competition> CList=competitionService.findMyC(user.getId());
        return R.ok().data("CList",CList);
    }

    @PostMapping("/ChooseToGuide")
    private R chooseToGuide(@RequestParam("Cid")int Cid,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Competition> competitionList=competitionService.findMyC(user.getId());

        //重复报名
        for(Competition c : competitionList){
            if(c.getId()==Cid){
                return R.errorC();
            }
        }

        competitionService.chooseToGuide(Cid,user.getId());
        return R.okC();
    }


    @PostMapping("/publish")
    private R publish(Competition competition){
        String res=competitionService.publish(competition);
        return R.ok().data("res",res);
    }

}
