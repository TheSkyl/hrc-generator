package com.cebrains.hrc.modular.member.service.impl;

import com.cebrains.hrc.common.persistence.model.ProductSettlement;
import com.cebrains.hrc.common.persistence.dao.ProductSettlementMapper;
import com.cebrains.hrc.modular.member.service.IProductSettlementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品结算 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-09-21
 */
@Service
public class ProductSettlementServiceImpl extends ServiceImpl<ProductSettlementMapper, ProductSettlement> implements IProductSettlementService {

}
