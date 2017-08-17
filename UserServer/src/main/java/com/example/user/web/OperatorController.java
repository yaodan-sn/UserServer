package com.example.user.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.user.service.OperatorService;
import com.lefu.common.ResponseBean;
import com.lefu.user.bean.OperatorBean;
import com.lefu.user.bean.TokenBean;

@Controller
@RequestMapping("/operator")
public class OperatorController {

	@Resource
	private OperatorService operatorService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login/login");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseBean<TokenBean> login(OperatorBean operatorBean) {
		return operatorService.login(operatorBean);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(OperatorBean operatorBean) {
		return operatorService.saveUserRegister(operatorBean);
	}

	@RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
	@ResponseBody
	public String verifyCode(String phone, Model model) {
		return operatorService.verifyCode(phone);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object operator(OperatorBean operatorBean) {
		return this.operatorService.findOperator(operatorBean);
	}
}