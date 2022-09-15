package com.opproject.whatsfinalclone.Models;

public class UsersModel {

    String Uname , Uid , UEmail , UPassWord , LastSeen , ULogo , about ;

    public UsersModel(){}

    public UsersModel(String uname, String UEmail, String UPassWord) {
        Uname = uname;
        this.UEmail = UEmail;
        this.UPassWord = UPassWord;
    }

    public UsersModel(String uname, String uid, String UEmail, String UPassWord, String lastSeen, String ULogo, String about) {
        Uname = uname;
        Uid = uid;
        this.UEmail = UEmail;
        this.UPassWord = UPassWord;
        LastSeen = lastSeen;
        this.ULogo = ULogo;
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getUEmail() {
        return UEmail;
    }

    public void setUEmail(String UEmail) {
        this.UEmail = UEmail;
    }

    public String getUPassWord() {
        return UPassWord;
    }

    public void setUPassWord(String UPassWord) {
        this.UPassWord = UPassWord;
    }

    public String getLastSeen() {
        return LastSeen;
    }

    public void setLastSeen(String lastSeen) {
        LastSeen = lastSeen;
    }

    public String getULogo() {
        return ULogo;
    }

    public void setULogo(String ULogo) {
        this.ULogo = ULogo;
    }
}
