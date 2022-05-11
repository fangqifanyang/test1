package com.wc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Competition {
    Integer id;
    String name;
    String intro;
    String academy;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
    public Date getStart_time() {
        return start_time;
    }
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
    public Date getEnd_time() {
        return end_time;
    }

    Integer people;
    Integer max_people;
    Date start_time;
    Date end_time;
    String tag;
    Integer signed_people;

}
