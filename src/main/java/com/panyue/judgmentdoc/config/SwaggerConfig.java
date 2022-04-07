package com.panyue.judgmentdoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createResApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.example.judgmentdoc.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("裁判文书说理评估系统API").
                contact(new Contact("panyue", "https://github.com/pyyybf/judgmentDoc-backend", "3052899600@qq.com"))
                .description("裁判文书说理评估系统API文档")
                .version("1.0.0")
                .build();
    }
}
