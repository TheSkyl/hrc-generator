package com.cebrains.hrc.modular.healthProgram.service.impl;

import com.cebrains.hrc.common.persistence.model.HealthProgram;
import com.cebrains.hrc.common.persistence.dao.HealthProgramMapper;
import com.cebrains.hrc.modular.healthProgram.service.IHealthProgramService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 健康方案 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-08-26
 */
@Service
public class HealthProgramServiceImpl extends ServiceImpl<HealthProgramMapper, HealthProgram> implements IHealthProgramService {

}
