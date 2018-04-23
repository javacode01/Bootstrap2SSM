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
	
	/**
	 * 用户名密码登录（使用时需根据需求修改，整体逻辑已实现）
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping(value="/mobile/login",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
//	public ResultData login(HttpServletRequest request, HttpServletResponse response) {
//		ResultData rd = new ResultData();
//		try {
//			String loginName = request.getParameter("loginName");//登录名
//			String password = request.getParameter("password");//密码
//			if(SysUtils.isNull(loginName)||SysUtils.isNull(password)){//校验参数不为空
//				rd.setCode(Const.PARAMETER_NULL);
//				rd.setResult(Const.PARAMETER_NULL_DESCRIPTION);
//				return rd;
//			}
//			PubUsersExample example = new PubUsersExample();
//			example.createCriteria().andLoginIdEqualTo(loginName).andPasswordEqualTo(password);
//			List<PubUsers> list = pubUsersService.listPubUsers(example);
//			if(null==list||list.size()<1) {
//				rd.setCode(Const.PARAMETER_ERROR);
//				rd.setResult(Const.PARAMETER_ERROR_DESCRIPTION);
//				return rd;
//			}
//			PubUsers user = list.get(0);
//			//只有设置允许登陆的地市用户才能登陆
//			if(!user.getOrganCode().matches(Const.APP_ORGAN_CODE)) {
//				rd.setCode(Const.PARAMETER_ERROR);
//				rd.setResult(Const.PARAMETER_ERROR_DESCRIPTION);
//				return rd;
//			}
//			loginSuccessResult(rd,user);
//			return rd;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.error(e);
//			rd.setCode(Const.SYS_ERROR);
//			rd.setResult(Const.SYS_ERROR_DESCRIPTION);
//			return rd;
//		}
//	}
	
	/**
	 * 使用token登录（使用时需根据需求修改，整体逻辑已实现）
	 * @param token
	 * @return
	 */
//	@RequestMapping(value="/mobile/tokenLogin",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
//	public ResultData tokenLogin(HttpServletRequest request, HttpServletResponse response){
//		ResultData rd = new ResultData();
//		try {
//			String token = request.getParameter(Const.REQUEST_TOKEN);
//			if(SysUtils.isNull(token)){
//				rd.setCode(Const.TOKEN_NULL);
//				rd.setResult(Const.TOKEN_NULL_DESCRIPTION);
//				return rd;
//			}
//			//解析token值
//			rd = JwtUtil.parseJWT(token, JwtUtil.TOKEN_TYPE_LOGIN);
//			if(!Const.SUCCESS.equals(rd.getCode())){
//				rd.setCode(Const.TOKEN_ERROR);
//				rd.setResult(Const.TOKEN_ERROR_DESCRIPTION);
//				return rd;
//			}
//			String userid = (String) rd.getResult();
//			//判断必要信息是否存在
//			if(SysUtils.isNull(userid)){
//				rd.setCode(Const.TOKEN_ERROR);
//				rd.setResult(Const.TOKEN_ERROR_DESCRIPTION);
//				return rd;
//			}
//			//从数据库获取用户信息
//			PubUsers user = pubUsersService.getPubUsersById(userid);
//			if(null==user) {
//				rd.setCode(Const.TOKEN_ERROR);
//				rd.setResult(Const.TOKEN_ERROR_DESCRIPTION);
//				return rd;
//			}
//			//只有设置允许登陆的地市用户才能登陆
//			if(!user.getOrganCode().matches(Const.APP_ORGAN_CODE)) {
//				rd.setCode(Const.TOKEN_ERROR);
//				rd.setResult(Const.TOKEN_ERROR_DESCRIPTION);
//				return rd;
//			}
//			loginSuccessResult(rd,user);
//			return rd;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.error(e);
//			rd.setCode(Const.SYS_ERROR);
//			rd.setResult(Const.SYS_ERROR_DESCRIPTION);
//			return rd;
//		}
//	}
	
	/**
	 * 刷新token（使用时需根据需求修改，整体逻辑已实现）
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping(value="/mobile/refreshToken",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
//	public ResultData refreshToken(HttpServletRequest request, HttpServletResponse response){
//		ResultData rd = new ResultData();
//		try {
//			String token = request.getParameter(Const.REQUEST_TOKEN);
//			if(SysUtils.isNull(token)){
//				rd.setCode(Const.TOKEN_NULL);
//				rd.setResult(Const.TOKEN_NULL_DESCRIPTION);
//				return rd;
//			}
//			//解析token值
//			rd = JwtUtil.parseJWT(token, JwtUtil.TOKEN_TYPE_LOGIN);
//			if(!Const.SUCCESS.equals(rd.getCode())||null==rd.getResult()||"".equals(rd.getResult())){
//				rd.setCode(Const.TOKEN_ERROR);
//				rd.setResult(Const.TOKEN_ERROR_DESCRIPTION);
//				return rd;
//			}
//			//刷新token
//			Map<String,String> resultMap = new HashMap<String,String>();
//			resultMap.put("requestToken", JwtUtil.createJWT((String)rd.getResult(), JwtUtil.TOKEN_TYPE_REQUEST, JwtUtil.JWT_EXP));
//			rd.setCode(Const.SUCCESS);
//			rd.setResult(resultMap);
//			return rd;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.error(e);
//			rd.setCode(Const.SYS_ERROR);
//			rd.setResult(Const.SYS_ERROR_DESCRIPTION);
//			return rd;
//		}
//	}
	
	/**
	 * 登录成功后返回值（使用时需根据需求修改，整体逻辑已实现）
	 * @param rd
	 * @param user
	 * @return
	 * @throws Exception
	 */
//	private ResultData loginSuccessResult(ResultData rd,PubUsers user) throws Exception{
//		Map<String,String> resultMap = new HashMap<String,String>();
//		resultMap.put("loginToken", JwtUtil.createJWT(JwtUtil.generalSubject(user.getUserId()),JwtUtil.TOKEN_TYPE_LOGIN,JwtUtil.JWT_REFRESH_TTL));
//		resultMap.put("requestToken", JwtUtil.createJWT(JwtUtil.generalSubject(user.getUserId()), JwtUtil.TOKEN_TYPE_REQUEST, JwtUtil.JWT_EXP));
//		resultMap.put("userName", user.getUserName());
//		resultMap.put("depName", user.getOrganCodeName());
//		resultMap.put("role", MobileUtil.getUserRole(user.getUserId()));
//		rd.setCode(Const.SUCCESS);
//		rd.setResult(resultMap);
//		return rd;
//	}
}
