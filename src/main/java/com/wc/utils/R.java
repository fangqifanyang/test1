package com.wc.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
  
    private Boolean success;
    
    private Integer code;
    
    private String message;
    
    private Map<String, Object> data = new HashMap<String, Object>();
    
    private R(){}
    
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(20000);
        r.setMessage("成功");
        return r;
    }
    
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(20001);
        r.setMessage("失败");
        return r;
    }

    public static R okC(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(20005);
        r.setMessage("成功报名");
        return r;
    }

    public static R errorC(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(20004);
        r.setMessage("重复报名");
        return r;
    }

    public static R NTR(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(20002);
        r.setMessage("已有指导老师");
        return r;
    }
    public static R Acceptok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(20003);
        r.setMessage("接受邀请");
        return r;
    }

    public static R refuse(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(20006);
        r.setMessage("拒绝邀请");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}