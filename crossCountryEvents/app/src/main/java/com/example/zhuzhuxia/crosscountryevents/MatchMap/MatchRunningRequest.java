package com.example.zhuzhuxia.crosscountryevents.MatchMap;

import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;
import com.example.zhuzhuxia.crosscountryevents.ResponseObject;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

import java.io.IOException;

/**
 * Created by zhuzhuxia on 2018/5/24.
 */

public class MatchRunningRequest extends baseRequest {


    private static final String Action = "http://" +
                CrossCountryApplication.getIp()
            +
            ":8080/CrossCountry/IndexServlet?";

    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";
    private static final String RequestParamKey_UserMatchName = "matchname";
    private static final String RequestParamKey_LONGITUDE = "longitude";
    private static final String RequestParamKey_LATITUDE = "latitude";

    private static final String RequestParamKey_Action = "action";
    public static class MatchRecordParam {
        public String action;
        public String username;
        public String matchname;
        public String password;
        public double longitude;
        public double latitude;
    }

    public String getUrl(MatchRunningRequest.MatchRecordParam param) {
        return Action
                + "&" + RequestParamKey_Action + "=" + param.action
                + "&" + RequestParamKey_UserName + "=" + param.username
                + "&" + RequestParamKey_UserPassword + "=" + param.password
                + "&" + RequestParamKey_UserMatchName + "=" + param.matchname
                + "&" + RequestParamKey_LONGITUDE + "=" + param.longitude
                + "&" + RequestParamKey_LATITUDE + "=" + param.latitude
                ;
    }

    @Override
    protected void onFail(IOException e) {
        sendFailMsg(-100, e.getMessage());
    }

    @Override
    protected void onResponseFail(int code) {
        sendFailMsg(code, "服务出现异常");
    }

    @Override
    protected void onResponseSuccess(String body) {
        MatchRecordResponse responseObject = gson.fromJson(body, MatchRecordResponse.class);
        if (responseObject == null) {
            sendFailMsg(-101, "数据格式错误");
            return;
        }

        if (responseObject.code.equals(ResponseObject.CODE_SUCCESS)) {
            sendSuccMsg(responseObject.data);
        } else if (responseObject.code.equals(ResponseObject.CODE_FAIL)) {
            sendFailMsg(Integer.valueOf(responseObject.errCode), responseObject.errMsg);
        }
    }

}
