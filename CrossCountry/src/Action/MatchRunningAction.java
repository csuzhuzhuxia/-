package Action;

import Utils.Error;
import Utils.ResponseObject;
import Utils.SqlManager;
import model.MatchRecordInfo;
import model.userInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MatchRunningAction extends IAction {
    private static final String RequestParamKey_UserName = "username";
    private static final String RequestParamKey_UserPassword = "password";
    private static final String RequestParamKey_UserMatchName = "matchname";
    private static final String RequestParamKey_LONGITUDE = "longitude";
    private static final String RequestParamKey_LATITUDE = "latitude";




    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
//        System.out.println("ZHi 行了");
//        String sql = "select * from user_info;";
        Connection dbConnection = null;
        Statement stmt = null;

        try{

//
            String userName = getParam(request, RequestParamKey_UserName, "");
            String password = getParam(request, RequestParamKey_UserPassword, "");
            String matchname = getParam(request, RequestParamKey_UserMatchName, "");
            double longitude = Double.parseDouble(getParam(request, RequestParamKey_LONGITUDE, ""));
            double latitude = Double.parseDouble(getParam(request, RequestParamKey_LATITUDE, ""));
            dbConnection = SqlManager.getConnection();
            stmt = dbConnection.createStatement();


            String sqlStr = "select * from user_info where username = '"+userName+"' and password = '"+password+"' limit 1;";
            stmt.execute(sqlStr);
//            System.out.println(sqlStr);
            ResultSet resultSet = stmt.getResultSet();
            if(!resultSet.next()){
                ResponseObject responseObject = ResponseObject.getFailResponse(
                        Error.errorCode_Sign_Invalid, Error.getErrorMsg_Sign_Invalid());
                responseObject.send(response);
                return;
            }
            String sqlStr_1 = "select * from user_match where username = '"+userName+"' and matchname = '"+matchname+"' limit 1;";
            stmt.execute(sqlStr_1);
//            System.out.println(sqlStr_1);
            ResultSet resultSet1 = stmt.getResultSet();
//        int updateCount = stmt.getUpdateCount();// 获取受影响的行数
            // 如果查询到结果就是登陆成功
            if (resultSet1.next()) {

                String sqlStr_update = "update  user_match set longitude = "
                        +
                            longitude
                        +
                            "  where username = '"
                        +
                            userName
                        +
                            "' and matchname = '"
                        +
                            matchname
                        +
                            "';update  user_match set latitude = "
                        +
                            latitude
                        +
                            " where username = '"
                        +
                            userName
                        +
                        "' and matchname = '"
                        +
                            matchname
                        +
                            "';";



                stmt.execute(sqlStr_update);
//                System.out.println(sqlStr_update);

                String sqlStr_update_1 = "select miid from match_info where matchname = '"
                        +
                            matchname
                        +
                            "';";
                stmt.execute(sqlStr_update_1);
                ResultSet resultSet2 = stmt.getResultSet();
//                System.out.println(sqlStr_update_1);
                if(resultSet2.next()){
                    String matchTable = "matchloc_"+
                            userName
                            +
                            "_"
                            +
                            resultSet2.getInt("miid");

                    String  sqlStr_update_2= "insert into "
                            +
                            matchTable
                            +
                            "(longitude,latitude) values("
                            +
                            longitude
                            +
                            ","
                            +
                            latitude
                            +
                            ");";
//                    System.out.println(sqlStr_update_2);
                    stmt.execute(sqlStr_update_2);
//                    System.out.println(sqlStr_update_2);
//                ResultSet resultSet_update = stmt.getResultSet();

                    MatchRecordInfo matchRecordInfo = new MatchRecordInfo();
                    matchRecordInfo.isClosePunchPoint = false;
                    matchRecordInfo.isFinish = false;

                    ResponseObject responseObject = ResponseObject
                            .getSuccessResponse(matchRecordInfo);
                    responseObject.send(response);
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
