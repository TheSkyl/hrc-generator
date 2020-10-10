package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Treatment;
import com.cebrains.hrc.common.persistence.model.TreatmentReport;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class TreatmentReportWrapper extends BaseCustomWarpper<TreatmentReport> {

    public TreatmentReportWrapper(List<TreatmentReport> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(TreatmentReport item) {
        item.setMemberName(ConstantFactory.me().getMemberName(item.getMember()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
        item.setCreatedByName(ConstantFactory.me().getUserNameById(item.getCreatedBy()));
    }

}
