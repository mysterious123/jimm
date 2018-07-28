package common.util;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 2018/5/8.
 */
public class JdbcUtil {

    private static String userName;
    private static String url;
    private static String passWord;
    private static String driver;


    private Connection connection;

    // 定义sql语句的执行对象
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    static{
        //加载数据库配置信息，并给相关的属性赋值
        loadConfig();
    }

    /**
     * 加载数据库配置信息，并给相关的属性赋值
     */
    public static void loadConfig() {
        try {

            InputStream inStream = JdbcUtil.class
                    .getResourceAsStream("/jdbc.properties");
            Properties prop = new Properties();
            prop.load(inStream);
            userName = prop.getProperty("jdbc.username");
            passWord = prop.getProperty("jdbc.password");
            driver = prop.getProperty("jdbc.driver");
            url = prop.getProperty("jdbc.url");

        } catch (Exception e) {
            throw new RuntimeException("读取数据库配置文件异常！", e);
        }
    }

    public JdbcUtil() {

    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            System.out.println(url);
            Class.forName(driver); // 注册驱动
            connection = DriverManager.getConnection(url, userName, passWord); // 获取连接
        } catch (Exception e) {
            throw new RuntimeException("get connection error!", e);
        }
        return connection;
    }

    /**
     * 执行更新操作
     *
     * @param sql
     *            sql语句
     * @param params
     *            执行参数
     * @return 执行结果
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<?> params)
            throws SQLException {
        boolean flag = false;
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        // 填充sql语句中的占位符
//        if (params != null && !params.isEmpty()) {
//            for (int i = 0; i < params.size(); i++) {
//                pstmt.setObject(index++, params.get(i));
//            }
//        }
        String time = "2018-05-08T16:39:01.721Z";
        DateTime dateTime = DateTime.parse(time);
        System.out.println(dateTime.getMillis()/1000);
        pstmt.setString(1,"1");
        pstmt.setLong(2,dateTime.getMillis()/1000);
        pstmt.setString(3,"1");
        pstmt.setString(4,"1");
        pstmt.setString(5,"1");
        pstmt.setInt(6,1);
        pstmt.setString(7,"1");


        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 执行查询操作
     *
     * @param sql
     *            sql语句
     * @param params
     *            执行参数
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findResult(String sql, List<?> params)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 释放资源
     */
    public void releaseConn() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println();
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        try {
            List<Map<String, Object>> result = jdbcUtil.findResult(
                    "select * from alerts.status", null);
            for (Map<String, Object> m : result) {
                System.out.println(m);
            }
//            for(int i = 0;i<100;i++){
//
//            }
//            String time = "2018-05-08T16:39:01.721Z";
//            DateTime dateTime = DateTime.parse(time);
//            System.out.println(dateTime.getMillis()/1000);
//            List<Object> a = new ArrayList<>();
//            a.add("111");
//            a.add(dateTime.getMillis()/1000);
//            a.add("3");
//            a.add("4");
//            a.add("5");
//            a.add(6);
//            a.add("7");
//
//            jdbcUtil.updateByPreparedStatement("INSERT INTO alerts.status (Identifier,FirstOccurrence,Node,NodeAlias,ServerName,Severity,Summary) VALUES (?,?,?,?,?,?,?)",a);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
    }
}
