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

public class LoginAction extends IAction {

    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";



    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
       String sql = "select * from user_info;";
        Connection dbConnection = null;
        Statement stmt = null;

        try{

//        try {
            String userName = getParam(request, RequestParamKey_UserName, "");
            String password = getParam(request, RequestParamKey_UserPassword, "");
            dbConnection = SqlManager.getConnection();
            stmt = dbConnection.createStatement();

            String sqlStr = "select * from user_info where username = '"+userName+"' and password = '"+password+"' limit 1;";
            stmt.execute(sqlStr);
            ResultSet resultSet = stmt.getResultSet();
//        int updateCount = stmt.getUpdateCount();// 获取受影响的行数
            // 如果查询到结果
            if (resultSet.next()) {
                String is_first_login = resultSet.getString("is_first_login");
                String email = resultSet.getString("email");

                String nickname = resultSet.getString("nickname");
                String  telphone = resultSet.getString("telphone");
                String sex = resultSet.getString("sex");
                String  sign = resultSet.getString("sign");
                String url = resultSet.getString("url");
                int match_times = resultSet.getInt("match_times");
                userInfo info = new userInfo(userName,nickname,email,telphone,sex,sign,url,match_times);
                info.password = password;
                info.is_first_login  = is_first_login;

                if(is_first_login .equals("0")){
                    String sqlStr_loginTimes = "update user_info set is_first_login ='1'  where username = '"
                            +
                                userName
                            +
                                "';";
                    stmt.execute(sqlStr_loginTimes);
                }

                ResponseObject responseObject = ResponseObject
                        .getSuccessResponse(info);
                responseObject.send(response);
            }else{
                ResponseObject responseObject = ResponseObject.getFailResponse(
                        Error.errorCode_LoginFail, Error.getCreateFailMsg());
                responseObject.send(response);
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
