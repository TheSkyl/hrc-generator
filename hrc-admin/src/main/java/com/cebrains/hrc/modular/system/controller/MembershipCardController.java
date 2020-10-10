package com.cebrains.hrc.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Dict;
import com.cebrains.hrc.common.persistence.vo.KeyAndValueVo;
import com.cebrains.hrc.common.persistence.vo.MemberCardSuggestVo;
import com.cebrains.hrc.core.base.controller.BaseController;
import com.cebrains.hrc.core.base.tips.ErrorTip;
import com.cebrains.hrc.modular.resource.wrapper.MembershipCardWrapper;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.cebrains.hrc.core.log.LogObjectHolder;
import com.cebrains.hrc.common.persistence.model.MembershipCard;
import com.cebrains.hrc.modular.system.service.IMembershipCardService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 会员卡控制器
 *
 * @author frank
 * @Date 2018-04-16 17:48:21
 */
@Controller
@RequestMapping("/membershipCard")
public class MembershipCardController extends BaseController {

    private String PREFIX = "/system/membershipCard/";

    @Autowired
    private IMembershipCardService membershipCardService;

    /**
     * 跳转到会员卡首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "membershipCard.html";
    }

    /**
     * 跳转到添加会员卡
     */
    @RequestMapping("/membershipCard_add")
    public String membershipCardAdd(Model model) {
        model.addAttribute("projects", ConstantFactory.me().findAllProject());
        return PREFIX + "membershipCard_add.html";
    }

    /**
     * 跳转到修改会员卡
     */
    @RequestMapping("/membershipCard_update/{membershipCardId}")
    public String membershipCardUpdate(@PathVariable Integer membershipCardId, Model model) {
        MembershipCard membershipCard = membershipCardService.selectById(membershipCardId);
        membershipCard.setUserName(ConstantFactory.me().getMemberName(membershipCard.getUser()));
        membershipCard.setLevelName(ConstantFactory.me().getMemberLevelName(membershipCard.getLevel()));
        String projectName = ConstantFactory.me().getProjectName(membershipCard.getProject());
        membershipCard.setProjectName("未知".equals(projectName) ? "未绑定" : projectName);

        model.addAttribute("item", membershipCard);
        LogObjectHolder.me().set(membershipCard);
        return PREFIX + "membershipCard_edit.html";
    }

    /**
     * 获取会员卡列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<MembershipCard> membershipCards = membershipCardService.selectList(null);
        return new MembershipCardWrapper(membershipCards).wrap();
    }

    /**
     * 获取会员卡建议列表
     */
    @RequestMapping(value = "/suggestList/{k}")
    @ResponseBody
    public Object suggestList(@PathVariable String k) {
        List<Map<String, Object>> result = membershipCardService.selectSuggestCardList(k);
        return result;
    }



    /**
     * 根据康护获取会员卡列表
     */
    @PostMapping(value = "/cardByMember")
    @ResponseBody
    public Object cardByTreatment(@RequestParam String member) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isEmpty(member)){
            result.put("status", 0);
            result.put("message", "找不到该用户");
            return result;
        }

        List<MembershipCard> cardList = membershipCardService.selectList(new EntityWrapper<MembershipCard>().eq("user",member ));
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
     * 新增会员卡
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MembershipCard membershipCard) {
        try {
            if (StringUtils.isEmpty(membershipCard.getNumber())) {
                String cardNo = RandomStringUtils.randomAlphabetic(3).concat(RandomStringUtils.randomNumeric(6));
                membershipCard.setNumber(cardNo.toUpperCase());
            }
            if (membershipCard.getBalance() != null && membershipCard.getBalance() > 0) {
                List<Dict> dicts = ConstantFactory.me().getLevelStandardDicts().stream().filter(ls -> ls.getNum() <= membershipCard.getBalance()).sorted(Comparator.comparingInt(Dict::getNum).reversed()).collect(Collectors.toList());
                if (dicts != null && dicts.size() > 0) {
                    membershipCard.setLevel(Integer.valueOf(dicts.get(0).getName()));
                }
            }
            membershipCardService.insert(membershipCard);
        } catch (Exception e) {
            return new ErrorTip(500, "请再试一次");
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除会员卡
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer membershipCardId) {
        membershipCardService.deleteById(membershipCardId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员卡
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MembershipCard membershipCard) {
        membershipCardService.updateById(membershipCard);
        return super.SUCCESS_TIP;
    }

    /**
     * 会员卡详情
     */
    @RequestMapping(value = "/detail/{membershipCardId}")
    @ResponseBody
    public Object detail(@PathVariable("membershipCardId") Integer membershipCardId) {
        return membershipCardService.selectById(membershipCardId);
    }
}
