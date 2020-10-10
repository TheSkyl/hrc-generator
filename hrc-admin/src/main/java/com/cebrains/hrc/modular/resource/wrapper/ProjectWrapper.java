package com.cebrains.hrc.modular.resource.wrapper;

import com.cebrains.hrc.common.constant.factory.ConstantFactory;
import com.cebrains.hrc.common.persistence.model.Project;
import com.cebrains.hrc.core.base.warpper.BaseCustomWarpper;

import java.util.List;

/**
 * 项目管理的包装类
 *
 * @author frank
 * @date 2017年2月13日 下午10:47:03
 */
public class ProjectWrapper extends BaseCustomWarpper<Project> {

    public ProjectWrapper(List<Project> list) {
        super(list);
    }

    @Override
    public void wrapTheObject(Project item) {
        item.setProjectCategory(ConstantFactory.me().getProjectCategory(item.getCategory()));
    }

}
