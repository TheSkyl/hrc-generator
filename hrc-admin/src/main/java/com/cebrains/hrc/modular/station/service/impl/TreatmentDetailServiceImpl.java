package com.cebrains.hrc.modular.station.service.impl;

import com.cebrains.hrc.common.persistence.model.TreatmentDetail;
import com.cebrains.hrc.common.persistence.dao.TreatmentDetailMapper;
import com.cebrains.hrc.modular.station.service.ITreatmentDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 康护关联 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-05-14
 */
@Service
public class TreatmentDetailServiceImpl extends ServiceImpl<TreatmentDetailMapper, TreatmentDetail> implements ITreatmentDetailService {

    @Autowired
    TreatmentDetailMapper treatmentDetailMapper;

    @Override
    public List<String> findProjectNames(Integer treatmentId) {
        return treatmentDetailMapper.findProjectNames(treatmentId);
    }

    @Override
    public List<Map<String, String>> findUsedConsumable(Integer treatmentId) {
        return treatmentDetailMapper.findUsedConsumable(treatmentId);
    }
}
