package com.csii.meter.console.constants;

/**
 * api解析变量
 *
 * @author guoyu
 * @version 1.0
 * @date 2021/6/2 2:46 下午
 */
public interface XmlConstant {
    //xml 元素
    String APP = "app";
    String API = "api";
    String TYPE = "type";
    String REQ_METHOD = "reqMethod";
    String URL = "url";
    String NOTE = "note";
    String REQUEST= "request";
    String RESPONSE = "response";
    String FIELD = "field";
    String CLASS = "class";

    //元素属性
    String ATTR_NAME = "name";
    String ATTR_NOTE = "note";
    String ATTR_TYPE = "type";
    String ATTR_VALUE = "value";
    String ATTR_ALIAS = "alias";
    String ATTR_REQUIRE = "require";

    //数据类型
    String INTEGER = "integer";
    String LONG = "long";
    String STRING = "string";
    String DOUBLE = "double";
    String FLOAT = "float";
    String BOOLEAN = "boolean";
    String OBJECT = "object";
    String ARRAY = "array";

    //分隔符
    String XML_SEPARATOR = "@@";
}
