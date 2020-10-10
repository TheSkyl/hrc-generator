package com.cebrains.hrc.modular.resource.service.impl;

import com.cebrains.hrc.common.persistence.model.Expert;
import com.cebrains.hrc.common.persistence.dao.ExpertMapper;
import com.cebrains.hrc.modular.resource.service.IExpertService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专家表 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-03-06
 */
@Service
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements IExpertService {

}
