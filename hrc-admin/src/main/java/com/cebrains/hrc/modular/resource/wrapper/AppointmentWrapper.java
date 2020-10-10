package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Appointment;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class AppointmentWrapper extends BaseCustomWarpper<Appointment> {

    public AppointmentWrapper(List<Appointment> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(Appointment item) {
        item.setMemberName(ConstantFactory.me().getMemberName(item.getMemberId()));
        item.setTypeName(ConstantFactory.me().getAppointmentTypeName(item.getType()));
//        item.setProjectName(ConstantFactory.me().getProjectName(item.getProject()));
        item.setProjectName(StringUtils.join(ConstantFactory.me().getProjectNamesByAppointment(item.getId()),","));
        item.setStatusName(ConstantFactory.me().getAppointmentStatusName(item.getStatus()));
        item.setDepartmentName(ConstantFactory.me().getDeptName(item.getDepartment()));
        item.setTechnicianName(ConstantFactory.me().getUserNameById(item.getTechnician()));
//        item.setBloodTypeName(ConstantFactory.me().getBloodTypeName(item.getGender()));
//        item.setMaritalStatusName(ConstantFactory.me().getMaritalStatusName(item.getMaritalStatus()));
//        item.setHaveChildrenName(ConstantFactory.me().getYesOrNoName(item.getHaveChildren()));
//        item.setPreferredNursingTimeName(ConstantFactory.me().getPreferredNursingTimeName(item.getPreferredNursingTime()));
//        item.setAlcoholAllergyName(ConstantFactory.me().getYesOrNoName(item.getAlcoholAllergy()));
    }

}
