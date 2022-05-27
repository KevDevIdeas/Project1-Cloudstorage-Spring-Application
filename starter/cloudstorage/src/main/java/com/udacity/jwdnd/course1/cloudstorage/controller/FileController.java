package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/home/file")
public class FileController {

    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping
        public String handleFileUpload (MultipartFile fileUpload, Authentication authentication, Model model) throws IOException {
        Integer userId = userService.getUserIdByName(authentication.getName());

        if (fileService.checkIfFileExists(fileUpload.getOriginalFilename(), userId)) {
            model.addAttribute("fileUploadStatus","fileNameAlreadyExists");
        }
        else if(fileUpload.getOriginalFilename()==""){
            model.addAttribute("fileUploadStatus","noFileName");
        }
        else if(fileUpload.getSize()>= 2000000){
            model.addAttribute("fileUploadStatus","tooLarge");
        }
        else if(fileUpload.isEmpty()){
            model.addAttribute("fileUploadStatus","noFileSelected");
        } else{
            try{
                fileService.addFile(fileUpload, userId);
                model.addAttribute("changeStatus", "ok");
            }catch (Exception e){
                model.addAttribute("changeStatus", "failure");
            }
        }

        return "result";
    }

    @DeleteMapping
    public String deleteFile ( @RequestParam(name = "fileId") Integer fileId, Model model) {

        try {
            fileService.deletefile(fileId);
            model.addAttribute("changeStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("changeStatus", "failure");
        }
        return "result";
    }

    @GetMapping
    public ResponseEntity downloadFile (@RequestParam(name = "fileId") Integer fileId, Authentication authentication, Model model) {
    Integer userId = userService.getUserIdByName(authentication.getName());
    File file = fileService.downloadFile(fileId, userId);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment ; filename = \"" + file.getFileName() + "\"")
            .body(file.getFileData());
    }

}
