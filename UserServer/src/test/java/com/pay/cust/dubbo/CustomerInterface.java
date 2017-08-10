package com.pay.cust.dubbo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pay.cust.entity.Customer;

/**
 * 商户接口
 * @Description: 商户接口
 * @see: CustomerInterface 此处填写需要参考的类
 * @version 2016年11月28日 上午10:40:12
 * @author zikun.liu
 */

public interface CustomerInterface {

	/**
	 * 更新POS商户信息
	 * 仅修改商户类型为POS的商户
	 * @param customer 				商户信息
	 * @param username 				修改人或修改来源
	 * @param modifyReason 			修改原因
	 * @param isChange 				 是否修改手机号 Y/N
	 * @return json字符串
	 * 		   json.get("resultCode")     返回"00"表示成功, "98"业务异常, "99"系统异常<br/>
	 * 		   json.get("resultMessage")  返回失败原因，返回查无商户信息表示找不到该编号的商户，返回查无此mcc信息表示找不到该商户的mcc
	 */
	public String updatePosCustomerStatus(Customer customer, String username, String modifyReason,String isChange);

	/**
	 * 根据商户编号获得商户信息
	 * @Description  		根据商户编号获得商户信息
	 * @param customerNo 	商户编号
	 * @return Customer 	商户信息
	 */

	public Customer findByCustomerNo(String customerNo);

	/**
	 * 根据商户编号获得商户信息
	 * @Description  		根据商户编号获得商户信息
	 * @param customerNo 	商户编号
	 * @return  json字符串
	 * 			json.get("Customer")   	        返回商户信息
	 * 			json.get("resultCode") 	        返回"00"表示成功, "99"或其他编码则表示失败
	 * 			json.get("resultMessage")  返回失败原因，返回查询商户信息失败表示没有找到该商户编号的商户信息
	 */

	public String  findByCustomerNoJson(String customerNo);

	/**
	 * 根据商户全名和状态获得商户集合
	 * @Description  		根据商户全名和状态获得商户集合
	 * @param fullName 		商户全名
	 * @param status 		商户状态（非必传）
	 * @return				商户信息集合
	 */

	public List<Customer> findByFullNameAndStatus(String fullName,String status);

	/**
	 * 根据代理商编号和状态获得商户集合
	 * @Description  		根据代理商编号和状态获得商户集合
	 * @param agentNo 		代理商编号
	 * @param status 		商户状态
	 * @return				商户编号集合
	 */

	public List<String> findByAgentNo(String agentNo,String status);

	/**
	 * 根据银联注册码查询商户
	 * @param buRegisterNo	银联注册码
	 * @return				商户信息，返回按银联编号查询商户信息失败表示执行查询方法时出现异常
	 * @author xihui.gao
	 */
	public Customer findByBuRegisterNo(String buRegisterNo);

	/**
	 * 以手机号码查询商户信息
	 * @param phoneNo		手机号码
	 * @return				商户信息集合
	 * @author xihui.gao
	 */
	public List<Customer> findByPhoneNo(String phoneNo);


	/**
	 * 以手机号码查询带有状态的商户信息
	 * @param phoneNo		手机号码
	 * @param inStatus		包含的状态，默认查询所有状态的商户
	 * @param notInStatus	不包含的状态，从所有状态中去除
	 * @return				商户信息集合，返回按电话号码查询商户信息失败表示执行方法时异常
	 * @author xihui.gao
	 */
	public List<Customer> findByPhoneNoWithStatus(String phoneNo,List<String> inStatus,List<String> notInStatus);

	/**
	 * 根据手机号和商户类型查询带有状态的商户信息
	 * @author dan.yao
	 * @param phoneNo 			手机号
	 * @param customerType  	商户类型
	 * @param inStatus 			包含的状态, 默认查询所有状态的商户
	 * @param notInStatus   	不包含的状态
	 * @return  json字符串
	 * 			json.get("customerList");商户信息List集合
	 * 			json.get("resultCode");  返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 			json.get("resultMessage");   返回失败原因
	 */
	String findByPhoneNoAndCustomerTypeAndStatus(String phoneNo, String customerType,
			List<String> inStatus, List<String> notInStatus);



