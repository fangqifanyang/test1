package com.wc.service;

import com.wc.entity.Competition;
import com.wc.entity.Reward;

import java.util.Date;
import java.util.List;

public interface CompetitionService {

    //查看竞赛(老师)
    List<Competition> LISTC(String academy);

    //查看所有竞赛
    List<Competition> SListC();

    //搜索竞赛(模糊查询)
    List<Competition> TsearchC(String keyword, String academy);

    //搜索竞赛(模糊查询)学生
    List<Competition> SsearchC(String keyword,Date Stime,Date Etime);

    //搜索可报名竞赛(模糊查询)学生
    List<Competition> SsearchAC(String keyword,Date currenttime,int Uid);

    //查看可报名竞赛(学生)
    List<Competition> ListAttendCompetition(Date currenttime);
    //查看可报名竞赛(老师)
    List<Competition> TListAttendCompetition(Date currenttime, String academy,int Uid);

    //查看我参加的竞赛(老师)
    List<Competition> findMyC(int Uid);
    //老师选择指导哪些竞赛
    void chooseToGuide(int Cid,int Uid);

    //统计竞赛报名人数
    Integer countPeople(Integer cid);

    //更新竞赛报名人数
    void updatepeople();

    //查看竞赛获奖信息
    List<Reward> ListReward(int Uid);

    //竞赛获奖信息模糊查询
    List<Reward> searchR(String keyword, int Uid);

    //查询竞赛id
    Integer findID(String name);


    //发布竞赛
    String publish(Competition competition);

    void updatesigned_people(Integer cid);

    Integer findCType(Integer cid);
}
