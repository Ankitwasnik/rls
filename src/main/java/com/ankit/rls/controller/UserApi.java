package com.ankit.rls.controller;

import com.ankit.rls.dto.UserInfo;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserApi {

  @GetMapping
  List<UserInfo> getUsers();

}
