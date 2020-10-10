package com.cebrains.hrc.modular.member.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.DeptMapper;
import com.cebrains.hrc.common.persistence.model.*;
import com.cebrains.hrc.common.persistence.vo.IdAndNameVo;
import com.cebrains.hrc.common.persistence.vo.KeyAndValueVo;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.core.shiro.ShiroKit;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.goods.service.IConsumableService;
import com.cebrains.hrc.modular.resource.service.IProjectService;
import com.cebrains.hrc.modular.resource.wrapper.MemberSettlementWrapper;
import com.cebrains.hrc.modular.station.service.ITreatmentDetailService;
import com.cebrains.hrc.modular.station.service.ITreatmentService;
import com.cebrains.hrc.modular.system.service.IDeptService;
import com.cebrains.hrc.modular.system.service.IMembershipCardService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.modular.member.service.IMemberSettlementService;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * 会员结算控制器
 *
 * @author frank
 * @Date 2018-05-15 14:19:28
 */
@Controller
@RequestMapping("/memberSettlement")
public class MemberSettlementController extends BaseController {

    private String PREFIX = "/member/memberSettlement/";

    @Autowired
    private IMemberSettlementService memberSettlementService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ITreatmentService treatmentService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IMembershipCardService membershipCardService;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private IConsumableService consumableService;
    @Autowired
    private ITreatmentDetailService treatmentDetailService;

    /**
     * 跳转到会员结算首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberSettlement.html";
    }

    /**
     * 跳转到添加会员结算
     */
    @RequestMapping("/memberSettlement_add")
    public String memberSettlementAdd(Model model,@RequestParam(value = "tid",required = false,defaultValue = "0")Integer treatmentId) {
        model.addAttribute("tid","");
        model.addAttribute("tval","");model.addAttribute("tproj","");
        model.addAttribute("ttime",null);
        if(treatmentId>0){
            Treatment treatment = treatmentService.selectById(treatmentId);
            if(treatment!=null){
                model.addAttribute("tid",treatment.getId());
                model.addAttribute("tval",ConstantFactory.me().getMemberName(treatment.getUserId()));
                model.addAttribute("tproj",ConstantFactory.me().getProjectNamesByTreatment(treatment.getId()));
                model.addAttribute("ttime",treatment.getCreateTime());
            }
        }

        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer departmentId = shiroUser.getDeptId();
        Dept dept = deptMapper.selectById(departmentId);
        model.addAttribute("createdBy",shiroUser.getId());
        if (dept != null && dept.getIsStore()!= null && dept.getIsStore() == 1) {
            model.addAttribute("departmentId", departmentId);
            model.addAttribute("departmentName", dept.getSimplename());
        }
        model.addAttribute("paymentMethodDict", ConstantFactory.me().findDictByKey(IConstantFactory.DICT_KEY_PAYM));
        return PREFIX + "memberSettlement_add.html";
    }

    /**
     * 跳转到修改会员结算
     */
    @RequestMapping("/memberSettlement_update/{memberSettlementId}")
    public String memberSettlementUpdate(@PathVariable Integer memberSettlementId, Model model) {
        MemberSettlement memberSettlement = memberSettlementService.selectById(memberSettlementId);
        model.addAttribute("item", memberSettlement);
        LogObjectHolder.me().set(memberSettlement);
        return PREFIX + "memberSettlement_edit.html";
    }

    /**
     * 获取会员结算列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<MemberSettlement> memberSettlements = null;
        if(ShiroKit.lacksPermission(IConstantFactory.PERMISSION_MEMBER_SETTLEMENT_LIST_OTHER_DEPT)){
            ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
            Integer departmentId = shiroUser.getDeptId();
            memberSettlements = memberSettlementService.selectByDepartment(departmentId);
        }else{
            Wrapper wrapper = new EntityWrapper<>();
            wrapper = wrapper.orderDesc(Collections.singletonList("create_time"));
            memberSettlements = memberSettlementService.selectList(wrapper);
        }
        return new MemberSettlementWrapper(memberSettlements).wrap();
    }

    /**
     * 根据康护获取会员卡列表
     */
    @PostMapping(value = "/cardByTreatment")
    @ResponseBody
    public Object cardByTreatment(@RequestParam String treatment,@RequestParam String member) {
        Map<String, Object> result = new HashMap<>();
        Integer userId;
        if(StringUtils.isNotEmpty(treatment)) {
            Treatment t = treatmentService.selectById(Integer.valueOf(treatment));
            if (t == null) {
                result.put("status", 0);
                result.put("message", "找不到该用户");
                return result;
            }
            userId = t.getUserId();
        }else{
            userId=Integer.valueOf(member);
        }

        List<MembershipCard> cardList = membershipCardService.selectList(new EntityWrapper<MembershipCard>().eq("user",userId ));
        if(cardList==null || cardList.size()==0){
            result.put("status", 0);
            result.put("message", "该用户没有办理会员卡,请选择其他支付方式");
            return result;
        }
        List<KeyAndValueVo> cards = new ArrayList<>();
        cardList.forEach(c-> cards.add(new KeyAndValueVo(c.getNumber(),String.valueOf(c.getId()))));
        result.put("status", 1);
        result.put("data",cards);
        return result;
    }

