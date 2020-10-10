package com.cebrains.hrc.modular.member.service;

import com.cebrains.hrc.common.persistence.model.Appointment;
import com.baomidou.mybatisplus.service.IService;
import com.cebrains.hrc.common.persistence.model.Treatment;

/**
 * <p>
 * 预约 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-04-12
 */
public interface IAppointmentService extends IService<Appointment> {

    void addTreatmentForTechnician(Treatment treatment);
}
