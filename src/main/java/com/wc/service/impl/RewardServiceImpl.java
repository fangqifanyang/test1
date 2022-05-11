package com.wc.service.impl;

import com.wc.Dto.rewardDto;
import com.wc.dao.CompetitionDao;
import com.wc.dao.GroupDao;
import com.wc.dao.RewardDao;
import com.wc.entity.Reward;
import com.wc.entity.RewardVo;
import com.wc.service.RewardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("rewardService")
public class RewardServiceImpl implements RewardService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private CompetitionDao competitionDao;

    @Autowired
    private RewardDao rewardDao;

    @Override
    public Boolean checkR(Reward reward) {
        List<Reward> rewards= rewardDao.checkR( reward);
        if(rewards.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public void insertR(Reward reward) {
        rewardDao.insertR(reward);
    }

    @Override
    public void updateR(Reward reward) {
        rewardDao.updateR(reward);
    }


    @Override
    public void create(Integer cid) {
        rewardDao.create(cid);
    }

    @Override
    public void drop(Integer cid) {
        rewardDao.drop(cid);
    }

    @Override
    public void insert(String tablename, Integer group_id, String group_name, String cup) {
        rewardDao.insert(tablename, group_id, group_name, cup);
    }

    @Override
    public void createONE(Integer cid) {
        rewardDao.createONE(cid);
    }

    @Override
    public void insertONE(String tablename, Integer user_id, String user_name, String cup) {
        rewardDao.insertONE(tablename, user_id, user_name, cup);
    }

    @Override
    public boolean checkRONE(Reward r) {
        List<Reward> rewards= rewardDao.checkRONE( r);
        if(rewards.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public void insertRONE(Reward r) {
        rewardDao.insertRONE(r);
    }

    @Override
    public void updateRONE(Reward r) {
        rewardDao.updateRONE(r);
    }

    @Override
    public List<RewardVo> findReward(Integer uid, String keyword) {
        List<RewardVo> List1=rewardDao.findTeamReward(uid, keyword);
        List<RewardVo> List2=rewardDao.findOneReward(uid, keyword);
        for(RewardVo r:List2){
            List1.add(r);
        }
        return List1;
    }

    @Override
    public List<RewardVo> findTeacherReward(Integer uid, String keyword) {
        return rewardDao.findTeacherReward(uid, keyword);
    }


    @Override
    public List<Reward> ListReward(Integer uid) {
        List<Reward> rewards=rewardDao.ListReward(uid);
        for(Reward r:rewards){
            r.setCompetition_name(competitionDao.findCname(r.getCompetition_id()));
            r.setGroup_name(groupDao.findGname(r.getGroup_id()));
        }
        return rewards;
    }

    private List<rewardDto> e2d(List<Reward> userList) {
        if(userList==null||userList.size()==0){
            return null;
        }
        List<rewardDto> dtoList=new ArrayList<>();
        for(Reward u:userList){
            rewardDto dto=new rewardDto();
            BeanUtils.copyProperties(u,dto);
            dto.setCompetition_name(competitionDao.findCname(u.getCompetition_id()));
            dto.setGroup_name(groupDao.findGname(u.getGroup_id()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
