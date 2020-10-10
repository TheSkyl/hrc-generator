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
 * 特殊申请
 * </p>
 *
 * @author frank123
 * @since 2018-07-11
 */
@TableName("special_application")
public class SpecialApplication extends Model<SpecialApplication> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员姓名
     */
    private Integer member;
    /**
     * 申请项目
     */
    private Integer project;
    /**
     * 具体情况说明
     */
    @TableField("condition_description")
    private String conditionDescription;
    /**
     * 既往病史
     */
    @TableField("disease_history")
    private String diseaseHistory;
    /**
     * 近期检查
     */
    @TableField("recent_check")
    private String recentCheck;
    /**
     * 近期康护
     */
    @TableField("recent_treatment")
    private String recentTreatment;
    /**
     * 补充说明
     */
    private String remark;
    /**
     * 申请部门
     */
    private Integer department;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    private Integer flag;
    /**
     * 申请人
     */
    @TableField("created_by")
    private Integer createdBy;
    /**
     * 申请状态
     */
    private Integer status;
    /**
     * 申请单号
     */
    @TableField("application_number")
    private String applicationNumber;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String createdByName;
    @TableField(exist = false)
    private String projectName;
    @TableField(exist = false)
    private String statusName;
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

    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    public String getRecentCheck() {
        return recentCheck;
    }

    public void setRecentCheck(String recentCheck) {
        this.recentCheck = recentCheck;
    }

    public String getRecentTreatment() {
        return recentTreatment;
    }

    public void setRecentTreatment(String recentTreatment) {
        this.recentTreatment = recentTreatment;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SpecialApplication{" +
        "id=" + id +
        ", member=" + member +
        ", project=" + project +
        ", conditionDescription=" + conditionDescription +
        ", diseaseHistory=" + diseaseHistory +
        ", recentCheck=" + recentCheck +
        ", recentTreatment=" + recentTreatment +
        ", remark=" + remark +
        ", department=" + department +
        ", createTime=" + createTime +
        ", flag=" + flag +
        ", createdBy=" + createdBy +
        ", status=" + status +
        ", applicationNumber=" + applicationNumber +
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

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
