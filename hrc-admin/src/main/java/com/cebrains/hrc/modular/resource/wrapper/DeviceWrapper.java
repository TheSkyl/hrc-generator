package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Device;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 设备管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class DeviceWrapper extends BaseCustomWarpper<Device> {

    public DeviceWrapper(List<Device> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(Device item) {
        item.setStatusName(ConstantFactory.me().getDeviceStatus(item.getStatus()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
        item.setMaintainerName(ConstantFactory.me().getUserNameById(item.getMaintainer()));
    }

}
