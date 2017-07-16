package com.lefu.user.dubbo;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.lefu.common.ResponseBean;
import com.lefu.user.bean.OperatorBean;
import com.lefu.user.bean.TokenBean;
import com.lefu.user.service.OperatorService;

@Service(version = "1.0.0", group = "user", registry = { "lefu" })
public class OperatorInterfaceImpl implements OperatorInterface {

	@Resource
	private OperatorService operatorService;

	@Override
	public ResponseBean<TokenBean> login(OperatorBean operatorBean) {
		return operatorService.login(operatorBean);
	}
}
