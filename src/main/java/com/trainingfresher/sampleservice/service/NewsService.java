package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.repository.result.NewsResult;

import java.util.List;

public interface NewsService {
    List<NewsResult> getNewsInfo();
}
