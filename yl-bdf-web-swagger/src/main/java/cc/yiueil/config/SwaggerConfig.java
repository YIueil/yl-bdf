package cc.yiueil.config;

import cc.yiueil.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * SwaggerConfig swagger支持
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/31 23:43
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.basePackage:cc.yiueil}")
	private String basePackage;
	
	@Bean
    public Docket docket() {
	 	String bp = StringUtils.isBlank(basePackage) ? "cc.yiueil" : basePackage;
        return new Docket(DocumentationType.SWAGGER_2)
          .groupName("default")
          .select()
          .apis(RequestHandlerSelectors.basePackage(bp))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"应用 API",
				"swagger接口管理",
				"1.0",
				"www.yiueil.cc",
				new Contact("YIueil", "www.yiueil.cc", "yiueil@163.com"),
				"nameless",
				"www.nameless.cn",
				Collections.emptyList()
		);
	}
}