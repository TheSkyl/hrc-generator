package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.MedicineSupplier;
import com.cebrains.hrc.common.persistence.model.Project;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class MedicineSupplierWrapper extends BaseCustomWarpper<MedicineSupplier> {

    public MedicineSupplierWrapper(List<MedicineSupplier> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(MedicineSupplier item) {
        item.setCategoryName(ConstantFactory.me().getMedicineSupplierCategory(item.getCategory()));
    }

}
