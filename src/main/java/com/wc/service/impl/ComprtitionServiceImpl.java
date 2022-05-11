package com.wc.service.impl;

import com.wc.dao.CompetitionDao;
import com.wc.entity.Competition;
import com.wc.entity.Reward;
import com.wc.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service("competitionService")
public class ComprtitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;


    @Override
    public List<Competition> LISTC(String academy) {
        List<Competition> CompetitionList =competitionDao.ListC(academy);
        return CompetitionList;
    }

    @Override
    public List<Competition> SListC() {
        return competitionDao.SListC();
    }

    @Override
    public List<Competition> TsearchC(String keyword, String academy) {
//        List<Competition> searchResult =competitionDao.TsearchC(keyword,academy);
//        return searchResult;
        return null;
    }

    @Override
    public List<Competition> SsearchC(String keyword,Date Stime,Date Etime) {
        return competitionDao.SsearchC(keyword,Stime,Etime);
    }

    @Override
    public List<Competition> SsearchAC(String keyword, Date currenttime ,int Uid) {
        return competitionDao.SsearchAC(keyword, currenttime,Uid);
    }

    @Override
    public List<Competition> ListAttendCompetition(Date currenttime) {
        List<Competition> Result=competitionDao.ListAttendCompetition(currenttime);
        return Result;
    }

    @Override
    public List<Competition> TListAttendCompetition(Date currenttime, String academy,int Uid) {
        List<Competition> Result=competitionDao.TListAttendCompetition(currenttime, academy,Uid);
        return Result;
    }

    @Override
    public List<Reward> ListReward(int Uid) {
        List<Reward> rewards=competitionDao.ListReward(Uid);
        return rewards;
    }

    @Override
    public List<Reward> searchR(String keyword, int Uid) {
        return competitionDao.searchR(keyword, Uid);
    }

    @Override
    public Integer findID(String name) {
        return competitionDao.findID(name);
    }

    @Override
    public String publish(Competition competition) {
        if(competitionDao.checkPublish(competition)==null){
            competitionDao.publish(competition);
            return "发布成功";
        }else{
            return "发布失败(重复发布)";
        }
    }

    @Override
    public void updatesigned_people(Integer cid) {
        competitionDao.updateSigned_people(cid);
    }

    @Override
    public Integer findCType(Integer cid) {
        return competitionDao.findCType(cid);
    }

    @Override
    public List<Competition> findMyC(int Uid) {
        List<Competition> CList=competitionDao.findMyC(Uid);
        return CList;
    }

    @Override
    public void chooseToGuide(int Cid, int Uid) {
        competitionDao.chooseToGuide(Cid, Uid);
    }

    @Override
    public Integer countPeople(Integer cid) {
        return competitionDao.countPeople(cid);
    }

    @Override
    public void updatepeople() {
        List<Competition> list=competitionDao.SListC();
        for(Competition r:list){
            Integer sum;
            if(competitionDao.findCType(r.getId())>1){
                sum=competitionDao.countPeople(r.getId());
            }else {
                sum=competitionDao.countONEPeople(r.getId());
            }

            competitionDao.updatepeople(sum,r.getId());
        }
    }

}
