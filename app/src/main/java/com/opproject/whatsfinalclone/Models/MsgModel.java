package com.opproject.whatsfinalclone.Models;

public class MsgModel {
    String uId , msg , msgId;
    Long time;

    public MsgModel(){}

    public MsgModel(String uId, String msg) {
        this.uId = uId;
        this.msg = msg;
    }

    public MsgModel(String uId, String msg, Long time) {
        this.uId = uId;
        this.msg = msg;
        this.time = time;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
