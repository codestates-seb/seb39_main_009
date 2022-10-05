package teamparkinglot.parkinggo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.config.secret.SecretCode;
import teamparkinglot.parkinggo.security.entrypoint.CustomAccessDeniedHanler;
import teamparkinglot.parkinggo.security.entrypoint.CustomAuthenticationEntrypoint;
import teamparkinglot.parkinggo.security.filter.ExceptionHandlerFilter;
import teamparkinglot.parkinggo.security.filter.JwtAuthenticationFilter;
import teamparkinglot.parkinggo.security.filter.JwtAuthorizationFilter;
import teamparkinglot.parkinggo.token.service.TokenService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {


    private final TokenService tokenService;
    private final MemberRepository memberRepository;
    private final SecretCode secretCode;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomDsl());

        http
                .headers()
                        .xssProtection()
                        .and()
                        .contentSecurityPolicy("script-src 'self");

        http.cors().configurationSource(corsConfig());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers("/api/member", "/api/member/*", "/api/bookmark", "/api/bookmark/*", "/api/parking/{id}/reservation", "/api/pay/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/reviews/{parkingId}").permitAll()
                .antMatchers("/api/reviews/{parkingId}").authenticated()
                .anyRequest().permitAll();

        http
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntrypoint())
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHanler());

        return http.build();
    }

    private CorsConfigurationSource corsConfig() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("RefreshToken");
        configuration.addExposedHeader("Set-Cookie");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    private class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, memberRepository, tokenService, bCryptPasswordEncoder(), secretCode);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

            builder
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository, secretCode))
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterBefore(exceptionHandlerFilter, JwtAuthorizationFilter.class)
                    .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class);
            super.configure(builder);
        }
    }
}
