package com.cebrains.hrc.modular.goods.service.impl;

import com.cebrains.hrc.common.persistence.model.Device;
import com.cebrains.hrc.common.persistence.dao.DeviceMapper;
import com.cebrains.hrc.modular.goods.service.IDeviceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备管理 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-05-10
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

}
