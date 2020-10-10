package com.cebrains.hrc.modular.healthProgram.controller;

import com.cebrains.hrc.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.HealthProgramCategory;
import com.cebrains.hrc.modular.healthProgram.service.IHealthProgramCategoryService;

/**
 * 健康方案分类控制器
 *
 * @author frank
 * @Date 2018-08-26 21:14:12
 */
@Controller
@RequestMapping("/healthProgramCategory")
public class HealthProgramCategoryController extends BaseController {

    private String PREFIX = "/healthProgram/healthProgramCategory/";

    @Autowired
    private IHealthProgramCategoryService healthProgramCategoryService;

    /**
     * 跳转到健康方案分类首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "healthProgramCategory.html";
    }

    /**
     * 跳转到添加健康方案分类
     */
    @RequestMapping("/healthProgramCategory_add")
    public String healthProgramCategoryAdd() {
        return PREFIX + "healthProgramCategory_add.html";
    }

    /**
     * 跳转到修改健康方案分类
     */
    @RequestMapping("/healthProgramCategory_update/{healthProgramCategoryId}")
    public String healthProgramCategoryUpdate(@PathVariable Integer healthProgramCategoryId, Model model) {
        HealthProgramCategory healthProgramCategory = healthProgramCategoryService.selectById(healthProgramCategoryId);
        model.addAttribute("item",healthProgramCategory);
        LogObjectHolder.me().set(healthProgramCategory);
        return PREFIX + "healthProgramCategory_edit.html";
    }

    /**
     * 获取健康方案分类列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return healthProgramCategoryService.selectList(null);
    }

    /**
     * 新增健康方案分类
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HealthProgramCategory healthProgramCategory) {
        healthProgramCategoryService.insert(healthProgramCategory);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除健康方案分类
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer healthProgramCategoryId) {
        healthProgramCategoryService.deleteById(healthProgramCategoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改健康方案分类
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HealthProgramCategory healthProgramCategory) {
        healthProgramCategoryService.updateById(healthProgramCategory);
        return super.SUCCESS_TIP;
    }

    /**
     * 健康方案分类详情
     */
    @RequestMapping(value = "/detail/{healthProgramCategoryId}")
    @ResponseBody
    public Object detail(@PathVariable("healthProgramCategoryId") Integer healthProgramCategoryId) {
        return healthProgramCategoryService.selectById(healthProgramCategoryId);
    }
}
