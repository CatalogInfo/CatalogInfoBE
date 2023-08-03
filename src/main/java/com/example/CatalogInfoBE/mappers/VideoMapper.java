package com.example.CatalogInfoBE.mappers;

import com.example.CatalogInfoBE.dto.requests.VideoRequest;
import com.example.CatalogInfoBE.dto.responses.VideoResponse;
import com.example.CatalogInfoBE.models.table_entities.Video;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ModelStringMapper.class, CategoryMapper.class, ModelMapper.class })
public interface VideoMapper extends BaseDtoMapper<Video, VideoRequest, VideoResponse> {
    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);
}
