package com.ankit.rls.controller;

import com.ankit.rls.dto.CreateUserRequest;
import com.ankit.rls.dto.UserInfo;
import com.ankit.rls.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserApiController implements UserApi {

  private final UserService userService;

  @Override
  public List<UserInfo> getUsers() {
    return userService.getUsers();
  }

  @Override
  public void createUser(CreateUserRequest createUserRequest) {
    userService.createUser(createUserRequest);
  }

}
