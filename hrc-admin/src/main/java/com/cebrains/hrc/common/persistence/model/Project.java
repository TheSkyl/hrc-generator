package com.cebrains.hrc.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author frank123
 * @since 2018-03-06
 */
@TableName("project")
public class Project extends Model<Project> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目类别
     */
    private Integer category;
    /**
     * 疗程次数
     */
    @TableField("treatment_course")
    private Integer treatmentCourse;
    /**
     * 一线疗程价格
     */
    @TableField("price_treatment_1")
    private BigDecimal priceTreatment1;
    /**
     * 二线疗程价格
     */
    @TableField("price_treatment_2")
    private BigDecimal priceTreatment2;
    /**
     * 三线疗程价格
     */
    @TableField("price_treatment_3")
    private BigDecimal priceTreatment3;
    /**
     * 一线单次价格
     */
    @TableField("price_once_1")
    private BigDecimal priceOnce1;
    /**
     * 二线单次价格
     */
    @TableField("price_once_2")
    private BigDecimal priceOnce2;
    /**
     * 三线单次价格
     */
    @TableField("price_once_3")
    private BigDecimal priceOnce3;

    /**
     * 疗效方案
     */
    private String solution;
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
     * 显示字段
     * @return
     */
    @TableField(exist = false)
    private String projectCategory;


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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getTreatmentCourse() {
        return treatmentCourse;
    }

    public void setTreatmentCourse(Integer treatmentCourse) {
        this.treatmentCourse = treatmentCourse;
    }

    public BigDecimal getPriceTreatment1() {
        return priceTreatment1;
    }

    public void setPriceTreatment1(BigDecimal priceTreatment1) {
        this.priceTreatment1 = priceTreatment1;
    }

    public BigDecimal getPriceTreatment2() {
        return priceTreatment2;
    }

    public void setPriceTreatment2(BigDecimal priceTreatment2) {
        this.priceTreatment2 = priceTreatment2;
    }

    public BigDecimal getPriceTreatment3() {
        return priceTreatment3;
    }

    public void setPriceTreatment3(BigDecimal priceTreatment3) {
        this.priceTreatment3 = priceTreatment3;
    }

    public BigDecimal getPriceOnce1() {
        return priceOnce1;
    }

    public void setPriceOnce1(BigDecimal priceOnce1) {
        this.priceOnce1 = priceOnce1;
    }

    public BigDecimal getPriceOnce2() {
        return priceOnce2;
    }

    public void setPriceOnce2(BigDecimal priceOnce2) {
        this.priceOnce2 = priceOnce2;
    }

    public BigDecimal getPriceOnce3() {
        return priceOnce3;
    }

    public void setPriceOnce3(BigDecimal priceOnce3) {
        this.priceOnce3 = priceOnce3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
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
        return "Project{" +
        "id=" + id +
        ", name=" + name +
        ", category=" + category +
        ", treatmentCourse=" + treatmentCourse +
        ", priceTreatment1=" + priceTreatment1 +
        ", priceTreatment2=" + priceTreatment2 +
        ", priceTreatment3=" + priceTreatment3 +
        ", priceOnce1=" + priceOnce1 +
        ", priceOnce2=" + priceOnce2 +
        ", priceOnce3=" + priceOnce3 +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }
}
