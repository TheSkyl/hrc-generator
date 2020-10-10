package com.cebrains.hrc.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 耗材管理
 * </p>
 *
 * @author frank123
 * @since 2018-05-10
 */
@TableName("consumable")
public class Consumable extends Model<Consumable> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 耗材名称
     */
    private String name;
    /**
     * 耗材编号
     */
    private String number;
    /**
     * 规格型号
     */
    private String specification;
    /**
     * 耗材单价
     */
    private Double price;
    /**
     * 计量单位
     */
    @TableField("measure_unit")
    private String measureUnit;
    /**
     * 生产企业
     */
    private String manufacturer;
    /**
     * 所属门店
     */
    private Integer department;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 非存储字段
     * @return
     */
    @TableField(exist = false)
    private String departmentName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Consumable{" +
        "id=" + id +
        ", name=" + name +
        ", number=" + number +
        ", specification=" + specification +
        ", price=" + price +
        ", measureUnit=" + measureUnit +
        ", manufacturer=" + manufacturer +
        ", department=" + department +
        ", amount=" + amount +
        ", createTime=" + createTime +
        "}";
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
