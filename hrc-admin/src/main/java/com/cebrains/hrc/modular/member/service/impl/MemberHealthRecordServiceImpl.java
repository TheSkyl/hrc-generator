package com.cebrains.hrc.modular.member.service.impl;

import com.cebrains.hrc.common.persistence.model.MemberHealthRecord;
import com.cebrains.hrc.common.persistence.dao.MemberHealthRecordMapper;
import com.cebrains.hrc.modular.member.service.IMemberHealthRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员健康档案 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-07-02
 */
@Service
public class MemberHealthRecordServiceImpl extends ServiceImpl<MemberHealthRecordMapper, MemberHealthRecord> implements IMemberHealthRecordService {

}
