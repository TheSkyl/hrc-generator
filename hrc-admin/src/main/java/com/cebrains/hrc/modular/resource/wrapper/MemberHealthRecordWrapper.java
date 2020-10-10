package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.MemberHealthRecord;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 健康档案的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class MemberHealthRecordWrapper extends BaseCustomWarpper<MemberHealthRecord> {

    public MemberHealthRecordWrapper(List<MemberHealthRecord> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(MemberHealthRecord item) {
        item.setCreatedByName(ConstantFactory.me().getUserNameById(item.getCreatedBy()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
        item.setMemberName(ConstantFactory.me().getMemberName(item.getMember()));
        item.setMemberPhone(ConstantFactory.me().getMemberPhone(item.getMember()));
    }

}
