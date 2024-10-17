package org.iclass.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
// 스프링 시큐리티가 관리하는 사용자 인증 정보 DTO
public class UserAccount2DTO extends User {

    //예상 시나리오: 소셜 인증을 추가한다면 소셜 인증 여부를 저장
    private boolean social;
    //이 외에도 필한 사용자 정보를 추가로 저장하는 User 자식 클래스

    public UserAccount2DTO(String username,
                           String password,
                           Collection<? extends GrantedAuthority> authorities
       , boolean social
       ) {
        super(username, password, authorities);
        this.social = social;
    }
    // Collection<? extends GrantedAuthority>
    // : Collection 의 제너릭 타입 ? 은 GrantedAuthority 를 상속받은 타입이어야 한다.
    // : 권한(role) 은 여러가지 값을 가질 수 있도록 Collection (리스트, 맵, set) 타입.
}
