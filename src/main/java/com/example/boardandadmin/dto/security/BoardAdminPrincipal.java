package com.example.boardandadmin.dto.security;

import com.example.boardandadmin.domain.constant.RoleType;
import com.example.boardandadmin.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardAdminPrincipal(
        String username,
        String password,
        String email,
        String nickname,
        String memo,
        Collection<? extends GrantedAuthority> authorities,
//        String AUTHORITY,
        Map<String,Object> oAuth2Attribute
) implements UserDetails, OAuth2User {

    public static BoardAdminPrincipal of(String username, String password,Set<RoleType> roleTypes, String email, String nickname, String memo) {

        return of(
                username,
                password,
                roleTypes,
                email,
                nickname,
                memo,
                null
        );
    }
    public static BoardAdminPrincipal of(String username, String password, Set<RoleType> roleTypes ,String email, String nickname, String memo, Map<String,Object> oAuth2Attribute) {


        return new BoardAdminPrincipal(
                username,
                password,
                email,
                nickname,
                memo,
                roleTypes.stream()
                        .map(RoleType::getRoleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                oAuth2Attribute
        );
    }

    public static BoardAdminPrincipal from(UserAccountDto dto){
        return BoardAdminPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.roleTypes(),
                dto.email(),
                dto.nickname(),
                dto.memo()
        );
    }

    public UserAccountDto toDto(){
        return UserAccountDto.of(
                username,
                password,
                authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(RoleType::valueOf)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname,
                memo
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
//        auth.add(new SimpleGrantedAuthority(AUTHORITY));
//        return auth;  TODO: 권한 기능 추가하기
        return authorities;
    }

    @Override
    public String getPassword() {return password;}
    @Override
    public String getUsername() {return username;}

    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}


    @Override
    public Map<String, Object> getAttributes() {return oAuth2Attribute;}
    @Override
    public String getName() {return username;}


}
