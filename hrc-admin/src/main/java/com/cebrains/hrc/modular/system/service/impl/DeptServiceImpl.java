package com.cebrains.hrc.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.persistence.dao.DeptMapper;
import com.cebrains.hrc.common.persistence.model.Dept;
import com.cebrains.hrc.modular.system.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

    @Resource
    DeptMapper deptMapper;

    @Override
    public void deleteDept(Integer deptId) {

        Dept dept = deptMapper.selectById(deptId);

        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + dept.getId() + "]%");
        List<Dept> subDepts = deptMapper.selectList(wrapper);
        for (Dept temp : subDepts) {
            temp.deleteById();
        }

        dept.deleteById();
    }

    @Override
    public List<Dept> queryDeptBySuggest(String k) {
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("simplename", "%"+k+"%").or().like("fullname", "%"+k+"%");
        return deptMapper.selectList(wrapper);
    }
}
