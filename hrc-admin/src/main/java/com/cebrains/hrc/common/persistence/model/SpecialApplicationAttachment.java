package com.cebrains.hrc.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author frank123
 * @since 2018-07-22
 */
@TableName("special_application_attachment")
public class SpecialApplicationAttachment extends Model<SpecialApplicationAttachment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("special_application")
    private Integer specialApplication;
    private String path;
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecialApplication() {
        return specialApplication;
    }

    public void setSpecialApplication(Integer specialApplication) {
        this.specialApplication = specialApplication;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SpecialApplicationAttachment{" +
        "id=" + id +
        ", specialApplication=" + specialApplication +
        ", path=" + path +
        ", createTime=" + createTime +
        "}";
    }
}
