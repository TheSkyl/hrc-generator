package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.MemberSettlement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cebrains.hrc.common.persistence.model.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员结算 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-05-15
 */
public interface MemberSettlementMapper extends BaseMapper<MemberSettlement> {

    Integer userExperienced(@Param("tid") Integer treatment);

    List<Map<String,Object>> treatmentPriceInformation(@Param("tid")Integer treatment);

    Integer queryMembershipBalanceByTreatmentId(@Param("tid")Integer treatment);

    List<MemberSettlement> selectByDepartment(@Param("did") Integer departmentId);
}
