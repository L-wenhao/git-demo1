package com.csii.meter.console.datamodel;

import java.util.List;

/**
 * 树形结构接口
 */
public interface TreeStructure {
    String getParentId();
    String getId();
    List<TreeStructure> getChildren();
    void setChildren(List<TreeStructure> list);
}
