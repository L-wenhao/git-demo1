package com.csii.meter.console.constants;

/**
 * xml通用变量
 *
 * @author guoyu
 * @version 1.0
 * @date 2021/6/11 4:33 下午
 */
public interface ApiConstant {

    //simple class name
    String INTEGER = "Integer";
    String INT = "int";
    String STRING = "String";
    String FLOAT = "Float";
    String DOUBLE = "Double";
    String BOOLEAN = "Boolean";
    String LONG = "Long";
    String MAP = "Map";
    String DATE = "Date";
    String LOCAL_DATE_TIME = "LocalDateTime";
    String LIST = "List";
    String BIGDECIMAL = "BigDecimal";
    String STRING_ATTR = "String[]";
    String INTEGER_ATTR = "Integer[]";
    String OBJ_ATTR = "Object[]";


    //自定义数据类型
    String integer = "integer";
    String string = "string";
    String floater = "float";
    String doubler = "double";
    String booleaner = "boolean";
    String longer = "long";
    String map = "map";
    String date = "date";
    String dateTime = "dateTime";
    String ARRAY = "array";
    String bigDecimal = "bigDecimal";
    String OBJECT = "object";

    //分割符
    String separator = "@@";
    String separator_v2 = "::";

    //通讯方式
    String HTTP = "http";
    String RPC = "rpc";

    //项目框架
    Integer SPRING_CLOUD_MODE = 1;   //spring cloud
    Integer DUBBO_MODE = 2;         //dubbo


    //xml元素及属性
    String PROJECT = "project";
    String APP = "app";
    String API = "api";
    String REQ_METHOD = "reqMethod";
    String CLASS = "class";
    String URL = "url";
    String NAME = "name";
    String NOTE = "note";
    String TYPE = "type";
    String REQUEST = "request";
    String RESPONSE = "response";
    String FIELD = "field";
    String ALIAS = "alias";

    String DATA = "data";

    //xml默认文件名
    String API_FILE = "api.xml";
    String API_SCHEMA_FILE = "api.xsd";

    String GET = "get";
    String POST = "post";

    Integer depth = 5;

    String POWER_SERVICE = "powerService";
    String BASE_PATH = "basePackage";
}
