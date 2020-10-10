package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Member;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class MemberWrapper extends BaseCustomWarpper<Member> {

    public MemberWrapper(List<Member> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(Member item) {
        item.setGenderName(ConstantFactory.me().getSexName(item.getGender()));
        item.setClinicName(ConstantFactory.me().getDeptName(item.getClinic()));
        item.setCreatorName(ConstantFactory.me().getUserNameById(item.getCreatedBy()));
//        item.setBloodTypeName(ConstantFactory.me().getBloodTypeName(item.getGender()));
//        item.setMaritalStatusName(ConstantFactory.me().getMaritalStatusName(item.getMaritalStatus()));
//        item.setHaveChildrenName(ConstantFactory.me().getYesOrNoName(item.getHaveChildren()));
//        item.setPreferredNursingTimeName(ConstantFactory.me().getPreferredNursingTimeName(item.getPreferredNursingTime()));
//        item.setAlcoholAllergyName(ConstantFactory.me().getYesOrNoName(item.getAlcoholAllergy()));
    }

}
