package com.trainingfresher.sampleservice.service.Impl;

import com.trainingfresher.sampleservice.repository.UserRepository;
import com.trainingfresher.sampleservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public String writeFileToServer(InputStream inputStream, String fileName, String subFolder, String folder)
            throws Exception {

        String safeFileName = safeToString(fileName) ;//+ File.pathSeparator + UString.extractFileExt(fileName);

        String uploadPath = folder + File.separator + getSafeFileName(subFolder) + File.separator;
        File udir = new File(uploadPath);
        if (!udir.exists()) {
            udir.mkdirs();
        }
        try (OutputStream out = new FileOutputStream(udir.getAbsolutePath() + File.separator + safeFileName)) {
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = inputStream.read(buffer, 0, 1024 * 8)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return uploadPath + File.separator + safeFileName;

    }



    public static String getSafeFileName(String input) {
        StringBuilder sb = new StringBuilder();
        if (input != null) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c != '/' && c != '\\' && c != 0 ) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null || obj1.toString().isEmpty()) {
            return defaultValue;
        }

        return obj1.toString();
    }

    public static String safeToString(Object obj1) {
        return safeToString(obj1, "");
    }
}
