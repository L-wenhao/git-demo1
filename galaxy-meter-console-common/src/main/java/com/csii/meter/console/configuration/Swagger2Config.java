package com.csii.meter.console.configuration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")
public class Swagger2Config {
    // swagger2的配置文件
    @Bean
    public Docket createRestApi() {
        //添加请求头参数
        List<Parameter> pars = new ArrayList<>();
        //应用标识
        ParameterBuilder appPar = new ParameterBuilder();
        //环境标识
        ParameterBuilder envPar = new ParameterBuilder();
        appPar.name("appCode").description("应用标识").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).scalarExample("testApp").build();
        envPar.name("envCode").description("环境标识").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).scalarExample("dev").build();
        pars.add(appPar.build());
        pars.add(envPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.csii.meter.console.controller")).paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    //配置content type
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json"));

    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Galaxy Meter接口文档")// 页面标题
                .version("1.0")// 版本号
                .description("Galaxy Meter控制台接口文档")// 描述
                .build();
    }
}
