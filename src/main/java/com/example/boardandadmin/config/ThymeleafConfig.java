package com.example.boardandadmin.config;

/*
* This lets you use a Spring Boot user-custom property called spring.thymeleaf3.decoupled-logic to turn on/off the decoupled logic feature for Thymeleaf 3.
Use this property inside application.properties or application.yaml to activate Thymeleaf 3 decoupled logic.
I personally feel weird that Spring Boot common application properties for Thymeleaf isn't taking care of this option by these days as it's not a difficult thing to provide at all,
even though this feature seems not so popular to Spring Boot and Thymeleaf users.

이 스프링 부트 설정은 사용자 정의 프로퍼티 spring.thymeleaf3.decoupled-logic 을 사용하여 타임리프 3의 템플릿 로직 분리(decoupled logic) 기능을 이용할 수 있게 해줍니다.
application.properties 혹은 application.yaml 에서 사용하여 템플릿 로직 분리 옵션을 제어해 보세요.
저는 개인적으로 이 옵션이 스프링 부트 애플리케이션 프로퍼티에서 설정할 수 있도록 제공되지 않는다는 사실이 이상하게 느껴집니다. 세팅이 어려운 부분이 아니기 때문에요.
비록 스프링 부트와 타임리프 유저들에게 그렇게 인기있는 기능은 아니라 하더라도요.

I made the prefix of the property to spring.thymeleaf3, it might have been smoother to use spring.thymealeaf instead, but I chose not to do that way to avoid confusion.
This is as far as I know the simplest and the most graceful way to use decoupled logic in Spring Boot.
Also, I suggest you use this Spring Boot configuration with the dependency spring-boot-configuration-processor for better IDE support and experience.

프로퍼티 앞글자는 spring.thymeleaf3로 지었습니다. spring.thymealeaf로 지었으면 더 좋았겠지만, 혼란을 피하기 위해 구분을 두었습니다.
제가 아는 한 이것이 스프링 부트에서 타임리프 로직 분리기능을 가장 쉽고 우아하게 사용할 수 있는 방법입니다.
또한, 이 프로퍼티를 spring-boot-configuration-processor 스프링 부트 디펜던시와 함께 사용하여 IDE 의 지원과 나은 경험을 느껴보세요.

Tested on Spring Boot 2.7.0 + thymelaef 3.0.15
*
* */

/*
* spring boot properties에다가 노출을 안시켜놨기때문에 굳이 여기다가 잡아줘야됨
* */

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.decoupledLogic());  //원래 있던 deCoupledLogic을 뚫어 준 것 뿐

        return defaultTemplateResolver;
    }


    /*
    * 프로퍼티로 노출시키는 방식*/
    @ConfigurationProperties("spring.thymeleaf3")
    public record Thymeleaf3Properties(boolean decoupledLogic) {}

}

