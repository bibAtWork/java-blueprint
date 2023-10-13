package de.bibatwork.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpringTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringTemplateApplication::main).with(TestSpringTemplateApplication.class).run(args);
	}

}
