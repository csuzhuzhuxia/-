package model;

public class userInfo {
    public String  username;
    public String  password;
    public String  nickname;
    public String  email;
    public String  telPhone;
    public String sex;
    public String sign;
    public String url;
    public int match_times;
    public String is_first_login;
//    public int signupmatch;


    public userInfo( String username ,String  nickname,String  email,String  telPhone,String sex,String sign,String url,int match_times) {

        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.telPhone = telPhone;
        this.sex = sex;
        this.sign = sign;
        this.url = url;
        this.match_times = match_times;
    }
}
