package com.lefu.user.entity;

import java.io.Serializable;
import java.util.Date;

public class Operator implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 乐观锁
     */
    private Integer optimistic;

    /**
     * 操作员的登录名称
     */
    private String username;

    /**
     *  所属对象的编号
     */
    private String ownerNo;

    /**
     * 所属类型
     */
    private String ownerType;

    /**
     * 操作员类型
     */
    private String operatorType;

    /**
     * 操作员的登录密码
     */
    private String password;

    /**
     * 状态
     */
    private String status;

    /**
     * 当前登录失败次数
     */
    private Integer maxErrorTimes;

    /**
     * 登录失败的时间
     */
    private Date lastLoginErrTime;

    /**
     * 密码修改间隔（单位：天）
     */
    private Integer modifyPasswdCycle;

    /**
     * 密码最后修改时间
     */
    private Date passwdModifyTime;

    /**
     * 登录许可起始时间(每天的登录时间段)
     */
    private String allowBeginTime;

    /**
     * 登录许可截止时间(每天的登录时间段)
     */
    private String allowEndTime;

    /**
     * 操作员真实姓名
     */
    private String realname;

    /**
     * 创建人
     */
    private String createOperator;

    /**
     * 重复登录标志
     */
    private String reloginFlag;

    /**
     * 密码生效时间
     */
    private Date passwdEffectTime;

    /**
     * 密码到期时间
     */
    private Date passwdExpireTime;

    /**
     * 同时登录的数量
     */
    private Integer tryTimes;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * public_password
     */
    private String publicPassword;

    /**
     * 短信是否发送
     */
    private String isSend;

    /**
     * 最后发送短信时间
     */
    private Date lastSendTime;

    /**
     * 手机密码
     */
    private String phonePwd;

    /**
     * 保留信息
     */
    private String reservedInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    /**
     * operator
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 乐观锁
     * @return optimistic 乐观锁
     */
    public Integer getOptimistic() {
        return optimistic;
    }

    /**
     * 乐观锁
     * @param optimistic 乐观锁
     */
    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    /**
     * 操作员的登录名称
     * @return username 操作员的登录名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 操作员的登录名称
     * @param username 操作员的登录名称
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     *  所属对象的编号
     * @return owner_no  所属对象的编号
     */
    public String getOwnerNo() {
        return ownerNo;
    }

    /**
     *  所属对象的编号
     * @param ownerNo  所属对象的编号
     */
    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo == null ? null : ownerNo.trim();
    }

    /**
     * 所属类型
     * @return owner_type 所属类型
     */
    public String getOwnerType() {
        return ownerType;
    }

    /**
     * 所属类型
     * @param ownerType 所属类型
     */
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType == null ? null : ownerType.trim();
    }

    /**
     * 操作员类型
     * @return operator_type 操作员类型
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * 操作员类型
     * @param operatorType 操作员类型
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }

    /**
     * 操作员的登录密码
     * @return password 操作员的登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 操作员的登录密码
     * @param password 操作员的登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 当前登录失败次数
     * @return max_error_times 当前登录失败次数
     */
    public Integer getMaxErrorTimes() {
        return maxErrorTimes;
    }

    /**
     * 当前登录失败次数
     * @param maxErrorTimes 当前登录失败次数
     */
    public void setMaxErrorTimes(Integer maxErrorTimes) {
        this.maxErrorTimes = maxErrorTimes;
    }

    /**
     * 登录失败的时间
     * @return last_login_err_time 登录失败的时间
     */
    public Date getLastLoginErrTime() {
        return lastLoginErrTime;
    }

    /**
     * 登录失败的时间
     * @param lastLoginErrTime 登录失败的时间
     */
    public void setLastLoginErrTime(Date lastLoginErrTime) {
        this.lastLoginErrTime = lastLoginErrTime;
    }

    /**
     * 密码修改间隔（单位：天）
     * @return modify_passwd_cycle 密码修改间隔（单位：天）
     */
    public Integer getModifyPasswdCycle() {
        return modifyPasswdCycle;
    }

    /**
     * 密码修改间隔（单位：天）
     * @param modifyPasswdCycle 密码修改间隔（单位：天）
     */
    public void setModifyPasswdCycle(Integer modifyPasswdCycle) {
        this.modifyPasswdCycle = modifyPasswdCycle;
    }

    /**
     * 密码最后修改时间
     * @return passwd_modify_time 密码最后修改时间
     */
    public Date getPasswdModifyTime() {
        return passwdModifyTime;
    }

    /**
     * 密码最后修改时间
     * @param passwdModifyTime 密码最后修改时间
     */
    public void setPasswdModifyTime(Date passwdModifyTime) {
        this.passwdModifyTime = passwdModifyTime;
    }

    /**
     * 登录许可起始时间(每天的登录时间段)
     * @return allow_begin_time 登录许可起始时间(每天的登录时间段)
     */
    public String getAllowBeginTime() {
        return allowBeginTime;
    }

    /**
     * 登录许可起始时间(每天的登录时间段)
     * @param allowBeginTime 登录许可起始时间(每天的登录时间段)
     */
    public void setAllowBeginTime(String allowBeginTime) {
        this.allowBeginTime = allowBeginTime == null ? null : allowBeginTime.trim();
    }

    /**
     * 登录许可截止时间(每天的登录时间段)
     * @return allow_end_time 登录许可截止时间(每天的登录时间段)
     */
    public String getAllowEndTime() {
        return allowEndTime;
    }

    /**
     * 登录许可截止时间(每天的登录时间段)
     * @param allowEndTime 登录许可截止时间(每天的登录时间段)
     */
    public void setAllowEndTime(String allowEndTime) {
        this.allowEndTime = allowEndTime == null ? null : allowEndTime.trim();
    }

    /**
     * 操作员真实姓名
     * @return realname 操作员真实姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 操作员真实姓名
     * @param realname 操作员真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 创建人
     * @return create_operator 创建人
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * 创建人
     * @param createOperator 创建人
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * 重复登录标志
     * @return relogin_flag 重复登录标志
     */
    public String getReloginFlag() {
        return reloginFlag;
    }

    /**
     * 重复登录标志
     * @param reloginFlag 重复登录标志
     */
    public void setReloginFlag(String reloginFlag) {
        this.reloginFlag = reloginFlag == null ? null : reloginFlag.trim();
    }

    /**
     * 密码生效时间
     * @return passwd_effect_time 密码生效时间
     */
    public Date getPasswdEffectTime() {
        return passwdEffectTime;
    }

    /**
     * 密码生效时间
     * @param passwdEffectTime 密码生效时间
     */
    public void setPasswdEffectTime(Date passwdEffectTime) {
        this.passwdEffectTime = passwdEffectTime;
    }

    /**
     * 密码到期时间
     * @return passwd_expire_time 密码到期时间
     */
    public Date getPasswdExpireTime() {
        return passwdExpireTime;
    }

    /**
     * 密码到期时间
     * @param passwdExpireTime 密码到期时间
     */
    public void setPasswdExpireTime(Date passwdExpireTime) {
        this.passwdExpireTime = passwdExpireTime;
    }

    /**
     * 同时登录的数量
     * @return try_times 同时登录的数量
     */
    public Integer getTryTimes() {
        return tryTimes;
    }

    /**
     * 同时登录的数量
     * @param tryTimes 同时登录的数量
     */
    public void setTryTimes(Integer tryTimes) {
        this.tryTimes = tryTimes;
    }

    /**
     * 机构编码
     * @return org_code 机构编码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构编码
     * @param orgCode 机构编码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * public_password
     * @return public_password public_password
     */
    public String getPublicPassword() {
        return publicPassword;
    }

    /**
     * public_password
     * @param publicPassword public_password
     */
    public void setPublicPassword(String publicPassword) {
        this.publicPassword = publicPassword == null ? null : publicPassword.trim();
    }

    /**
     * 短信是否发送
     * @return is_send 短信是否发送
     */
    public String getIsSend() {
        return isSend;
    }

    /**
     * 短信是否发送
     * @param isSend 短信是否发送
     */
    public void setIsSend(String isSend) {
        this.isSend = isSend == null ? null : isSend.trim();
    }

    /**
     * 最后发送短信时间
     * @return last_send_time 最后发送短信时间
     */
    public Date getLastSendTime() {
        return lastSendTime;
    }

    /**
     * 最后发送短信时间
     * @param lastSendTime 最后发送短信时间
     */
    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    /**
     * 手机密码
     * @return phone_pwd 手机密码
     */
    public String getPhonePwd() {
        return phonePwd;
    }

    /**
     * 手机密码
     * @param phonePwd 手机密码
     */
    public void setPhonePwd(String phonePwd) {
        this.phonePwd = phonePwd == null ? null : phonePwd.trim();
    }

    /**
     * 保留信息
     * @return reserved_info 保留信息
     */
    public String getReservedInfo() {
        return reservedInfo;
    }

    /**
     * 保留信息
     * @param reservedInfo 保留信息
     */
    public void setReservedInfo(String reservedInfo) {
        this.reservedInfo = reservedInfo == null ? null : reservedInfo.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后更新时间
     * @return last_update_time 最后更新时间
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 最后更新时间
     * @param lastUpdateTime 最后更新时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Operator other = (Operator) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOptimistic() == null ? other.getOptimistic() == null : this.getOptimistic().equals(other.getOptimistic()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getOwnerNo() == null ? other.getOwnerNo() == null : this.getOwnerNo().equals(other.getOwnerNo()))
            && (this.getOwnerType() == null ? other.getOwnerType() == null : this.getOwnerType().equals(other.getOwnerType()))
            && (this.getOperatorType() == null ? other.getOperatorType() == null : this.getOperatorType().equals(other.getOperatorType()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMaxErrorTimes() == null ? other.getMaxErrorTimes() == null : this.getMaxErrorTimes().equals(other.getMaxErrorTimes()))
            && (this.getLastLoginErrTime() == null ? other.getLastLoginErrTime() == null : this.getLastLoginErrTime().equals(other.getLastLoginErrTime()))
            && (this.getModifyPasswdCycle() == null ? other.getModifyPasswdCycle() == null : this.getModifyPasswdCycle().equals(other.getModifyPasswdCycle()))
            && (this.getPasswdModifyTime() == null ? other.getPasswdModifyTime() == null : this.getPasswdModifyTime().equals(other.getPasswdModifyTime()))
            && (this.getAllowBeginTime() == null ? other.getAllowBeginTime() == null : this.getAllowBeginTime().equals(other.getAllowBeginTime()))
            && (this.getAllowEndTime() == null ? other.getAllowEndTime() == null : this.getAllowEndTime().equals(other.getAllowEndTime()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getCreateOperator() == null ? other.getCreateOperator() == null : this.getCreateOperator().equals(other.getCreateOperator()))
            && (this.getReloginFlag() == null ? other.getReloginFlag() == null : this.getReloginFlag().equals(other.getReloginFlag()))
            && (this.getPasswdEffectTime() == null ? other.getPasswdEffectTime() == null : this.getPasswdEffectTime().equals(other.getPasswdEffectTime()))
            && (this.getPasswdExpireTime() == null ? other.getPasswdExpireTime() == null : this.getPasswdExpireTime().equals(other.getPasswdExpireTime()))
            && (this.getTryTimes() == null ? other.getTryTimes() == null : this.getTryTimes().equals(other.getTryTimes()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getPublicPassword() == null ? other.getPublicPassword() == null : this.getPublicPassword().equals(other.getPublicPassword()))
            && (this.getIsSend() == null ? other.getIsSend() == null : this.getIsSend().equals(other.getIsSend()))
            && (this.getLastSendTime() == null ? other.getLastSendTime() == null : this.getLastSendTime().equals(other.getLastSendTime()))
            && (this.getPhonePwd() == null ? other.getPhonePwd() == null : this.getPhonePwd().equals(other.getPhonePwd()))
            && (this.getReservedInfo() == null ? other.getReservedInfo() == null : this.getReservedInfo().equals(other.getReservedInfo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOptimistic() == null) ? 0 : getOptimistic().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getOwnerNo() == null) ? 0 : getOwnerNo().hashCode());
        result = prime * result + ((getOwnerType() == null) ? 0 : getOwnerType().hashCode());
        result = prime * result + ((getOperatorType() == null) ? 0 : getOperatorType().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMaxErrorTimes() == null) ? 0 : getMaxErrorTimes().hashCode());
        result = prime * result + ((getLastLoginErrTime() == null) ? 0 : getLastLoginErrTime().hashCode());
        result = prime * result + ((getModifyPasswdCycle() == null) ? 0 : getModifyPasswdCycle().hashCode());
        result = prime * result + ((getPasswdModifyTime() == null) ? 0 : getPasswdModifyTime().hashCode());
        result = prime * result + ((getAllowBeginTime() == null) ? 0 : getAllowBeginTime().hashCode());
        result = prime * result + ((getAllowEndTime() == null) ? 0 : getAllowEndTime().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getCreateOperator() == null) ? 0 : getCreateOperator().hashCode());
        result = prime * result + ((getReloginFlag() == null) ? 0 : getReloginFlag().hashCode());
        result = prime * result + ((getPasswdEffectTime() == null) ? 0 : getPasswdEffectTime().hashCode());
        result = prime * result + ((getPasswdExpireTime() == null) ? 0 : getPasswdExpireTime().hashCode());
        result = prime * result + ((getTryTimes() == null) ? 0 : getTryTimes().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getPublicPassword() == null) ? 0 : getPublicPassword().hashCode());
        result = prime * result + ((getIsSend() == null) ? 0 : getIsSend().hashCode());
        result = prime * result + ((getLastSendTime() == null) ? 0 : getLastSendTime().hashCode());
        result = prime * result + ((getPhonePwd() == null) ? 0 : getPhonePwd().hashCode());
        result = prime * result + ((getReservedInfo() == null) ? 0 : getReservedInfo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", optimistic=").append(optimistic);
        sb.append(", username=").append(username);
        sb.append(", ownerNo=").append(ownerNo);
        sb.append(", ownerType=").append(ownerType);
        sb.append(", operatorType=").append(operatorType);
        sb.append(", password=").append(password);
        sb.append(", status=").append(status);
        sb.append(", maxErrorTimes=").append(maxErrorTimes);
        sb.append(", lastLoginErrTime=").append(lastLoginErrTime);
        sb.append(", modifyPasswdCycle=").append(modifyPasswdCycle);
        sb.append(", passwdModifyTime=").append(passwdModifyTime);
        sb.append(", allowBeginTime=").append(allowBeginTime);
        sb.append(", allowEndTime=").append(allowEndTime);
        sb.append(", realname=").append(realname);
        sb.append(", createOperator=").append(createOperator);
        sb.append(", reloginFlag=").append(reloginFlag);
        sb.append(", passwdEffectTime=").append(passwdEffectTime);
        sb.append(", passwdExpireTime=").append(passwdExpireTime);
        sb.append(", tryTimes=").append(tryTimes);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", publicPassword=").append(publicPassword);
        sb.append(", isSend=").append(isSend);
        sb.append(", lastSendTime=").append(lastSendTime);
        sb.append(", phonePwd=").append(phonePwd);
        sb.append(", reservedInfo=").append(reservedInfo);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}