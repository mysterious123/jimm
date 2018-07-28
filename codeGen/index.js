const mysql = require('mysql');
const swig = require('swig');
const fs = require('fs-extra')
const path = require('path');
const _ = require('lodash');
const utils = require('./utils.js')
const config = require('./config.js')
const constants = require('./constants.js')
const del = require('del');


var connection = mysql.createConnection(config.dbInfo);

connection.connect();

del.sync(['dist/*']);
genBean();
genController();
genService();
genServiceImpl();
genMapper();
genListPage();
genAddPage();
genEditPage();
genStore();

//生成JAVA Bean
function genBean() {
  connection.query(constants.sql.QUERY_TABLE_METAINFO(config.tableName), function(error, results, fields) {
    if (error) throw error;
    var distPath = __dirname + "/dist/model/" + config.moduleName;
    fs.ensureDirSync(distPath);
    render("bean", distPath + '/' +config.className + ".java", {
      ...config,
      ...utils,
      metaInfo: results
    })
  });
}

//生成JAVA Controller
function genController() {
  var distPath = __dirname + "/dist/web/" + config.moduleName;
  fs.ensureDirSync(distPath);
  render("controller", distPath + '/' + config.className + "Controller" + ".java", {
    ...config,
    ...utils
  })
}

//生成JAVA Service
function genService() {
  var distPath = __dirname + "/dist/service/" + config.moduleName;
  fs.ensureDirSync(distPath);
  render("service", distPath + '/' + config.className + "Service" + ".java", {
    ...config,
    ...utils
  })
}

//生成JAVA ServiceImpl
function genServiceImpl() {
  var distPath = __dirname + "/dist/service/" + config.moduleName + "/impl";
  fs.ensureDirSync(distPath);
  render("serviceImpl", distPath + '/' + config.className + "ServiceImpl" + ".java", {
    ...config,
    ...utils
  })
}

//生成xml mapper
function genMapper() {
  connection.query(constants.sql.QUERY_TABLE_METAINFO(config.tableName), function(error, results, fields) {
    if (error) throw error;
    var distPath = __dirname + "/dist/mapper/" + config.moduleName;
    fs.ensureDirSync(distPath);
    render("mapper", distPath + '/' + config.className + "Mapping" + ".xml", {
      ...config,
      ...utils,
      metaInfo: results
    })
  });
}

//生成ListPage
function genListPage() {
  connection.query(constants.sql.QUERY_TABLE_METAINFO(config.tableName), function(error, results, fields) {
    if (error) throw error;
    var distPath = __dirname + "/dist/view/" + config.moduleName;
    fs.ensureDirSync(distPath);
    render("listPage", distPath + '/' + utils.lowcase(config.className) + "List" + ".vue", {
      ...config,
      ...utils,
      metaInfo: results
    })
  });
}

//生成AddPage
function genAddPage() {
  connection.query(constants.sql.QUERY_TABLE_METAINFO(config.tableName), function(error, results, fields) {
    if (error) throw error;
    var distPath = __dirname + "/dist/view/" + config.moduleName;
    fs.ensureDirSync(distPath);
    render("addPage", distPath + '/' + utils.lowcase(config.className) + "Add" + ".vue", {
      ...config,
      ...utils,
      metaInfo: results
    })
  });
}
function genEditPage(){
  connection.query(constants.sql.QUERY_TABLE_METAINFO(config.tableName), function(error, results, fields) {
    if (error) throw error;
    var distPath = __dirname + "/dist/view/" + config.moduleName;
    fs.ensureDirSync(distPath);
    render("editPage", distPath + '/' + utils.lowcase(config.className) + "Edit" + ".vue", {
      ...config,
      ...utils,
      metaInfo: results
    })
  });
}
//生成vuex Store
function genStore() {
  var distPath = __dirname + "/dist/stroe/modules";
  fs.ensureDirSync(distPath);
  render("store", distPath + '/' + config.moduleName + ".js", {
    ...config,
    ...utils,
  })
}

//基本渲染方法
function render(tplName, distPath, variable) {
  var result = swig.renderFile(__dirname + "/template/" + tplName, variable);
  fs.writeFile(distPath, result, {
    flag: 'w'
  }, function(err) {
    if (err) {
      console.error(err);
    } else {
      console.log(distPath + ' 写入成功');
    }
  });
}
connection.end();
