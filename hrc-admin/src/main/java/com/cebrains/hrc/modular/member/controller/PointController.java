package com.cebrains.hrc.modular.member.controller;

import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.modular.resource.wrapper.PointWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.Point;
import com.cebrains.hrc.modular.member.service.IPointService;

import java.util.List;

/**
 * 积分控制器
 *
 * @author frank
 * @Date 2018-03-08 14:53:20
 */
@Controller
@RequestMapping("/point")
public class PointController extends BaseController {

    private String PREFIX = "/member/point/";

    @Autowired
    private IPointService pointService;

    /**
     * 跳转到积分首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "point.html";
    }

    /**
     * 跳转到添加积分
     */
    @RequestMapping("/point_add")
    public String pointAdd() {
        return PREFIX + "point_add.html";
    }

    /**
     * 跳转到修改积分
     */
    @RequestMapping("/point_update/{pointId}")
    public String pointUpdate(@PathVariable Integer pointId, Model model) {
        Point point = pointService.selectById(pointId);
        model.addAttribute("item",point);
        LogObjectHolder.me().set(point);
        return PREFIX + "point_edit.html";
    }

    /**
     * 获取积分列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Point> points = pointService.selectList(null);
        return new PointWrapper(points).wrap();
    }

    /**
     * 新增积分
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Point point) {
        pointService.insert(point);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除积分
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer pointId) {
        pointService.deleteById(pointId);
        return SUCCESS_TIP;
    }

    /**
     * 修改积分
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Point point) {
        pointService.updateById(point);
        return super.SUCCESS_TIP;
    }

    /**
     * 积分详情
     */
    @RequestMapping(value = "/detail/{pointId}")
    @ResponseBody
    public Object detail(@PathVariable("pointId") Integer pointId) {
        return pointService.selectById(pointId);
    }
}
