package com.wc.service;

import com.wc.Dto.rewardDto;
import com.wc.entity.Reward;
import com.wc.entity.RewardVo;

import java.util.List;

public interface RewardService {

    Boolean checkR(Reward reward);
    void insertR(Reward reward);
    void updateR(Reward reward);
    List<Reward> ListReward(Integer uid);
    void create(Integer cid);
    void drop(Integer cid);
    void insert(String tablename,Integer group_id,String group_name,String cup);

    void createONE(Integer cid);

    void insertONE(String tablename, Integer user_id, String user_name, String cup);

    boolean checkRONE(Reward r);

    void insertRONE(Reward r);

    void updateRONE(Reward r);

    List<RewardVo> findReward(Integer uid,String keyword);
    List<RewardVo> findTeacherReward(Integer uid,String keyword);
}
