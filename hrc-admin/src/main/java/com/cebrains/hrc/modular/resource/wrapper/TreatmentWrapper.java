package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Treatment;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class TreatmentWrapper extends BaseCustomWarpper<Treatment> {

    public TreatmentWrapper(List<Treatment> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(Treatment item) {
        item.setUserName(ConstantFactory.me().getMemberName(item.getUserId()));
//        item.setTypeName(ConstantFactory.me().getTreatmentTypeName(item.getType()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
        item.setTechnicianName(ConstantFactory.me().getUserNameById(item.getTechnician()));
        item.setStatusName(ConstantFactory.me().getTreatmentStatusName(item.getStatus()));
//        item.setStatusName(ConstantFactory.me().getTreatmentStatusName(item.getStatus()));
    }

}
