package com.trainingfresher.sampleservice.service.Impl;

import com.trainingfresher.sampleservice.repository.NewsRepository;
import com.trainingfresher.sampleservice.repository.result.NewsResult;
import com.trainingfresher.sampleservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<NewsResult> getNewsInfo() {
        List<NewsResult> results = newsRepository.getNewsInfo();
        return results;
    }
}
