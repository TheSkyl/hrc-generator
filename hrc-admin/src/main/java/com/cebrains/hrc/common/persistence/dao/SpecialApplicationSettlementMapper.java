package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.SpecialApplicationSettlement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 特殊申请结算 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-07-18
 */
public interface SpecialApplicationSettlementMapper extends BaseMapper<SpecialApplicationSettlement> {

    Map<String,Object> paymentInfo(@Param("said") Integer said);

    List<SpecialApplicationSettlement> selectListByNumber(@Param("san") String specialApplicationNumber,@Param("did")Integer department);
}
