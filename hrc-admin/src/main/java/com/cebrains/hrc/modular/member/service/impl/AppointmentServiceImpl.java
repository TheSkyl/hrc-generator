package com.cebrains.hrc.modular.member.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cebrains.hrc.common.persistence.dao.AppointmentMapper;
import com.cebrains.hrc.common.persistence.dao.TreatmentMapper;
import com.cebrains.hrc.common.persistence.model.Appointment;
import com.cebrains.hrc.common.persistence.model.Treatment;
import com.cebrains.hrc.modular.member.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预约 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-04-12
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements IAppointmentService {

    @Autowired
    private TreatmentMapper treatmentMapper;

    @Override
    public void addTreatmentForTechnician(Treatment treatment) {
        treatmentMapper.insert(treatment);
    }
}
