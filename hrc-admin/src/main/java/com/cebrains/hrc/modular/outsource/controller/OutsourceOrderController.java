package com.cebrains.hrc.modular.outsource.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.model.*;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.outsource.service.IOutsourceOrderDeliveryService;
import com.cebrains.hrc.modular.outsource.service.IOutsourceOrderSettlementService;
import com.cebrains.hrc.modular.resource.service.IMedicineSupplierService;
import com.cebrains.hrc.modular.resource.wrapper.OutsourceOrderWrapper;
import com.cebrains.hrc.modular.resource.wrapper.SpecialApplicationWrapper;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.modular.outsource.service.IOutsourceOrderService;

import java.util.List;

/**
 * 外协订单控制器
 *
 * @author frank
 * @Date 2018-07-22 22:16:52
 */
@Controller
@RequestMapping("/outsourceOrder")
public class OutsourceOrderController extends BaseController {

    private String PREFIX = "/outsource/outsourceOrder/";

    @Autowired
    private IOutsourceOrderService outsourceOrderService;
    @Autowired
    private IOutsourceOrderDeliveryService orderDeliveryService;
    @Autowired
    private IOutsourceOrderSettlementService orderSettlementService;
    @Autowired
    private IMedicineSupplierService medicineSupplierService;

    /**
     * 跳转到外协订单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "outsourceOrder.html";
    }

    /**
     * 跳转到添加外协订单
     */
    @RequestMapping("/outsourceOrder_add")
    public String outsourceOrderAdd(Model model) {
        List<MedicineSupplier> suppliers = medicineSupplierService.selectList(null);
        model.addAttribute("suppliers",suppliers);
        return PREFIX + "outsourceOrder_add.html";
    }

    /**
     * 跳转到修改外协订单
     */
    @RequestMapping("/outsourceOrder_update/{outsourceOrderId}")
    public String outsourceOrderUpdate(@PathVariable Integer outsourceOrderId, Model model) {
        OutsourceOrder outsourceOrder = outsourceOrderService.selectById(outsourceOrderId);
        model.addAttribute("item",outsourceOrder);
        LogObjectHolder.me().set(outsourceOrder);
        return PREFIX + "outsourceOrder_edit.html";
    }

    /**
     * 跳转到修改外协订单
     */
    @RequestMapping("/outsourceOrder_detail/{outsourceOrderId}")
    public String outsourceOrderDetail(@PathVariable Integer outsourceOrderId, Model model) {
        OutsourceOrder outsourceOrder = outsourceOrderService.selectById(outsourceOrderId);
        model.addAttribute("item",outsourceOrder);
        LogObjectHolder.me().set(outsourceOrder);
        return PREFIX + "outsourceOrder_detail.html";
    }

    /**
     * 跳转到修改外协订单 - 发货
     */
    @RequestMapping("/outsourceOrder_deliver/{outsourceOrderId}")
    public String outsourceOrderDeliver(@PathVariable Integer outsourceOrderId, Model model) {
        OutsourceOrder outsourceOrder = outsourceOrderService.selectById(outsourceOrderId);
        outsourceOrder.setSupplierName(ConstantFactory.me().getMedicineSupplier(outsourceOrder.getSupplier()));
        model.addAttribute("item",outsourceOrder);
        LogObjectHolder.me().set(outsourceOrder);
        switch (outsourceOrder.getStatus()){
            case 1:  // 先接单
                return PREFIX + "outsourceOrder_detail_1.html";
            case 2: // 发货
                List<OutsourceOrderDelivery> osorders = orderDeliveryService.selectList(new EntityWrapper<OutsourceOrderDelivery>().eq("outsource_order", outsourceOrderId));
                if(osorders!=null && osorders.size()>0){
                    model.addAttribute("osdId",osorders.get(0).getId());
                }
                return PREFIX + "outsourceOrder_deliver.html";

        }
        return PREFIX + "outsourceOrder_detail_1.html";
    }

