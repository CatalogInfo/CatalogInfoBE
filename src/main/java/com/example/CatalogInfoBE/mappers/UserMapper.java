package com.example.CatalogInfoBE.mappers;


import com.example.CatalogInfoBE.dto.requests.UserRequest;
import com.example.CatalogInfoBE.dto.responses.UserResponse;
import com.example.CatalogInfoBE.models.table_entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ModelMapper.class })
public interface UserMapper extends BaseDtoMapper<User, UserRequest, UserResponse> {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
