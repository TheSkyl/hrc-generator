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
 * 设备管理
 * </p>
 *
 * @author frank123
 * @since 2018-05-10
 */
@TableName("device")
public class Device extends Model<Device> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 设备编号
     */
    @TableField("device_number")
    private String deviceNumber;
    /**
     * 设备名称
     */
    @TableField("device_name")
    private String deviceName;
    /**
     * 设备单价
     */
    private Double price;
    /**
     * 设备数量
     */
    private Integer amount;
    /**
     * 设备状态:0正常1异常
     */
    private Integer status;
    /**
     * 采购日期
     */
    @TableField("purchase_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseTime;
    /**
     * 所属门店
     */
    private Integer department;
    /**
     * 维护人
     */
    private Integer maintainer;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 标记
     */
    private Integer flag;

    /**
     * 非存储字段
     * @return
     */
    @TableField(exist = false)
    private String statusName;
    @TableField(exist = false)
    private String departmentName;
    @TableField(exist = false)
    private String maintainerName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(Integer maintainer) {
        this.maintainer = maintainer;
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
        return "Device{" +
        "id=" + id +
        ", deviceNumber=" + deviceNumber +
        ", deviceName=" + deviceName +
        ", price=" + price +
        ", amount=" + amount +
        ", status=" + status +
        ", purchaseTime=" + purchaseTime +
        ", department=" + department +
        ", maintainer=" + maintainer +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    public String getMaintainerName() {
        return maintainerName;
    }
}
