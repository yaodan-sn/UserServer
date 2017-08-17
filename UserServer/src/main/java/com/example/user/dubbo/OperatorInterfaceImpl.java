package com.example.user.dubbo;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.user.service.OperatorService;
import com.lefu.common.ResponseBean;
import com.lefu.user.bean.OperatorBean;
import com.lefu.user.bean.TokenBean;
import com.lefu.user.dubbo.OperatorInterface;

@Service(version = "1.0.0", group = "user", registry = { "lefu" })
public class OperatorInterfaceImpl implements OperatorInterface {

	@Resource
	private OperatorService operatorService;

	@Override
	public ResponseBean<TokenBean> login(OperatorBean operatorBean) {
		return operatorService.login(operatorBean);
	}
}
