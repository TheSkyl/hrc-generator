package com.cebrains.hrc.modular.resource.controller;

import com.cebrains.hrc.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.Expert;
import com.cebrains.hrc.modular.resource.service.IExpertService;

/**
 * 专家管理控制器
 *
 * @author frank
 * @Date 2018-03-06 16:57:12
 */
@Controller
@RequestMapping("/expert")
public class ExpertController extends BaseController {

    private String PREFIX = "/resource/expert/";

    @Autowired
    private IExpertService expertService;

    /**
     * 跳转到专家管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "expert.html";
    }

    /**
     * 跳转到添加专家管理
     */
    @RequestMapping("/expert_add")
    public String expertAdd() {
        return PREFIX + "expert_add.html";
    }

    /**
     * 跳转到修改专家管理
     */
    @RequestMapping("/expert_update/{expertId}")
    public String expertUpdate(@PathVariable Integer expertId, Model model) {
        Expert expert = expertService.selectById(expertId);
        model.addAttribute("item",expert);
        LogObjectHolder.me().set(expert);
        return PREFIX + "expert_edit.html";
    }

    /**
     * 获取专家管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return expertService.selectList(null);
    }

    /**
     * 新增专家管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Expert expert) {
        expertService.insert(expert);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除专家管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer expertId) {
        expertService.deleteById(expertId);
        return SUCCESS_TIP;
    }

    /**
     * 修改专家管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Expert expert) {
        expertService.updateById(expert);
        return super.SUCCESS_TIP;
    }

    /**
     * 专家管理详情
     */
    @RequestMapping(value = "/detail/{expertId}")
    @ResponseBody
    public Object detail(@PathVariable("expertId") Integer expertId) {
        return expertService.selectById(expertId);
    }
}
