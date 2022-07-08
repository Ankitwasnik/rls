package com.ankit.rls.service;

import com.ankit.rls.dto.UserInfo;
import com.ankit.rls.mapper.UserMapper;
import com.ankit.rls.model.User;
import com.ankit.rls.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public List<UserInfo> getUsers() {
    List<User> users = userRepository.findAll();
    return userMapper.mapUsers(users);
  }

}
