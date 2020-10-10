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
 * 外协订单结算
 * </p>
 *
 * @author frank123
 * @since 2018-07-23
 */
@TableName("outsource_order_settlement")
public class OutsourceOrderSettlement extends Model<OutsourceOrderSettlement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 外协订单
     */
    @TableField("outsource_order")
    private Integer outsourceOrder;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 支付方式
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 支付时间
     */
    @TableField("pay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 收款账户
     */
    @TableField("account_name")
    private String accountName;
    /**
     * 收款账号
     */
    @TableField("account_number")
    private String accountNumber;
    /**
     * 收款银行
     */
    @TableField("account_bank")
    private String accountBank;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 操作人
     */
    @TableField("created_by")
    private Integer createdBy;
    private Integer flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutsourceOrder() {
        return outsourceOrder;
    }

    public void setOutsourceOrder(Integer outsourceOrder) {
        this.outsourceOrder = outsourceOrder;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
        return "OutsourceOrderSettlement{" +
        "id=" + id +
        ", outsourceOrder=" + outsourceOrder +
        ", amount=" + amount +
        ", payType=" + payType +
        ", payTime=" + payTime +
        ", accountName=" + accountName +
        ", accountNumber=" + accountNumber +
        ", accountBank=" + accountBank +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", flag=" + flag +
        "}";
    }
}
