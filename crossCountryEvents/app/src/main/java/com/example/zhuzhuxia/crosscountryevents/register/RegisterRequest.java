package com.example.zhuzhuxia.crosscountryevents.register;

import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;
import com.example.zhuzhuxia.crosscountryevents.ResponseObject;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

import java.io.IOException;

/**
 * Created by zhuzhuxia on 2018/5/21.
 */

public class RegisterRequest extends baseRequest {

    private static final String Action = "http://" +
            CrossCountryApplication.getIp() +
            ":8080/CrossCountry/IndexServlet?";

    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";
    private static final String RequestParamKey_Action = "action";
    public static class RegisterParam {
        public String action;
        public String username;
        public String password;
        public String email;
        public String telphone;
    }

    public String getUrl(RegisterRequest.RegisterParam param) {
        return Action
                + "&" + RequestParamKey_Action + "=" + param.action
                + "&" + RequestParamKey_UserName + "=" + param.username
                + "&" + RequestParamKey_UserPassword + "=" + param.password
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
        RegisterResponse responseObject = gson.fromJson(body, RegisterResponse.class);
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
