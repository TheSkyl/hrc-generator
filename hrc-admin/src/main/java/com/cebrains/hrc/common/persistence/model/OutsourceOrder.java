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
 * 
 * </p>
 *
 * @author frank123
 * @since 2018-07-22
 */
@TableName("outsource_order")
public class OutsourceOrder extends Model<OutsourceOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 供应商
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 供应商
     */
    private Integer supplier;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 收货人电话
     */
    @TableField("receiver_phone")
    private String receiverPhone;
    /**
     * 收货人地址
     */
    @TableField("receiver_address")
    private String receiverAddress;
    /**
     * 采购说明
     */
    @TableField("purchase_memo")
    private String purchaseMemo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 创建人
     */
    @TableField("created_by")
    private Integer createdBy;
    private Integer flag;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String supplierName;
    @TableField(exist = false)
    private String statusName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getPurchaseMemo() {
        return purchaseMemo;
    }

    public void setPurchaseMemo(String purchaseMemo) {
        this.purchaseMemo = purchaseMemo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
        return "OutsourceOrder{" +
        "id=" + id +
        ", supplier=" + supplier +
        ", receiver=" + receiver +
        ", receiverPhone=" + receiverPhone +
        ", receiverAddress=" + receiverAddress +
        ", purchaseMemo=" + purchaseMemo +
        ", remark=" + remark +
        ", status=" + status +
        ", createdTime=" + createdTime +
        ", createdBy=" + createdBy +
        ", flag=" + flag +
        "}";
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
