package com.cebrains.hrc.system;

import com.cebrains.hrc.base.BaseJunit;
import com.cebrains.hrc.common.persistence.dao.DeptMapper;
import com.cebrains.hrc.common.persistence.model.Dept;
import com.cebrains.hrc.modular.system.dao.DeptDao;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 字典服务测试
 *
 * @author frank
 * @date 2017-04-27 17:05
 */
public class DeptTest extends BaseJunit {

    @Resource
    DeptDao deptDao;

    @Resource
    DeptMapper deptMapper;

    @Test
    public void addDeptTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {

    }

    @Test
    public void listTest() {

    }
}
