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
 * 康护关联
 * </p>
 *
 * @author frank123
 * @since 2018-05-14
 */
@TableName("treatment_detail")
public class TreatmentDetail extends Model<TreatmentDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 康护记录
     */
    @TableField("treatment_id")
    private Integer treatmentId;
    /**
     * 结算记录
     */
    @TableField("settlement")
    private Integer settlement;
    /**
     * 康护项目
     */
    @TableField("project_id")
    private Integer projectId;
    /**
     * 耗材
     */
    @TableField("consumable_id")
    private Integer consumableId;
    /**
     * 录入时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 耗材使用数量
     */
    @TableField("consumable_amount")
    private Integer consumableAmount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getConsumableId() {
        return consumableId;
    }

    public void setConsumableId(Integer consumableId) {
        this.consumableId = consumableId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getConsumableAmount() {
        return consumableAmount;
    }

    public void setConsumableAmount(Integer consumableAmount) {
        this.consumableAmount = consumableAmount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TreatmentDetail{" +
        "id=" + id +
        ", treatmentId=" + treatmentId +
        ", projectId=" + projectId +
        ", consumableId=" + consumableId +
        ", createTime=" + createTime +
        ", consumableAmount=" + consumableAmount +
        "}";
    }

    public Integer getSettlement() {
        return settlement;
    }

    public void setSettlement(Integer settlement) {
        this.settlement = settlement;
    }
}