    /**
     * 新增会员结算
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberSettlement memberSettlement, String consumable) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer departmentId = shiroUser.getDeptId();
        Dept dept = deptMapper.selectById(departmentId);
        memberSettlement.setDepartment(shiroUser.getDeptId());
        memberSettlement.setCreatedBy(shiroUser.getId());
        List<TreatmentDetail> tds = new ArrayList<>();
        // 判断是否已经结算
        Integer tid = memberSettlement.getTreatment();
        if(tid!=null&&tid>=0){
//            return new ErrorTip(404,"无此康护记录");
            int count = memberSettlementService.selectCount(new EntityWrapper<MemberSettlement>().eq("treatment", tid));
            if(count>0){
                return new ErrorTip(404,"已结算, 请勿重复结算");
            }
        }
        // 产品
        List<Consumable> cs = new ArrayList<>();
        if (StringUtils.isNotEmpty(consumable)) {
            try {
                JSONArray ja = JSONArray.parseArray(consumable);
                if (ja != null && ja.size() > 0)
                    for (int i = 0; i < ja.size(); i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        TreatmentDetail td = new TreatmentDetail();
                        Integer cid = jo.getInteger("id");
                        td.setConsumableId(cid);
                        Integer camount = jo.getInteger("amount");
                        td.setConsumableAmount(camount);
                        tds.add(td);
                        if (cid != null && camount > 0) {
                            Consumable c = consumableService.selectById(cid);
                            if (c.getDepartment() != memberSettlement.getDepartment()) {
                                return new ErrorTip(200, "不能使用其他门店耗材");
                            }
                            if (c.getAmount() < camount) {
                                return new ErrorTip(200, String.format("耗材[%s]", c.getName()));
                            }
                            c.setAmount(c.getAmount() - camount);
                            cs.add(c);
                        }else {
                            return new ErrorTip(200, "请正确选择使用耗材");
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return new ErrorTip(200, "请正确选择使用耗材");
        }
        Double price = memberSettlement.getPaymentAmount();
        if(price <0)
            return new ErrorTip(400,"结算金额不能为负数");
        if (memberSettlement.getPaymentMethod() == 1) { //1会员卡
            Integer membershipCard = memberSettlement.getMembershipCard();
            if(membershipCard==null||membershipCard<=0){
                return new ErrorTip(400,"所选会员卡不正确");
            }
            MembershipCard mc = membershipCardService.selectById(membershipCard);
            if(mc==null){
                return new ErrorTip(400,"找不到会员卡信息");
            }

            Double balance = mc.getBalance();
//            Integer balance = memberSettlementService.queryMembershipBalanceByTreatmentId(memberSettlement.getTreatment());
            if (balance !=null && balance >= 0 && balance >= price) {
                memberSettlementService.insert(memberSettlement);
                membershipCardService.deductMoney(tid, price);
                Treatment treatment = new Treatment();
                treatment.setId(tid);
                treatment.setStatus(3); // 已结算
                treatmentService.updateById(treatment);
            }else{
                return new ErrorTip(400,"您的会员卡余额不足,请充值!");
            }
        }else{
            memberSettlementService.insert(memberSettlement);
            Treatment treatment = new Treatment();
            treatment.setId(tid);
            treatment.setStatus(3); // 已结算
            treatmentService.updateById(treatment);
        }
        for(int i=0;i<tds.size();i++){
            TreatmentDetail td = tds.get(i);
            td.setSettlement(memberSettlement.getId());
            treatmentDetailService.insert(td);
        }
        for(int i=0;i<cs.size();i++){
            Consumable c = cs.get(i);
            consumableService.updateById(c);
        }

        return super.SUCCESS_TIP;
    }

    /**
     * 删除会员结算
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberSettlementId) {
        memberSettlementService.deleteById(memberSettlementId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员结算
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberSettlement memberSettlement) {
        if(memberSettlement.getPaymentAmount()<0)
            return new ErrorTip(200,"结算金额不能为负数");
        memberSettlementService.updateById(memberSettlement);
        return super.SUCCESS_TIP;
    }

    /**
     * 会员结算详情
     */
    @RequestMapping(value = "/detail/{memberSettlementId}")
    @ResponseBody
    public Object detail(@PathVariable("memberSettlementId") Integer memberSettlementId) {
        return memberSettlementService.selectById(memberSettlementId);
    }

