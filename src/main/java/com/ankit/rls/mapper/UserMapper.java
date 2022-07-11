package com.ankit.rls.mapper;

import com.ankit.rls.dto.CreateUserRequest;
import com.ankit.rls.dto.UserInfo;
import com.ankit.rls.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	List<UserInfo> mapUsers(List<User> users);

	UserInfo mapUser(User user);

	User mapCreateRequest(CreateUserRequest createUserRequest);
}
