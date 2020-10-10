package com.cebrains.hrc.modular.goods.service.impl;

import com.cebrains.hrc.common.persistence.model.ConsumableCheckLog;
import com.cebrains.hrc.common.persistence.dao.ConsumableCheckLogMapper;
import com.cebrains.hrc.modular.goods.service.IConsumableCheckLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 盘库日志 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-10-31
 */
@Service
public class ConsumableCheckLogServiceImpl extends ServiceImpl<ConsumableCheckLogMapper, ConsumableCheckLog> implements IConsumableCheckLogService {
    @Autowired
    ConsumableCheckLogMapper consumableCheckLogMapper;
    @Override
    public List<ConsumableCheckLog> selectListByDepartment(Integer department) {
        return consumableCheckLogMapper.selectListByDepartment(department);
    }
}
