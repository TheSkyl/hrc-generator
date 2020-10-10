package com.cebrains.hrc.modular.outsource.service.impl;

import com.cebrains.hrc.common.persistence.model.SpecialApplication;
import com.cebrains.hrc.common.persistence.dao.SpecialApplicationMapper;
import com.cebrains.hrc.common.persistence.vo.SpecialApplicationSuggestVo;
import com.cebrains.hrc.modular.outsource.service.ISpecialApplicationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 特殊申请 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-07-11
 */
@Service
public class SpecialApplicationServiceImpl extends ServiceImpl<SpecialApplicationMapper, SpecialApplication> implements ISpecialApplicationService {
    @Autowired
    SpecialApplicationMapper specialApplicationMapper;
    @Override
    public List<SpecialApplicationSuggestVo> findSuggest(Integer departmentId, String k) {
        return specialApplicationMapper.findSuggest(departmentId, k);
    }
}
