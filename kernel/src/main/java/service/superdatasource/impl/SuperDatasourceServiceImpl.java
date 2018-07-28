package service.superdatasource.impl;

import dao.BaseMapper;
import model.superdatasource.SuperDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superdatasource.SuperDatasourceService;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service("SuperDatasourceService")
public class SuperDatasourceServiceImpl implements SuperDatasourceService{
    private final Logger L = LoggerFactory.getLogger(SuperDatasourceServiceImpl.class);
    private static final String NAMESPACE = SuperDatasource.class.getName();

    @Resource
    private BaseMapper<SuperDatasource> baseMapper;

    @Override
    public int insert(SuperDatasource superDatasource) {
        superDatasource.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superDatasource);
    }
    
    @Override
    public int delete(SuperDatasource superDatasource) {
        superDatasource.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superDatasource);
    }

    @Override
    public int update(SuperDatasource superDatasource) {
        superDatasource.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superDatasource);
    }

    @Override
    public List<SuperDatasource> selectList(SuperDatasource superDatasource) {
        superDatasource.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superDatasource);
    }

    @Override
    public SuperDatasource selectOne(SuperDatasource superDatasource) {
        superDatasource.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superDatasource);
    }

    @Override
    public int connectTest(SuperDatasource superDatasource) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "",
                driverClass = "";
        switch (superDatasource.getDbType()){
            case "oracle":
                driverClass="oracle.jdbc.driver.OracleDriver";
                jdbcUrl = "jdbc:oracle:thin:@"+superDatasource.getServerIp()+":"+superDatasource.getPortNum()+":"+superDatasource.getDatabaseName();
                break;
            case "db2":
                driverClass="com.ibm.db2.jcc.DB2Driver";
                jdbcUrl = "jdbc:db2://"+superDatasource.getServerIp()+":"+superDatasource.getPortNum()+"/"+superDatasource.getDatabaseName();

                break;
            case "mysql":
                driverClass="com.mysql.jdbc.Driver";
                jdbcUrl = "jdbc:mysql://"+superDatasource.getServerIp()+":"+superDatasource.getPortNum()+"/"+superDatasource.getDatabaseName();
                break;
            case"sqlserver":
                driverClass="com.microsoft.sqlserver.jdbc.SQLServerDriver";
                jdbcUrl = "jdbc:sqlserver://"+superDatasource.getServerIp()+":"+superDatasource.getPortNum()+"; DatabaseName="+superDatasource.getDatabaseName();
                break;
            case "sybase":
                break;
        }
            Class.forName(driverClass);
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(jdbcUrl, superDatasource.getUserName(), superDatasource.getPassword());
                // String productName = connection.getMetaData().getDatabaseProductName();
            } catch (Exception e) {
                return 1;
            } finally {
                if(connection != null)
                    connection.close();
            }
        return 0;
    }
}
