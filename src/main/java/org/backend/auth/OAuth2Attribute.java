package org.backend.auth;

import java.util.Map;
import org.backend.member.domain.Member;
import org.backend.member.domain.Role;

public class OAuth2Attribute {

    private Map<String, Object> attributes;
    private String attributeKey;
    private String name;
    private String email;

    public OAuth2Attribute(Map<String, Object> attributes,
                           String attributeKey,
                           String name,
                           String email) {
        this.attributes = attributes;
        this.attributeKey = attributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuth2Attribute of(String provider,
                                     String attributeKey,
                                     Map<String, Object> attributes) {
        switch(provider) {
            case "kakao":
                return ofKakao("id", attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2Attribute ofKakao(String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return new OAuth2Attribute(
                response,
                attributeKey,
                (String) response.get("name"),
                (String) response.get("kakao_account")
        );
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Member toEntity() {
        return new Member(
                name,
                email,
                Role.USER
        );
    }
}
