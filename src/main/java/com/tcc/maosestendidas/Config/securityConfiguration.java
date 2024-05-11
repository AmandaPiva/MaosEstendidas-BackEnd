package com.tcc.maosestendidas.Config;

import com.tcc.maosestendidas.models.pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration    //notation que indica um arquivo de configuração
@EnableWebSecurity    //notation que indica segurança para a aplicação
@RequiredArgsConstructor    //lombok que injeta as dependencias

public class securityConfiguration {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private PessoaService pessoaService;



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
               .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/api/v1/login/executa"),
                                new AntPathRequestMatcher("/api/v1/login/valida"),
                                new AntPathRequestMatcher("/api/v1/pessoa/role"),
                                new AntPathRequestMatcher("/api/v1/pessoa"),
                                new AntPathRequestMatcher("/api/v1/endereco"),
                                new AntPathRequestMatcher("/api/v1/endereco/viacep/{cep}"),
                                new AntPathRequestMatcher("/api/v1/requisicao/{id}"),
                                new AntPathRequestMatcher("/api/v1/requisicao/buscaRequisicoesPelaPessoa/{emailPessoa}"),
                                new AntPathRequestMatcher("/api/v1/requisicao/deleteRequisicao/{id}"),


                                new AntPathRequestMatcher("/error")
                        ).permitAll().anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(pessoaService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
