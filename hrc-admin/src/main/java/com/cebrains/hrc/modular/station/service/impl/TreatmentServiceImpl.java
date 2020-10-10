package com.cebrains.hrc.modular.station.service.impl;

import com.cebrains.hrc.common.persistence.model.Treatment;
import com.cebrains.hrc.common.persistence.dao.TreatmentMapper;
import com.cebrains.hrc.modular.station.service.ITreatmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 康护记录 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-05-11
 */
@Service
public class TreatmentServiceImpl extends ServiceImpl<TreatmentMapper, Treatment> implements ITreatmentService {

    @Autowired
    TreatmentMapper treatmentMapper;

    @Override
    public List<Map> findTreatmentSuggest(String k) {
        return treatmentMapper.findTreatmentSuggest(k);
    }

    @Override
    public Map findProjectNamesByTreatment(Integer treatment) {
        return treatmentMapper.findProjectNamesByTreatment(treatment);
    }

    @Override
    public List<Map> treatmentByMember(Integer member) {
        return treatmentMapper.treatmentByMember(member);
    }
}