	/**根据身份证号码、用户手机号查询商户信息
	 * @param customerIdentityNo	身份证号码
	 * @param customerTel			用户手机号码
	 ** @return json字符串
	 *			json.get("customerList");	商户信息List集合
	 * 			json.get("resultCode");  	返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 			json.get("resultMessage");   	返回失败原因
	 */
	public String  findCustomerNoByIdNoAndPhone(String customerIdentityNo, String customerTel);


	/**根据身份证号码、法人名称查询用户信息
	 * @param customerIdentityNo	用户身份证号码
	 * @param legalPerson			法人名称
	 * 		  json.get("customerList");	商户信息List集合
	 *    	  json.get("resultCode");  	返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		  json.get("resultMessage");   	返回失败原因
	 */
	public String findCustomerNoByIdNoAndLegalPerson(String customerIdentityNo, String legalPerson);

	/**根据用户编号查询MCC
	 * @param customerNo		商户编号
	 * 		  json.get("mccList");	        商户信息List集合
	 * 		  json.get("resultCode");  返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		  json.get("resultMessage");   返回失败原因
	 */
	public String findMccByCustomerNo(String customerNo);

	/**
	 * 商户审核更新商户状态
	 * @author dan.yao
	 * @param customer 						商户实体
	 * @param auditRejectReason 			审核拒绝原因
	 * @return json字符串
	 *         json.get("resultCode");  	返回"00"表示成功, "99"或其它编码表示失败
	 *         json.get("resultMessage");   返回失败原因
	 */
	String customerAudit(Customer customer, String auditRejectReason);


	/**
	 * 根据商户编号删除
	 * @param customerNo					商户编号
	 * @return json字符串
	 *         json.get("resultCode");  	返回"00"表示成功, "99"或其它编码表示失败
	 *         json.get("resultMessage");   返回失败原因
	 */
	public String delCustomer(String customerNo);
	/**
	 * 根据商户删除
	 * @param customer 						商户
	 * @return json字符串
	 *         json.get("resultCode");  	返回"00"表示成功, "99"或其它编码表示失败
	 *         json.get("resultMessage");   返回失败原因
	 */
	public String delCustomer(Customer customer);

	/**
	 * 修改商户pos数量
	 * @author dan.yao
	 * @param customerNo  			商户编号
	 * @param posCountApply 		增机台数
	 * @return json字符串
	 * 		json.get("resultCode") 	返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		json.get("resultMessage")  	返回失败原因
	 */
	String customerPosCountChange(String customerNo, Long posCountApply);

	/**
	 * 更新商户信息  保存到商户关键信息变更日志记录
	 * 修改商户表中 status shortName fullName mcc bankMcc linkMan phoneNo receiveAddress organizationCode
 	 * @author muya.cao
	 * @param customer 					商户
	 * @param username 					操作人
	 * @param modifyReason 				修改原因
	 * @param isChange 					是否修改手机号码
	 * @return json字符串
	 * 		json.get("resultCode") 		返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		json.get("resultMessage")   返回失败原因
	 */
	public String update(Customer customer, String username, String modifyReason,String isChange);

	/**
	 * 检查服务商业务范围
	 * @author weiyi.liu
	 * @param customer 					商户信息
	 * @return json字符串
	 * 		json.get("resultCode") 		返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		json.get("resultMessage")  		返回失败原因，返回商户所属服务商不存在表示找不到该编号的服务商，返回此商户不在您的业务授权区域表示
	 * 									地区码不在授权区域内
	 */
	public String checkAgentBusinessArea(Customer customer);


	/**根据身份证编号查询不为DELETE状态的用户
	 * @param customerIdentityNo	身份证编号
	 * @return json字符串
	 * 		   json.get("customerList");	商户信息List集合
	 * 		   json.get("resultCode");  	返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		   json.get("resultMessage");   	返回失败原因
	 */
	public String findCustomerNoByIdNoAndStatus(String customerIdentityNo);


