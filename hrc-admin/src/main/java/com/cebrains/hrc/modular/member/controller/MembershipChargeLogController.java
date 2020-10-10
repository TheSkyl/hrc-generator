package com.cebrains.hrc.modular.member.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.constant.factory.IConstantFactory;
import com.cebrains.hrc.common.persistence.dao.DeptMapper;
import com.cebrains.hrc.common.persistence.dao.MembershipCardMapper;
import com.cebrains.hrc.common.persistence.dao.UserMapper;
import com.cebrains.hrc.common.persistence.model.*;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.core.base.tips.SuccessResultTip;
import com.cebrains.hrc.core.base.tips.SuccessTip;
import com.cebrains.hrc.core.shiro.ShiroUser;
import com.cebrains.hrc.modular.resource.wrapper.MembershipChargeLogWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.cebrains.hrc.modular.member.service.IMembershipChargeLogService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 会员充值记录控制器
 *
 * @author frank
 * @Date 2018-04-17 16:16:52
 */
@Controller
@RequestMapping("/membershipChargeLog")
public class MembershipChargeLogController extends BaseController {

    private String PREFIX = "/member/membershipChargeLog/";

    @Autowired
    private IMembershipChargeLogService membershipChargeLogService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private MembershipCardMapper membershipCardMapper;

    /**
     * 跳转到会员充值记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "membershipChargeLog.html";
    }

    /**
     * 跳转到会员充值
     */
    @RequestMapping("/membershipChargeLog_add")
    public String membershipChargeLogAdd(Model model) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer opsUserId = shiroUser.getId();
        Integer departmentId = shiroUser.getDeptId();
        String deptName = shiroUser.getDeptName();
        String userName = shiroUser.getName();

        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("pid", 0);
        List<Dept> depts = deptMapper.selectList(wrapper);
        model.addAttribute("departments",depts);

        Wrapper<User> userWrapper = new EntityWrapper<>();
        userWrapper = userWrapper.eq("deptid", departmentId);
        List<User> users = userMapper.selectList(userWrapper);
        model.addAttribute("users",users);
        model.addAttribute("userId",opsUserId);
        model.addAttribute("userName",userName);
        model.addAttribute("departmentId",departmentId);
        model.addAttribute("departmentName",deptName);

        return PREFIX + "membershipChargeLog_add.html";
    }

    /**
     * 跳转到修改会员充值记录
     */
    @RequestMapping("/membershipChargeLog_update/{membershipChargeLogId}")
    public String membershipChargeLogUpdate(@PathVariable Integer membershipChargeLogId, Model model) {
        MembershipChargeLog membershipChargeLog = membershipChargeLogService.selectById(membershipChargeLogId);
        model.addAttribute("item",membershipChargeLog);
        LogObjectHolder.me().set(membershipChargeLog);
        return PREFIX + "membershipChargeLog_edit.html";
    }

    /**
     * 获取会员充值记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<MembershipChargeLog> membershipChargeLogs = membershipChargeLogService.selectList(new EntityWrapper<MembershipChargeLog>().orderDesc(Collections.singletonList("create_time")));
        return new MembershipChargeLogWrapper(membershipChargeLogs).wrap();
    }

    /**
     * 新增会员充值记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MembershipChargeLog membershipChargeLog) {
        ShiroUser shiroUser = (ShiroUser) getSession().getAttribute("shiroUser");
        Integer opsUserId = shiroUser.getId();
        Integer departmentId = shiroUser.getDeptId();
        if(membershipChargeLog.getAmount()!=null && membershipChargeLog.getAmount()>0) {
            MembershipCard membershipCard = membershipCardMapper.selectById(membershipChargeLog.getCard());
            if(membershipCard==null){
                return new ErrorTip(400,"该会员卡不存在");
            }
            if(membershipChargeLog.getGivenAmount()<0){
                return new ErrorTip(400,"赠送金额不正确");
            }
            membershipCard.setBalance(membershipCard.getBalance()+membershipChargeLog.getAmount()+membershipChargeLog.getGivenAmount());
            List<Dict> dicts = ConstantFactory.me().getLevelPresentDicts().stream().filter(ls -> ls.getNum() <= membershipChargeLog.getAmount()).sorted(Comparator.comparingInt(Dict::getNum).reversed()).collect(Collectors.toList());
            if(dicts!=null && dicts.size()>0){
                Dict pdict = dicts.get(0);
                Integer level = pdict.getNum();
                if(level>membershipCard.getLevel()) {
                    membershipCard.setLevel(level);
                }
                membershipCard.setBalance(membershipCard.getBalance()+Double.parseDouble(pdict.getName()));
                String discountValue = ConstantFactory.me().getMemberDiscountByLevel(level);
                if(StringUtils.isNotEmpty(discountValue)){
                    membershipCard.setDiscount(Integer.valueOf(discountValue));
                }
            }
            membershipCardMapper.updateById(membershipCard);
        }else{
            return new ErrorTip(200,"请确认充值金额");
        }
        membershipChargeLog.setCreated(opsUserId);
        membershipChargeLog.setDepartment(departmentId);
        membershipChargeLogService.insert(membershipChargeLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 计算充值金额给用户带来的等级
     */
    @RequestMapping(value = "/chargePresent")
    @ResponseBody
    public Object chargePresent(@Param("amount")Double amount,@Param("membershipCard")Integer membershipCard) {
        Map<String,String> result = new HashMap<>();
        if(amount>0&&membershipCard>0) {
            MembershipCard mc = membershipCardMapper.selectById(membershipCard);
            if(mc==null){
                return new ErrorTip(404,"该会员卡不存在");
            }
            List<Dict> dicts = ConstantFactory.me().getLevelPresentDicts().stream().filter(ls -> ls.getNum() <= amount).sorted(Comparator.comparingInt(Dict::getNum).reversed()).collect(Collectors.toList());
            if(dicts!=null && dicts.size()>0){
                Dict pdict = dicts.get(0);
                Integer level = pdict.getNum();
                if(level<mc.getLevel()){
                    level = mc.getLevel();
                }
                result.put("level",ConstantFactory.me().getDictsByName(IConstantFactory.DICT_KEY_MCN,level));
               result.put("present",pdict.getName());
               result.put("balance",String.valueOf(mc.getBalance()+amount+Double.parseDouble(pdict.getName())));
            }
            return new SuccessResultTip(result);
        }else{
            return new ErrorTip(400,"请确认充值金额");
        }
    }

    /**
     * 删除会员充值记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer membershipChargeLogId) {
        membershipChargeLogService.deleteById(membershipChargeLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员充值记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MembershipChargeLog membershipChargeLog) {
        membershipChargeLogService.updateById(membershipChargeLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 会员充值记录详情
     */
    @RequestMapping(value = "/detail/{membershipChargeLogId}")
    @ResponseBody
    public Object detail(@PathVariable("membershipChargeLogId") Integer membershipChargeLogId) {
        return membershipChargeLogService.selectById(membershipChargeLogId);
    }
}
