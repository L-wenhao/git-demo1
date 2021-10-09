package com.csii.meter.console.domain;

import lombok.*;

/**
 * 文件存储表 console_file_store
 * @author liuya
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileStore extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件内容
     */
    private byte[] content;

    /**
     * md5
     */
    private String md5;

    /**
     * 引用次数
     */
    private Integer useCount;
}
