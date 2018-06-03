package Action;

import Utils.Error;
import Utils.ResponseObject;
import Utils.SqlManager;
import model.userInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterAction extends IAction {
    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";
    private static final String RequestParamKey_UserEmail = "email";
    private static final String RequestParamKey_UserTelPhone = "telPhone";


    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Connection dbConnection = null;
        Statement stmt = null;
        try {
            String userName = getParam(request, RequestParamKey_UserName, "");
            String password = getParam(request, RequestParamKey_UserPassword, "");
            String email = getParam(request, RequestParamKey_UserEmail, "");
            String telPhone = getParam(request, RequestParamKey_UserTelPhone, "");
            dbConnection = SqlManager.getConnection();
            stmt = dbConnection.createStatement();

            String sqlStr = "select * from user_info where username = '" + userName + "' limit 1;";
            stmt.execute(sqlStr);
            ResultSet resultSet = stmt.getResultSet();
//        int updateCount = stmt.getUpdateCount();// 获取受影响的行数
//        // 如果查询到结果
            if (resultSet.next()) {
                ResponseObject responseObject = ResponseObject.getFailResponse(
                        Error.errorCode_Register_repeat, Error.getErrorMsg_Register_repeat());
                responseObject.send(response);
            } else {
                String sqlStr_register = "insert into user_info(username,password,email,telphone) values('"
                        +
                        userName
                        +
                        "','"
                        +
                        password
                        +
                        "','"
                        +
                        email
                        +
                        "','"
                        +
                        telPhone
                        +
                        "');";
                stmt.execute(sqlStr_register);
                ResultSet resultSet_register = stmt.getResultSet();

                int updateCount = stmt.getUpdateCount(); // 影响的行数

                if (updateCount > 0) {
                    userInfo info = new userInfo(userName,"", email, telPhone,"","","",0);

                    ResponseObject responseObject = ResponseObject
                            .getSuccessResponse(info);
                    responseObject.send(response);
                } else {
                    ResponseObject responseObject = ResponseObject.getFailResponse(
                            Error.errorCode_Register_fail, Error.getErrorMsg_Register_fail());
                    responseObject.send(response);
                }
            }
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
