package com.cebrains.hrc.modular.outsource.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Expert;
import com.cebrains.hrc.common.persistence.model.SpecialApplication;
import com.cebrains.hrc.common.persistence.model.SpecialApplicationExecDetail;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationExecDetailService;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationService;
import com.cebrains.hrc.modular.resource.service.IExpertService;
import com.cebrains.hrc.modular.resource.wrapper.SpecialApplicationExecWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.common.persistence.model.SpecialApplicationExec;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationExecService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 申请执行控制器
 *
 * @author frank
 * @Date 2018-07-18 07:33:06
 */
@Controller
@RequestMapping("/specialApplicationExec")
public class SpecialApplicationExecController extends BaseController {

    private String PREFIX = "/outsource/specialApplicationExec/";

    @Autowired
    private ISpecialApplicationExecService specialApplicationExecService;
    @Autowired
    private ISpecialApplicationService specialApplicationService;
    @Autowired
    private ISpecialApplicationExecDetailService specialApplicationExecDetailService;
    @Autowired
    private IExpertService expertService;

    /**
     * 跳转到申请执行首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "specialApplicationExec.html";
    }

    /**
     * 跳转到添加申请执行
     */
    @RequestMapping("/specialApplicationExec_add")
    public String specialApplicationExecAdd() {
        return PREFIX + "specialApplicationExec_add.html";
    }

    /**
     * 跳转到修改申请执行
     */
    @RequestMapping("/specialApplicationExec_update/{specialApplicationExecId}")
    public String specialApplicationExecUpdate(@PathVariable Integer specialApplicationExecId, Model model) {
        SpecialApplicationExec specialApplicationExec = specialApplicationExecService.selectById(specialApplicationExecId);
        model.addAttribute("item",specialApplicationExec);
        specialApplicationExec.setMemberName(ConstantFactory.me().getMemberNameBySpecialApplication(specialApplicationExec.getSpecialApplication()));
        SpecialApplication specialApplication = specialApplicationService.selectById(specialApplicationExec.getSpecialApplication());
        specialApplicationExec.setSpecialApplicationNumber(specialApplication.getApplicationNumber());
        model.addAttribute("applicationDepartment",ConstantFactory.me().getDeptName(specialApplication.getDepartment()));
        model.addAttribute("applier",ConstantFactory.me().getUserNameById(specialApplication.getCreatedBy()));
        List<Expert> experts = expertService.selectList(null);
        model.addAttribute("experts",experts);
        List<SpecialApplicationExecDetail> saeds = specialApplicationExecDetailService.selectList(new EntityWrapper<SpecialApplicationExecDetail>().eq("special_application_exec", specialApplicationExec.getId()));
        String selExperts = saeds.stream().map(SpecialApplicationExecDetail::getExpert).map(String::valueOf).collect(Collectors.joining(","));
        model.addAttribute("selExperts",selExperts);
        LogObjectHolder.me().set(specialApplicationExec);
        return PREFIX + "specialApplicationExec_edit.html";
    }

    /**
     * 获取申请执行列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<SpecialApplicationExec> specialApplicationExecs = specialApplicationExecService.selectList(null);
        return new SpecialApplicationExecWrapper(specialApplicationExecs).wrap();
    }

    /**
     * 新增申请执行
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SpecialApplicationExec specialApplicationExec) {
        specialApplicationExecService.insert(specialApplicationExec);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除申请执行
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer specialApplicationExecId) {
        specialApplicationExecService.deleteById(specialApplicationExecId);
        return SUCCESS_TIP;
    }

    /**
     * 修改申请执行
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SpecialApplicationExec specialApplicationExec, @RequestParam("experts[]") List<Integer> experts) {
        specialApplicationExec.setUpdateTime(new Date());
        specialApplicationExecService.updateById(specialApplicationExec);
        specialApplicationExecDetailService.delete(new EntityWrapper<SpecialApplicationExecDetail>().eq("special_application_exec",specialApplicationExec.getId()));
        if(experts!=null){
            experts.forEach(e->{
                SpecialApplicationExecDetail saed = new SpecialApplicationExecDetail();
                saed.setSpecialApplicationExec(specialApplicationExec.getId());
                saed.setExpert(e);
                specialApplicationExecDetailService.insert(saed);
            });
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 申请执行详情
     */
    @RequestMapping(value = "/detail/{specialApplicationExecId}")
    @ResponseBody
    public Object detail(@PathVariable("specialApplicationExecId") Integer specialApplicationExecId) {
        return specialApplicationExecService.selectById(specialApplicationExecId);
    }
}
