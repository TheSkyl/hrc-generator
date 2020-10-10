package com.cebrains.hrc.modular.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.Consumable;
import com.cebrains.hrc.common.persistence.model.ConsumableTransferLog;
import com.cebrains.hrc.common.persistence.model.User;
import com.cebrains.hrc.common.persistence.vo.ConsumableSuggestVo;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.goods.service.IConsumableService;
import com.cebrains.hrc.modular.goods.service.IConsumableTransferLogService;
import com.cebrains.hrc.modular.resource.wrapper.ConsumableWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 耗材管理控制器
 *
 * @author frank
 * @Date 2018-05-10 16:16:47
 */
@Controller
@RequestMapping("/consumable")
public class ConsumableController extends BaseController {

    private String PREFIX = "/goods/consumable/";

    @Autowired
    private IConsumableService consumableService;
    @Autowired
    private IConsumableTransferLogService consumableTransferLogService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 跳转到耗材管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "consumable.html";
    }

    /**
     * 跳转到添加耗材管理
     */
    @RequestMapping("/consumable_add")
    public String consumableAdd(Model model) {
        fillIfCanMaintainForOtherDept(model);
        return PREFIX + "consumable_add.html";
    }

    /**
     * 跳转到修改耗材管理
     */
    @RequestMapping("/consumable_update/{consumableId}")
    public String consumableUpdate(@PathVariable Integer consumableId, Model model) {
        Consumable consumable = consumableService.selectById(consumableId);
        consumable.setDepartmentName(ConstantFactory.me().getDeptName(consumable.getDepartment()));
        model.addAttribute("item",consumable);
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_CONSUMABLE_EDIT_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            String departmentName = shiroUser.getDeptName();
            model.addAttribute("departmentId",departmentId);
            model.addAttribute("departmentName",departmentName);
        }else{
            model.addAttribute("departmentId",null);
            model.addAttribute("departmentName",null);
        }
        LogObjectHolder.me().set(consumable);
        return PREFIX + "consumable_edit.html";
    }

    /**
     * 跳转到耗材调库管理界面
     */
    @RequestMapping("/consumable_transfer/{consumableId}")
    public String consumableTransfer(@PathVariable Integer consumableId, Model model) {
        Consumable consumable = consumableService.selectById(consumableId);
        consumable.setDepartmentName(ConstantFactory.me().getDeptName(consumable.getDepartment()));
        model.addAttribute("item",consumable);
        Integer srcDepartment = consumable.getDepartment();
        model.addAttribute("srcDepartmentId", srcDepartment);
        model.addAttribute("srcDepartmentName", ConstantFactory.me().getDeptName(srcDepartment));
        LogObjectHolder.me().set(consumable);
        return PREFIX + "consumable_transfer.html";
    }

    /**
     * 跳转到耗材调库管理界面
     */
    @RequestMapping("/consumable_check/{consumableId}")
    public String consumableCheck(@PathVariable Integer consumableId, Model model) {
        Consumable consumable = consumableService.selectById(consumableId);
        consumable.setDepartmentName(ConstantFactory.me().getDeptName(consumable.getDepartment()));
        model.addAttribute("item",consumable);
        LogObjectHolder.me().set(consumable);
        return PREFIX + "consumable_check.html";
    }

    /**
     * 获取耗材管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Wrapper<Consumable> wrapper = null;
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_CONSUMABLE_LIST_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("department", departmentId);
        }
        List<Consumable> consumables = consumableService.selectList(wrapper);
        return new ConsumableWrapper(consumables).wrap();
    }



    /**
     * 获取耗材建议列表
     */
    @RequestMapping(value = "/suggestList/{d}/{k}")
    @ResponseBody
    public Object suggestList(@PathVariable Integer d,@PathVariable String k) {
        List<ConsumableSuggestVo> result = new ArrayList<>();
        Wrapper<Consumable> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("department",d).like("name", "%"+k+"%");
        List<Consumable> consumables = consumableService.selectList(wrapper);
        if(consumables!=null){
            consumables.forEach(m -> result.add(new ConsumableSuggestVo(m.getId(),m.getName(),m.getAmount())));
        }
        return result;
    }



    /**
     * 获取耗材库存可用数量
     */
    @RequestMapping(value = "/available")
    @ResponseBody
    public Object suggestList(@RequestParam("c") Integer c) {
        Consumable consumable = consumableService.selectById(c);
        return new ConsumableSuggestVo(consumable.getId(),consumable.getName(),consumable.getAmount());
    }

    /**
     * 新增耗材管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Consumable consumable) {
        consumableService.insert(consumable);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除耗材管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer consumableId) {
        consumableService.deleteById(consumableId);
        return SUCCESS_TIP;
    }

    /**
     * 修改耗材管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Consumable consumable) {
        consumableService.updateById(consumable);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改耗材管理
     */
    @RequestMapping(value = "/transfer")
    @ResponseBody
    public Object transfer(Integer srcDepartment, Integer destDepartment,Integer amount,Integer id) {
        if(srcDepartment.equals(destDepartment)){
            return new ErrorTip(400,"提供门店与接收门店不能相同");
        }
        Consumable sc = consumableService.selectById(id);
        if(sc.getAmount()<amount) {
            return new ErrorTip(400, "库存不足");
        }

        List<ConsumableTransferLog> ctls = consumableTransferLogService.selectList(new EntityWrapper<ConsumableTransferLog>().eq("src_department",sc.getDepartment()).and().eq("src_id",sc.getId()).and().eq("dest_department",destDepartment));
//        List<Consumable> dcs = consumableService.selectList(new EntityWrapper<Consumable>().eq("department",destDepartment).and().eq("name",sc.getName()));
        Consumable dc = null;
        if(ctls!=null && ctls.size()>0){
            ConsumableTransferLog ctl = ctls.get(0);
            dc = consumableService.selectById(ctl.getDestId());
            dc.setAmount(dc.getAmount()+amount);
            consumableService.updateById(dc);
        }else{
            dc = new Consumable();
            dc.setName(sc.getName());
            dc.setAmount(amount);
            dc.setDepartment(destDepartment);
            dc.setManufacturer(sc.getManufacturer());
            dc.setNumber(sc.getNumber());
            dc.setPrice(sc.getPrice());
            dc.setMeasureUnit(sc.getMeasureUnit());
            dc.setSpecification(sc.getSpecification());
            consumableService.insert(dc);
        }

        sc.setAmount(sc.getAmount()-amount);
        consumableService.updateById(sc);
        ConsumableTransferLog nctl = new ConsumableTransferLog();
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        nctl.setCreatedBy(shiroUser.getId());
        nctl.setSrcId(id);
        nctl.setDestId(dc.getId());
        nctl.setSrcDepartment(srcDepartment);
        nctl.setDestDepartment(destDepartment);
        nctl.setAmount(amount);
        consumableTransferLogService.insert(nctl);

        return super.SUCCESS_TIP;
    }

    /**
     * 耗材管理详情
     */
    @RequestMapping(value = "/detail/{consumableId}")
    @ResponseBody
    public Object detail(@PathVariable("consumableId") Integer consumableId) {
        return consumableService.selectById(consumableId);
    }


    /**
     * 对是否有权限为其他门店设备进行修改
     * @param model
     */
    private void fillIfCanMaintainForOtherDept(Model model) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_CONSUMABLE_EDIT_OTHER_DEPT)){
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
