package com.csii.meter.console.datamodel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PermissionTree implements Serializable, TreeStructure {
    private String id;//本节点id
    private String url; //路径
    private String name;//本节点名称
    private String parentId;//本节点的父节点
    private String perms;
    private String menuCode;
    private Integer sort;
    private Integer type;
    private String target;
    private String component;
    /**
     * 权限标识
     */
    private String icon;
    private List<TreeStructure> children;
}
