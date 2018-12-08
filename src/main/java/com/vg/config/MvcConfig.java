package com.vg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//https://blog.csdn.net/thekey1314/article/details/81044999 解决静态资源不能访问的坑
//@Configuration
//public class MvcConfig extends WebMvcConfigurationSupport  {
//
//	   @Bean
//	    public MyHandler securityInterceptor() {
//	        return new MyHandler();
//	    }
//	// 以下WebMvcConfigurerAdapter 比较常用的重写接口
//	// /** 解决跨域问题 **/
//	// public void addCorsMappings(CorsRegistry registry) ;
//	// /** 添加拦截器 **/
//	// void addInterceptors(InterceptorRegistry registry);
//	// /** 这里配置视图解析器 **/
//	// void configureViewResolvers(ViewResolverRegistry registry);
//	// /** 配置内容裁决的一些选项 **/
//	// void configureContentNegotiation(ContentNegotiationConfigurer
//	// configurer);
//	// /** 视图跳转控制器 **/
//	// void addViewControllers(ViewControllerRegistry registry);
//	// /** 静态资源处理 **/
//	// void addResourceHandlers(ResourceHandlerRegistry registry);
//	// /** 默认静态资源处理器 **/
//	// void configureDefaultServletHandling(DefaultServletHandlerConfigurer
//	// configurer);
////		registry.addInterceptor(securityInterceptor()).addPathPatterns("/user/**");
//
//	   @Override
//	    protected void addInterceptors(InterceptorRegistry registry) {
//	        registry.addInterceptor(securityInterceptor()).addPathPatterns("/user/**");
//	        super.addInterceptors(registry);
//	    }
//	     
//	    @Override
//	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//	        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//	        super.addResourceHandlers(registry);
//	    }

//}

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Bean
	public MyHandler securityInterceptor() {
		return new MyHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor()).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}