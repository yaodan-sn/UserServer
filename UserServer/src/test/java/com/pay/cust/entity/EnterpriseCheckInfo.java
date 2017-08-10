package com.pay.cust.entity;






import com.pay.common.entity.BaseMysqlEntity;

/**
 * 商户企业信息验证
 * @author zikun.liu
 * 2016-11-10 11:54:22
 */





public class EnterpriseCheckInfo extends BaseMysqlEntity<Long>{

	private static final long serialVersionUID = 1232427668166267147L;

	//状态：y成功，n未查询到，y_信息不符
	private String status;

	//营业执照编号
	private String businessLicenseNo;

	//企业名称
	private String name;

	//企业法人
	private String legalPerson;

	//代理商编码
	private String agentNo;

	//营业范围
	private String businessScope;

	//公司类型
	private String companyType;

	//注册资本
	private String registeredCapital;

	//营业期限
	private String validityDate;

	//成立日期
	private String creationDate;

	//发照日期
	private String grantDate;

	//登记机关
	private String registrationAuthority;

	//经营状态
	private String manageState;

	//商户编码
	private String customerNo;

	//添加类型
	private String addType;

	//卡类型转换规则
	private String remark;

	//身份证真实性  ‘y’,‘n’
	private String statusIdcard;

	//营业执照 该身份证认证状态  ‘y’,‘n’
	private String idcardBusslicStatus;

	//身份证、银行卡号实名认证状态  ‘y’,‘n’
	private String idcardSettlecardStatus;

	//原始编号
	private String originalLicenseNo;


	private String realyType; //Y1-入网全真,Y2-资质补充后全真,Y3-入网准真,Y4-资质补充准真

	
	public String getRealyType() {
		return realyType;
	}
	public void setRealyType(String realyType) {
		this.realyType = realyType;
	}

	
	public String getStatus() {
	  return status;
	}

	public void setStatus(String status) {
	  this.status = status;
	}

	
	public String getBusinessLicenseNo() {
	  return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
	  this.businessLicenseNo = businessLicenseNo;
	}

	
	public String getName() {
	  return name;
	}

	public void setName(String name) {
	  this.name = name;
	}

	
	public String getLegalPerson() {
	  return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
	  this.legalPerson = legalPerson;
	}

	
	public String getAgentNo() {
	  return agentNo;
	}

	public void setAgentNo(String agentNo) {
	  this.agentNo = agentNo;
	}

	
	public String getBusinessScope() {
	  return businessScope;
	}

	public void setBusinessScope(String businessScope) {
	  this.businessScope = businessScope;
	}

	
	public String getCompanyType() {
	  return companyType;
	}

	public void setCompanyType(String companyType) {
	  this.companyType = companyType;
	}

	
	public String getRegisteredCapital() {
	  return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
	  this.registeredCapital = registeredCapital;
	}

	
	public String getValidityDate() {
	  return validityDate;
	}

	public void setValidityDate(String validityDate) {
	  this.validityDate = validityDate;
	}

	
	public String getCreationDate() {
	  return creationDate;
	}

	public void setCreationDate(String creationDate) {
	  this.creationDate = creationDate;
	}

	
	public String getGrantDate() {
	  return grantDate;
	}

	public void setGrantDate(String grantDate) {
	  this.grantDate = grantDate;
	}

	
	public String getRegistrationAuthority() {
	  return registrationAuthority;
	}

	public void setRegistrationAuthority(String registrationAuthority) {
	  this.registrationAuthority = registrationAuthority;
	}

	
	public String getManageState() {
	  return manageState;
	}

	public void setManageState(String manageState) {
	  this.manageState = manageState;
	}

	
	public String getCustomerNo() {
	  return customerNo;
	}

	public void setCustomerNo(String customerNo) {
	  this.customerNo = customerNo;
	}

	
	public String getAddType() {
	  return addType;
	}

	public void setAddType(String addType) {
	  this.addType = addType;
	}

	
	public String getRemark() {
	  return remark;
	}

	public void setRemark(String remark) {
	  this.remark = remark;
	}

	
	public String getStatusIdcard() {
	  return statusIdcard;
	}

	public void setStatusIdcard(String statusIdcard) {
	  this.statusIdcard = statusIdcard;
	}

	
	public String getIdcardBusslicStatus() {
	  return idcardBusslicStatus;
	}

	public void setIdcardBusslicStatus(String idcardBusslicStatus) {
	  this.idcardBusslicStatus = idcardBusslicStatus;
	}

	
	public String getIdcardSettlecardStatus() {
	  return idcardSettlecardStatus;
	}

	public void setIdcardSettlecardStatus(String idcardSettlecardStatus) {
	  this.idcardSettlecardStatus = idcardSettlecardStatus;
	}

	
	public String getOriginalLicenseNo() {
	  return originalLicenseNo;
	}

	public void setOriginalLicenseNo(String originalLicenseNo) {
	  this.originalLicenseNo = originalLicenseNo;
	}
	
	
	public String toString() {
		return "EnterpriseCheckInfo [status=" + status + ", businessLicenseNo=" + businessLicenseNo + ", name=" + name
				+ ", legalPerson=" + legalPerson + ", agentNo=" + agentNo + ", businessScope=" + businessScope
				+ ", companyType=" + companyType + ", registeredCapital=" + registeredCapital + ", validityDate="
				+ validityDate + ", creationDate=" + creationDate + ", grantDate=" + grantDate
				+ ", registrationAuthority=" + registrationAuthority + ", manageState=" + manageState + ", customerNo="
				+ customerNo + ", addType=" + addType + ", remark=" + remark + ", statusIdcard=" + statusIdcard
				+ ", idcardBusslicStatus=" + idcardBusslicStatus + ", idcardSettlecardStatus=" + idcardSettlecardStatus
				+ ", originalLicenseNo=" + originalLicenseNo + ", realyType=" + realyType + ", id=" + id
				+ ", createTime=" + createTime + ", optimistic=" + optimistic + ", lastUpdateTime=" + lastUpdateTime
				+ "]";
	}





}