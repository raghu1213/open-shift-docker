package com.xchange.gambool.activity.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xchange.gambool.activity.controller"))
                .build()
                .apiInfo(buildApiInfo())
    }

    private fun buildApiInfo(): ApiInfo? {
        return ApiInfo(
                "Gambool Activity Service API",
                "Spring Boot REST API for Activity Service",
                "1.0",
                "wwww.TermsAndConditions.placeholder.com",
                Contact("xchange.gambool","www.xchange.gambool.com","xchange.gambool@gambool.com"),
                "licence",
                "www.xchange.gambool.lincese.com",
                Collections.emptyList())
    }
}