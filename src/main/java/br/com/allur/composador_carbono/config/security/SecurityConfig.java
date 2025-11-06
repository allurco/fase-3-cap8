package br.com.allur.composador_carbono.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/fontes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/fontes", "/api/fontes/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/fontes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/fontes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/projetos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/projetos", "/api/projetos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/projetos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/projetos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/emissoes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/emissoes", "/api/emissoes/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/emissoes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/emissoes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/alertas", "/alertas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/alertas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/compensacoes", "/compensacoes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/compensacoes").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
