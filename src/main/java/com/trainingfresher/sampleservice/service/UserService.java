package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.repository.result.NewsResult;

import java.io.InputStream;
import java.util.List;

public interface UserService {
    public String writeFileToServer(InputStream inputStream, String fileName, String subFolder, String folder) throws Exception;
    public List<Object> searchJob(String address, String salary, String position);
}
