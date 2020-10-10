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
 * 申请执行详情
 * </p>
 *
 * @author frank123
 * @since 2018-07-18
 */
@TableName("special_application_exec_detail")
public class SpecialApplicationExecDetail extends Model<SpecialApplicationExecDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 特殊申请执行
     */
    @TableField("special_application_exec")
    private Integer specialApplicationExec;
    /**
     * 专家
     */
    private Integer expert;
    @TableField("create_time")
    private Date createTime;
    private Integer flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecialApplicationExec() {
        return specialApplicationExec;
    }

    public void setSpecialApplicationExec(Integer specialApplicationExec) {
        this.specialApplicationExec = specialApplicationExec;
    }

    public Integer getExpert() {
        return expert;
    }

    public void setExpert(Integer expert) {
        this.expert = expert;
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
        return "SpecialApplicationExecDetail{" +
        "id=" + id +
        ", specialApplicationExec=" + specialApplicationExec +
        ", expert=" + expert +
        ", createTime=" + createTime +
        ", flag=" + flag +
        "}";
    }
}
