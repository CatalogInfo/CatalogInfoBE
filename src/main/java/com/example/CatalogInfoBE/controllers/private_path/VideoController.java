package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.dto.requests.VideoRequest;
import com.example.CatalogInfoBE.dto.responses.VideoResponse;
import com.example.CatalogInfoBE.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping(value="/", produces = "application/json")
public class VideoController {
    @Autowired
    VideoService videoService;

    @PostMapping("/category/{categoryId}/video")
    public ResponseEntity<VideoResponse> createVideo(@PathVariable("categoryId") long categoryId, @RequestHeader HttpHeaders headers, @RequestBody VideoRequest videoRequest) {
        return new ResponseEntity<>(videoService.createVideo(categoryId, videoRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/category/{categoryId}/video/{videoId}")
    public ResponseEntity<String> deleteBook(@PathVariable("categoryId") Long categoryId,
                                             @PathVariable("videoId") String videoId) {
        videoService.deleteVideo(videoId, categoryId);

        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
