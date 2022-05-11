package com.wc.entity;

import lombok.Data;

@Data
public class GroupVo {
    Integer group_id;
    Integer competition_id;
    String group_name;
    String competition_name;
}