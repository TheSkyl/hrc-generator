package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.ConsumableTransferLog;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

public class ConsumableTransferLogWrapper extends BaseCustomWarpper<ConsumableTransferLog> {

    public ConsumableTransferLogWrapper(List<ConsumableTransferLog> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(ConsumableTransferLog item) {
        item.setSrcConsumableName(ConstantFactory.me().getConsumableName(item.getSrcId()));
        item.setDestConsumableName(ConstantFactory.me().getConsumableName(item.getDestId()));
        item.setSrcDepartmentName(ConstantFactory.me().getDeptName(item.getSrcDepartment()));
        item.setDestDepartmentName(ConstantFactory.me().getDeptName(item.getDestDepartment()));
        item.setCreatedByName(ConstantFactory.me().getUserNameById(item.getCreatedBy()));
    }

}