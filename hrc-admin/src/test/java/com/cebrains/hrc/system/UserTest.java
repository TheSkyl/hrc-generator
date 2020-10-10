package com.cebrains.hrc.system;

import com.cebrains.hrc.base.BaseJunit;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.modular.system.dao.UserMgrDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 用户测试
 *
 * @author frank
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMgrDao userMgrDao;

    @Resource
    UserMapper userMapper;

    @Test
    public void userTest() throws Exception {

    }

}
