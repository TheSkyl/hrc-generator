package com.cebrains.hrc.modular.healthProgram.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.persistence.model.HealthProgramCategory;
import com.cebrains.hrc.common.persistence.model.User;
import com.cebrains.hrc.common.persistence.vo.KeyAndValueVo;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.modular.healthProgram.service.IHealthProgramCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.common.persistence.model.HealthProgram;
import com.cebrains.hrc.modular.healthProgram.service.IHealthProgramService;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康方案管理控制器
 *
 * @author frank
 * @Date 2018-08-26 23:09:06
 */
@Controller
@RequestMapping("/healthProgram")
public class HealthProgramController extends BaseController {

    private String PREFIX = "/healthProgram/healthProgram/";

    @Autowired
    private IHealthProgramService healthProgramService;
    @Autowired
    private IHealthProgramCategoryService healthProgramCategoryService;

    /**
     * 跳转到健康方案管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "healthProgram.html";
    }

    /**
     * 跳转到添加健康方案管理
     */
    @RequestMapping("/healthProgram_add")
    public String healthProgramAdd(Model model) {
        List<HealthProgramCategory> categories = healthProgramCategoryService.selectList(null);
        model.addAttribute("categories",categories);
        return PREFIX + "healthProgram_add.html";
    }

    /**
     * 跳转到修改健康方案管理
     */
    @RequestMapping("/healthProgram_update/{healthProgramId}")
    public String healthProgramUpdate(@PathVariable Integer healthProgramId, Model model) {
        HealthProgram healthProgram = healthProgramService.selectById(healthProgramId);
        model.addAttribute("item",healthProgram);
        List<HealthProgramCategory> categories = healthProgramCategoryService.selectList(null);
        model.addAttribute("categories",categories);
        LogObjectHolder.me().set(healthProgram);
        return PREFIX + "healthProgram_edit.html";
    }

    @PostMapping("/programByCategory")
    @ResponseBody
    public Object programByCategory(@RequestParam Integer category) {
        List<KeyAndValueVo> result = new ArrayList<>();
        Wrapper<HealthProgram> healthProgramsWrapper = new EntityWrapper<>();
        healthProgramsWrapper = healthProgramsWrapper.eq("category", category);
        List<HealthProgram> users = healthProgramService.selectList(healthProgramsWrapper);
        if(users!=null)
            users.forEach(u -> result.add(new KeyAndValueVo(u.getSymptom(),String.valueOf(u.getProgram()))));
        return result;
    }

    /**
     * 获取健康方案管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return healthProgramService.selectList(null);
    }

    /**
     * 新增健康方案管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HealthProgram healthProgram) {
        healthProgramService.insert(healthProgram);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除健康方案管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer healthProgramId) {
        healthProgramService.deleteById(healthProgramId);
        return SUCCESS_TIP;
    }

    /**
     * 修改健康方案管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HealthProgram healthProgram) {
        healthProgramService.updateById(healthProgram);
        return super.SUCCESS_TIP;
    }

    /**
     * 健康方案管理详情
     */
    @RequestMapping(value = "/detail/{healthProgramId}")
    @ResponseBody
    public Object detail(@PathVariable("healthProgramId") Integer healthProgramId) {
        return healthProgramService.selectById(healthProgramId);
    }

    /**
     * 健康方案
     */
    @RequestMapping("/sol")
    public String solution() {
        return PREFIX + "solution.html";
    }
}
