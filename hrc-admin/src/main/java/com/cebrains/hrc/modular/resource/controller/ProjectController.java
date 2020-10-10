package com.cebrains.hrc.modular.resource.controller;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.modular.resource.wrapper.ProjectWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.Project;
import com.cebrains.hrc.modular.resource.service.IProjectService;

import java.util.List;

/**
 * 项目管理控制器
 *
 * @author frank
 * @Date 2018-03-06 18:34:15
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    private String PREFIX = "/resource/project/";

    @Autowired
    private IProjectService projectService;

    /**
     * 跳转到项目管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "project.html";
    }

    /**
     * 跳转到添加项目管理
     */
    @RequestMapping("/project_add")
    public String projectAdd(Model model) {
        model.addAttribute("projectCategories",ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_PROJC));
        return PREFIX + "project_add.html";
    }

    /**
     * 跳转到修改项目管理
     */
    @RequestMapping("/project_update/{projectId}")
    public String projectUpdate(@PathVariable Integer projectId, Model model) {
        Project project = projectService.selectById(projectId);
        model.addAttribute("item",project);
        model.addAttribute("categoryName", ConstantFactory.me().getProjectCategory(project.getCategory()));
        model.addAttribute("projectCategories",ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_PROJC));
        LogObjectHolder.me().set(project);
        return PREFIX + "project_edit.html";
    }

    /**
     * 获取项目管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Project> projects = projectService.selectList(null);
        return new ProjectWrapper(projects).wrap();
    }

    /**
     * 新增项目管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Project project) {
        projectService.insert(project);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除项目管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer projectId) {
        projectService.deleteById(projectId);
        return SUCCESS_TIP;
    }

    /**
     * 修改项目管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Project project) {
        projectService.updateById(project);
        return super.SUCCESS_TIP;
    }

    /**
     * 项目管理详情
     */
    @RequestMapping(value = "/detail/{projectId}")
    @ResponseBody
    public Object detail(@PathVariable("projectId") Integer projectId) {
        return projectService.selectById(projectId);
    }
}
