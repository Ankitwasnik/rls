package com.ankit.rls.service;

import com.ankit.rls.dto.CreateUserRequest;
import com.ankit.rls.dto.UserInfo;
import com.ankit.rls.mapper.UserMapper;
import com.ankit.rls.model.User;
import com.ankit.rls.repository.UserRepository;
import java.util.List;
import javax.validation.constraints.NotBlank;
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

  @Override
  public void createUser(CreateUserRequest createUserRequest) {
    verifyEmailIsUnique(createUserRequest.getEmail());
    User user = userMapper.mapCreateRequest(createUserRequest);
    userRepository.save(user);
  }

  private void verifyEmailIsUnique(@NotBlank String email) {
    if (userRepository.existsByEmail(email)) {
      log.error("User with email {} already exists", email);
      throw new RuntimeException("User with email " + email + " already exists");
    }
  }

}
