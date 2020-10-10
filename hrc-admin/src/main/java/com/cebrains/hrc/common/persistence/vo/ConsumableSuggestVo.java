package com.cebrains.hrc.common.persistence.vo;

public class ConsumableSuggestVo {
    private Integer id;
    private String name;
    private Integer available;

    public ConsumableSuggestVo(Integer id, String name, Integer available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
