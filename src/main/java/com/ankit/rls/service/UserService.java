package com.ankit.rls.service;

import com.ankit.rls.dto.CreateUserRequest;
import com.ankit.rls.dto.UserInfo;
import java.util.List;

public interface UserService {

  List<UserInfo> getUsers();

  void createUser(CreateUserRequest createUserRequest);
}
