package br.com.mecontrata.management.professional;

import br.com.server.resource.config.ResourceServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients(basePackages = {"br.com.mecontrata.management.client.service"})
@ComponentScan(basePackages = {"br.com.server.resource", "br.com.mecontrata.management.professional"}, excludeFilters={
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value= ResourceServerConfiguration.class)})
@EnableAsync
@EnableDiscoveryClient
public class ManagementProfessionalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementProfessionalApplication.class, args);
    }
}
