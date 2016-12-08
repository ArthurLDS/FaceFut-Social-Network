package br.com.crescer.social.security.enumeration;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Carlos H. Nonnemacher
 */
public enum SocialRoles implements GrantedAuthority {

    ROLE_USER;

    @Override
    public String getAuthority() {
        return toString();
    }

    public static List<SocialRoles> valuesToList() {
        return Arrays.asList(SocialRoles.values());
    }

}
