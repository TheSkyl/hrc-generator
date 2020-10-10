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
 * 特殊申请执行
 * </p>
 *
 * @author frank123
 * @since 2018-07-18
 */
@TableName("special_application_exec")
public class SpecialApplicationExec extends Model<SpecialApplicationExec> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 特殊申请
     */
    @TableField("special_application")
    private Integer specialApplication;
    /**
     * 费用
     */
    private Double cost;
    /**
     * 康护过程描述
     */
    @TableField("treatment_process")
    private String treatmentProcess;
    /**
     * 客户须知
     */
    private String instructions;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系人电话
     */
    private String phone;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    private Integer flag;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String statusName;
    @TableField(exist = false)
    private String specialApplicationNumber;


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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getTreatmentProcess() {
        return treatmentProcess;
    }

    public void setTreatmentProcess(String treatmentProcess) {
        this.treatmentProcess = treatmentProcess;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        return "SpecialApplicationExec{" +
        "id=" + id +
        ", specialApplication=" + specialApplication +
        ", cost=" + cost +
        ", treatmentProcess=" + treatmentProcess +
        ", instructions=" + instructions +
        ", contact=" + contact +
        ", phone=" + phone +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", flag=" + flag +
        "}";
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setSpecialApplicationNumber(String specialApplicationNumber) {
        this.specialApplicationNumber = specialApplicationNumber;
    }

    public String getSpecialApplicationNumber() {
        return specialApplicationNumber;
    }
}