	/**根据手机号查询不为DELETE状态的用户
	 * @param phone					手机号
	 * @return json字符串
	 * 		   json.get("customerList");	商户信息List集合
	 * 		   json.get("resultCode");  	返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		   json.get("resultMessage");   	返回失败原因
	 */
	public String findCustomerNoByPhoneAndStatus(String phone);


	/**根据手机号，创建时间，查询mcc为999开头，状态不为DELETE INIT 的用户
	 * @param phone				手机号
	 * @param createTime		创建时间
	 * @return json字符串
	 * 		   json.get("customerList");商户信息List集合
	 * 	       json.get("resultCode");  返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		   json.get("resultMessage");   返回失败原因
	 */
	public String findIshuaCustomerByCreateTime(String phone, Date createTime);


	/** 根据商户全称，手机号码查询用户信息
	 * @param fullName			商户全称
	 * @param phoneNo			手机号
	 * @return json字符串
	 * 		   json.get("customer");	商户信息
	 * 		   json.get("resultCode");  返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		   json.get("resultMessage");   返回失败原因
	 */
	public String findByFullNameAndPhoneNo(String fullName,String phoneNo);


	/**根据商户编号和商户状态查询
	 * @param customerNo		商户编号
	 * @param status			商户状态
	 *@return json字符串
	 * json.get("customer");	商户信息
	 * json.get("resultCode");  返回"00"表示成功, "98"业务异常, "99"系统异常
	 * json.get("resultMessage");   返回失败原因
	 */
	public String findByCustomerAndStatus(String customerNo,String status);


	/**
	 * 根据代理商编号查询商户信息
	 * @param agentNo 			  代理商编号
	 * @param index  			  查询页数
	 * @return map<String,Object>
	 * 		   map.get("pageRows");  	  获取总页数 String类型
	 *  	   map.get("index");    	  获取当前页数
	 *  	   map.get("customerList");  获取商户List集合 List<Customer>集合
	 *  								  返回请输入正确页数表示查询页数小于0或大于搜索结果的页数
	 */
	public Map<String,Object> findAllCustomerByAgentNo(String agentNo,Integer index);


	/**
	 * 商户入网审核
	 * @param customerNo 			商户编号
	 * @param reason 				审核失败原因
	 * @param status 				审核后商户状态
	 * @return json字符串
	 * 		json.get("resultCode") 	返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		json.get("resultMessage")  	返回失败原因
	 */
	public String customerNotice(String customerNo,String status,String reason);
	/**
	 * 商户入网审核
	 * @param  customerNo			商户编号
	 * @param  reason				审核失败原因（非必传）
	 * @return json字符串
	 * 		json.get("resultCode") 	返回"00"表示成功, "98"业务异常, "99"系统异常
	 * 		json.get("resultMessage")  	返回失败原因
	 */
	public String customerNotice(String customerNo,String reason);

	/**根据代理商编号，开始时间，结束时间 查询某段时间内为True 的商户
	 * @param  agentNo		代理商编号
	 * @param  startTime	开始时间
	 * @param  endTime		结束时间
	 * @return Integer 		数量
	 */
	public Integer findByAgentNoAndTime(String agentNo,Date startTime,Date endTime);


	/**根据代理商编号，开始时间，结束时间 查询某段时间内为True 的商户
	 * @param  agentNo		代理商编号
	 * @param  status	状态
	 * @return Integer 		数量
	 */
	public Integer findByAgentNoAndStatus(String agentNo,String status);


	/**变更商户对应 服务商
	 * @param  agentNo			需变更服务商编号
	 * @param  toagentNo 		目标变更服务商编号
	 * @return json字符串
	 * json.get("resultCode");  返回"00"表示成功, "98"业务异常, "99"系统异常
	 * json.get("resultMessage");   返回失败原因
	 */
	public String changeAgent(String agentNo,String toagentNo);
}
