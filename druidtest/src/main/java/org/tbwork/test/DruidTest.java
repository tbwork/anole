package org.tbwork.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: druidtest
 * @description:
 * @author: tommy.tb
 * @create: 2019-04-06 11:17
 **/
@Component
public class DruidTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private DruidDataSource druidDataSource;

    public List<String> test(){
        final List<String> idList = new LinkedList<String>();
        jdbcTemplate.query("select * from anole_config", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                idList.add(rs.getString("key"));
            }
        });
        return idList;
    }


    public static boolean print(Object object){
        System.out.println(object);
        return false;
    }

    public void runTest(int object){
        DruidPooledConnection conn = null;
        PreparedStatement ps = null;
        try {
            conn = druidDataSource.getConnection();

            int count = 10;
            String parameterString = "";
            for(int i = 0 ; i < count ; i ++){
                parameterString += "?,";
            }
            parameterString = parameterString.substring(0, parameterString.length()-1);
            String sql = "select * from anole_config where type = '003' and id IN ( " +parameterString+" )";
            System.out.println(sql);
            ps = conn.prepareStatement(sql);
            for(int i = 0 ; i < count ; i ++){
                ps.setObject(i+1, (short)(i+1));
            }
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps){
                    ps.close();
                }
                if (null != conn){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
