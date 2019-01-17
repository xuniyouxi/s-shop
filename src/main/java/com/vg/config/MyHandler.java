package com.vg.config;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.vg.config.Encrypt.JWTUtil;
import com.vg.config.MyAnn.Authorization;
import com.vg.config.Util.PrintJSON;
import com.vg.mapper.user.UserBehaviorMapper;
import com.vg.service.user.UserBehaviorservice;

@Configuration
public class MyHandler implements HandlerInterceptor {
	@Autowired
	UserBehaviorservice ubservice;
	@Autowired
	UserBehaviorMapper userbehavhourmapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=utf-8");

		if (!(handler instanceof HandlerMethod)) {
			System.out.println("可能请求了静态资源，放过");
			return true;
		}

		else {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			System.out.println("用户：" + request.getSession().getId() + "请求了：" + method);
			String token = request.getHeader("token");
			if (token != null && method.isAnnotationPresent(Authorization.class)) {
				Authorization useraut = method.getAnnotation(Authorization.class);
				try {
					// 获取token内容
					Map<String, Object> user_token = JWTUtil.parseJWT(token);

					// 通过token的id查询部分用户信息
					Map<String, Object> userInfo = userbehavhourmapper
							.getuserInfoByid((String) user_token.get("userId"));
					int user_role = (int) userInfo.get("user_role");
					if (useraut.authorization().equals("open")) {
						System.out.println("请求了公开接口");
						return true;
					}
					if (user_role == 1 && (useraut.authorization().equals("user"))) {

						if (!user_token.get("IMEI").equals(userInfo.get("user_equipment_id1"))
								&& !user_token.get("IMEI").equals(userInfo.get("user_equipment_id2"))) {
							System.out.println("账号异常，重新登录");
							String data = "{\"code\":250}";
							PrintJSON.printJson(response, data);
							return false;
						}
						if (!user_token.get("IMEI").equals(userInfo.get("token_id"))) {
							System.out.println("此账号已登录");
							String data = "{\"code\":250}";
							PrintJSON.printJson(response, data);
							return false;
						}
						System.out.println("访问了user接口，且权限对的上");
						return true;
					} else if (useraut.authorization().equals("admin")) {
						System.out.println("访问了admin接口，且权限对的上");
						return true;
					}

					else {
						// token不对,返回状态码
						String data = "{\"code\":250}";
						PrintJSON.printJson(response, data);

						System.out.println("访问的接口和权限对不上");
						return false;
					}
				} catch (Exception e) {
					// token不对,返回状态码
					String data = "{\"code\":250}";
					PrintJSON.printJson(response, data);

					System.out.println("假冒token");
					return false;
				}

			} else if (token == null && method.isAnnotationPresent(Authorization.class)) {
				Authorization useraut = method.getAnnotation(Authorization.class);
				if (useraut.authorization().equals("open")) {
					System.out.println("请求了公开接口");
					return true;
				} else
					System.out.println("没token还想请求私有接口");
				return false;
			}

			return false;

		}

	}

}
