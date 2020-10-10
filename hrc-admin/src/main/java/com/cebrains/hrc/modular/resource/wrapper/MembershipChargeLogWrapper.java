package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Member;
import com.cebrains.hrc.common.persistence.model.MembershipCard;
import com.cebrains.hrc.common.persistence.model.MembershipChargeLog;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class MembershipChargeLogWrapper extends BaseCustomWarpper<MembershipChargeLog> {

    public MembershipChargeLogWrapper(List<MembershipChargeLog> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(MembershipChargeLog item) {
        item.setCardNumber(ConstantFactory.me().getMembershipCardNumber(item.getCard()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
        item.setCreatedName(ConstantFactory.me().getUserNameById(item.getCreated()));
        Member member = ConstantFactory.me().getMemberByCard(item.getCard());
        if(member==null) {
            item.setMemberName("不存在或已删除");
            item.setMobile("不存在或已删除");
            item.setIdCard("不存在或已删除");
        }else{
            item.setMemberName(member.getRealName());
            item.setMobile(member.getPhone());
            item.setIdCard(member.getIdCard());
        }
    }

}
