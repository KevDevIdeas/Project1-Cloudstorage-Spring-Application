package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/credential")
public class CredentialController {

    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String postCredential (Authentication authentication, @ModelAttribute("newCredential") Credential credential, Model model) {
        Integer userId = userService.getUserIdByName(authentication.getName());
        credential.setUserId(userId);
        System.out.println("credential will now be added for user with userId: " + userId);
        try {
            credentialService.createCredential(credential);
            model.addAttribute("credentialUploadStatus", "ok");

        } catch (Exception e) {
            model.addAttribute("credentialUploadStatus", "failure");
        }

        return "result";
    }

    /*
    @PutMapping
    public String putNote (Authentication authentication, @ModelAttribute("newNote") Note note, Model model) {
        Integer userId = userService.getUserIdByName(authentication.getName());
        System.out.println("note update of note with noteId: " + note.getNoteId());
        try {
            noteService.updateNote(note);
            model.addAttribute("noteUploadStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("noteUploadStatus", "failure");
        }
            return "result";
    }

    @DeleteMapping
    public String deleteNote ( @RequestParam(name = "noteId") Integer noteId, Model model) {
        System.out.println("note deletion of note with noteId: " + noteId);
        try {
            noteService.deleteNote(noteId);
            model.addAttribute("noteUploadStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("noteUploadStatus", "failure");
        }
        return "result";
    }
*/
}
