package com.cebrains.hrc.modular.member.service.impl;

import com.cebrains.hrc.common.persistence.model.Member;
import com.cebrains.hrc.common.persistence.dao.MemberMapper;
import com.cebrains.hrc.modular.member.service.IMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-03-08
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
