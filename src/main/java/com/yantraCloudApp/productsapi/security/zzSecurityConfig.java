package com.yantraCloudApp.productsapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.*;

//@EnableWebSecurity
public class zzSecurityConfig {//extends WebSecurityConfigurerAdapter {

//    @Value("${auth0.audience}")
//    private String audience;
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String issuer;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .mvcMatchers(HttpMethod.POST,"/product/*").authenticated()
//                .mvcMatchers(HttpMethod.DELETE,"/product/*").authenticated()
//                .mvcMatchers(HttpMethod.PUT,"/product/*").authenticated()
//                .mvcMatchers(HttpMethod.GET,"/product/*").permitAll()
//                .mvcMatchers(HttpMethod.GET,"/product").permitAll()
////                .mvcMatchers("/product/*").authenticated()
////                .mvcMatchers("/api/private").permitAll()
////                .mvcMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
////                .and().cors()
//                .and().oauth2ResourceServer().jwt();
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
//                JwtDecoders.fromOidcIssuerLocation(issuer);
//
//        OAuth2TokenValidator<Jwt> audienceValidator = new zzAudienceValidator(audience);
//        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
//        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
//
//        jwtDecoder.setJwtValidator(withAudience);
//
//        return jwtDecoder;
//    }
}