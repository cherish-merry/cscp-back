package com.cscp.auth.client;

import com.cscp.userClient.api.UserApi;
import dto.RoleDto;
import dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/26 - 16:15
 */
@FeignClient(name = "user", fallback = UserClient.UserClientFallback.class)
public interface UserClient extends UserApi {
    @Component
    class UserClientFallback implements UserClient {
        @Override
        public UserDto getUserByUsername(String username) {
            return new UserDto();
        }

        @Override
        public UserDto getUserById(String id) {
            return new UserDto();
        }


        @Override
        public List<RoleDto> getRolesByUsername(String username) {
            return new LinkedList<>();
        }
    }
}
