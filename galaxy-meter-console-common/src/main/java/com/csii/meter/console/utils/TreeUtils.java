package com.csii.meter.console.utils;

import com.csii.meter.console.datamodel.TreeStructure;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TreeUtils {
    /**
     * 使用递归方法建树
     * @param treeStructureList 数据列表
     * @param rootFlag 根节点标识
     * @return
     */
    public static List<TreeStructure> buildByRecursive(List treeStructureList, String rootFlag) {
        if(Objects.isNull(treeStructureList) || StringUtils.isBlank(rootFlag)){
            return treeStructureList;
        }
        List<TreeStructure> trees = new ArrayList<TreeStructure>();
        Iterator<TreeStructure> iterator = treeStructureList.iterator();
        while (iterator.hasNext()) {
            TreeStructure treeStructure = iterator.next();
            if (treeStructure.getParentId().equals(rootFlag)) {
                trees.add(findChildren(treeStructure, treeStructureList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeStructureList 数据列表
     * @return
     */
    public static TreeStructure findChildren(TreeStructure treeStructure, List<TreeStructure> treeStructureList) {
        for (TreeStructure item : treeStructureList) {
            if (treeStructure.getId().equals(item.getParentId())) {
                if (treeStructure.getChildren() == null) {
                    treeStructure.setChildren(new ArrayList<>());
                }
                treeStructure.getChildren().add(findChildren(item, treeStructureList));
            }
        }
        return treeStructure;
    }


}
