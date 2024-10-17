package org.iclass.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
// 스프링 시큐리티가 관리하는 사용자 인증 정보 DTO
public class UserAccountDTO extends User {


    public UserAccountDTO(String username,
                          String password,
       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    // Collection<? extends GrantedAuthority>
    // : Collection 의 제너릭 타입 ? 은 GrantedAuthority 를 상속받은 타입이어야 한다.
    // : 권한(role) 은 여러가지 값을 가질 수 있도록 Collection (리스트, 맵, set) 타입.
}
