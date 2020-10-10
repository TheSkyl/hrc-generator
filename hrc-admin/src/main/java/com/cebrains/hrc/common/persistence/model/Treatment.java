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
 * 康护记录
 * </p>
 *
 * @author frank123
 * @since 2018-05-11
 */
@TableName("treatment")
public class Treatment extends Model<Treatment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 康护用户
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 康护门店
     */
    private Integer department;
    /**
     * 康护技师
     */
    private Integer technician;
    /**
     * 康护过程描述
     */
    @TableField("process_description")
    private String processDescription;
    /**
     * 康护结果描述
     */
    @TableField("result_description")
    private String resultDescription;
    /**
     * 状态 0:默认 1:存档
     */
    private Integer status;
    /**
     * 预约
     */
    private Integer appointment;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    private Integer flag;

    /**
     * 非存储字段
     */
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String departmentName;
    @TableField(exist = false)
    private String technicianName;
    @TableField(exist = false)
    private String statusName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
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
        return "Treatment{" +
        "id=" + id +
        ", userId=" + userId +
        ", department=" + department +
        ", technician=" + technician +
        ", processDescription=" + processDescription +
        ", resultDescription=" + resultDescription +
        ", status=" + status +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public Integer getAppointment() {
        return appointment;
    }

    public void setAppointment(Integer appointment) {
        this.appointment = appointment;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
