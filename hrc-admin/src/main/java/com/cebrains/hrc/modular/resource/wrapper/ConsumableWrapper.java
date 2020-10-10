package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Consumable;
import com.cebrains.hrc.common.persistence.model.Consumable;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

public class ConsumableWrapper extends BaseCustomWarpper<Consumable> {

    public ConsumableWrapper(List<Consumable> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(Consumable item) {
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
    }

}