package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.RehabilitationProgram;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 外协特殊申请管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class RehabilitationProgramWrapper extends BaseCustomWarpper<RehabilitationProgram> {

    public RehabilitationProgramWrapper(List<RehabilitationProgram> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(RehabilitationProgram item) {
        item.setMemberName(ConstantFactory.me().getMemberName(item.getMember()));
        item.setCreatedByName(ConstantFactory.me().getUserNameById(item.getCreatedBy()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
    }

}
