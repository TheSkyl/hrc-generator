package com.cebrains.hrc.modular.resource.service.impl;

import com.cebrains.hrc.common.persistence.model.Project;
import com.cebrains.hrc.common.persistence.dao.ProjectMapper;
import com.cebrains.hrc.modular.resource.service.IProjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author frank123
 * @since 2018-03-06
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public List<Project> projectInformationByTreatment(Integer treatment) {
        return projectMapper.projectInformationByTreatment(treatment);
    }
}
