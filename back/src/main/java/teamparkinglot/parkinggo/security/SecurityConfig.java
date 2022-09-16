package teamparkinglot.parkinggo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.secret.SecretCode;
import teamparkinglot.parkinggo.security.filter.ExceptionHandlerFilter;
import teamparkinglot.parkinggo.security.filter.JwtAuthenticationFilter;
import teamparkinglot.parkinggo.security.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
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

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilter(corsConfig())
                .authorizeRequests()
                .antMatchers("/api/join").permitAll()
                .antMatchers("/api/test").authenticated()
                .anyRequest().permitAll();

        return http.build();
    }

    private CorsFilter corsConfig() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }

    private class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, memberRepository, memberService, bCryptPasswordEncoder(), secretCode);
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
