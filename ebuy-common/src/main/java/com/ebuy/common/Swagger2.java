package com.ebuy.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhouxinghang on 2018/4/16.
 */

@Configuration
@EnableSwagger2 //启动Swagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            //Swagger会扫描该包下所有Controller定义的API,被@ApiIgnore注解的除外
            //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ebuy"))
            .paths(PathSelectors.any())
            .build();
    }


    /**
     * 创建api的基本内容，文档页面会显示
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Spring Boot中使用Swagger2构建RESTful APIs")
            .description("更多技术文章请关注：http://www.zhouxinghang.com/")
            .termsOfServiceUrl("http://www.zhouxinghang.com/")
            .build();
    }
}
