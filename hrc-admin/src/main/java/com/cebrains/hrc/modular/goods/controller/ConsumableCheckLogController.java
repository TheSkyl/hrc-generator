package com.cebrains.hrc.modular.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.model.Consumable;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.goods.service.IConsumableService;
import com.cebrains.hrc.modular.resource.wrapper.ConsumableCheckLogWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.ConsumableCheckLog;
import com.cebrains.hrc.modular.goods.service.IConsumableCheckLogService;

import java.util.List;

/**
 * 库存盘库控制器
 *
 * @author frank
 * @Date 2018-10-31 12:23:50
 */
@Controller
@RequestMapping("/consumableCheckLog")
public class ConsumableCheckLogController extends BaseController {

    private String PREFIX = "/goods/consumableCheckLog/";

    @Autowired
    private IConsumableCheckLogService consumableCheckLogService;
    @Autowired
    private IConsumableService consumableService;

    /**
     * 跳转到库存盘库首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "consumableCheckLog.html";
    }

    /**
     * 跳转到添加库存盘库
     */
    @RequestMapping("/consumableCheckLog_add")
    public String consumableCheckLogAdd() {
        return PREFIX + "consumableCheckLog_add.html";
    }

    /**
     * 跳转到修改库存盘库
     */
    @RequestMapping("/consumableCheckLog_update/{consumableCheckLogId}")
    public String consumableCheckLogUpdate(@PathVariable Integer consumableCheckLogId, Model model) {
        ConsumableCheckLog consumableCheckLog = consumableCheckLogService.selectById(consumableCheckLogId);
        model.addAttribute("item",consumableCheckLog);
        LogObjectHolder.me().set(consumableCheckLog);
        return PREFIX + "consumableCheckLog_edit.html";
    }

    /**
     * 获取库存盘库列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<ConsumableCheckLog> consumableCheckLogs = null;
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_CONSUMABLE_LIST_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            consumableCheckLogService.selectListByDepartment(departmentId);
        }else {
            consumableCheckLogs = consumableCheckLogService.selectList(null);
        }
        return new ConsumableCheckLogWrapper(consumableCheckLogs).wrap();
    }

    /**
     * 新增库存盘库
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ConsumableCheckLog consumableCheckLog) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
//        Integer departmentId = shiroUser.getDeptId();
        Integer userId = shiroUser.getId();
        Consumable c = consumableService.selectById(consumableCheckLog.getConsumable());
        c.setAmount(c.getAmount()+consumableCheckLog.getAmount());
        consumableService.updateById(c);
        consumableCheckLog.setCreatedBy(userId);
        consumableCheckLogService.insert(consumableCheckLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除库存盘库
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer consumableCheckLogId) {
        consumableCheckLogService.deleteById(consumableCheckLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改库存盘库
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ConsumableCheckLog consumableCheckLog) {
        consumableCheckLogService.updateById(consumableCheckLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 库存盘库详情
     */
    @RequestMapping(value = "/detail/{consumableCheckLogId}")
    @ResponseBody
    public Object detail(@PathVariable("consumableCheckLogId") Integer consumableCheckLogId) {
        return consumableCheckLogService.selectById(consumableCheckLogId);
    }
}
