package Action;

import Utils.Error;
import Utils.ResponseObject;
import Utils.SqlManager;
import model.SignInfo;
import model.matchInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyMatchAction extends IAction {

    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";


    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Connection dbConnection = null;
        Statement stmt = null;

        try{

//        try {
            String userName = getParam(request, RequestParamKey_UserName, "");
            String password = getParam(request, RequestParamKey_UserPassword, "");
//            String matchname = getParam(request, RequestParamKey_UserMatchName, "");
            dbConnection = SqlManager.getConnection();
            stmt = dbConnection.createStatement();


            String sqlStr_login = "select * from user_info where username = '"+userName+"' and password = '"+password+"' limit 1;";
            stmt.execute(sqlStr_login);
            ResultSet resultSet = stmt.getResultSet();
//        int updateCount = stmt.getUpdateCount();// 获取受影响的行数
            // 如果查询到结果,说明登陆成功
            if (resultSet.next()) {
                String sql_select = "select matchname,sponser,undertaker,matchsite,matchtime,matchstate from match_info where matchname in (select matchname from user_match where username = '"
                        +
                            userName
                        +
                            "');";
                stmt.execute(sql_select);
                ResultSet resultSet_select = stmt.getResultSet();

                List<matchInfo> matchInfos = new ArrayList<matchInfo>();


                while (resultSet_select.next()) {
                    matchInfo info = new matchInfo(resultSet_select.getString("matchname"),resultSet_select.getString("sponser"),resultSet_select.getString("undertaker"),
                            resultSet_select.getTimestamp("matchtime"),resultSet_select.getString("matchsite"),resultSet_select.getString("matchstate"));
                    matchInfos.add(info);
                }
                ResponseObject responseObject = ResponseObject
                        .getSuccessResponse(matchInfos);
                responseObject.send(response);

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
