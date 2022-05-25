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
            System.out.println("credential has been added with password encrpyted: " + credential.getPassword() + " password deccrypted: "+ credential.getDecryptedPassword() + " key: " + credential.getKey());
            model.addAttribute("changeStatus", "ok");

        } catch (Exception e) {
            model.addAttribute("changeStatus", "failure");
        }

        return "result";
    }

    @PutMapping
    public String putCredential (Authentication authentication, @ModelAttribute("newCredential") Credential credential, Model model) {
        Integer userId = userService.getUserIdByName(authentication.getName());
        System.out.println("credential update of credential with credentialId: " + credential.getCredentialId());
        try {
            credentialService.updateCredential(credential);
            model.addAttribute("changeStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("changeStatus", "failure");
        }
            return "result";
    }

    @DeleteMapping
    public String deleteCredential ( @RequestParam(name = "credentialId") Integer credentialId, Model model) {
        System.out.println("deletion of credential with credentialId: " + credentialId);
        try {
            credentialService.deleteCredential(credentialId);
            model.addAttribute("changeStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("changeStatus", "failure");
        }
        return "result";
    }
}
