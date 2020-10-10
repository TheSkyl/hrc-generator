package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.Appointment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预约 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-04-12
 */
public interface AppointmentMapper extends BaseMapper<Appointment> {

    List<String> findProjectNames(@Param("pid") Integer pid);
}
