package com.csii.meter.console.utils;

import com.csii.meter.console.exception.MeterConsoleException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;

/**
 * @author liangjianan
 * @Description 构建Document对象工具类
 * @Date 2021/6/15 9:44
 */
public class DocumentUtil {
    private static final Logger logger = LoggerFactory.getLogger(DocumentUtil.class);

    /**
     * 初始化Document对象,设置xml请求头
     *
     * @return
     */
    public static Document replenishDocument() {
        Document document = null;
        try {
            StringBuffer xmlBuffer = new StringBuffer(
                    "<config xmlns=\"http://www.csii.com.cn/schema/power/service\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                            "xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd   " +
                            "http://www.csii.com.cn/schema/power/service  http://www.csii.com.cn/schema/power/service/ps-config.xsd\"></config>");
            document = DocumentHelper.parseText(xmlBuffer.toString());
        } catch (DocumentException e) {
            logger.info("异常信息：{}", e.getMessage());
            throw new MeterConsoleException("构建初始Document失败");
        }
        return document;
    }

    /**
     * 格式化生成的配置文件xml
     *
     * @param doc
     * @return
     */
    public static String formatDocument(Document doc) throws IOException {
        if(Objects.isNull(doc))
            return null;
        XMLWriter xmlWriter = null;
        try {
            // stringWriter字符串是用来保存XML文档的
            StringWriter stringWriter = new StringWriter();
            // xmlWriter是用来把XML文档写入字符串的(工具)
            xmlWriter = new XMLWriter(stringWriter, getXmlFormat());
            // 把创建好的XML文档写入字符串
            xmlWriter.write(doc);
            return stringWriter.toString();
        } catch (Exception e) {
            logger.info("异常信息， {}", e.getMessage());
            throw e;
        } finally {
            if(Objects.nonNull(xmlWriter))
                xmlWriter.close();
        }
    }

    public static byte[] converterByte(Document doc) throws IOException {
        if(Objects.isNull(doc))
            return null;
        XMLWriter xmlWriter = null;
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            xmlWriter = new XMLWriter(byteOut, getXmlFormat());
            xmlWriter.write(doc);
            return byteOut.toByteArray();
        } catch (Exception e) {
            logger.info("异常信息， {}", e.getMessage());
            throw e;
        } finally {
            if(Objects.nonNull(xmlWriter)) {
                xmlWriter.close();
            }
            byteOut.close();
        }
    }

    private static OutputFormat getXmlFormat(){
        OutputFormat format = OutputFormat.createPrettyPrint(); //设置XML文档输出格式
        format.setEncoding("UTF-8"); //设置XML文档的编码类型
        format.setIndent(true); //设置是否缩进
        format.setIndent("  "); //以空格方式实现缩进
        format.setNewlines(true); //设置是否换行
        return format;
    }
}
