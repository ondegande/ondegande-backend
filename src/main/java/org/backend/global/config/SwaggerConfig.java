package org.backend.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");
        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(
                        new Components()
                        .addSecuritySchemes("Authorization", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                )
                .info(apiInfo());
    }

    public Info apiInfo() {
        return new Info()
                .title("부산's 온데간데 Test API")
                .description("사용자의 여행 코스 및 관광지에 관한 API")
                .version("1.0.1");
    }
}
