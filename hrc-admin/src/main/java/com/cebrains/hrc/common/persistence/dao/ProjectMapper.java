package com.cebrains.hrc.common.persistence.dao;

import com.cebrains.hrc.common.persistence.model.Project;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 项目表 Mapper 接口
 * </p>
 *
 * @author frank123
 * @since 2018-03-06
 */
public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> projectInformationByTreatment(@Param("tid") Integer treatment);
}
