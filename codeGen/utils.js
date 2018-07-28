var utils = {
  convertToCamel: function(field){
    return field.toLowerCase().replace(/\_(\w)/g, function(x){return x.slice(1).toUpperCase();});
  },
  upcase: function(className){
    return className.replace(/^\S/,function(s){return s.toUpperCase();});
  },
  lowcase: function(className){
    return className.replace(/^\S/,function(s){return s.toLowerCase();});
  },
  genGetter:function(field){
    return "get" + utils.convertToCamel(field).replace(/^\S/,function(s){return s.toUpperCase();});
  },
  genSetter:function(field){
    return "set" + utils.convertToCamel(field).replace(/^\S/,function(s){return s.toUpperCase();});
  },
  createInsertSql:function(){
    return
  }
}

module.exports = utils;
