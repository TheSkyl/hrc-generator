package com.cebrains.hrc.common.persistence.vo;

import com.cebrains.hrc.common.persistence.model.Project;

public class ProjectVO extends Project {
    private String projectCategory;

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }
}
