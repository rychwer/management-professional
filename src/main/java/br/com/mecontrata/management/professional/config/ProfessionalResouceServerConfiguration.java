package br.com.mecontrata.management.professional.config;

import br.com.server.resource.service.CustomRemoteTokenService;
import br.com.server.resource.service.CustomTokenExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableResourceServer
@EnableFeignClients(basePackages = "br.com.server.resource.service")
public class ProfessionalResouceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId).authenticationManager(authenticationManagerBean())
                .tokenExtractor(new CustomTokenExtractor()).authenticationEntryPoint(customAuthEntryPoint());
    }

    @Bean
    public ResourceServerTokenServices tokenService() {
        CustomRemoteTokenService tokenServices = new CustomRemoteTokenService();
        return tokenServices;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenService());
        return authenticationManager;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf().disable().anonymous().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/professional").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public AuthenticationEntryPoint customAuthEntryPoint(){
        return new RestProfessionalAuthenticationEntryPoint();
    }


}
