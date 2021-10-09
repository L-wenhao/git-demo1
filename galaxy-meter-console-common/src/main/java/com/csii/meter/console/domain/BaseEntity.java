package com.csii.meter.console.domain;

import com.csii.meter.console.utils.ShiroUtils;
import com.csii.meter.console.utils.SnowflakeIdFactory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 实体父类
 * 调用createId方法生成ID
 * 调用preInsert方法生成id、createdBy、updatedBy、createTime、updateTime
 * 调用preUpdate方法生成updatedBy、updateTime
 * @author liuya
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    /**
     * 主键id
     */
    private String id;

    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 更新者
     */
    private String updatedBy;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 新增前设置参数
     */
    public void preInsert() {
        createId();
        initCreated();
        initUpdated();
        initCreateTime();
        initUpdateTime();
    }

    /**
     * 修改前设置参数
     */
    public void preUpdate() {
        initUpdated();
        initUpdateTime();
    }

    public String createId() {
        this.id = SnowflakeIdFactory.generateId().toString();
        return this.id;
    }

    public String initCreated() {
        this.createdBy = ShiroUtils.getLoginName();
        return this.createdBy;
    }

    public String initUpdated() {
        this.updatedBy = ShiroUtils.getLoginName();
        return this.updatedBy;
    }

    public Timestamp initCreateTime() {
        this.createTime = new Timestamp(System.currentTimeMillis());
        return this.createTime;
    }

    public Timestamp initUpdateTime() {
        this.updateTime = new Timestamp(System.currentTimeMillis());
        return this.updateTime;
    }

}
