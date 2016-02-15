//package com.eugene.config;
//
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.filter.DelegatingFilterProxy;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import javax.servlet.Filter;
//
///**
// * Created by DCLab on 12/17/2015.
// */
//public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override   //配置root上下文,如Jpa数据源等等的配置
//    protected Class<?>[] getRootConfigClasses() {
////        return new Class<?>[] {ApplicationConfig.class, JpaConfig.class, SecurityConfig.class};
//        return new Class<?>[] {ApplicationConfig.class};
//    }
//
//    @Override   //配置dispatcher servlet，如果在root config指定了该转发规则就可以忽略
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[] {WebMvcConfig.class};
//    }
//
//    @Override   //指定开始被servlet处理的url,配置从/开始
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    //配置servlet过滤器
//    @Override
//    protected Filter[] getServletFilters() {
//        //编码
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return new Filter[] {characterEncodingFilter};
//    }
//
//
//}
