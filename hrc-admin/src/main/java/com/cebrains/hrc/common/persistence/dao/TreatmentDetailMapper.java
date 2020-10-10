package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.TreatmentDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 康护关联 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-05-14
 */
public interface TreatmentDetailMapper extends BaseMapper<TreatmentDetail> {

    List<String> findProjectNames(@Param("tid") Integer treatmentId);

    List<Map<String,String>> findUsedConsumable(@Param("tid") Integer treatmentId);

    List<Map<String,Object>> findProductNamesBySettlement(@Param("settlement")Integer settlement);
}
