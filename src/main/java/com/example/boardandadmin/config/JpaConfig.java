package com.example.boardandadmin.config;

import com.example.boardandadmin.dto.security.BoardAdminPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

//spring boot 에서 spring jpa를 사용하는 경우 autoconfiguration에서 저게 이미 다 들어가있으므로 따로 어노테이션을 지정할 필요 없음
@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {


        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(BoardAdminPrincipal.class::cast)
                .map(BoardAdminPrincipal::getUsername);


//        return () -> {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (Objects.isNull(authentication) || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof BoardAdminPrincipal)) {
//                return Optional.of("admin");
//            }
//            return Optional.of(authentication.getName());
//        };
    }

}
