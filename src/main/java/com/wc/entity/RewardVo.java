package com.wc.entity;

import lombok.Data;

@Data
public class RewardVo {
    String competition_name;
    String group_name;
    String cup;
    // 0 为个人 1为团队
    String type;
}
