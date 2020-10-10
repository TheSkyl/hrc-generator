package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.SpecialApplication;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 外协特殊申请管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class SpecialApplicationWrapper extends BaseCustomWarpper<SpecialApplication> {

    public SpecialApplicationWrapper(List<SpecialApplication> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(SpecialApplication item) {
        item.setMemberName(ConstantFactory.me().getMemberName(item.getMember()));
        item.setCreatedByName(ConstantFactory.me().getUserNameById(item.getCreatedBy()));
        item.setProjectName(ConstantFactory.me().getProjectNameBySpecialApplication(item.getProject()));
        item.setStatusName(ConstantFactory.me().getSpecialApplicationStatusName(item.getStatus()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
    }

}
