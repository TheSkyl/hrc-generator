package com.cebrains.hrc.modular.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cebrains.hrc.common.persistence.model.MembershipCard;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-04-16
 */
public interface IMembershipCardService extends IService<MembershipCard> {

    List<Map<String,Object>> selectSuggestCardList(String key);

    void deductMoney(Integer treatment, Double paymentAmount);
}
