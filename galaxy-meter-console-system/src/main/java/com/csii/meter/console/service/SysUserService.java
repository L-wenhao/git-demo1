package com.csii.meter.console.service;

import com.csii.meter.console.dto.SysUserDTO;
import com.csii.meter.console.vo.SysUserVO;

import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface SysUserService {
    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    public SysUserVO selectUserById(String id);

    /**
     * 查询用户信息列表
     *
     * @param dto 用户信息
     * @return 用户信息集合
     */
    public List<SysUserVO> selectUserList(SysUserDTO dto);

    /**
     * 新增用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    public int insertUser(SysUserDTO dto);

    /**
     * 修改用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    public int updateUser(SysUserDTO dto);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(String ids);

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    public int deleteUserById(String id);
    /**
     * 校验唯一性
     * @param loginId
     * @return
     */
    Integer checkUnique(String loginId);
    /**
     * 校验密码
     * @param id
     * @return
     */
    boolean checkPassword(String id, String password);
    /**
     * 修改密码
     * @param id
     * @return
     */
    int modifyPassword(String id, String password);
    /**
     * 绑定角色
     * @param id
     * @param roles
     */
    void addRoles(String id, List<String> roles);
}
