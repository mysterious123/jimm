package model.kafkadata;

import model.BaseModel;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28/028.
 */
@Alias("KafkaData")
public class KafkaData extends BaseModel  {

    private String tableName;
    private List<String> colList;
    private List<String> valList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColList() {
        return colList;
    }

    public void setColList(List<String> colList) {
        this.colList = colList;
    }

    public List<String> getValList() {
        return valList;
    }

    public void setValList(List<String> valList) {
        this.valList = valList;
    }
}
