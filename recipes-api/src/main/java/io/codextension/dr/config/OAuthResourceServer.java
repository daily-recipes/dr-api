package io.codextension.dr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

@Configuration
@EnableResourceServer
public class OAuthResourceServer implements ResourceServerConfigurer {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Value("${security.oauth2.resource.jwk.key-set-uri}")
    private String keySetUri;

    @Bean
    public TokenStore tokenStore() {
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        GoogleUserInfoTokenConverter googleUserInfoTokenConverter = new GoogleUserInfoTokenConverter();
        tokenConverter.setUserTokenConverter(googleUserInfoTokenConverter);
        return new JwkTokenStore(keySetUri, tokenConverter);

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.stateless(true).resourceId(resourceId);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().formLogin(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .mvcMatchers("/manage/health/readiness", "/manage/health/liveness", "manage/health/ping")
                        .permitAll().anyRequest().permitAll())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
