package com.cebrains.hrc.modular.outsource.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.SpecialApplicationAttachment;
import com.cebrains.hrc.common.persistence.model.SpecialApplicationExec;
import com.cebrains.hrc.common.persistence.model.User;
import com.cebrains.hrc.common.persistence.vo.SpecialApplicationSuggestVo;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationAttachmentService;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationExecService;
import com.cebrains.hrc.modular.resource.wrapper.SpecialApplicationWrapper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.SpecialApplication;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationService;

import java.util.ArrayList;
import java.util.List;

/**
 * 特殊申请控制器
 *
 * @author frank
 * @Date 2018-07-11 15:28:14
 */
@Controller
@RequestMapping("/specialApplication")
public class SpecialApplicationController extends BaseController {

    private String PREFIX = "/outsource/specialApplication/";

    @Autowired
    private ISpecialApplicationService specialApplicationService;
    @Autowired
    private ISpecialApplicationExecService iSpecialApplicationExecService;
    @Autowired
    private ISpecialApplicationAttachmentService specialApplicationAttachmentService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 跳转到特殊申请首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "specialApplication.html";
    }

    /**
     * 跳转到添加特殊申请
     */
    @RequestMapping("/specialApplication_add")
    public String specialApplicationAdd(Model model) {
        model.addAttribute("projects", ConstantFactory.me().findAllOutSourceProject());
        fillIfCanMaintainForOtherDept(model);
        return PREFIX + "specialApplication_add.html";
    }

    /**
     * 跳转到修改特殊申请
     */
    @RequestMapping("/specialApplication_update/{specialApplicationId}")
    public String specialApplicationUpdate(@PathVariable Integer specialApplicationId, Model model) {
        SpecialApplication specialApplication = specialApplicationService.selectById(specialApplicationId);
        model.addAttribute("item",specialApplication);
        model.addAttribute("projects", ConstantFactory.me().findAllOutSourceProject());
        specialApplication.setMemberName(ConstantFactory.me().getMemberName(specialApplication.getMember()));
        fillIfCanMaintainForOtherDept(model);
        LogObjectHolder.me().set(specialApplication);
        return PREFIX + "specialApplication_edit.html";
    }

    /**
     * 获取特殊申请列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<SpecialApplication> specialApplications = specialApplicationService.selectList(null);
        return new SpecialApplicationWrapper(specialApplications).wrap();
    }

    /**
     * 获取特殊申请列表
     */
    @RequestMapping(value = "/suggestList/{k}")
    @ResponseBody
    public Object suggestList(@PathVariable String k) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer departmentId = shiroUser.getDeptId();
        List<SpecialApplicationSuggestVo> result = specialApplicationService.findSuggest(departmentId,k);
        return result;
    }

    /**
     * 新增特殊申请
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SpecialApplication specialApplication,@RequestParam("attachment[]")List<String> attachment) {
        String applicationNumber = RandomStringUtils.randomAlphabetic(3).concat(RandomStringUtils.randomNumeric(6));
        specialApplication.setApplicationNumber(applicationNumber.toUpperCase());
        specialApplication.setStatus(1);
        specialApplicationService.insert(specialApplication);

        // 附件
        if(attachment!=null){
            attachment.forEach(a ->{
                SpecialApplicationAttachment mhra = new SpecialApplicationAttachment();
                mhra.setSpecialApplication(specialApplication.getId());
                mhra.setPath(a);
                specialApplicationAttachmentService.insert(mhra);
            });
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除特殊申请
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer specialApplicationId) {
        specialApplicationService.deleteById(specialApplicationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改特殊申请
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SpecialApplication specialApplication) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_OS_APPROVE)){
            specialApplication.setStatus(null);
        }
        specialApplicationService.updateById(specialApplication);
        if(specialApplication.getStatus()==2){ // 审核通过
            // 自动创建执行数据
            SpecialApplicationExec sae = new SpecialApplicationExec();
            sae.setSpecialApplication(specialApplication.getId());
            iSpecialApplicationExecService.insert(sae);
        }
        return super.SUCCESS_TIP;
    }


    /**
     * 特殊申请详情
     */
    @RequestMapping(value = "/detail/{specialApplicationId}")
    @ResponseBody
    public Object detail(@PathVariable("specialApplicationId") Integer specialApplicationId) {
        return specialApplicationService.selectById(specialApplicationId);
    }
    private void fillIfCanMaintainForOtherDept(Model model) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_OS_EDIT_OTHER_DEPT)){
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
