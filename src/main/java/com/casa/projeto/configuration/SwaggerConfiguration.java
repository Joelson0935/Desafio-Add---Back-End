package com.casa.projeto.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {
	
	/**
	 *  http://localhost:8082/Escolinha/swagger-ui/index.html
	 * @return retorna a aplicação no site do swagger
	 */

	@Bean
	public Docket projetoAdd() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(metaInfo());
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Desafio.Add", "API REST de cadastro de alunos.", "1.0", "Termos de Serviço",
				new Contact("Joelson Luiz Conceição Cerqueira", "joelsonluiz2010@gmail.com",
						"https://github.com/Joelson0935?tab=repositories"),
				"Apache License Version 2.0", "https://apache.org/licenses/LICENSE-2.0",
				new ArrayList<VendorExtension>());

		return apiInfo;
	}

}
