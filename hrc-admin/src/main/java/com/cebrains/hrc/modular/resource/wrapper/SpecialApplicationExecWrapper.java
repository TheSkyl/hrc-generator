package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.SpecialApplicationExec;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;
import org.aspectj.apache.bcel.classfile.Constant;

import java.util.List;

/**
 * 外协特殊申请管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class SpecialApplicationExecWrapper extends BaseCustomWarpper<SpecialApplicationExec> {

    public SpecialApplicationExecWrapper(List<SpecialApplicationExec> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(SpecialApplicationExec item) {
        item.setSpecialApplicationNumber(ConstantFactory.me().getNumberBySpecialApplication(item.getId()));
        item.setMemberName(ConstantFactory.me().getMemberNameBySpecialApplication(item.getSpecialApplication()));
        item.setStatusName(ConstantFactory.me().getStatusBySpecialApplication(item.getStatus()));
    }

}
