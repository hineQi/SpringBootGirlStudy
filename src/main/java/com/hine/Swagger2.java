package com.hine;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 齐海阳
 * Date: 2017/8/22
 * Time: 14:04
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

        public Docket createRestApi(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.hine.controller.GirlsController"))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo(){
            return new ApiInfoBuilder()
                    .title("Spring Boot中使用Swagger2构建RESTful APIs")
                    .description("更多问题关注haiyang.qi@autozi.com")
                    .termsOfServiceUrl("haiyang.qi@autozi.com")
                    .contact("齐海阳")
                    .version("1.0")
                    .build();
        }

}