    /**
     * 跳转到修改外协订单 - 发货
     */
    @RequestMapping("/outsourceOrder_settle/{outsourceOrderId}")
    public String outsourceOrderSettle(@PathVariable Integer outsourceOrderId, Model model) {
        OutsourceOrder outsourceOrder = outsourceOrderService.selectById(outsourceOrderId);
        outsourceOrder.setSupplierName(ConstantFactory.me().getMedicineSupplier(outsourceOrder.getSupplier()));
        model.addAttribute("item",outsourceOrder);
        LogObjectHolder.me().set(outsourceOrder);
        switch (outsourceOrder.getStatus()){
            case 1:  // 新订单
            case 2: // 已抢单
                return PREFIX + "outsourceOrder_detail_1.html";
            case 3: // 已发货
                List<Dict> dictByKey = ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_PAYM);
                model.addAttribute("paymentMethodDict", dictByKey.subList(2,dictByKey.size()));
                List<OutsourceOrderDelivery> osorders = orderDeliveryService.selectList(new EntityWrapper<OutsourceOrderDelivery>().eq("outsource_order", outsourceOrderId));
                if(osorders!=null && osorders.size()>0){
                    model.addAttribute("osd",osorders.get(0));
                }
                MedicineSupplier supplier = medicineSupplierService.selectById(outsourceOrder.getSupplier());
                model.addAttribute("supplier",supplier);
                return PREFIX + "outsourceOrder_settle.html";

        }
        return PREFIX + "outsourceOrder_detail_1.html";
    }

    /**
     * 获取外协订单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<OutsourceOrder> outsourceOrders = outsourceOrderService.selectList(null);
        return new OutsourceOrderWrapper(outsourceOrders).wrap();
    }

    /**
     * 新增外协订单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OutsourceOrder outsourceOrder) {
        if(StringUtils.isEmpty(outsourceOrder.getOrderNumber())){
            String orderNumber = RandomStringUtils.randomAlphabetic(3).concat(RandomStringUtils.randomNumeric(10));
            outsourceOrder.setOrderNumber(orderNumber);
        }
        outsourceOrderService.insert(outsourceOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 外协订单 - 抢单
     */
    @RequestMapping(value = "/rushOrder")
    @ResponseBody
    public Object rushOrder(OutsourceOrder outsourceOrder) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer userId = shiroUser.getId();
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_OUTSOURCE_DELIVER)){
            return new ErrorTip(400,"您无接单权限");
        }
        if(outsourceOrder==null || outsourceOrder.getId()==null || outsourceOrder.getId()<=0){
            return new ErrorTip(400,"订单不存在");
        }
        OutsourceOrder oso = outsourceOrderService.selectById(outsourceOrder.getId());
        if(oso==null){
            return new ErrorTip(400,"订单不存在");
        }
        int rushed = orderDeliveryService.selectCount(new EntityWrapper<OutsourceOrderDelivery>().eq("outsource_order", outsourceOrder.getId()));
        if(rushed>0){
            return new ErrorTip(400,"已被接单");
        }
        OutsourceOrderDelivery outsourceOrderDelivery = new OutsourceOrderDelivery();
        outsourceOrderDelivery.setOutsourceOrder(outsourceOrder.getId());
        outsourceOrderDelivery.setCreatedBy(userId);
        orderDeliveryService.insert(outsourceOrderDelivery);

        outsourceOrder.setStatus(2);    // 已接单
        outsourceOrderService.updateById(outsourceOrder);

        return super.SUCCESS_TIP;
    }

    /**
     * 删除外协订单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer outsourceOrderId) {
        outsourceOrderService.deleteById(outsourceOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改外协订单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OutsourceOrder outsourceOrder) {
        outsourceOrderService.updateById(outsourceOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改外协订单
     */
    @RequestMapping(value = "/deliver")
    @ResponseBody
    public Object deliver(OutsourceOrderDelivery outsourceOrderDelivery) {
        if(outsourceOrderDelivery==null || outsourceOrderDelivery.getId()==null || outsourceOrderDelivery.getId()<=0){
            return new ErrorTip(400,"请先接单");
        }
        orderDeliveryService.updateById(outsourceOrderDelivery);
        OutsourceOrder outsourceOrder = new OutsourceOrder();
        outsourceOrder.setId(outsourceOrderDelivery.getOutsourceOrder());
        outsourceOrder.setStatus(3);    // 已发货
        outsourceOrderService.updateById(outsourceOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改外协订单
     */
    @RequestMapping(value = "/settle")
    @ResponseBody
    public Object settle(OutsourceOrderSettlement outsourceOrderSettlement) {
//        if(outsourceOrderSettlement==null || outsourceOrderSettlement.getId()==null || outsourceOrderSettlement.getId()<=0){
//            return new ErrorTip(400,"请先发货");
//        }
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer userId = shiroUser.getId();
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_OUTSOURCE_SETTLE)){
            return new ErrorTip(400,"您无结算权限");
        }
        orderSettlementService.insert(outsourceOrderSettlement);
        OutsourceOrder outsourceOrder = new OutsourceOrder();
        outsourceOrder.setId(outsourceOrderSettlement.getOutsourceOrder());
        outsourceOrder.setStatus(5);    // 已发货
        outsourceOrder.setCreatedBy(userId);
        outsourceOrderService.updateById(outsourceOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 外协订单详情
     */
    @RequestMapping(value = "/detail/{outsourceOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("outsourceOrderId") Integer outsourceOrderId) {
        return outsourceOrderService.selectById(outsourceOrderId);
    }
}
