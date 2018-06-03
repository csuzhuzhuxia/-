package com.example.zhuzhuxia.crosscountryevents.myInfo;

import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;
import com.example.zhuzhuxia.crosscountryevents.ResponseObject;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

import java.io.IOException;

/**
 * Created by zhuzhuxia on 2018/5/21.
 */

public class EdtiRequest extends baseRequest {

    private static final String Action = "http://" +
            CrossCountryApplication.getIp()
            +
            ":8080/CrossCountry/IndexServlet?";



    private static final String RequestParamKey_Action = "action";
    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_changeFiled = "filed";
    private static final String RequestParamKey_changeValue = "value";
    public static class EditParam {
        public String action;
        public String username;
        public String changeFiled;
        public String changeValue;
    }

    public String getUrl(EdtiRequest.EditParam param) {
        return Action
                + "&" + RequestParamKey_Action + "=" + param.action
                + "&" + RequestParamKey_UserName + "=" + param.username
                + "&" + RequestParamKey_changeFiled + "=" + param.changeFiled
                + "&" + RequestParamKey_changeValue + "=" + param.changeValue
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
        EditResponse responseObject = gson.fromJson(body, EditResponse.class);
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
