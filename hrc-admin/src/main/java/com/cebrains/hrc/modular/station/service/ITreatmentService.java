package com.cebrains.hrc.modular.station.service;

import com.cebrains.hrc.common.persistence.model.Treatment;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 康护记录 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-05-11
 */
public interface ITreatmentService extends IService<Treatment> {

    List<Map> findTreatmentSuggest(String k);

    Map findProjectNamesByTreatment(Integer treatment);

    List<Map> treatmentByMember(Integer member);
}
