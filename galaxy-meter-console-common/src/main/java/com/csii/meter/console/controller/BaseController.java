package com.csii.meter.console.controller;

import com.csii.meter.console.page.PageDomain;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.enums.ResultCode;
import com.csii.meter.console.page.PageHelperDialect;
import com.csii.meter.console.page.TableDataInfo;
import com.csii.meter.console.page.TableSupport;
import com.csii.meter.console.request.BaseQueryRequest;
import com.csii.meter.console.utils.DateUtils;
import com.csii.meter.console.utils.ServletUtils;
import com.csii.meter.console.utils.SqlUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * web层通用数据处理
 *
 * @author liuya
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 封装一层，设置当前页、每页条数
     * @param pageRequest
     */
    protected void startPage(BaseQueryRequest pageRequest) {
        this.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage(Integer num, Integer size) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (num != null) {
            pageNum = num;
        }
        if (size != null) {
            pageSize = size;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize, SqlUtils.escapeOrderBySql(pageDomain.getOrderBy()));
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected PageInfo getPageInfo(List<?> list) {
        Page page = PageHelperDialect.LOCAL_PAGE.get();
        if(Objects.isNull(list)){
            return new PageInfo<>(new ArrayList<>());
        }
        if(Objects.isNull(page)){
            return new PageInfo<>(list);
        }
        page.clear();
        page.addAll(list);
        PageInfo<?> pageInfo = new PageInfo<>(page);
        PageHelperDialect.LOCAL_PAGE.remove();
        return pageInfo;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected Result<Void> toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected Result toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public Result<Void> success() {
        return Result.success();
    }

    /**
     * 返回失败消息
     */
    public Result<Void> error() {
        return Result.fail();
    }

    /**
     * 返回成功消息
     */
    public Result success(String message) {
        return Result.successWithoutData(message);
    }

    /**
     * 返回失败消息
     */
    public Result error(String message) {
        return Result.fail(message);
    }
}
