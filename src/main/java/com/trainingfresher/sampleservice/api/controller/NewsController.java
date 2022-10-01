package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.api.response.ApiResponse;
import com.trainingfresher.sampleservice.repository.result.NewsResult;
import com.trainingfresher.sampleservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @PostMapping("/info")
    public ResponseEntity<ApiResponse> getNewsInfo() {
        List<NewsResult> results = newsService.getNewsInfo();
        ApiResponse response = ApiResponse.success(results, HttpStatus.OK.value(), "Danh sách thông tin tuyển dụng");
        return ResponseEntity.ok(response);
    }
}
