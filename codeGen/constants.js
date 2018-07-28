var util = require('util');

module.exports = {
  sql: {
    QUERY_TABLE_METAINFO : function(tableName){
      return util.format("show full fields from %s",tableName);
    }
  }
}
