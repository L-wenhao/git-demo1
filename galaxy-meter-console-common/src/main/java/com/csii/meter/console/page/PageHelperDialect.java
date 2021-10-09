package com.csii.meter.console.page;import com.csii.meter.console.exception.MeterConsoleException;import com.github.pagehelper.Page;import com.github.pagehelper.dialect.AbstractHelperDialect;import com.github.pagehelper.util.MetaObjectUtil;import lombok.extern.slf4j.Slf4j;import org.apache.commons.lang3.StringUtils;import org.apache.ibatis.cache.CacheKey;import org.apache.ibatis.mapping.BoundSql;import org.apache.ibatis.mapping.MappedStatement;import org.apache.ibatis.mapping.ParameterMapping;import org.apache.ibatis.reflection.MetaObject;import org.springframework.stereotype.Component;import java.util.*;import java.util.stream.Collectors;/** * mysql 分页插件 * * @author guoyu * @version 1.0 * @date 2021/9/14 3:59 下午 */@Slf4j@Componentpublic class PageHelperDialect extends AbstractHelperDialect {    public static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal();    private String databaseId = null;    private static List<String> pageKeys = new ArrayList<>();   //优化分页查询ID集合，mapper全限定名.sqlId    static {        pageKeys.add("com.csii.meter.console.mapper.OperLogMapper.selectOperLogList");    }    private boolean flag;    @Override    public Object processPageParameter(MappedStatement ms, Map<String, Object> paramMap, Page page, BoundSql boundSql, CacheKey pageKey) {        paramMap.put(PAGEPARAMETER_FIRST, page.getStartRow());        paramMap.put(PAGEPARAMETER_SECOND, page.getPageSize());        //处理pageKey        pageKey.update(page.getStartRow());        pageKey.update(page.getPageSize());        if(pageKeys.contains(ms.getId())){            flag = true;        }        //处理参数配置        if (boundSql.getParameterMappings() != null) {            List<ParameterMapping> newParameterMappings = new ArrayList<ParameterMapping>(boundSql.getParameterMappings());            if (page.getStartRow() == 0) {                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), PAGEPARAMETER_SECOND, int.class).build());            } else {                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), PAGEPARAMETER_FIRST, long.class).build());                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), PAGEPARAMETER_SECOND, int.class).build());            }            MetaObject metaObject = MetaObjectUtil.forObject(boundSql);            metaObject.setValue("parameterMappings", newParameterMappings);        }        return paramMap;    }    @Override    public String getPageSql(String sql, Page page, CacheKey pageKey) {        return getSql(sql,page);    }    @Override    public void afterAll() {        super.afterAll();        Page page = this.getLocalPage();        if(Objects.nonNull(page)){            LOCAL_PAGE.set(page);        }    }    /**     * 优化分页查询sql     * @param sql     * @param page     * @return     */    public String getSql(String sql, Page page){        if(flag){   //优化分页sql           return getPageSql(sql,page);        }        return getDefaultSql(sql, page);    }    /**     * 优化的分页，先查询出id集合缩小结果集，再根据联表带出分页信息     * @param sql     * @param page     * @return     */    private String getPageSql(String sql, Page page){        boolean isExist = false;        sql = sql.toUpperCase();        String fields = sql.substring(sql.indexOf("SELECT")+6,sql.indexOf("FROM"));        List<String> fieldList = Arrays.stream(fields.split(",")).collect(Collectors.toList());        if(fieldList != null && fieldList.size()>0){            for(int i=0;i<fieldList.size();i++){                if("ID".equals(fieldList.get(i).trim())){                    isExist = true;                    fieldList.remove(i);                    i--;                }            }            if(isExist){                fieldList.add("t1.ID");            }            fields = fieldList.stream().collect(Collectors.joining(","));        }        String where = sql.substring(sql.indexOf("FROM"));        String tableName = null;        String tableNameStr = where.replace("FROM","");        String[] attr = tableNameStr.split(" ");        if(attr != null && attr.length > 0){            tableName = attr[1];        }        if(StringUtils.isEmpty(tableName)){            throw new MeterConsoleException("解析不出sql，请检查sql");        }        StringBuilder childSql = new StringBuilder();        childSql.append("SELECT ID \n");        childSql.append(where + " \n");        if (page.getStartRow() == 0) {            childSql.append("\n LIMIT ? ");        } else {            childSql.append("\n LIMIT ?, ? ");        }        StringBuilder pageSql = new StringBuilder();        pageSql.append("SELECT \n");        pageSql.append(fields.toString()+" \n");        pageSql.append("FROM \n");        pageSql.append(tableName+" t1 \n");        pageSql.append("JOIN \n");        pageSql.append("("+childSql + ") t2 \n");        pageSql.append("ON t1.ID = t2.ID \n");        return pageSql.toString();    }    /**     * 默认走limit分页     * @param sql     * @param page     * @return     */    private String getDefaultSql(String sql, Page page){        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);        sqlBuilder.append(sql);        if (page.getStartRow() == 0) {            sqlBuilder.append("\n LIMIT ? ");        } else {            sqlBuilder.append("\n LIMIT ?, ? ");        }        return sqlBuilder.toString();    }}