package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.Member;
import com.cebrains.hrc.common.persistence.model.MembershipCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-04-16
 */
public interface MembershipCardMapper extends BaseMapper<MembershipCard> {

    List<Map<String,Object>> selectSuggestCardList(@Param("key") String key);

    void deductMoneyByTreatment(@Param("tid")Integer treatment, @Param("amount")Double paymentAmount);
}
