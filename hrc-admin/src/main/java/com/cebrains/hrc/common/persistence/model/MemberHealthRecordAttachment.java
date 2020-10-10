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
 * 健康档案附件
 * </p>
 *
 * @author frank123
 * @since 2018-07-02
 */
@TableName("member_health_record_attachment")
public class MemberHealthRecordAttachment extends Model<MemberHealthRecordAttachment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 健康档案
     */
    @TableField("health_record")
    private Integer healthRecord;
    /**
     * 存储地址
     */
    private String path;
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(Integer healthRecord) {
        this.healthRecord = healthRecord;
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
        return "MemberHealthRecordAttachment{" +
        "id=" + id +
        ", healthRecord=" + healthRecord +
        ", path=" + path +
        ", createTime=" + createTime +
        "}";
    }
}
