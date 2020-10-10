package com.cebrains.hrc.modular.goods.service;

import com.cebrains.hrc.common.persistence.model.ConsumableCheckLog;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 盘库日志 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-10-31
 */
public interface IConsumableCheckLogService extends IService<ConsumableCheckLog> {

    List<ConsumableCheckLog> selectListByDepartment(Integer department);
}
