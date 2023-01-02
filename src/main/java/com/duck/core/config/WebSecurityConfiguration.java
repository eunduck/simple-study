package com.duck.core.config;

import com.duck.core.security.CustomAuthenticationEntryPoint;
import com.duck.core.security.CustomUserDetailsService;
import com.duck.core.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by eunduck on 2022/10/30.
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticateFilter;
    private final CustomAuthenticationEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable() // api csrf 사용안함
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증, 세션 사용 안 함
                .and()
                .authorizeRequests() // 다음 리퀘스트 권한 체크
                .antMatchers(PERMIT_URL_ARRAY).permitAll()
                .anyRequest().authenticated() // 나머지 요청 모두 인증된 회원만 접근 가능
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class)
                ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private static final String[] PERMIT_URL_ARRAY = {
            "/login",
            "/signup",
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}
