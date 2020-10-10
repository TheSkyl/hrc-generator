package com.cebrains.hrc.modular.station.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.TreatmentReport;
import com.cebrains.hrc.common.persistence.model.User;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.resource.wrapper.TreatmentReportWrapper;
import com.cebrains.hrc.modular.station.service.ITreatmentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 康护报告控制器
 *
 * @author frank
 * @Date 2018-10-09 08:30:03
 */
@Controller
@RequestMapping("/treatmentReport")
public class TreatmentReportController extends BaseController {

    private String PREFIX = "/station/treatmentReport/";

    @Autowired
    private ITreatmentReportService treatmentReportService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 跳转到康护报告首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "treatmentReport.html";
    }

    /**
     * 跳转到添加康护报告
     */
    @RequestMapping("/treatmentReport_add")
    public String treatmentReportAdd(Model model) {
        fillIfCanMaintainForOtherDept(model);
        return PREFIX + "treatmentReport_add.html";
    }

    /**
     * 跳转到修改康护报告
     */
    @RequestMapping("/treatmentReport_update/{treatmentReportId}")
    public String treatmentReportUpdate(@PathVariable Integer treatmentReportId, Model model) {
        TreatmentReport treatmentReport = treatmentReportService.selectById(treatmentReportId);
        treatmentReport.setMemberName(ConstantFactory.me().getMemberName(treatmentReport.getMember()));
        treatmentReport.setDepartmentName(ConstantFactory.me().getDeptName(treatmentReport.getDepartment()));
        treatmentReport.setCreatedByName(ConstantFactory.me().getUserNameById(treatmentReport.getCreatedBy()));
        model.addAttribute("item",treatmentReport);
        LogObjectHolder.me().set(treatmentReport);
        return PREFIX + "treatmentReport_edit.html";
    }

    /**
     * 获取康护报告列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<TreatmentReport> treatmentReports = treatmentReportService.selectList(null);
        return new TreatmentReportWrapper(treatmentReports).wrap();
    }

    /**
     * 新增康护报告
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TreatmentReport treatmentReport) {
        treatmentReportService.insert(treatmentReport);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除康护报告
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer treatmentReportId) {
        treatmentReportService.deleteById(treatmentReportId);
        return SUCCESS_TIP;
    }

    /**
     * 修改康护报告
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TreatmentReport treatmentReport) {
        treatmentReportService.updateById(treatmentReport);
        return super.SUCCESS_TIP;
    }

    /**
     * 康护报告详情
     */
    @RequestMapping(value = "/detail/{treatmentReportId}")
    @ResponseBody
    public Object detail(@PathVariable("treatmentReportId") Integer treatmentReportId) {
        return treatmentReportService.selectById(treatmentReportId);
    }


    /**
     * 对是否有权限为其他门店设备进行修改
     * @param model
     */
    private void fillIfCanMaintainForOtherDept(Model model) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_DEVICE_EDIT_OTHER_DEPT)){
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
            model.addAttribute("users",new ArrayList<User>());
        }
    }
}
