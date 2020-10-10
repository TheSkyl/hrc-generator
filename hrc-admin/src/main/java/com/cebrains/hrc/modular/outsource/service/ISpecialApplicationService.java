package com.cebrains.hrc.modular.outsource.service;

import com.cebrains.hrc.common.persistence.model.SpecialApplication;
import com.baomidou.mybatisplus.service.IService;
import com.cebrains.hrc.common.persistence.vo.SpecialApplicationSuggestVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 特殊申请 服务类
 * </p>
 *
 * @author frank123
 * @since 2018-07-11
 */
public interface ISpecialApplicationService extends IService<SpecialApplication> {

    List<SpecialApplicationSuggestVo> findSuggest(Integer departmentId, String k);
}
