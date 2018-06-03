import Action.*;
import Utils.Error;
import Utils.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String RequestParamKey_Action = "action";
    private static final String RequestAction_Login = "Login";
    private static final String RequestAction_Register = "Register";
    private static final String RequestAction_edit = "Edit";
    private static final String RequestAction_SearchMatchList = "SearchMatchList";
    private static final String RequestAction_MyMatchList = "MyMatchList";
    private static final String RequestAction_signUpMatch = "signUpMatch";
    private static final String RequestAction_MatchRecord = "MatchRecord";
    private static final String RequestAction_HeartBeat = "heartBeat";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 处理用户的请求
        String action = request.getParameter(RequestParamKey_Action);
        if (action == null || "".equals(action)) {
            ResponseObject responseObject = ResponseObject.getFailResponse(
                    Error.errorCode_NoAction, Error.getNoActionMsg());
            responseObject.send(response);
            return;
        }
        try {
            if (RequestAction_Login.equals(action)) {
                // 进行登陆。
                new LoginAction().doAction(request, response);
            } else if (RequestAction_Register.equals(action)) {
                // 进行注册。
                new RegisterAction().doAction(request, response);
            }else if(RequestAction_edit.equals(action)){
                new EditAction().doAction(request,response);
            }else if(RequestAction_SearchMatchList.equals(action)){
                new MatchSearchAction().doAction(request,response);
            }else if(RequestAction_signUpMatch.equals(action)){
                new SignUpAction().doAction(request,response);
            }else if(RequestAction_MyMatchList.equals(action)){
                new MyMatchAction().doAction(request,response);
            }else if(RequestAction_MatchRecord.equals(action)){
                new MatchRunningAction().doAction(request,response);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // 数据库异常，返回错误信息
            ResponseObject responseObject = ResponseObject.getFailResponse(
                    Error.errorCode_Exception,
                    Error.getExceptionMsg(e.getMessage()));
            try {
                responseObject.send(response);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
