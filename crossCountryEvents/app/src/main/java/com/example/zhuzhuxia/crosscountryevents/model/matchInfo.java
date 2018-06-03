package com.example.zhuzhuxia.crosscountryevents.model;

import java.sql.Timestamp;

/**
 * Created by zhuzhuxia on 2018/5/22.
 */

public class matchInfo {
    public String matchname; //比赛名字
    public String sponser;//主办单位
    public String undertaker;//承包单位
    public Timestamp matchtime;//比赛时间
    public String matchsite;//比赛地点
    public String matchstate;//比赛状态

    public static final String MATCH_STATE_WAITING = "0";
    public static final String MATCH_STATE_ING = "1";
    public static final String MATCH_STATE_DONE = "2";

    public matchInfo(String matchname, String sponser, String undertaker, Timestamp matchtime, String matchsite, String matchstate) {
        this.matchname = matchname;
        this.sponser = sponser;
        this.undertaker = undertaker;
        this.matchtime = matchtime;
        this.matchsite = matchsite;
        this.matchstate = matchstate;
    }
}
