package com.cebrains.hrc.modular.outsource.service.impl;

import com.cebrains.hrc.common.persistence.model.OutsourceOrder;
import com.cebrains.hrc.common.persistence.dao.OutsourceOrderMapper;
import com.cebrains.hrc.modular.outsource.service.IOutsourceOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-07-22
 */
@Service
public class OutsourceOrderServiceImpl extends ServiceImpl<OutsourceOrderMapper, OutsourceOrder> implements IOutsourceOrderService {

}
