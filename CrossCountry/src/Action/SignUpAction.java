package Action;

import Utils.Error;
import Utils.ResponseObject;
import Utils.SqlManager;
import model.SignInfo;
import model.userInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpAction extends IAction {
    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";
    private static final String RequestParamKey_UserMatchName = "matchname";


    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String sql = "select * from user_info;";
        Connection dbConnection = null;
        Statement stmt = null;

        try{

//        try {
            String userName = getParam(request, RequestParamKey_UserName, "");
            String password = getParam(request, RequestParamKey_UserPassword, "");
            String matchname = getParam(request, RequestParamKey_UserMatchName, "");
            dbConnection = SqlManager.getConnection();
            stmt = dbConnection.createStatement();


            String sqlStr_login = "select * from user_info where username = '"+userName+"' and password = '"+password+"' limit 1;";
            System.out.println(sqlStr_login);
            stmt.execute(sqlStr_login);
            ResultSet resultSet = stmt.getResultSet();
//        int updateCount = stmt.getUpdateCount();// 获取受影响的行数
            // 如果查询到结果,说明登陆成功
            if (resultSet.next()) {


                String sqlStr_sgin_test = "select umid from user_match where username = '"
                        +
                        userName
                        +
                        "' and matchname = '"
                        +
                        matchname
                        +
                        "';";
                stmt.execute(sqlStr_sgin_test);
                ResultSet resultSet_sign_test = stmt.getResultSet();
                if(resultSet_sign_test.next()){
                    ResponseObject responseObject = ResponseObject.getFailResponse(
                            Error.errorCode_Sign_hasDone, Error.getErrorMsg_Sign_hasDone());
                    responseObject.send(response);
                    return;
                }


                String sqlStr_update_1 = "select miid from match_info where matchname = '"
                        +
                        matchname
                        +
                        "';";
                stmt.execute(sqlStr_update_1);
                ResultSet resultSet1 = stmt.getResultSet();
                if(resultSet1.next()){
                    String matchTable = "matchloc_"+
                            userName
                            +
                            "_"
                            +
                            resultSet1.getInt("miid");


                    String sqlStr_create = "create table "
                            +
                            matchTable
                            +
                            "(umid serial,time timestamp, longitude double precision,  latitude double precision);";
                    stmt.execute(sqlStr_create);

                    String sqlStr_insert = "insert into user_match(username,matchname) values('"
                            +
                            userName
                            +
                            "','"
                            +
                            matchname
                            +
                            "');";
                    stmt.execute(sqlStr_insert);
                    int updateCount = stmt.getUpdateCount();// 获取受影响的行数
                    // 如果查询到结果
                    if (updateCount>0) {//如果插入成功
                        String sqlTime = "update user_info set match_times = (match_times+1) where username= '"
                                +
                                    userName
                                +
                                    "';";
                        stmt.execute(sqlTime);
                        System.out.println(sqlTime);
                        String sqlStr_sgin = "select umid from user_match where username = '"
                                +
                                userName
                                +
                                "' and matchname = '"
                                +
                                matchname
                                +
                                "';";
                        stmt.execute(sqlStr_sgin);
                        ResultSet resultSet_sign = stmt.getResultSet();
                        if(resultSet_sign.next()){
                            SignInfo signInfo = new SignInfo();
                            signInfo.id = resultSet_sign.getInt("umid");
                            ResponseObject responseObject = ResponseObject
                                    .getSuccessResponse(signInfo);
                            responseObject.send(response);
                        }else {
                            ResponseObject responseObject = ResponseObject.getFailResponse(
                                    Error.errorCode_Sign_fail, Error.getErrorMsg_Sign_fail());
                            responseObject.send(response);
                        }
                    }else{
                        ResponseObject responseObject = ResponseObject.getFailResponse(
                                Error.errorCode_Sign_fail, Error.getErrorMsg_Sign_fail());
                        responseObject.send(response);
                    }
                }

            }else{
                ResponseObject responseObject = ResponseObject.getFailResponse(
                        Error.errorCode_Sign_Invalid, Error.getErrorMsg_Sign_Invalid());
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
