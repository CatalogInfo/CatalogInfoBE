package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.dto.requests.VideoRequest;
import com.example.CatalogInfoBE.dto.responses.VideoResponse;
import com.example.CatalogInfoBE.mappers.VideoMapper;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.models.table_entities.Video;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import com.example.CatalogInfoBE.repos.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public VideoResponse createVideo(long categoryId, VideoRequest videoRequest) {
        Category category = categoryRepository.getReferenceById(categoryId);
        Video video = VideoMapper.INSTANCE.toEntity(videoRequest);

        category.addVideo(video);
        video.setCategory(category);

        categoryRepository.save(category);
        videoRepository.save(video);

        return VideoMapper.INSTANCE.toDto(video);
    }
}
