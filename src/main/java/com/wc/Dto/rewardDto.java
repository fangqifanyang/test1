package com.wc.Dto;

import lombok.Data;

@Data
public class rewardDto {
    Integer id;
    Integer competition_id;
    Integer group_id;
    String cup;
    String intro;
    String competition_name;
    String group_name;
}
