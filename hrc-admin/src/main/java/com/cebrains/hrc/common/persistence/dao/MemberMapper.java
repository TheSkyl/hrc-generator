package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.Member;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-03-08
 */
public interface MemberMapper extends BaseMapper<Member> {

    String findMemberNameByTreatmentId(@Param("tid") Integer treatment);

    Member getMemberByCard(@Param("card") Integer card);
}
