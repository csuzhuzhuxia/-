package model;

import java.sql.Timestamp;

public class matchInfo {
    public String matchname; //比赛名字
    public String sponser;//主办单位
    public String undertaker;//承包单位
    public Timestamp matchtime;//比赛时间
    public String matchsite;//比赛地点
    public String matchstate;//比赛状态

    public matchInfo(String matchname, String sponser, String undertaker, Timestamp matchtime, String matchsite, String matchstate) {
        this.matchname = matchname;
        this.sponser = sponser;
        this.undertaker = undertaker;
        this.matchtime = matchtime;
        this.matchsite = matchsite;
        this.matchstate = matchstate;
    }


//    public String matcharea;//比赛区域
//    public String beginpoint;//起点坐标
//    public String endpoint;//终点坐标
}
