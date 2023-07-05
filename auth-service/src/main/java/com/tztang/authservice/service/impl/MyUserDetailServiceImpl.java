package com.tztang.authservice.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tztang.authservice.mapper.SysUserMapper;
import com.tztang.authservice.mapper.SysUserRoleMapper;
import com.tztang.authservice.pojo.entity.LoginUser;
import com.tztang.authservice.pojo.entity.SysUserEntity;
import com.tztang.authservice.pojo.params.LoginParams;
import com.tztang.authservice.service.MyUserDetailService;
import com.tztang.common.contanst.RedisKeyConst;
import com.tztang.common.util.JwtUtil;
import com.tztang.common.util.RedisHelper;
import com.tztang.common.util.ResultResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class MyUserDetailServiceImpl implements MyUserDetailService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private RedisHelper redisHelper;

    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        SysUserEntity sysUser = sysUserMapper.selectOne(
                Wrappers.<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getUserName, account)
        );
        if (ObjectUtil.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        /**
         * 权限
         */
        return new LoginUser(sysUser, AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public ResultResponse login(LoginParams params) {
        SysUserEntity sysUser = sysUserMapper.selectOne(
                Wrappers.<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getUserName, params.getUserName())
        );
        if (!params.getPassword().equals(sysUser.getPassword())) {
            return ResultResponse.badRequest("密码错误");
        }
        String roleId = sysUserRoleMapper.getRole(sysUser.getId());
        //生成token
        HashMap<String, Object> jwtMap = new HashMap<>();
        jwtMap.put(JwtUtil.USER_NAME, params.getUserName());
        jwtMap.put(JwtUtil.USER_ROLE, roleId);
        String jwt = JwtUtil.createJwt(jwtMap);
        //redis存储
        HashMap<String, Object> redisMap = new HashMap<>();
        redisMap.put(RedisKeyConst.USER_ROLE_KEY, roleId);
        redisMap.put(RedisKeyConst.TOKEN, jwt);
        redisHelper.storeLoginInfo(RedisKeyConst.LOGIN_KEY_PREFIX + params.getUserName(), redisMap);
        return ResultResponse.success(jwt);
    }

}
