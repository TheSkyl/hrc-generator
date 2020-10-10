package com.cebrains.hrc.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 特殊申请结算
 * </p>
 *
 * @author frank123
 * @since 2018-07-18
 */
@TableName("special_application_settlement")
public class SpecialApplicationSettlement extends Model<SpecialApplicationSettlement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 特殊申请
     */
    @TableField("special_application")
    private Integer specialApplication;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 支付方式
     */
    @TableField("pay_type")
    private Integer payType;
    @TableField("payment_description")
    private String paymentDescription;
    private String payee;
    /**
     * 收款时间
     */
    @TableField("pay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 结算时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer flag;
    @TableField(exist = false)
    private String specialApplicationNumber;
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String payTypeName;


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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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
        return "SpecialApplicationSettlement{" +
        "id=" + id +
        ", specialApplication=" + specialApplication +
        ", amount=" + amount +
        ", payType=" + payType +
        ", paymentDescription=" + paymentDescription +
        ", payee=" + payee +
        ", payTime=" + payTime +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public void setSpecialApplicationNumber(String specialApplicationNumber) {
        this.specialApplicationNumber = specialApplicationNumber;
    }

    public String getSpecialApplicationNumber() {
        return specialApplicationNumber;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getPayTypeName() {
        return payTypeName;
    }
}
