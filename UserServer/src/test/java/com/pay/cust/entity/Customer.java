/**
 * @Title: Customer.java
 * @Package com.pay.cust.entity
 * @Description: TODO(商户基本信息表)
 * @author jianwen.zhu
 * @date 2016年11月9日 上午10:49:30
 *  V1.0
 */
package com.pay.cust.entity;

import java.util.Date;







import com.pay.common.entity.BaseMysqlEntity;


/**
 * @ClassName: Customer
 * @Description: TODO(商户基本信息表)
 * @author jianwen.zhu
 * @date 2016年11月9日 上午10:49:30
 *
 */



public class Customer extends BaseMysqlEntity<Long>{

	private static final long serialVersionUID = 1232427668166267147L;

	//商户编号
	private String customerNo;

	//代理商编号
	private String agentNo;

	//商户简称
	private String shortName;

	//商户全称
	private String fullName;

	//商户状态
	private String status;

	//商户所在地
	private String organizationCode;

	//商户手机号
	private String phoneNo;

	//商户接收地址
	private String receiveAddress;

	//商户联系人
	private String linkman;

	//商户邮箱
	private String email;

	//商户类型
	private String customerType;

	//行业编码  银行根据此编码判断区分费率标准
	private String mcc;

	//商户银联mcc
	private String bankMcc;

	//商户开通时间
	private Date openTime;

	//商户银联现场注册码
	private String buRegisterNo;

	//流程Id
	private Long processInstanceId;

	//三要素验证结果
	private String realNameAuth;
	//区、县编码
	private String c_p_OrgCode;
	//原因
	private String reasonCode;

	//使用定制权限
	private String useCustomPermission;

	//是否固定通道
	private String isFixedChannel;




	
	public String getCustomerNo() {
	  return customerNo;
	}

	public void setCustomerNo(String customerNo) {
	  this.customerNo = customerNo;
	}
	
	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	
	public String getShortName() {
	  return shortName;
	}

	public void setShortName(String shortName) {
	  this.shortName = shortName;
	}

	
	public String getFullName() {
	  return fullName;
	}

	public void setFullName(String fullName) {
	  this.fullName = fullName;
	}

	
	public String getOrganizationCode() {
	  return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
	  this.organizationCode = organizationCode;
	}

	
	public String getPhoneNo() {
	  return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
	  this.phoneNo = phoneNo;
	}

	
	public String getReceiveAddress() {
	  return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
	  this.receiveAddress = receiveAddress;
	}

	
	public String getLinkman() {
	  return linkman;
	}

	public void setLinkman(String linkman) {
	  this.linkman = linkman;
	}

	
	public String getEmail() {
	  return email;
	}

	public void setEmail(String email) {
	  this.email = email;
	}

	
	public String getBankMcc() {
	  return bankMcc;
	}

	public void setBankMcc(String bankMcc) {
	  this.bankMcc = bankMcc;
	}

	
	
	public Date getOpenTime() {
	  return openTime;
	}

	public void setOpenTime(Date openTime) {
	  this.openTime = openTime;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	
	public String getBuRegisterNo() {
		return buRegisterNo;
	}

	public void setBuRegisterNo(String buRegisterNo) {
		this.buRegisterNo = buRegisterNo;
	}

	
	public String getMcc() {
		return mcc;
	}

	
	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	
	public String getRealNameAuth() {
		return realNameAuth;
	}

	public void setRealNameAuth(String realNameAuth) {
		this.realNameAuth = realNameAuth;
	}

	
	public String getC_p_OrgCode() {
		return c_p_OrgCode;
	}

	public void setC_p_OrgCode(String c_p_OrgCode) {
		this.c_p_OrgCode = c_p_OrgCode;
	}

	
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	
	public String getUseCustomPermission() {
		return useCustomPermission;
	}

	public void setUseCustomPermission(String useCustomPermission) {
		this.useCustomPermission = useCustomPermission;
	}

	
	public String getIsFixedChannel() {
		return isFixedChannel;
	}

	public void setIsFixedChannel(String isFixedChannel) {
		this.isFixedChannel = isFixedChannel;
	}


	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [customerNo=");
		builder.append(customerNo);
		builder.append(", agentNo=");
		builder.append(agentNo);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", organizationCode=");
		builder.append(organizationCode);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", receiveAddress=");
		builder.append(receiveAddress);
		builder.append(", linkman=");
		builder.append(linkman);
		builder.append(", email=");
		builder.append(email);
		builder.append(", customerType=");
		builder.append(customerType);
		builder.append(", mcc=");
		builder.append(mcc);
		builder.append(", bankMcc=");
		builder.append(bankMcc);
		builder.append(", openTime=");
		builder.append(openTime);
		builder.append(", buRegisterNo=");
		builder.append(buRegisterNo);
		builder.append(", processInstanceId=");
		builder.append(processInstanceId);
		builder.append(", realNameAuth=");
		builder.append(realNameAuth);
		builder.append(", c_p_OrgCode=");
		builder.append(c_p_OrgCode);
		builder.append(", reasonCode=");
		builder.append(reasonCode);
		builder.append(", useCustomPermission=");
		builder.append(useCustomPermission);
		builder.append(", isFixedChannel=");
		builder.append(isFixedChannel);
		builder.append("]");
		return builder.toString();
	}







}
