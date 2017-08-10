package com.pay.enterprise.dubbo;

import com.pay.cust.entity.EnterpriseCheckInfo;

/**
 * 企业资质校验借口， 校验企业信息方式：自动，人工
 * @author shiqing.liu
 *
 */
public interface EnterpriseQualificationAuditInterface {
	/**
	 * 具体 逻辑流程参照：《企业资质校验模块--- 自动审核.pdf》
	 * 以下是大致流程：
	 * 1.根据 customerNo 查询  EnterpriseInfo，EnterpriseCheckInfo，Customer，对三实体进行校验
	 * 2.根据 EnterpriseName, ID, regNo 查询 EnterpriseInfoResult 得到结果集列表
	 * 3.对 结果集进行过滤查找
	 * 4.对结果集 进行 企业名、 法人、 注册号 的比对更新更新的内容 : EnterpriseCheckInfo EnterpriseInfoResult
	 * 5.根据结果判断是否查询 QiXinBao XinLian
	 * @param customerNo : 商户编号， 必传.
	 */
	EnterpriseCheckInfo auditAutoAsync(String customerNo);
	
	/**
	 * 具体 逻辑流程参照：《企业资质校验模块--- 人工审核.pdf》
	 * 以下是大致流程：
	 * 1.根据 customerNo 查询  EnterpriseInfo，EnterpriseCheckInfo，Customer，对三实体进行校验
	 * 2.更新 CustomerBusinessLicense，EnterpriseInfoResult，EnterpriseInfoResult 
	 * 3.资质补充记录表:CustomerBusinessLicense 逻辑操作
	 * 4.EnterpriseName,Id, regNo, legalPerson 查询 EnterpriseInfoResult 得到结果集列表
	 * 5.对结果集 进行 过滤查找逻辑
	 * 5.根据结果判断是否查询 QiXinBao XinLian
	 * @param status : 人工审核的状态 Y - 成功 N - 失败 Y_ 信息不符
	 * @param customerNo : 商户编号
	 * @param mccKeyWord : 企业名称关键字 多个以“，” 隔开
	 */
	void auditManualAsync(String status, String customerNo, String mccKeyWord);
	
	 /**
     * 删除资质补充，重置企业结果
     * @param customerNo  商户编号
     * @param businessLicenseNo  
     * @param isDelCbl:是否更新 customer_business_license audit_status = null
     * @param isResetEcr 是否重置enterprise_info_result的商编为null
     */
	void resetEnterpriseInfoResultAndCustomerBusinessLicense(String customerNo, String businessLicenseNo, boolean isDelCbl, boolean  isSetEir); 
	

}
