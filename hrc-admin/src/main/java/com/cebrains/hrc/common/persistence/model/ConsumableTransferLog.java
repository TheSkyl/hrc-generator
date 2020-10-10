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
 * 耗材调拨记录
 * </p>
 *
 * @author frank123
 * @since 2018-10-30
 */
@TableName("consumable_transfer_log")
public class ConsumableTransferLog extends Model<ConsumableTransferLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 出库耗材
     */
    @TableField("src_id")
    private Integer srcId;
    /**
     * 入库耗材
     */
    @TableField("dest_id")
    private Integer destId;
    /**
     * 调拨数量
     */
    private Integer amount;
    /**
     * 出库门店
     */
    @TableField("src_department")
    private Integer srcDepartment;
    /**
     * 入库门店
     */
    @TableField("dest_department")
    private Integer destDepartment;
    /**
     * 操作人
     */
    @TableField("created_by")
    private Integer createdBy;
    @TableField("create_time")
    private Date createTime;
    private Integer flag;

    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String srcConsumableName;
    @TableField(exist = false)
    private String destConsumableName;
    @TableField(exist = false)
    private String srcDepartmentName;
    @TableField(exist = false)
    private String destDepartmentName;
    @TableField(exist = false)
    private String createdByName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSrcDepartment() {
        return srcDepartment;
    }

    public void setSrcDepartment(Integer srcDepartment) {
        this.srcDepartment = srcDepartment;
    }

    public Integer getDestDepartment() {
        return destDepartment;
    }

    public void setDestDepartment(Integer destDepartment) {
        this.destDepartment = destDepartment;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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

    public String getSrcConsumableName() {
        return srcConsumableName;
    }

    public void setSrcConsumableName(String srcConsumableName) {
        this.srcConsumableName = srcConsumableName;
    }

    public String getDestConsumableName() {
        return destConsumableName;
    }

    public void setDestConsumableName(String destConsumableName) {
        this.destConsumableName = destConsumableName;
    }

    public String getSrcDepartmentName() {
        return srcDepartmentName;
    }

    public void setSrcDepartmentName(String srcDepartmentName) {
        this.srcDepartmentName = srcDepartmentName;
    }

    public String getDestDepartmentName() {
        return destDepartmentName;
    }

    public void setDestDepartmentName(String destDepartmentName) {
        this.destDepartmentName = destDepartmentName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    @Override
    public String toString() {
        return "ConsumableTransferLog{" +
        "id=" + id +
        ", srcId=" + srcId +
        ", destId=" + destId +
        ", amount=" + amount +
        ", srcDepartment=" + srcDepartment +
        ", destDepartment=" + destDepartment +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }
}
