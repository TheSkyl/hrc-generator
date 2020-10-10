package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.OutsourceOrder;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 外协特殊申请管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class OutsourceOrderWrapper extends BaseCustomWarpper<OutsourceOrder> {

    public OutsourceOrderWrapper(List<OutsourceOrder> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(OutsourceOrder item) {
        item.setSupplierName(ConstantFactory.me().getMedicineSupplier(item.getSupplier()));
        item.setStatusName(ConstantFactory.me().getOutsourceOrderStatus(item.getStatus()));
    }

}
