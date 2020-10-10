package com.cebrains.hrc.modular.member.service;

import com.cebrains.hrc.common.persistence.model.MemberSettlement;
import com.baomidou.mybatisplus.service.IService;
import com.cebrains.hrc.common.persistence.model.Project;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员结算 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-05-15
 */
public interface IMemberSettlementService extends IService<MemberSettlement> {

    Integer userExperienced(Integer treatment);

    List<Map<String,Object>> treatmentPriceInformation(Integer treatment);

    Integer queryMembershipBalanceByTreatmentId(Integer treatment);

    List<MemberSettlement> selectByDepartment(Integer departmentId);
}
