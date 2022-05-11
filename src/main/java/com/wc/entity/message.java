package com.wc.entity;

import lombok.Data;

@Data
public class message {
    Integer id;
    Integer send_id;
    Integer received_id;
    Integer group_id;
    Integer competition_id;
    String type;
    String content;
    String response;
}
