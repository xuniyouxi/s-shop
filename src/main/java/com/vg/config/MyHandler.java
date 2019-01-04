package com.vg.config;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Encrypt.JWTUtil;
import com.vg.config.MyAnn.Authorization;
import com.vg.service.user.UserBehaviorservice;

@Configuration
public class MyHandler implements HandlerInterceptor {
	@Autowired
	UserBehaviorservice ubservice;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");


		JSONObject res = new JSONObject();
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
					int user_role = ubservice
							.getUserRoleById((String) JWTUtil.parseJWT(request.getHeader("token")).get("userId"));
					if (user_role == 0) {

					
						System.out.println("虚假token滚蛋");
						return false;
					}
					if (user_role == 1
							&& (useraut.authorization().equals("user") || useraut.authorization().equals("open"))) {
						System.out.println("访问了user接口，且权限对的上");
						return true;
					} else if (user_role == 2
							&& (useraut.authorization().equals("user") || useraut.authorization().equals("open"))) {
						System.out.println("访问了admin接口，且权限对的上");
						return true;
					}

					else {
						System.out.println("访问的接口和权限对不上");
						return false;
					}
				} catch (Exception e) {

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
