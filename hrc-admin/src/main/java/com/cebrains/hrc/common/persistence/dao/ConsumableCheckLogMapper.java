package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.ConsumableCheckLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 盘库日志 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-10-31
 */
public interface ConsumableCheckLogMapper extends BaseMapper<ConsumableCheckLog> {

    List<ConsumableCheckLog> selectListByDepartment(@Param("department") Integer department);
}
