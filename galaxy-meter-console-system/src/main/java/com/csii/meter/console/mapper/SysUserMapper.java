package com.csii.meter.console.mapper;

import com.csii.meter.console.domain.SysUser;
import com.csii.meter.console.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author liuya
 * @date 2020-09-20
 */
@Repository
public interface SysUserMapper {
    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    public SysUser selectUserById(String id);
    /**
     * 根据ID和密码查询用户
     * @param id
     * @param password
     * @return
     */
    SysUser selectUserByIdAndPaas(@Param("id") String id, @Param("password") String password);
    /**
     * 根据ID修改密码
     * @param id
     * @param password
     * @return
     */
    int modifyPassword(@Param("id") String id, @Param("password") String password);
    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息集合
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 批量新增用户角色
     *
     * @param list 用户信息
     * @return 结果
     */
    int batchInsertUserRole(List<SysUserRole> list);
    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 删除用户信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    public int deleteUserById(String id);
    /**
     * 删除用户角色关联
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    int deleteUserRoleByUserId(String id);
    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(String[] ids);

    /**
     * 检查登录id唯一性
     * @param loginId
     * @return
     */
    int checkUniqueByLoginId(@Param("loginId") String loginId);
}
