package com.pezhvak.project.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAppMapper {
    UserApp toUser(UserAppDTO userAppDTO);

    UserAppDTO toUserDTO(UserApp userApp);

    List<UserApp> toUsers(List<UserAppDTO> userAppDTOS);

    List<UserAppDTO> toUserDTOs(List<UserApp> userApps);
}
