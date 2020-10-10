package com.cebrains.hrc.modular.outsource.controller;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.model.Dict;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.modular.resource.wrapper.SpecialApplicationSettlementWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.common.persistence.model.SpecialApplicationSettlement;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationSettlementService;

import java.util.List;
import java.util.Map;

/**
 * 特殊申请结算控制器
 *
 * @author frank
 * @Date 2018-07-18 17:10:05
 */
@Controller
@RequestMapping("/specialApplicationSettlement")
public class SpecialApplicationSettlementController extends BaseController {

    private String PREFIX = "/outsource/specialApplicationSettlement/";

    @Autowired
    private ISpecialApplicationSettlementService specialApplicationSettlementService;

    /**
     * 跳转到特殊申请结算首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "specialApplicationSettlement.html";
    }

    /**
     * 跳转到添加特殊申请结算
     */
    @RequestMapping("/specialApplicationSettlement_add")
    public String specialApplicationSettlementAdd(Model model) {
        List<Dict> dictByKey = ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_PAYM);
        model.addAttribute("paymentMethodDict", dictByKey.subList(2,dictByKey.size()));
        return PREFIX + "specialApplicationSettlement_add.html";
    }

    /**
     * 跳转到修改特殊申请结算
     */
    @RequestMapping("/specialApplicationSettlement_update/{specialApplicationSettlementId}")
    public String specialApplicationSettlementUpdate(@PathVariable Integer specialApplicationSettlementId, Model model) {
        SpecialApplicationSettlement specialApplicationSettlement = specialApplicationSettlementService.selectById(specialApplicationSettlementId);
        specialApplicationSettlement.setSpecialApplicationNumber(ConstantFactory.me().getNumberBySpecialApplication(specialApplicationSettlement.getSpecialApplication()));
        specialApplicationSettlement.setPayTypeName(ConstantFactory.me().getPaymentMethodName(specialApplicationSettlement.getPayType()));
        model.addAttribute("item",specialApplicationSettlement);
        LogObjectHolder.me().set(specialApplicationSettlement);
        return PREFIX + "specialApplicationSettlement_edit.html";
    }

    /**
     * 获取特殊申请结算列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String specialApplicationNumber) {
        List<SpecialApplicationSettlement> specialApplicationSettlements = null;
        if(StringUtils.isEmpty(specialApplicationNumber)) {
            specialApplicationSettlements = specialApplicationSettlementService.selectList(null);
        }else{
            specialApplicationSettlements = specialApplicationSettlementService.selectListByNumber(specialApplicationNumber, null);
        }
        return new SpecialApplicationSettlementWrapper(specialApplicationSettlements).wrap();
    }



    /**
     * 获取申请执行列表
     */
    @PostMapping(value = "/paymentInfo")
    @ResponseBody
    public Object paymentInfo(String said) {
        Map<String, Object> pinfo = specialApplicationSettlementService.paymentInfo(said);
        pinfo.put("amount",Double.valueOf(pinfo.get("cost").toString())-Double.parseDouble(pinfo.get("payed").toString()));
        return pinfo;
    }

    /**
     * 新增特殊申请结算
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SpecialApplicationSettlement specialApplicationSettlement) {
        specialApplicationSettlementService.insert(specialApplicationSettlement);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除特殊申请结算
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer specialApplicationSettlementId) {
        specialApplicationSettlementService.deleteById(specialApplicationSettlementId);
        return SUCCESS_TIP;
    }

    /**
     * 修改特殊申请结算
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SpecialApplicationSettlement specialApplicationSettlement) {
        specialApplicationSettlementService.updateById(specialApplicationSettlement);
        return super.SUCCESS_TIP;
    }

    /**
     * 特殊申请结算详情
     */
    @RequestMapping(value = "/detail/{specialApplicationSettlementId}")
    @ResponseBody
    public Object detail(@PathVariable("specialApplicationSettlementId") Integer specialApplicationSettlementId) {
        return specialApplicationSettlementService.selectById(specialApplicationSettlementId);
    }
}
