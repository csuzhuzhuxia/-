package com.example.zhuzhuxia.crosscountryevents.Talk.Comment;


import com.example.zhuzhuxia.crosscountryevents.Talk.Talk.User;

/**
 * Created by zhuzhuxia on 2018/2/26.
 */

public class Comment {
    public User user;
    public String text;//评论的内容

    public Comment(){

    }

    public Comment(User user, String content){
        this.user = user;
        this.text = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return text;
    }

    public void setContent(String content) {
        this.text = content;
    }
}
