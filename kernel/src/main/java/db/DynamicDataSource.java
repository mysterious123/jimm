package db;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by Administrator on 2018/4/17/017.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //System.out.println("此时获取到的数据源为："+DataSourceContextHolder.getDbType());
        return DataSourceContextHolder.getDbType();
    }
}
