package com.cscp.documentServer.client;

import com.cscp.userClient.api.UserApi;
import dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/26 - 16:15
 */
@FeignClient(name = "user",fallback = UserClient.UserClientFallback.class)
public interface UserClient extends UserApi {
    @Component
     class UserClientFallback implements UserClient {
        @Override
        public UserDto getUserByUsername(String username) {
            return new UserDto();
    }

        @Override
        public UserDto getCurrentUser() {
            return new UserDto();
        }
    }
}
