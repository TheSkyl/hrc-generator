package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.ConsumableCheckLog;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

public class ConsumableCheckLogWrapper extends BaseCustomWarpper<ConsumableCheckLog> {

    public ConsumableCheckLogWrapper(List<ConsumableCheckLog> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(ConsumableCheckLog item) {
        item.setConsumableName(ConstantFactory.me().getConsumableName(item.getConsumable()));
        item.setDepartmentName(ConstantFactory.me().getDepartmentNameByConsumable(item.getConsumable()));
        item.setCreatedByName(ConstantFactory.me().getUserNameById(item.getCreatedBy()));
    }

}