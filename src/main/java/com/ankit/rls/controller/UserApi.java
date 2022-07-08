package com.ankit.rls.controller;

import com.ankit.rls.dto.CreateUserRequest;
import com.ankit.rls.dto.UserInfo;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserApi {

  @GetMapping
  List<UserInfo> getUsers();

  @PostMapping
  void createUser(@Valid @RequestBody CreateUserRequest createUserRequest);

}
