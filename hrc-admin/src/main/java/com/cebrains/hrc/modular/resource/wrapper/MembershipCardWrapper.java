package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.MembershipCard;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class MembershipCardWrapper extends BaseCustomWarpper<MembershipCard> {

    public MembershipCardWrapper(List<MembershipCard> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(MembershipCard item) {
        item.setUserName(ConstantFactory.me().getMemberName(item.getUser()));
        item.setLevelName(ConstantFactory.me().getMemberLevelName(item.getLevel()));
        String projectName = ConstantFactory.me().getProjectName(item.getProject());
        item.setProjectName("未知".equals(projectName)?"未绑定":projectName);
        item.setDiscountText(item.getDiscount()<100?(item.getDiscount()/10)+"折":"--");
//        item.setBloodTypeName(ConstantFactory.me().getBloodTypeName(item.getGender()));
//        item.setMaritalStatusName(ConstantFactory.me().getMaritalStatusName(item.getMaritalStatus()));
//        item.setHaveChildrenName(ConstantFactory.me().getYesOrNoName(item.getHaveChildren()));
//        item.setPreferredNursingTimeName(ConstantFactory.me().getPreferredNursingTimeName(item.getPreferredNursingTime()));
//        item.setAlcoholAllergyName(ConstantFactory.me().getYesOrNoName(item.getAlcoholAllergy()));
    }

}
