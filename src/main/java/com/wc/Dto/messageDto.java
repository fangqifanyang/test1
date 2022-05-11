package com.wc.Dto;

import lombok.Data;

    @Data
    public class messageDto {
        Integer id;
        Integer send_id;
        Integer received_id;
        Integer group_id;
        Integer competition_id;
        String sender_name;
        String group_name;
        String competition_name;
        String type;
        String content;
        String response;
    }

