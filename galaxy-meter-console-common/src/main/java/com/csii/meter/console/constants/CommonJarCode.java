package com.csii.meter.console.constants;

/**
 *
 * @author tsw
 * @version 1.0
 * @date 2021/7/20 4:01 下午
 */
public interface CommonJarCode {

    /*** 上传方式（1手工上传 2maven上传）*/
    Integer UPLOAD_TYPE_HAND = 1;
    Integer UPLOAD_TYPE_MAVEN = 2;

    /*** 文件类型（0公共包 1API包） */
    Integer FILE_TYPE_PUBLIC = 0;
    Integer FILE_TYPE_API = 1;

    /*** 状态（0默认 1变更）*/
    Integer STATUS_DEFAULT = 0;
    Integer STATUS_CHANGE = 1;

    /*** 状态（-1变更未发布 0未发布 1已发布） */
    Integer PUBLISH_STATUS_CHANGE = -1;
    Integer PUBLISH_STATUS_NOTSEND = 0;
    Integer PUBLISH_STATUS_SEND = 1;

}
