package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.Treatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 康护记录 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-05-11
 */
public interface TreatmentMapper extends BaseMapper<Treatment> {

    List<Map> findTreatmentSuggest(@Param("k") String keyword);

    Map findProjectNamesByTreatment(@Param("treatment") Integer treatment);

    List<Map> treatmentByMember(@Param("member")Integer member);
}
