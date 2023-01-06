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
 * swagger支持
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
          .groupName("default") //  组名
          .select()
          .apis(RequestHandlerSelectors.basePackage(bp))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"应用 API", // 大标题
				"swagger接口管理", // 小标题
				"1.0", // 接口版本号
				"www.yiueil.cc", // 团队地址
				new Contact("YIueil", "www.yiueil.cc", "yiueil@163.com"), // 作者信息
				"nameless", // 许可证
				"www.nameless.cn", // 许可地址
				Collections.emptyList() // 厂家扩展
		);
	}
}