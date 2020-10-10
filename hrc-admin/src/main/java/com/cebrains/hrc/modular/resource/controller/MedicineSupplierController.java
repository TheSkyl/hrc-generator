package com.cebrains.hrc.modular.resource.controller;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.modular.resource.wrapper.MedicineSupplierWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.common.persistence.model.MedicineSupplier;
import com.cebrains.hrc.modular.resource.service.IMedicineSupplierService;

import java.util.List;

/**
 * 产品供应商控制器
 *
 * @author frank
 * @Date 2018-03-07 10:23:11
 */
@Controller
@RequestMapping("/medicineSupplier")
public class MedicineSupplierController extends BaseController {

    private String PREFIX = "/resource/medicineSupplier/";

    @Autowired
    private IMedicineSupplierService medicineSupplierService;

    /**
     * 跳转到产品供应商首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "medicineSupplier.html";
    }

    /**
     * 跳转到添加产品供应商
     */
    @RequestMapping("/medicineSupplier_add")
    public String medicineSupplierAdd(Model model) {
        model.addAttribute("medicineSupplierCategories", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_MSC));
        return PREFIX + "medicineSupplier_add.html";
    }

    /**
     * 跳转到修改产品供应商
     */
    @RequestMapping("/medicineSupplier_update/{medicineSupplierId}")
    public String medicineSupplierUpdate(@PathVariable Integer medicineSupplierId, Model model) {
        MedicineSupplier medicineSupplier = medicineSupplierService.selectById(medicineSupplierId);
        model.addAttribute("item",medicineSupplier);
        model.addAttribute("medicineSupplierCategories", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_MSC));
        LogObjectHolder.me().set(medicineSupplier);
        return PREFIX + "medicineSupplier_edit.html";
    }

    /**
     * 获取产品供应商列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<MedicineSupplier> medicineSuppliers = medicineSupplierService.selectList(null);
        return new MedicineSupplierWrapper(medicineSuppliers).wrap();
    }

    /**
     * 新增产品供应商
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MedicineSupplier medicineSupplier) {
        medicineSupplierService.insert(medicineSupplier);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除产品供应商
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer medicineSupplierId) {
        medicineSupplierService.deleteById(medicineSupplierId);
        return SUCCESS_TIP;
    }

    /**
     * 修改产品供应商
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MedicineSupplier medicineSupplier) {
        medicineSupplierService.updateById(medicineSupplier);
        return super.SUCCESS_TIP;
    }

    /**
     * 产品供应商详情
     */
    @RequestMapping(value = "/detail/{medicineSupplierId}")
    @ResponseBody
    public Object detail(@PathVariable("medicineSupplierId") Integer medicineSupplierId) {
        return medicineSupplierService.selectById(medicineSupplierId);
    }
}
