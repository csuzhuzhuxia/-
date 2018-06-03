package com.example.zhuzhuxia.crosscountryevents.Talk.Talk;


import com.example.zhuzhuxia.crosscountryevents.Talk.Comment.Comment;

import java.util.ArrayList;

/**
 * Created by zhuzhuxia on 2018/2/26.
 */

public class Talk {
    public User user; //发说说的作者
    public String text;//发表的内容
    public ArrayList<Comment> comments;//发表的评论
    public int thumb_number;//点赞人数
    public boolean isThumb;

    public Talk(String name,int sourceId){
        this.user = new User(sourceId,name);
        this.text = "新华社韩国平昌2月25日电（记者 姬烨 耿学鹏）第二十三届冬季奥林匹克运动会25日晚在平昌奥林匹克体育场闭幕。中国作为下届冬奥会主办国，在闭幕式上奉献了《2022相约北京》8分钟文艺表演。国家主席习近平通过视频，向全世界发出诚挚邀请——2022年相约北京！";
        this.comments =null;
        thumb_number = 0;
        isThumb = false;
    }
}
