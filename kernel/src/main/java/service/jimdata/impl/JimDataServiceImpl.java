package service.jimdata.impl;

import dao.BaseMapper;
import db.DataSourceContextHolder;
import model.jimdata.data.JimData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.jimdata.JimDataService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@Service("JimDataService")
public class JimDataServiceImpl implements JimDataService{
    private final Logger L = LoggerFactory.getLogger(JimDataServiceImpl.class);
    private static final String NAMESPACE = JimData.class.getName();

    @Resource
    private BaseMapper<JimData> baseMapper;

    @Override
    public int insert(JimData jimData) {
        jimData.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(jimData);
    }

    @Override
    public int delete(JimData jimData) {
        jimData.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(jimData);
    }

    @Override
    public int update(JimData jimData) {
        jimData.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(jimData);
    }

    @Override
    public List<JimData> selectList(JimData jimData) {
        System.out.println("DataSourceContextHolder    : "+ DataSourceContextHolder.getDbType());
        jimData.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(jimData);
    }

    @Override
    public JimData selectOne(JimData jimData) {
        jimData.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(jimData);
    }

    //生成表
    public int createTable(JimData jimData){
        Date date=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);

        jimData.setDbName("data"+dateString);
        jimData.setStatmentId(NAMESPACE + ".createTable");
        return baseMapper.insert(jimData);    //sql是建表sql
    }
}
