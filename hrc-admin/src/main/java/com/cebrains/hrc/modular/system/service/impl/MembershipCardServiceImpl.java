package com.cebrains.hrc.modular.system.service.impl;

import com.cebrains.hrc.common.persistence.model.MembershipCard;
import com.cebrains.hrc.common.persistence.dao.MembershipCardMapper;
import com.cebrains.hrc.modular.system.service.IMembershipCardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-04-16
 */
@Service
public class MembershipCardServiceImpl extends ServiceImpl<MembershipCardMapper, MembershipCard> implements IMembershipCardService {

    @Autowired
    MembershipCardMapper membershipCardMapper;

    @Override
    public List<Map<String,Object>> selectSuggestCardList(String key) {
        return membershipCardMapper.selectSuggestCardList(key);
    }

    @Override
    public void deductMoney(Integer treatment, Double paymentAmount) {
        membershipCardMapper.deductMoneyByTreatment(treatment,paymentAmount);
    }
}
