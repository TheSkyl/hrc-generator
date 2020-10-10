package com.cebrains.hrc.modular.outsource.service.impl;

import com.cebrains.hrc.common.persistence.model.SpecialApplicationSettlement;
import com.cebrains.hrc.common.persistence.dao.SpecialApplicationSettlementMapper;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationSettlementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 特殊申请结算 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-07-18
 */
@Service
public class SpecialApplicationSettlementServiceImpl extends ServiceImpl<SpecialApplicationSettlementMapper, SpecialApplicationSettlement> implements ISpecialApplicationSettlementService {
    @Autowired
    SpecialApplicationSettlementMapper specialApplicationSettlementMapper;

    @Override
    public Map<String, Object> paymentInfo(String said) {
        return specialApplicationSettlementMapper.paymentInfo(Integer.valueOf(said));
    }

    @Override
    public List<SpecialApplicationSettlement> selectListByNumber(String specialApplicationNumber, Integer department) {
        return specialApplicationSettlementMapper.selectListByNumber(specialApplicationNumber,department);
    }
}