    /**
     * 会员结算详情
     */
    @PostMapping(value = "/experienced")
    @ResponseBody
    public Object experienced(@RequestParam Integer treatment) {
        Map<String, Object> result = new HashMap<>();
        result.put("exped", memberSettlementService.userExperienced(treatment) > 0 ? 0 : 1);
        return result;
    }

    /**
     * 计算价格
     */
    @PostMapping(value = "/calcPrice")
    @ResponseBody
    public Object calcPrice(@RequestParam Integer treatment,@RequestParam Integer member, @RequestParam Integer paymentMethod, @RequestParam(required = false) Integer membershipCard,String consumable) {
        Wrapper<MemberSettlement> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("treatment", treatment);
        Map<String, Object> result = new HashMap<>();
        int count = memberSettlementService.selectCount(wrapper);
        if (count > 0) {
            result.put("status", 0);
            result.put("message", "本次康护已结算");
            return result;
        }

        double pprice = 0;
        try{
            JSONArray ja = JSONArray.parseArray(consumable);
            if (ja != null && ja.size() > 0)
                for (int i = 0; i < ja.size(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    pprice +=consumableService.selectById(jo.getInteger("id")).getPrice()*jo.getInteger("amount");
                }
        }catch (Exception e){
            e.printStackTrace();
            result.put("status", 0);
            result.put("message", "请正确填写结算产品信息！");
            return result;
        }

//        Treatment t = treatmentService.selectById(treatment);
//        Dept dept = deptService

        // 获取用户康护项目以及费用
        List<Project> projects = projectService.projectInformationByTreatment(treatment);
        switch (paymentMethod) {
            case 0:     // 项目体验
                if (memberSettlementService.userExperienced(treatment) > 0) {
                    result.put("status", 0);
                    result.put("message", "该用户已体验过,请重新选择支付方式");
                    return result;
                }
                break;
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
                List<Map<String, Object>> priceInfo = memberSettlementService.treatmentPriceInformation(treatment);
                double price = 0;
                if (priceInfo != null && priceInfo.size()>0) {
//                    result.put("status", 0);
//                    result.put("message", "找不到会员卡信息");
//                    return result;

                    Integer discount = (Integer) priceInfo.get(0).get("discount");
                    Integer pstd = (Integer) priceInfo.get(0).get("pstd");
                    price = projects.stream().mapToDouble(p -> priceByInfo(p, discount, pstd)).sum();
                }
                Double balance = mc.getBalance();
                if (balance ==null || balance < (price+pprice)) {
                    result.put("message","温馨提示: 会员卡余额不足!");
                }
                result.put("status", 1);
                result.put("price", (price+pprice));
                break;
            default:    // 其他
                List<Map<String, Object>> priceInfoE = memberSettlementService.treatmentPriceInformation(treatment);
                if (priceInfoE == null || priceInfoE.size()==0) {
                    result.put("status", 0);
                    result.put("message", "找不到会员卡信息");
                    return result;
                }
                Integer discountE = (Integer) priceInfoE.get(0).get("discount");
                Integer pstdE = (Integer) priceInfoE.get(0).get("pstd");
                double priceE = projects.stream().mapToDouble(p -> priceByInfo(p, discountE, pstdE)).sum();
                result.put("status", 1);
                result.put("price", priceE+pprice);
                break;
        }
        return result;
    }

    private static double priceByInfo(Project p, Integer discount, Integer pstd) {
        if (pstd == null || pstd < 1 || pstd > 3)
            pstd = 1;
        try {
            Class<? extends Project> pc = p.getClass();
            Field priceOnce = pc.getDeclaredField("priceOnce" + pstd);
            priceOnce.setAccessible(true);
            BigDecimal price = (BigDecimal) priceOnce.get(p);
            return price.doubleValue() * discount / 100;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
