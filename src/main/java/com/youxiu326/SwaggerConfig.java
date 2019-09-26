package com.youxiu326;
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
 * Created by lihui on 2019/1/22.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.youxiu326.controller"))
                .paths(PathSelectors.any()).build();

    }


/*  以注解方式查询
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD,ElementType.TYPE})
    public @interface EnableApi {
    }
    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if(declaringClass.isAnnotationPresent(EnableApi.class)) // 被注解的类
                    return true;
                if(input.isAnnotatedWith(EnableApi.class)) // 被注解的方法
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }*/

    //构建api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("springBoot测试使用Swagger2构建RESTful API")
                //创建人
                .contact(new Contact("lihui", "http://blog.youxiu326.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

}