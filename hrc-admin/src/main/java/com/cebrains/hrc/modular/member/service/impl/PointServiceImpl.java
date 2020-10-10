package com.cebrains.hrc.modular.member.service.impl;

import com.cebrains.hrc.common.persistence.model.Point;
import com.cebrains.hrc.common.persistence.dao.PointMapper;
import com.cebrains.hrc.modular.member.service.IPointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分表 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-03-08
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements IPointService {

}
