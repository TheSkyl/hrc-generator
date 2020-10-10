package com.cebrains.hrc.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author frank123
 * @since 2018-02-26
 */
@TableName("region")
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    private Integer code;
    @TableField("parent_code")
    private Integer parentCode;
    private Integer type;
    private String name;
    @TableField("full_name")
    private String fullName;
    @TableField("full_region_name")
    private String fullRegionName;
    @TableField("brief_region_name")
    private String briefRegionName;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullRegionName() {
        return fullRegionName;
    }

    public void setFullRegionName(String fullRegionName) {
        this.fullRegionName = fullRegionName;
    }

    public String getBriefRegionName() {
        return briefRegionName;
    }

    public void setBriefRegionName(String briefRegionName) {
        this.briefRegionName = briefRegionName;
    }

    @Override
    protected Serializable pkVal() {
        return this.code;
    }

    @Override
    public String toString() {
        return "Region{" +
        "code=" + code +
        ", parentCode=" + parentCode +
        ", type=" + type +
        ", name=" + name +
        ", fullName=" + fullName +
        ", fullRegionName=" + fullRegionName +
        ", briefRegionName=" + briefRegionName +
        "}";
    }
}
