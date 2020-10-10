package com.cebrains.hrc.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.base.BaseJunit;
import com.cebrains.hrc.common.persistence.dao.MenuMapper;
import com.cebrains.hrc.common.persistence.model.Menu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Stack;

/**
 * 菜单测试
 *
 * @author frank
 * @date 2017-06-13 21:23
 */
public class MenuTest extends BaseJunit {

    @Autowired
    MenuMapper menuMapper;

    /**
     * 初始化pcodes
     *
     * @author frank
     * @Date 2017/6/13 21:24
     */
    @Test
    public void generatePcodes() {

    }

}
