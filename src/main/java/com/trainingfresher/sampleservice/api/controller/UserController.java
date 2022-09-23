package com.trainingfresher.sampleservice.api.controller;


import com.trainingfresher.sampleservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
