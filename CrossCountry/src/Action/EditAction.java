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

public class EditAction extends IAction {
    private static final String RequestParamKey_username = "username";
    private static final String RequestParamKey_changeFiled = "filed";
    private static final String RequestParamKey_changeValue = "value";


    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        Connection dbConnection = null;
        Statement stmt = null;


        String changeFiled = getParam(request, RequestParamKey_changeFiled, "");
        String changeValue = getParam(request, RequestParamKey_changeValue, "");
        String username = getParam(request, RequestParamKey_username, "");


        dbConnection = SqlManager.getConnection();
        stmt = dbConnection.createStatement();

        String sqlStr = "update user_info set "
                +
                    changeFiled
                +
                    "='"
                +
                    changeValue
                +
                    "' where username = '"
                +
                    username +
                "'; ";
        stmt.execute(sqlStr);
//        ResultSet resultSet = stmt.getResultSet();
        int updateCount = stmt.getUpdateCount();// 获取受影响的行数
        if (updateCount > 0) {
            String sqlStr_update = "select * from user_info where username = '"+username+"' limit 1;";
            stmt.execute(sqlStr_update);
            ResultSet resultSet = stmt.getResultSet();
//        int updateCount = stmt.getUpdateCount();// 获取受影响的行数
            // 如果查询到结果
            if (resultSet.next()) {
                String nickname  = resultSet.getString("nickname");
                String email = resultSet.getString("email");
                String  telphone = resultSet.getString("telphone");
                String sex = resultSet.getString("sex");
                String  sign = resultSet.getString("sign");
                String url = resultSet.getString("url");
                int match_times = resultSet.getInt("match_times");
                userInfo info = new userInfo(username,nickname,email,telphone,sex,sign,url,match_times);

                ResponseObject responseObject = ResponseObject
                        .getSuccessResponse(info);
                responseObject.send(response);
            }else{
                ResponseObject responseObject = ResponseObject.getFailResponse(
                        Error.errorCode_Edit_fail, Error.getErrorMsg_Edit_fail());
                responseObject.send(response);
            }


        } else {
            ResponseObject responseObject = ResponseObject.getFailResponse(
                    Error.errorCode_Edit_fail, Error.getErrorMsg_Edit_fail());
            responseObject.send(response);
        }




    }
}
