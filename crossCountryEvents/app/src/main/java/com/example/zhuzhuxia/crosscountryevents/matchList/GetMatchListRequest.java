package com.example.zhuzhuxia.crosscountryevents.matchList;

import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;
import com.example.zhuzhuxia.crosscountryevents.ResponseObject;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

import java.io.IOException;

/**
 * Created by zhuzhuxia on 2018/5/22.
 */

public class GetMatchListRequest extends baseRequest {

    private static final String Action = "http://" +
            CrossCountryApplication.getIp() +
            ":8080/CrossCountry/IndexServlet?";
    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";
    private static final String RequestParamKey_Action = "action";


    public static class MatchListParam {
        public String action;


    }

    public String getUrl(MatchListParam param) {
        return Action
                + "&" + RequestParamKey_Action + "=" + param.action;
    }


    public static class MyMatchListParam {
        public String action;
        public String username;
        public String password;
    }

    public String getUrl(MyMatchListParam param) {
        return Action
                + "&" + RequestParamKey_Action + "=" + param.action
                + "&" + RequestParamKey_UserName + "=" + param.username
                + "&" + RequestParamKey_UserPassword + "=" + param.password
                ;
    }

    @Override
    protected void onFail(IOException e) {
        sendFailMsg(-100,e.toString());
    }

    @Override
    protected void onResponseSuccess(String body) {
        MatchListResponseObject liveListresponseObject = gson.fromJson(body, MatchListResponseObject.class);
        if (liveListresponseObject == null) {
            sendFailMsg(-101, "数据格式错误");
            return;
        }

        if (liveListresponseObject.code.equals(ResponseObject.CODE_SUCCESS)) {
            sendSuccMsg(liveListresponseObject.data);
        } else if (liveListresponseObject.code.equals(ResponseObject.CODE_FAIL)) {
            sendFailMsg(Integer.valueOf(liveListresponseObject.errCode), liveListresponseObject.errMsg);
        }
    }

    @Override
    protected void onResponseFail(int code) {
        sendFailMsg(code,"服务器异常");
    }
}
