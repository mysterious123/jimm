//package common.util;
//
//import com.sybase.jdbc3.jdbc.SybDriver;
//
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
///**
// * Created by Administrator on 2018/5/8.
// */
//public class Test {
//    public static void main(String[] args) {
//        SybDriver sybDriver = (SybDriver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
//        sybDriver.setVersion(com.sybase.jdbcx.SybDriver.VERSION_6);
//        DriverManager.registerDriver(sybDriver);
//
//        props.put("ENABLE_BULK_LOAD","true");
//
//        dbConn.setAutoCommit(false);
//        String SQLString = "insert into batch_inserts (row_id, colname1, colname2)\n values (?,?,?) \n";
//
//        PreparedStatement pstmt;
//        try
//        {
//            pstmt = dbConn.prepareStatement(SQLString);
//        }
//        catch (SQLException sqle)
//        {
//            //displaySQLEx("Couldn't prepare statement",sqle);
//            return;
//        }
//
//        for (String[] val : valuesToInsert)
//        {
//            pstmt.setString(1, val[0]);  //row_id    varchar(30)
//            pstmt.setString(2, val[1]);//logical_server varchar(30)
//            pstmt.setString(3, val[2]);  //client_host varchar(30)
//
//            try
//            {
//                pstmt.addBatch();
//            }
//            catch (SQLException sqle)
//            {
//                displaySQLEx("Failed to build batch",sqle);
//                break;
//            }
//        }
//
//        try {
//            pstmt.executeBatch();
//            dbConn.commit();
//            pstmt.close();
//        } catch (SQLException sqle) {
//            //handle
//        }
//
//        try {
//            if (dbConn != null)
//                dbConn.close();
//        } catch (Exception e) {
//            //handle
//        }
//    }
//}
