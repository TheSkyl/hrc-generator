package com.cebrains.hrc.modular.member.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.Appointment;
import com.cebrains.hrc.common.persistence.model.Treatment;
import com.cebrains.hrc.common.persistence.model.TreatmentDetail;
import com.cebrains.hrc.common.persistence.model.User;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.member.service.IAppointmentService;
import com.cebrains.hrc.modular.resource.wrapper.AppointmentWrapper;
import com.cebrains.hrc.modular.station.service.ITreatmentDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * 会员预约控制器
 *
 * @author frank
 * @Date 2018-04-12 15:28:57
 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController extends BaseController {

    private String PREFIX = "/member/appointment/";

    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private ITreatmentDetailService treatmentDetailService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 跳转到会员预约首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "appointment.html";
    }

    /**
     * 跳转到添加会员预约
     */
    @RequestMapping("/appointment_add")
    public String appointmentAdd(Model model) {
        model.addAttribute("projects", ConstantFactory.me().findAllProject());
        model.addAttribute("typeDict", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_APPT));
        fillIfCanAppointForOtherDept(model);
        return PREFIX + "appointment_add.html";
    }

    /**
     * 跳转到修改会员预约
     */
    @RequestMapping("/appointment_update/{appointmentId}")
    public String appointmentUpdate(@PathVariable Integer appointmentId,Model model) {
        Appointment appointment = appointmentService.selectById(appointmentId);
        appointment.setMemberName(ConstantFactory.me().getMemberName(appointment.getMemberId()));
        appointment.setDepartmentName(ConstantFactory.me().getDeptName(appointment.getDepartment()));
//        appointment.setTypeName(ConstantFactory.me().getAppointmentTypeName(appointment.getType()));
//        appointment.setProjectName(ConstantFactory.me().getProjectName(appointment.getProject()));
        if(appointment.getStatus()==3){
            appointment.setProjectName(StringUtils.join(ConstantFactory.me().getProjectNamesByAppointment(appointment.getId()),","));
            appointment.setTypeName(ConstantFactory.me().getAppointmentTypeName(appointment.getType()));
            appointment.setStatusName(ConstantFactory.me().getAppointmentStatusName(appointment.getStatus()));
            appointment.setTechnicianName(ConstantFactory.me().getUserNameById(appointment.getTechnician()));
            model.addAttribute("item",appointment);
            return PREFIX +"appointment_detail.html";
        }
        model.addAttribute("projects", ConstantFactory.me().findAllProject());
        model.addAttribute("typeDict", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_APPT));
        model.addAttribute("statusDict", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_APPST));
        model.addAttribute("item",appointment);
        fillIfCanAppointForOtherDept(model);
        LogObjectHolder.me().set(appointment);
        return PREFIX + "appointment_edit.html";
    }

    /**
     * 获取会员预约列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Wrapper<Appointment> wrapper = new EntityWrapper<>();
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_APPOINTMENT_LIST_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            wrapper = wrapper.eq("department", departmentId);
        }
        wrapper = wrapper.orderDesc(Collections.singletonList("create_time"));
        List<Appointment> appointments = appointmentService.selectList(wrapper);
        return new AppointmentWrapper(appointments).wrap();
    }

    /**
     * 新增会员预约
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Appointment appointment, @RequestParam("projects[]") List<Integer> projects) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_APPOINTMENT_FOR_OTHER_DEPT)){
            // 不能为其他门店预约
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            if(departmentId!=appointment.getDepartment()){
                return new ErrorTip(400,"不能预约其他门店");
            }
        }
        if (projects != null)
            appointment.setProject(StringUtils.join(projects,","));
        appointmentService.insert(appointment);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除会员预约
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appointmentId) {
        appointmentService.deleteById(appointmentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员预约
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Appointment appointment,@RequestParam("projects[]") List<Integer> projects) {
        if (projects != null)
            appointment.setProject(StringUtils.join(projects,","));
        boolean updated = appointmentService.updateById(appointment);
        if(updated && appointment.getStatus()==3){ // 已签到,生成康护记录
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            if(appointment.getDepartment()!=departmentId){
                return new ErrorTip(400,"不可为其他门店预约签到");
            }
            Treatment treatment = new Treatment();
            treatment.setUserId(appointment.getMemberId());
            treatment.setDepartment(appointment.getDepartment());
            treatment.setTechnician(appointment.getTechnician());
            treatment.setAppointment(appointment.getId());
            treatment.setStatus(1); // 1标示来自预约签到,尚未康护#select>

            appointmentService.addTreatmentForTechnician(treatment);

            if (projects != null){
                projects.forEach(p -> {
                    TreatmentDetail td = new TreatmentDetail();
                    td.setTreatmentId(treatment.getId());
                    td.setProjectId(p);
                    treatmentDetailService.insert(td);
                });
            }
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 会员预约详情
     */
    @RequestMapping(value = "/detail/{appointmentId}")
    @ResponseBody
    public Object detail(@PathVariable("appointmentId") Integer appointmentId) {
        return appointmentService.selectById(appointmentId);
    }


    /**
     * 对是否有权限为其他门店预约,进行修改
     * @param model
     */
    private void fillIfCanAppointForOtherDept(Model model) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_APPOINTMENT_FOR_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            String departmentName = shiroUser.getDeptName();
            model.addAttribute("departmentId",departmentId);
            model.addAttribute("departmentName",departmentName);
            Wrapper<User> userWrapper = new EntityWrapper<>();
            userWrapper = userWrapper.eq("deptid", departmentId);
            List<User> users = userMapper.selectList(userWrapper);
            model.addAttribute("users",users);
        }else{
            model.addAttribute("departmentId",null);
            model.addAttribute("departmentName",null);
        }
    }
}
