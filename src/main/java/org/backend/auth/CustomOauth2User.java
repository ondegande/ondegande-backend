package org.backend.auth;

import java.util.Collection;
import java.util.Map;
import org.backend.member.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class CustomOauth2User extends DefaultOAuth2User {

    private String socialId;
    private String email;
    private Role role;

    public CustomOauth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes,
                            String nameAttributeKey,
                            String socialId,
                            String email,
                            Role role) {
        super(authorities, attributes, nameAttributeKey);
        this.socialId = socialId;
        this.email = email;
        this.role = role;
    }

    public String getSocialId() {
        return socialId;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
