package com.cebrains.hrc.modular.member.service.impl;

import com.cebrains.hrc.common.persistence.model.MemberSettlement;
import com.cebrains.hrc.common.persistence.dao.MemberSettlementMapper;
import com.cebrains.hrc.common.persistence.model.Project;
import com.cebrains.hrc.modular.member.service.IMemberSettlementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员结算 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-05-15
 */
@Service
public class MemberSettlementServiceImpl extends ServiceImpl<MemberSettlementMapper, MemberSettlement> implements IMemberSettlementService {
    @Autowired
    MemberSettlementMapper memberSettlementMapper;

    @Override
    public Integer userExperienced(Integer treatment) {
        return memberSettlementMapper.userExperienced(treatment);
    }

    @Override
    public List<Map<String, Object>> treatmentPriceInformation(Integer treatment) {
        return memberSettlementMapper.treatmentPriceInformation(treatment);
    }

    @Override
    public Integer queryMembershipBalanceByTreatmentId(Integer treatment) {
        return memberSettlementMapper.queryMembershipBalanceByTreatmentId(treatment);
    }

    @Override
    public List<MemberSettlement> selectByDepartment(Integer departmentId) {
        return memberSettlementMapper.selectByDepartment(departmentId);
    }
}
