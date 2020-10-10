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
 * 外协订单接单发货
 * </p>
 *
 * @author frank123
 * @since 2018-07-23
 */
@TableName("outsource_order_delivery")
public class OutsourceOrderDelivery extends Model<OutsourceOrderDelivery> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 外协订单
     */
    @TableField("outsource_order")
    private Integer outsourceOrder;
    /**
     * 发货物品说明
     */
    @TableField("shipping_instructions")
    private String shippingInstructions;
    /**
     * 发货时间
     */
    @TableField("delivery_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTime;
    /**
     * 配送信息
     */
    @TableField("shipping_information")
    private String shippingInformation;
    /**
     * 配送单号
     */
    @TableField("shipping_order_number")
    private String shippingOrderNumber;
    /**
     * 产品价格
     */
    @TableField("medicine_price")
    private Double medicinePrice;
    /**
     * 运费价格
     */
    @TableField("shipping_price")
    private Double shippingPrice;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 制单人
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

    public String getShippingInstructions() {
        return shippingInstructions;
    }

    public void setShippingInstructions(String shippingInstructions) {
        this.shippingInstructions = shippingInstructions;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getShippingInformation() {
        return shippingInformation;
    }

    public void setShippingInformation(String shippingInformation) {
        this.shippingInformation = shippingInformation;
    }

    public String getShippingOrderNumber() {
        return shippingOrderNumber;
    }

    public void setShippingOrderNumber(String shippingOrderNumber) {
        this.shippingOrderNumber = shippingOrderNumber;
    }

    public Double getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public Double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Double shippingPrice) {
        this.shippingPrice = shippingPrice;
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
        return "OutsourceOrderDelivery{" +
        "id=" + id +
        ", outsourceOrder=" + outsourceOrder +
        ", shippingInstructions=" + shippingInstructions +
        ", deliveryTime=" + deliveryTime +
        ", shippingInformation=" + shippingInformation +
        ", shippingOrderNumber=" + shippingOrderNumber +
        ", medicinePrice=" + medicinePrice +
        ", shippingPrice=" + shippingPrice +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createdBy=" + createdBy +
        ", flag=" + flag +
        "}";
    }
}
