package com.ankit.rls.mapper;

import com.ankit.rls.dto.UserInfo;
import com.ankit.rls.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	List<UserInfo> mapUsers(List<User> users);

	UserInfo mapUser(User user);

}
