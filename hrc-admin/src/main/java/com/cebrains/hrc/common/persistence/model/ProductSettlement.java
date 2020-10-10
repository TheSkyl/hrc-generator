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
 * 产品结算
 * </p>
 *
 * @author frank123
 * @since 2018-09-21
 */
@TableName("product_settlement")
public class ProductSettlement extends Model<ProductSettlement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 产品记录
     */
    private Integer product;
    /**
     * 支付方式
     */
    @TableField("payment_method")
    private Integer paymentMethod;

    private Integer member;
    /**
     * 结算会员卡
     */
    @TableField("membership_card")
    private Integer membershipCard;
    /**
     * 支付金额
     */
    @TableField("payment_amount")
    private Double paymentAmount;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(Integer membershipCard) {
        this.membershipCard = membershipCard;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
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
        return "ProductSettlement{" +
        "id=" + id +
        ", product=" + product +
        ", paymentMethod=" + paymentMethod +
        ", membershipCard=" + membershipCard +
        ", paymentAmount=" + paymentAmount +
        ", status=" + status +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }
}
