package com.cebrains.hrc.modular.outsource.service;

import com.cebrains.hrc.common.persistence.model.SpecialApplicationSettlement;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 特殊申请结算 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-07-18
 */
public interface ISpecialApplicationSettlementService extends IService<SpecialApplicationSettlement> {

    Map<String, Object> paymentInfo(String said);

    List<SpecialApplicationSettlement> selectListByNumber(String specialApplicationNumber, Integer department);
}
