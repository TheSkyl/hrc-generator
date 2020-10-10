package com.cebrains.hrc.modular.station.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.DeptMapper;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.*;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.goods.service.IConsumableService;
import com.cebrains.hrc.modular.resource.service.IProjectService;
import com.cebrains.hrc.modular.resource.wrapper.TreatmentWrapper;
import com.cebrains.hrc.modular.station.service.ITreatmentDetailService;
import com.cebrains.hrc.modular.station.service.impl.TreatmentDetailServiceImpl;
import com.cebrains.hrc.modular.system.service.IDeptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.modular.station.service.ITreatmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 康护记录控制器
 *
 * @author frank
 * @Date 2018-05-11 11:45:01
 */
@Controller
@RequestMapping("/treatment")
public class TreatmentController extends BaseController {

    private String PREFIX = "/station/treatment/";

    @Autowired
    private ITreatmentService treatmentService;
    @Autowired
    private ITreatmentDetailService treatmentDetailService;

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IConsumableService consumableService;

    /**
     * 跳转到康护记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "treatment.html";
    }
    /**
     * 跳转到康护记录首页
     */
    @RequestMapping("/record")
    public String record() {
        return PREFIX + "treatment_record.html";
    }

    /**
     * 跳转到添加康护记录
     */
    @RequestMapping("/treatment_add")
    public String treatmentAdd(Model model) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer departmentId = shiroUser.getDeptId();
        Dept dept = deptMapper.selectById(departmentId);
        List<User> users = new ArrayList<>();
        if (dept != null && dept.getIsStore()!= null && dept.getIsStore() == 1) {
            model.addAttribute("departmentId", departmentId);
            model.addAttribute("departmentName", dept.getSimplename());
            Wrapper<User> userWrapper = new EntityWrapper<>();
            userWrapper = userWrapper.eq("deptid", departmentId);
            users = userMapper.selectList(userWrapper);

        }
        List<Project> projects = projectService.selectList(null);
        model.addAttribute("projects", projects);
        model.addAttribute("users", users);
        return PREFIX + "treatment_add.html";
    }

    /**
     * 跳转到修改康护记录
     */
    @RequestMapping("/treatment_update/{treatmentId}")
    public String treatmentUpdate(@PathVariable Integer treatmentId, Model model) {
        Treatment treatment = treatmentService.selectById(treatmentId);
        treatment.setUserName(ConstantFactory.me().getMemberName(treatment.getUserId()));
        treatment.setDepartmentName(ConstantFactory.me().getDeptName(treatment.getDepartment()));
        treatment.setTechnicianName(ConstantFactory.me().getUserNameById(treatment.getTechnician()));
        model.addAttribute("item", treatment);
        if(treatment.getStatus()==0||treatment.getStatus()==1){ // 尚未形成康护记录
            List<Project> projects = projectService.selectList(null);
            model.addAttribute("projects", projects);
        }
//        Wrapper<TreatmentDetail> treatmentDetailWrapper = new EntityWrapper<>();
//        treatmentDetailWrapper = treatmentDetailWrapper.eq("treatment_id", treatmentId);
//        List<TreatmentDetail> treatmentDetails = treatmentDetailService.selectList(treatmentDetailWrapper);
//        if(treatmentDetails!=null && treatmentDetails.size()>0){
//            List<String>
//            treatmentDetails.stream().collect();
//
//            });
//        }
        List<String> pns = treatmentDetailService.findProjectNames(treatmentId);
        String projectNames = String.join(" , ", pns);
        List<Map<String, String>> usedConsumable = treatmentDetailService.findUsedConsumable(treatmentId);
        model.addAttribute("projectNames", projectNames);
        model.addAttribute("usedConsumable", usedConsumable);
        LogObjectHolder.me().set(treatment);
        return PREFIX + "treatment_edit.html";
    }

    /**
     * 获取康护列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Wrapper<Treatment> wrapper = null;
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_TREATMENT_LIST_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("department", departmentId);
        }
        if(wrapper==null) {
            wrapper = new EntityWrapper<>();
            wrapper = wrapper.lt("status", 2);
        }else{
            wrapper = wrapper.and().lt("status", 2);
        }
        wrapper = wrapper.orderDesc(Collections.singletonList("create_time"));
        List<Treatment> treatments = treatmentService.selectList(wrapper);
        return new TreatmentWrapper(treatments).wrap();
    }

    /**
     * 获取康护记录列表
     */
    @RequestMapping(value = "/listRecord")
    @ResponseBody
    public Object listRecord(String condition) {
        Wrapper<Treatment> wrapper = null;
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_TREATMENT_LIST_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("department", departmentId);
        }
        if(wrapper==null) {
            wrapper = new EntityWrapper<>();
            wrapper = wrapper.in("status", Arrays.asList(2,3));
        }else{
            wrapper = wrapper.and().in("status", Arrays.asList(2,3));
        }
        wrapper = wrapper.orderDesc(Collections.singletonList("create_time"));
        List<Treatment> treatments = treatmentService.selectList(wrapper);
        return new TreatmentWrapper(treatments).wrap();
    }

    /**
     * 根据会员获取康复记录
     */
    @PostMapping(value = "/treatmentByMember")
    @ResponseBody
    public Object treatmentByMember(@RequestParam Integer member) {
        List<Map> result = treatmentService.treatmentByMember(member);
        return result;
    }

    /**
     * 获取康护记录建议列表
     */
    @RequestMapping(value = "/suggestList/{k}")
    @ResponseBody
    public Object suggestList(@PathVariable String k) {
        List<Map> result = treatmentService.findTreatmentSuggest(String.format("%%%s%%", k));
        return result;
    }

    /**
     * 获取康护记录建议列表
     */
    @RequestMapping(value = "/projById")
    @ResponseBody
    public Object projById(@RequestParam Integer treatment) {
        Map result = treatmentService.findProjectNamesByTreatment(treatment);
        return result;
    }

    /**
     * 新增康护记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Treatment treatment, @RequestParam("projects[]") List<Integer> projects, String consumable) {
        List<TreatmentDetail> tds = new ArrayList<>();
        List<Consumable> cs = new ArrayList<>();
        if (StringUtils.isNotEmpty(consumable)) {
            try {
                JSONArray ja = JSONArray.parseArray(consumable);
                if (ja != null && ja.size() > 0)
                    for (int i = 0; i < ja.size(); i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        TreatmentDetail td = new TreatmentDetail();
//                        td.setTreatmentId(treatment.getId());
                        Integer cid = jo.getInteger("id");
                        td.setConsumableId(cid);
                        Integer camount = jo.getInteger("amount");
                        td.setConsumableAmount(camount);
//                        treatmentDetailService.insert(td);
                        tds.add(td);
                        if (cid != null && camount > 0) {
                            Consumable c = consumableService.selectById(cid);
                            if (c.getDepartment() != treatment.getDepartment()) {
                                return new ErrorTip(200, "不能使用其他门店耗材");
                            }
                            if (c.getAmount() < camount) {
                                return new ErrorTip(200, String.format("耗材[%s]", c.getName()));
                            }
                            c.setAmount(c.getAmount() - camount);
                            cs.add(c);
                        }else {
                            return new ErrorTip(200, "请正确选择使用耗材");
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return new ErrorTip(200, "请正确选择使用耗材");
        }
        treatment.setStatus(2); // 2表示康护结束,形成康护记录,不可修改
        treatmentService.insert(treatment);
        for(int i=0;i<tds.size();i++){
            TreatmentDetail td = tds.get(i);
            td.setTreatmentId(treatment.getId());
            treatmentDetailService.insert(td);
        }
        for(int i=0;i<cs.size();i++){
            Consumable c = cs.get(i);
            consumableService.updateById(c);
        }
        if (projects != null)
            projects.forEach(p -> {
                TreatmentDetail td = new TreatmentDetail();
                td.setTreatmentId(treatment.getId());
                td.setProjectId(p);
                treatmentDetailService.insert(td);
            });

        return super.SUCCESS_TIP;
    }

    /**
     * 删除康护记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer treatmentId) {
        treatmentService.deleteById(treatmentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改康护记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Treatment treatment, @RequestParam(value = "projects[]",required = false) List<Integer> projects, String consumable) {
        Treatment t = treatmentService.selectById(treatment.getId());
        if(t==null || t.getStatus()==2){
            return new ErrorTip(200, "已经形成康护记录,无法进行编辑");
        }

        List<TreatmentDetail> tds = new ArrayList<>();
        List<Consumable> cs = new ArrayList<>();
        if (StringUtils.isNotEmpty(consumable)) {
            try {
                JSONArray ja = JSONArray.parseArray(consumable);
                if (ja != null && ja.size() > 0)
                    for (int i = 0; i < ja.size(); i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        TreatmentDetail td = new TreatmentDetail();
//                        td.setTreatmentId(treatment.getId());
                        Integer cid = jo.getInteger("id");
                        td.setConsumableId(cid);
                        Integer camount = jo.getInteger("amount");
                        td.setConsumableAmount(camount);
//                        treatmentDetailService.insert(td);
                        tds.add(td);
                        if (cid != null && camount > 0) {
                            Consumable c = consumableService.selectById(cid);
                            if (c.getDepartment() != treatment.getDepartment()) {
                                return new ErrorTip(200, "不能使用其他门店耗材");
                            }
                            if (c.getAmount() < camount) {
                                return new ErrorTip(200, String.format("耗材[%s]", c.getName()));
                            }
                            c.setAmount(c.getAmount() - camount);
                            cs.add(c);
                        }else {
                            return new ErrorTip(200, "请正确选择使用耗材");
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return new ErrorTip(200, "请正确选择使用耗材");
        }
        treatment.setStatus(2); // 2表示康护结束,形成康护记录,不可修改
        treatment.setDepartment(null);
        treatment.setTechnician(null);
        treatment.setAppointment(null);
        treatmentService.updateById(treatment);
        for(int i=0;i<tds.size();i++){
            TreatmentDetail td = tds.get(i);
            td.setTreatmentId(treatment.getId());
            treatmentDetailService.insert(td);
        }
        for(int i=0;i<cs.size();i++){
            Consumable c = cs.get(i);
            consumableService.updateById(c);
        }
//        if (projects != null)
//            projects.forEach(p -> {
//                TreatmentDetail td = new TreatmentDetail();
//                td.setTreatmentId(treatment.getId());
//                td.setProjectId(p);
//                treatmentDetailService.insert(td);
//            });
        return super.SUCCESS_TIP;
    }

    /**
     * 康护记录详情
     */
    @RequestMapping(value = "/detail/{treatmentId}")
    @ResponseBody
    public Object detail(@PathVariable("treatmentId") Integer treatmentId) {
        return treatmentService.selectById(treatmentId);
    }
}
