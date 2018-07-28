var local = {
  host: '127.0.0.1',
  user: 'root',
  password: 'root',
  database: 'jlog'
}

var remote = {
  host: '172.18.60.121',
  user: 'root',
  password: '123qwe!@#QWE',
  database: 'jim'
}
module.exports = {
  moduleName : 'communicationlineinfo',
  className  : 'CommunicationLineInfo',
  tableName  : 'COMMUNICATION_LINE_INFO',
  dbInfo     : remote
}
