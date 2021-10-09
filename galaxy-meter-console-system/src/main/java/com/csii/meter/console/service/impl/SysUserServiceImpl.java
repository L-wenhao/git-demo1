package com.csii.meter.console.service.impl;

import com.csii.meter.console.constants.Constants;
import com.csii.meter.console.converter.SysUserConverter;
import com.csii.meter.console.domain.SysUser;
import com.csii.meter.console.domain.SysUserRole;
import com.csii.meter.console.dto.SysUserDTO;
import com.csii.meter.console.mapper.SysUserMapper;
import com.csii.meter.console.service.SysUserService;
import com.csii.meter.console.utils.ConvertUtils;
import com.csii.meter.console.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户信息Service业务层处理
 *
 * @author liuya
 * @date 2020-09-20
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    @Override
    public SysUserVO selectUserById(String id) {
        return SysUserConverter.INSTANCE.domain2vo(userMapper.selectUserById(id));
    }
    /**
     * 查询用户信息列表
     *
     * @param dto 用户信息
     * @return 用户信息
     */
    @Override
    public List<SysUserVO> selectUserList(SysUserDTO dto) {
        SysUser user = SysUserConverter.INSTANCE.dto2domain(dto);
        List<SysUser> users = userMapper.selectUserList(user);
        return SysUserConverter.INSTANCE.domain2vo(users);
    }

    /**
     * 新增用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUserDTO dto) {
        SysUser user = SysUserConverter.INSTANCE.dto2domain(dto);
        //设置为 非管理员权限
        user.setAdminFlag(Integer.valueOf(Constants.NO));
        user.preInsert();

        //新增绑定角色
        String[] split = dto.getRoleIds().split(",");
        List<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        this.addRoles(user.getId(), strings);
        return userMapper.insertUser(user);
    }
    /**
     * 修改用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUserDTO dto) {
        SysUser user = SysUserConverter.INSTANCE.dto2domain(dto);
        user.preUpdate();
        return userMapper.updateUser(user);
    }
    /**
     * 删除用户信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) {
        return userMapper.deleteUserByIds(ConvertUtils.toStrArray(ids));
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteUserById(String id) {
        return userMapper.deleteUserById(id);
    }
    /**
     * 检查登录id唯一性
     * @param loginId
     * @return
     */
    @Override
    public Integer checkUnique(String loginId) {
        return userMapper.checkUniqueByLoginId(loginId);
    }

    /**
     * 校验密码
     * @param id
     * @param password
     * @return
     */
    @Override
    public boolean checkPassword(String id, String password) {
        SysUser user = userMapper.selectUserByIdAndPaas(id, password);
        if(Objects.isNull(user)){
            return false;
        }
        return true;
    }
    /**
     * 修改密码
     * @param id
     * @return
     */
    @Override
    public int modifyPassword(String id, String password) {
        return userMapper.modifyPassword(id, password);
    }
    /**
     * 绑定角色
     * @param id
     * @param roles
     */
    @Override
    @Transactional
    public void addRoles(String id, List<String> roles) {
        //先删除用户下所有角色
        userMapper.deleteUserRoleByUserId(id);
        List<SysUserRole> list = new ArrayList<>();
        if(null != roles){
            roles.forEach(s -> {
                SysUserRole ur = SysUserRole.builder().userId(id).roleId(s).build();
                ur.preInsert();
                list.add(ur);
            });
        }
        //再添加
        userMapper.batchInsertUserRole(list);
    }
}
