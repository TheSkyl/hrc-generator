package com.cebrains.hrc.modular.member.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.DeptMapper;
import com.cebrains.hrc.common.persistence.dao.MemberSettlementMapper;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.*;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.goods.service.IConsumableService;
import com.cebrains.hrc.modular.member.service.IMemberSettlementService;
import com.cebrains.hrc.modular.system.service.IMembershipCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.modular.member.service.IProductSettlementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品结算控制器
 *
 * @author frank
 * @Date 2018-09-21 23:22:37
 */
@Controller
@RequestMapping("/productSettlement")
public class ProductSettlementController extends BaseController {

    private String PREFIX = "/member/productSettlement/";

    @Autowired
    private IProductSettlementService productSettlementService;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IMembershipCardService membershipCardService;
    @Autowired
    private IConsumableService consumableService;

    /**
     * 跳转到产品结算首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "productSettlement.html";
    }

    /**
     * 跳转到添加产品结算
     */
    @RequestMapping("/productSettlement_add")
    public String productSettlementAdd(Model model) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer departmentId = shiroUser.getDeptId();
        Dept dept = deptMapper.selectById(departmentId);
        model.addAttribute("createdBy",shiroUser.getId());
        if (dept != null && dept.getIsStore()!= null && dept.getIsStore() == 1) {
            model.addAttribute("departmentId", departmentId);
            model.addAttribute("departmentName", dept.getSimplename());
        }
        model.addAttribute("paymentMethodDict", ConstantFactory.me().paymentMethodNameWithoutExp());
        return PREFIX + "productSettlement_add.html";
    }

    /**
     * 跳转到修改产品结算
     */
    @RequestMapping("/productSettlement_update/{productSettlementId}")
    public String productSettlementUpdate(@PathVariable Integer productSettlementId, Model model) {
        ProductSettlement productSettlement = productSettlementService.selectById(productSettlementId);
        model.addAttribute("item",productSettlement);
        LogObjectHolder.me().set(productSettlement);
        return PREFIX + "productSettlement_edit.html";
    }

    /**
     * 获取产品结算列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return productSettlementService.selectList(null);
    }

    /**
     * 新增产品结算
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ProductSettlement productSettlement) {
        productSettlementService.insert(productSettlement);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除产品结算
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer productSettlementId) {
        productSettlementService.deleteById(productSettlementId);
        return SUCCESS_TIP;
    }

    /**
     * 修改产品结算
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ProductSettlement productSettlement) {
        productSettlementService.updateById(productSettlement);
        return super.SUCCESS_TIP;
    }

    /**
     * 产品结算详情
     */
    @RequestMapping(value = "/detail/{productSettlementId}")
    @ResponseBody
    public Object detail(@PathVariable("productSettlementId") Integer productSettlementId) {
        return productSettlementService.selectById(productSettlementId);
    }


    /**
     * 计算价格
     */
    @PostMapping(value = "/calcPrice")
    @ResponseBody
    public Object calcPrice(@RequestParam Integer member, @RequestParam Integer paymentMethod, @RequestParam(required = false) Integer membershipCard,@RequestParam("cs") String  cs) {
        Map<String, Object> result = new HashMap<>();
        double price = 0;
        try{
        JSONArray ja = JSONArray.parseArray(cs);
        if (ja != null && ja.size() > 0)
            for (int i = 0; i < ja.size(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                price +=consumableService.selectById(jo.getInteger("id")).getPrice()*jo.getInteger("amount");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("status", 0);
            result.put("message", "请正确填写结算产品信息！");
            return result;
        }
        switch (paymentMethod) {
            case 1: // 会员卡
                if(membershipCard==null||membershipCard<=0){
                    result.put("status", 0);
                    result.put("message", "所选会员卡不正确");
                    return result;
                }
                MembershipCard mc = membershipCardService.selectById(membershipCard);
                if(mc==null){
                    result.put("status", 0);
                    result.put("message", "找不到会员卡信息");
                    return result;
                }

                Double balance = mc.getBalance();
                if (balance ==null || balance < price) {
                    result.put("message","温馨提示: 会员卡余额不足!");
                }
                result.put("status", 1);
                result.put("price", price);
                break;
            default:    // 其他
                result.put("status", 1);
                result.put("price", price);
                break;
        }

        // 计算价格

        return result;
    }

}
