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
 * 会员结算
 * </p>
 *
 * @author frank123
 * @since 2018-05-15
 */
@TableName("member_settlement")
public class MemberSettlement extends Model<MemberSettlement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 康护记录
     */
    private Integer treatment;
    /**
     * 支付方式
     */
    @TableField("payment_method")
    private Integer paymentMethod;
    /**
     * 会员卡,限会员卡支付
     */
    @TableField("membership_card")
    private Integer membershipCard;
    /**
     * 支付金额
     */
    @TableField("payment_amount")
    private Double paymentAmount;
    /**
     * 顾客打分
     */
    @TableField("member_rating")
    private Double memberRating;
    /**
     * 前台打分
     */
    @TableField("foreground_rating")
    private Double foregroundRating;
    /**
     * 技师打分
     */
    @TableField("technician_rating")
    private Double technicianRating;
    /**
     * 经理打分
     */
    @TableField("manager_rating")
    private Double managerRating;

    private Integer member;

    @TableField("created_by")
    private Integer createdBy;

    private Integer department;
    /**
     * 结算状态
     */
    private Integer status;
    /**
     * 结算时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 状态
     */
    private Integer flag;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String projectNames;
    @TableField(exist = false)
    private String paymentMethodName;
    @TableField(exist = false)
    private String membershipCardName;
    @TableField(exist = false)
    private String productNames;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTreatment() {
        return treatment;
    }

    public void setTreatment(Integer treatment) {
        this.treatment = treatment;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getMemberRating() {
        return memberRating;
    }

    public void setMemberRating(Double memberRating) {
        this.memberRating = memberRating;
    }

    public Double getForegroundRating() {
        return foregroundRating;
    }

    public void setForegroundRating(Double foregroundRating) {
        this.foregroundRating = foregroundRating;
    }

    public Double getTechnicianRating() {
        return technicianRating;
    }

    public void setTechnicianRating(Double technicianRating) {
        this.technicianRating = technicianRating;
    }

    public Double getManagerRating() {
        return managerRating;
    }

    public void setManagerRating(Double managerRating) {
        this.managerRating = managerRating;
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
        return "MemberSettlement{" +
        "id=" + id +
        ", treatment=" + treatment +
        ", paymentMethod=" + paymentMethod +
        ", paymentAmount=" + paymentAmount +
        ", memberRating=" + memberRating +
        ", foregroundRating=" + foregroundRating +
        ", technicianRating=" + technicianRating +
        ", managerRating=" + managerRating +
        ", status=" + status +
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

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public Integer getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(Integer membershipCard) {
        this.membershipCard = membershipCard;
    }

    public String getMembershipCardName() {
        return membershipCardName;
    }

    public void setMembershipCardName(String membershipCardName) {
        this.membershipCardName = membershipCardName;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getProductNames() {
        return productNames;
    }
}
