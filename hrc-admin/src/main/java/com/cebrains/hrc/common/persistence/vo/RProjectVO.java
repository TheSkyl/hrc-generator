package com.cebrains.hrc.common.persistence.vo;

import com.cebrains.hrc.common.persistence.model.Project;

import java.util.List;

public class RProjectVO {
    private Integer id;
    private String name;
    private List<Project> rps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getRps() {
        return rps;
    }

    public void setRps(List<Project> rps) {
        this.rps = rps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
