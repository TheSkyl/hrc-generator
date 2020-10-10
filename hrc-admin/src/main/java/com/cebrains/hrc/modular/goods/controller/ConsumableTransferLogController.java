package com.cebrains.hrc.modular.goods.controller;

import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.modular.resource.wrapper.ConsumableTransferLogWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.ConsumableTransferLog;
import com.cebrains.hrc.modular.goods.service.IConsumableTransferLogService;

import java.util.List;

/**
 * 耗材调库记录控制器
 *
 * @author frank
 * @Date 2018-10-30 11:26:55
 */
@Controller
@RequestMapping("/consumableTransferLog")
public class ConsumableTransferLogController extends BaseController {

    private String PREFIX = "/goods/consumableTransferLog/";

    @Autowired
    private IConsumableTransferLogService consumableTransferLogService;

    /**
     * 跳转到耗材调库记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "consumableTransferLog.html";
    }

    /**
     * 跳转到添加耗材调库记录
     */
    @RequestMapping("/consumableTransferLog_add")
    public String consumableTransferLogAdd() {
        return PREFIX + "consumableTransferLog_add.html";
    }

    /**
     * 跳转到修改耗材调库记录
     */
    @RequestMapping("/consumableTransferLog_update/{consumableTransferLogId}")
    public String consumableTransferLogUpdate(@PathVariable Integer consumableTransferLogId, Model model) {
        ConsumableTransferLog consumableTransferLog = consumableTransferLogService.selectById(consumableTransferLogId);
        model.addAttribute("item",consumableTransferLog);
        LogObjectHolder.me().set(consumableTransferLog);
        return PREFIX + "consumableTransferLog_edit.html";
    }

    /**
     * 获取耗材调库记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<ConsumableTransferLog> consumableTransferLogs = consumableTransferLogService.selectList(null);
        return new ConsumableTransferLogWrapper(consumableTransferLogs).wrap();
    }

    /**
     * 新增耗材调库记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ConsumableTransferLog consumableTransferLog) {
        consumableTransferLogService.insert(consumableTransferLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除耗材调库记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer consumableTransferLogId) {
        consumableTransferLogService.deleteById(consumableTransferLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改耗材调库记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ConsumableTransferLog consumableTransferLog) {
        consumableTransferLogService.updateById(consumableTransferLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 耗材调库记录详情
     */
    @RequestMapping(value = "/detail/{consumableTransferLogId}")
    @ResponseBody
    public Object detail(@PathVariable("consumableTransferLogId") Integer consumableTransferLogId) {
        return consumableTransferLogService.selectById(consumableTransferLogId);
    }
}
