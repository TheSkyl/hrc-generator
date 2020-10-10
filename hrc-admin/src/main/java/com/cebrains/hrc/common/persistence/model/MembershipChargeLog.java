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
 * 会员卡充值记录
 * </p>
 *
 * @author frank123
 * @since 2018-04-17
 */
@TableName("membership_charge_log")
public class MembershipChargeLog extends Model<MembershipChargeLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员卡
     */
    private Integer card;
    /**
     * 充值金额
     */
    private Double amount;
    /**
     * 所属门店
     */
    private Integer department;
    /**
     * 操作员
     */
    @TableField("given_amount")
    private Double givenAmount;
    private String memo;
    private Integer created;
    @TableField("create_time")
    private Date createTime;
    /**
     * 标记
     */
    private Integer flag;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String cardNumber;
    @TableField(exist = false)
    private String departmentName;
    @TableField(exist = false)
    private String createdName;
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String mobile;
    @TableField(exist = false)
    private String idCard;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
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
        return "MembershipChargeLog{" +
        "id=" + id +
        ", card=" + card +
        ", amount=" + amount +
        ", department=" + department +
        ", created=" + created +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public Double getGivenAmount() {
        return givenAmount;
    }

    public void setGivenAmount(Double givenAmount) {
        this.givenAmount = givenAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
