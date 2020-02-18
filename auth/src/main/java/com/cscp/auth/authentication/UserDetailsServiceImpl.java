package com.cscp.auth.authentication;

import com.cscp.auth.client.UserClient;
import com.cscp.auth.service.TokenService;
import dto.RoleDto;
import dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/11 - 23:19
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto userDto = userClient.getUserByUsername(s);
        List<RoleDto> roleDtos = userClient.getRolesByUsername(s);

        StringBuilder stringBuilder = new StringBuilder();
        if (!CollectionUtils.isEmpty(roleDtos)) {
            roleDtos.forEach(roleDto -> {
                stringBuilder.append("ROLE_").append(roleDto.getRoleName()).append(",");
            });
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (userDto == null || StringUtils.isEmpty(userDto.getUsername())) {
            return null;
        }
        return new User(userDto.getUsername(), userDto.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString()));
    }
}
