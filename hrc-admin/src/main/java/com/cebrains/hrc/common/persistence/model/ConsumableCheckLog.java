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
 * 盘库日志
 * </p>
 *
 * @author frank123
 * @since 2018-10-31
 */
@TableName("consumable_check_log")
public class ConsumableCheckLog extends Model<ConsumableCheckLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 耗材
     */
    private Integer consumable;
    /**
     * 增建数量
     */
    private Integer amount;
    /**
     * 盘库备注
     */
    private String memo;
    /**
     * 盘库时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 操作人
     */
    @TableField("created_by")
    private Integer createdBy;
    private Integer flag;
    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String consumableName;
    @TableField(exist = false)
    private String departmentName;
    @TableField(exist = false)
    private String createdByName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsumable() {
        return consumable;
    }

    public void setConsumable(Integer consumable) {
        this.consumable = consumable;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
        return "ConsumableCheckLog{" +
        "id=" + id +
        ", consumable=" + consumable +
        ", amount=" + amount +
        ", memo=" + memo +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", flag=" + flag +
        "}";
    }

    public void setConsumableName(String consumableName) {
        this.consumableName = consumableName;
    }

    public String getConsumableName() {
        return consumableName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedByName() {
        return createdByName;
    }
}
