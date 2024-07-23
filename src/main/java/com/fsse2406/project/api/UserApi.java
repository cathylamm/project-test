package com.fsse2406.project.api;

import com.fsse2406.project.data.user.domainObject.response.UserResponseData;
import com.fsse2406.project.data.user.dto.response.UserResponseDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {
    @GetMapping("/me/details")
    public UserResponseDto getMyUserDetails(JwtAuthenticationToken jwt){
        UserResponseDto loginUser = new UserResponseDto(jwt);
        return loginUser;
    }
}
