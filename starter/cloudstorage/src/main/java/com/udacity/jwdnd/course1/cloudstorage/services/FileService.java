package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }


    public void addFile(MultipartFile multipartFile, Integer userId) throws IOException {
        fileMapper.insert(new File(null,multipartFile.getOriginalFilename(),multipartFile.getContentType(),String.valueOf(multipartFile.getSize()), userId, multipartFile.getBytes()));
    }

    public void deletefile (int fileId) {fileMapper.deleteByFileId(fileId);}

    public List<File> getFiles(int userId){

        List <File> files = fileMapper.getFiles(userId);
        return files;
    }

    public File downloadFile(int fileId, int userId){
        File downloadedFile = fileMapper.downloadFile(fileId, userId);
        return downloadedFile;
    }

    public boolean checkIfFileExists(String fileName, int userId){
        return !fileMapper.checkFileExistsByName(fileName, userId).isEmpty();
    }
}
