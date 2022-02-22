package com.lxf.common.userInfo.service.impl;

import com.lxf.common.userInfo.entity.UserInfo;
import com.lxf.common.userInfo.mapper.UserInfoMapper;
import com.lxf.common.userInfo.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lxf
 * @since 2022-02-20
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
