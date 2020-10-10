package com.cebrains.hrc.modular.goods.service.impl;

import com.cebrains.hrc.common.persistence.model.Consumable;
import com.cebrains.hrc.common.persistence.dao.ConsumableMapper;
import com.cebrains.hrc.modular.goods.service.IConsumableService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 耗材管理 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-05-10
 */
@Service
public class ConsumableServiceImpl extends ServiceImpl<ConsumableMapper, Consumable> implements IConsumableService {

}
