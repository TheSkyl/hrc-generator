package com.cebrains.hrc.modular.station.service;

import com.cebrains.hrc.common.persistence.model.TreatmentDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 康护关联 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-05-14
 */
public interface ITreatmentDetailService extends IService<TreatmentDetail> {

    List<String> findProjectNames(Integer treatmentId);

    List<Map<String,String>> findUsedConsumable(Integer treatmentId);
}
