package com.example.CatalogInfoBE.mappers;

import com.example.CatalogInfoBE.models.interfaces.Model;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ModelMapper {
    default Long toId(Model entity) {
        return entity.getId();
    }

    List<Long> toIds(List<Model> entities);
}
