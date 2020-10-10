package com.cebrains.hrc.modular.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.Appointment;
import com.cebrains.hrc.common.persistence.model.User;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.resource.wrapper.DeviceWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.Device;
import com.cebrains.hrc.modular.goods.service.IDeviceService;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理控制器
 *
 * @author frank
 * @Date 2018-05-10 14:12:10
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {

    private String PREFIX = "/goods/device/";

    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 跳转到设备管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "device.html";
    }

    /**
     * 跳转到添加设备管理
     */
    @RequestMapping("/device_add")
    public String deviceAdd(Model model) {
        model.addAttribute("deviceStatusDict", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_DEVST));
        fillIfCanMaintainForOtherDept(model);
        return PREFIX + "device_add.html";
    }

    /**
     * 跳转到修改设备管理
     */
    @RequestMapping("/device_update/{deviceId}")
    public String deviceUpdate(@PathVariable Integer deviceId, Model model) {
        model.addAttribute("deviceStatusDict", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_DEVST));
        Device device = deviceService.selectById(deviceId);
        device.setDepartmentName(ConstantFactory.me().getDeptName(device.getDepartment()));
        Wrapper<User> userWrapper = new EntityWrapper<>();
        userWrapper = userWrapper.eq("deptid", device.getDepartment());
        List<User> users = userMapper.selectList(userWrapper);
        model.addAttribute("users",users);
        model.addAttribute("item",device);
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_DEVICE_EDIT_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            String departmentName = shiroUser.getDeptName();
            model.addAttribute("departmentId",departmentId);
            model.addAttribute("departmentName",departmentName);
        }else{
            model.addAttribute("departmentId",null);
            model.addAttribute("departmentName",null);
        }
        LogObjectHolder.me().set(device);
        return PREFIX + "device_edit.html";
    }

    /**
     * 获取设备管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Wrapper<Device> wrapper = null;
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_DEVICE_LIST_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("department", departmentId);
        }
        List<Device> devices = deviceService.selectList(wrapper);
        return new DeviceWrapper(devices).wrap();
    }

    /**
     * 新增设备管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Device device) {
        deviceService.insert(device);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除设备管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer deviceId) {
        deviceService.deleteById(deviceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改设备管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Device device) {
        deviceService.updateById(device);
        return super.SUCCESS_TIP;
    }

    /**
     * 设备管理详情
     */
    @RequestMapping(value = "/detail/{deviceId}")
    @ResponseBody
    public Object detail(@PathVariable("deviceId") Integer deviceId) {
        return deviceService.selectById(deviceId);
    }

    /**
     * 对是否有权限为其他门店设备进行修改
     * @param model
     */
    private void fillIfCanMaintainForOtherDept(Model model) {
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_DEVICE_EDIT_OTHER_DEPT)){
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
