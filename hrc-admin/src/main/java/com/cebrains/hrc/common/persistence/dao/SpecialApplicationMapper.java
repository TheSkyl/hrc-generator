package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.SpecialApplication;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cebrains.hrc.common.persistence.vo.SpecialApplicationSuggestVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 特殊申请 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-07-11
 */
public interface SpecialApplicationMapper extends BaseMapper<SpecialApplication> {

    String getMemberNameBySpecialApplication(@Param("sa") Integer specialApplication);

    List<SpecialApplicationSuggestVo> findSuggest(@Param("did")Integer departmentId, @Param("k")String k);
}
