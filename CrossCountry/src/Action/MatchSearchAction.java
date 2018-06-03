package Action;

import Utils.Error;
import Utils.ResponseObject;
import Utils.SqlManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.matchInfo;
import model.userInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MatchSearchAction extends IAction {

////    insert into match_info(matchname,sponser,undertaker,matchtime,matchsite,matchstate,matcharea,beginpoint,endpoint) values('中南友谊赛','中南大学','湖南 越野协会','201','','','','','','','','','','')
    //查询
        //select st_astext(st_transform(matcharea,4326)) from match_info;
    //这个是查询



    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        Connection dbConnection = null;
        Statement stmt = null;

        try{
            dbConnection = SqlManager.getConnection();
            stmt = dbConnection.createStatement();

            String sql = "select matchname,sponser,undertaker,matchsite,matchtime,matchstate from match_info;";
            stmt.execute(sql);
            ResultSet resultSet = stmt.getResultSet();

            List<matchInfo> features = new ArrayList<matchInfo>();


            while (resultSet.next()) {
                matchInfo info = new matchInfo(resultSet.getString("matchname"),resultSet.getString("sponser"),resultSet.getString("undertaker"),
                        resultSet.getTimestamp("matchtime"),resultSet.getString("matchsite"),resultSet.getString("matchstate"));
                features.add(info);
            }
            ResponseObject responseObject = ResponseObject
                        .getSuccessResponse(features);
                responseObject.send(response);

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
