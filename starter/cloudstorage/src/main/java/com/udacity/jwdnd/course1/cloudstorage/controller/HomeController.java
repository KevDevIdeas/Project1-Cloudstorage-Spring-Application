package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;
    private UserService userService;
    private CredentialService credentialService;
    private FileService fileService;

    public HomeController(NoteService noteService, UserService userService, CredentialService credentialService, FileService fileService) {
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage (Authentication authentication, @ModelAttribute("newNote") Note note, @ModelAttribute("newCredential") Credential credential, @ModelAttribute("newFile") File file, Model model) {
        int userId = userService.getUserIdByName(authentication.getName());
        model.addAttribute("notesOnPage", this.noteService.getNotes(userId));
        model.addAttribute("credentialsOnPage", this.credentialService.getCredentials(userId));
        model.addAttribute("filesOnPage", this.fileService.getFiles(userId));
        return "home";
    }

}
