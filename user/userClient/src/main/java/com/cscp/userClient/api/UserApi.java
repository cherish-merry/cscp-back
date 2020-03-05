package com.cscp.userClient.api;

import dto.RoleDto;
import dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/23 - 11:26
 */
public interface UserApi {
    @PostMapping("/api/getUserByUsername")
    UserDto getUserByUsername(@RequestBody  String username);

    @PostMapping("/api/getUserById")
    public UserDto getUserById(String id);

    @GetMapping("/api/{username}/roles")
    List<RoleDto> getRolesByUsername(@PathVariable("username") String username);
}
