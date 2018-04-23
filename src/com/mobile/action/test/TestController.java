package com.mobile.action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.service.test.TestService;
import com.mobile.utils.Const;
import com.mobile.utils.ResultData;

/**
 * 手机接口测试类
 * @author Administrator
 *
 */
@RestController
public class TestController {
	
	private static final Logger logger = Logger.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;
	
	/**
	 * 测试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mobile/test/test",method=RequestMethod.GET,produces="application/json")
	public ResultData test(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String test = request.getParameter("test");
			testService.test(test);
			rd.setCode(Const.SUCCESS);
			rd.setResult(Const.SUCCESS_DESCRIPTION);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			rd.setCode(Const.SYS_ERROR);
			rd.setResult(Const.SYS_ERROR_DESCRIPTION);
		}
		return rd;
	}
}
