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
 * 会员健康档案
 * </p>
 *
 * @author frank123
 * @since 2018-07-02
 */
@TableName("member_health_record")
public class MemberHealthRecord extends Model<MemberHealthRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员
     */
    private Integer member;
    private String project;
    /**
     * 康护
     */
    private String disease;
    /**
     * 健康状况评估
     */
    private String evaluation;
    /**
     * 备注
     */
    private String remark;
    /**
     * 建档门店
     */
    private Integer department;
    /**
     * 操作员
     */
    @TableField("created_by")
    private Integer createdBy;
    /**
     * 建档时间
     */
    @TableField("create_time")
    private Date createTime;
    private Integer flag;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String createdByName;
    @TableField(exist = false)
    private String departmentName;
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String memberPhone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MemberHealthRecord{" +
        "id=" + id +
        ", member=" + member +
        ", disease=" + disease +
        ", evaluation=" + evaluation +
        ", remark=" + remark +
        ", department=" + department +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
