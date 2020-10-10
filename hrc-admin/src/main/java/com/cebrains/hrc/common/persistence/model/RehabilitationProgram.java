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
 * 康复方案
 * </p>
 *
 * @author frank123
 * @since 2018-07-04
 */
@TableName("rehabilitation_program")
public class RehabilitationProgram extends Model<RehabilitationProgram> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员姓名
     */
    private Integer member;

    @TableField("member_health_record")
    private Integer memberHealthRecord;
    /**
     * 康护
     */
    private String disease;
    /**
     * 方案说明
     */
    private String description;
    /**
     * 最终疗效
     */
    @TableField("curative_effect")
    private String curativeEffect;
    @TableField("final_effect")
    private Integer finalEffect;
    @TableField("customer_satisfaction")
    private Integer customerSatisfaction;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 制定门店
     */
    private Integer department;
    /**
     * 操作员
     */
    @TableField("created_by")
    private Integer createdBy;
    /**
     * 制定日期
     */
    @TableField("create_time")
    private Date createTime;
    private Integer flag;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String createdByName;
    @TableField(exist = false)
    private String departmentName;


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

    public Integer getMemberHealthRecord() {
        return memberHealthRecord;
    }

    public void setMemberHealthRecord(Integer memberHealthRecord) {
        this.memberHealthRecord = memberHealthRecord;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurativeEffect() {
        return curativeEffect;
    }

    public void setCurativeEffect(String curativeEffect) {
        this.curativeEffect = curativeEffect;
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
        return this.member;
    }

    @Override
    public String toString() {
        return "RehabilitationProgram{" +
        "id=" + id +
        ", member=" + member +
        ", disease=" + disease +
        ", description=" + description +
        ", curativeEffect=" + curativeEffect +
        ", remark=" + remark +
        ", department=" + department +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
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

    public Integer getFinalEffect() {
        return finalEffect;
    }

    public void setFinalEffect(Integer finalEffect) {
        this.finalEffect = finalEffect;
    }

    public Integer getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public void setCustomerSatisfaction(Integer customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }
}
