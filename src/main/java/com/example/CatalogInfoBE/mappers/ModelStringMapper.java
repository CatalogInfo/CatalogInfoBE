package com.example.CatalogInfoBE.mappers;

import com.example.CatalogInfoBE.models.interfaces.Model;
import com.example.CatalogInfoBE.models.interfaces.ModelString;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ModelStringMapper {
    default String toId(ModelString entity) {
        return entity.getId();
    }

    List<String> toIds(List<ModelString> entities);
}
