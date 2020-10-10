package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.SpecialApplicationSettlement;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 外协特殊申请管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class SpecialApplicationSettlementWrapper extends BaseCustomWarpper<SpecialApplicationSettlement> {

    public SpecialApplicationSettlementWrapper(List<SpecialApplicationSettlement> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(SpecialApplicationSettlement item) {
        item.setSpecialApplicationNumber(ConstantFactory.me().getNumberBySpecialApplication(item.getSpecialApplication()));
        item.setMemberName(ConstantFactory.me().getMemberNameBySpecialApplication(item.getSpecialApplication()));
        item.setPayTypeName(ConstantFactory.me().getPaymentMethodName(item.getPayType()));
//        item.setStatusName(ConstantFactory.me().getStatusBySpecialApplicationSettlement(item.getId()));
    }

}
