package com.wc.entity;

import lombok.Data;

@Data
public class Reward {
    Integer id;
    Integer competition_id;
    Integer group_id;
    Integer user_id;
    String cup;
    String group_name;
    String competition_name;
    String user_name;

//    public Reward(Integer id,Integer competition_id, Integer group_id,String cup, String intro, String competition_name, String group_name) {
//        this.id = id;
//        this.competition_id = competition_id;
//        this.group_id = group_id;
//        this.cup = cup;
//        this.intro = intro;
//        this.competition_name = competition_name;
//        this.group_name = group_name;
//    }
}

