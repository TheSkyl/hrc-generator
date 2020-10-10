package com.cebrains.hrc.modular.system.service;

import com.cebrains.hrc.common.persistence.model.Dept;

import java.util.List;

/**
 * 部门服务
 *
 * @author frank
 * @date 2017-04-27 17:00
 */
public interface IDeptService {

    /**
     * 删除部门
     *
     * @author frank
     * @Date 2017/7/11 22:30
     */
   void deleteDept(Integer deptId);

    /**
     * 根据建议搜索部门
     *
     * @param k
     * @return
     */
    List<Dept> queryDeptBySuggest(String k);
}
