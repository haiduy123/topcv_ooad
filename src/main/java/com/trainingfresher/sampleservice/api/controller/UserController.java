package com.trainingfresher.sampleservice.api.controller;


import com.trainingfresher.sampleservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${upload-file.subFolder}")
    private String subFolder;

    @Value("${upload-file.folder}")
    private String folder;

    @Autowired
    private UserService userService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) {

        //Save file to server
        String fileName = file.getOriginalFilename();
        String fileInputPath = null;
        try {
            fileInputPath = userService.writeFileToServer(file.getInputStream(), fileName, this.subFolder, this.folder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(fileInputPath);
    }

    @GetMapping ("/download")
    public ResponseEntity<Object> downloadFile(@RequestParam String fileName) throws IOException
    {
        File file = new File("D:/cv_ooad/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }
}
