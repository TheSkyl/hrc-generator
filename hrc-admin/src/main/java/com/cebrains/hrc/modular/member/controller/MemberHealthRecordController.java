package com.cebrains.hrc.modular.member.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.*;
import com.cebrains.hrc.common.persistence.vo.RProjectVO;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.member.service.IMemberHealthRecordAttachmentService;
import com.cebrains.hrc.modular.member.service.impl.MemberHealthRecordAttachmentServiceImpl;
import com.cebrains.hrc.modular.resource.service.IProjectService;
import com.cebrains.hrc.modular.resource.wrapper.MemberHealthRecordWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.modular.member.service.IMemberHealthRecordService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 健康档案控制器
 *
 * @author frank
 * @Date 2018-07-02 15:58:52
 */
@Controller
@RequestMapping("/memberHealthRecord")
public class MemberHealthRecordController extends BaseController {

    private String PREFIX = "/member/memberHealthRecord/";

    @Autowired
    private IMemberHealthRecordService memberHealthRecordService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IMemberHealthRecordAttachmentService memberHealthRecordAttachmentService;
    @Autowired
    private IProjectService projectService;

    /**
     * 跳转到健康档案首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberHealthRecord.html";
    }

    /**
     * 跳转到添加健康档案
     */
    @RequestMapping("/memberHealthRecord_add")
    public String memberHealthRecordAdd(Model model) {
        fillIfCanMaintainForOtherDept(model);
        return PREFIX + "memberHealthRecord_add.html";
    }

    /**
     * 跳转到修改健康档案
     */
    @RequestMapping("/memberHealthRecord_update/{memberHealthRecordId}")
    public String memberHealthRecordUpdate(@PathVariable Integer memberHealthRecordId, Model model) {
        MemberHealthRecord memberHealthRecord = memberHealthRecordService.selectById(memberHealthRecordId);
        model.addAttribute("item",memberHealthRecord);
        fillIfCanMaintainForOtherDept(model);
        LogObjectHolder.me().set(memberHealthRecord);
        return PREFIX + "memberHealthRecord_edit.html";
    }

    /**
     * 跳转到修改健康档案
     */
    @RequestMapping("/memberHealthRecord_detail/{memberHealthRecordId}")
    public String memberHealthRecordDetail(@PathVariable Integer memberHealthRecordId, Model model) {
        MemberHealthRecord memberHealthRecord = memberHealthRecordService.selectById(memberHealthRecordId);
        memberHealthRecord.setDepartmentName(ConstantFactory.me().getDeptName(memberHealthRecord.getDepartment()));
        memberHealthRecord.setCreatedByName(ConstantFactory.me().getUserNameById(memberHealthRecord.getCreatedBy()));
        memberHealthRecord.setMemberName(ConstantFactory.me().getMemberName(memberHealthRecord.getMember()));
        memberHealthRecord.setMemberPhone(ConstantFactory.me().getMemberPhone(memberHealthRecord.getMember()));
        List<MemberHealthRecordAttachment> hras = memberHealthRecordAttachmentService.selectList(new EntityWrapper<MemberHealthRecordAttachment>().eq("health_record", memberHealthRecordId));
        if(hras.size()>0){
            model.addAttribute("document",hras.stream().map(h -> h.getPath()).collect(Collectors.toList()));
        }
        model.addAttribute("item",memberHealthRecord);
        LogObjectHolder.me().set(memberHealthRecord);
        return PREFIX + "memberHealthRecord_detail.html";
    }

    /**
     * 选择健康方案
     */
    @PostMapping("/projectByMember")
    @ResponseBody
    public List<Map<String,Object>> projectByMember(@RequestParam("member")Integer member) {
        List<Map<String,Object>> resultList = new ArrayList<>();
        Wrapper<MemberHealthRecord> wrapper = new EntityWrapper<MemberHealthRecord>();
        wrapper.eq("member",member);
        List<MemberHealthRecord> memberHealthRecords = memberHealthRecordService.selectList(wrapper);
        memberHealthRecords.forEach(mhr ->{
            Map<String,Object> result = new HashMap<>();
            result.put("id",mhr.getProject());
            result.put("name",mhr.getEvaluation());
            resultList.add(result);
        });
        return resultList;
    }

    /**
     * 选择健康方案
     */
    @RequestMapping("/solutions")
    public String solutions( Model model) {
        List<Dict> allProjectCategories = ConstantFactory.me().findAllProjectCategories();
        List<RProjectVO> rps = new ArrayList<>();
        for(Dict d : allProjectCategories){
            RProjectVO rp = new RProjectVO();
            rp.setId(d.getId());
            rp.setName(d.getName());
            Wrapper<Project> projectWrapper = new EntityWrapper<>();
            projectWrapper.eq("category",d.getNum());
            List<Project> projects = projectService.selectList(projectWrapper);
            rp.setRps(projects);
            rps.add(rp);
        }
        model.addAttribute("item",rps);
        return PREFIX + "memberHealthRecord_solutions.html";
    }

    /**
     * 获取健康档案列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<MemberHealthRecord> memberHealthRecords = memberHealthRecordService.selectList(null);
        return new MemberHealthRecordWrapper(memberHealthRecords).wrap();
    }

    /**
     * 新增健康档案
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberHealthRecord memberHealthRecord,@RequestParam String doc,@RequestParam("attachment[]")List<String> attachment) {
        memberHealthRecordService.insert(memberHealthRecord);
//        if(StringUtils.isNotEmpty(doc)){
//            MemberHealthRecordAttachment mhra = new MemberHealthRecordAttachment();
//            mhra.setHealthRecord(memberHealthRecord.getId());
//            mhra.setPath(doc);
//            memberHealthRecordAttachmentService.insert(mhra);
//        }
        // 附件
        if(attachment!=null){
            attachment.forEach(a ->{
                MemberHealthRecordAttachment mhra = new MemberHealthRecordAttachment();
                mhra.setHealthRecord(memberHealthRecord.getId());
                mhra.setPath(a);
                memberHealthRecordAttachmentService.insert(mhra);
            });
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除健康档案
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberHealthRecordId) {
        memberHealthRecordService.deleteById(memberHealthRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改健康档案
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberHealthRecord memberHealthRecord) {
        memberHealthRecordService.updateById(memberHealthRecord);
        return super.SUCCESS_TIP;
    }

    /**
     * 健康档案详情
     */
    @RequestMapping(value = "/detail/{memberHealthRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("memberHealthRecordId") Integer memberHealthRecordId) {
        return memberHealthRecordService.selectById(memberHealthRecordId);
    }

    /**
     * 对是否有权限为其他门店设备进行修改
     * @param model
     */
    private void fillIfCanMaintainForOtherDept(Model model) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_HR_EDIT_OTHER_DEPT)){
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
