package com.trainingfresher.sampleservice.service;

import java.io.InputStream;

public interface UserService {
    public String writeFileToServer(InputStream inputStream, String fileName, String subFolder, String folder) throws Exception;
}
