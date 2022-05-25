package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/note")
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String postNote (Authentication authentication, @ModelAttribute("newNote") Note note, Model model) {
        Integer userId = userService.getUserIdByName(authentication.getName());
        note.setUserId(userId);
        System.out.println("note will now be added for user with userId: " + userId);
        try {
            noteService.addNote(note);
            model.addAttribute("changeStatus", "ok");
            System.out.println("Inside Result Controller:   noteId:"+note.getNoteId());
            System.out.println("Inside Result Controller:   noteTitle:"+note.getNoteTitle());
            System.out.println("Inside Result Controller:   noteDescription:"+note.getNoteDescription());

        } catch (Exception e) {
            model.addAttribute("changeStatus", "failure");
        }

        return "result";
    }

    @PutMapping
    public String putNote (Authentication authentication, @ModelAttribute("newNote") Note note, Model model) {
        Integer userId = userService.getUserIdByName(authentication.getName());
        System.out.println("note update of note with noteId: " + note.getNoteId());
        try {
            noteService.updateNote(note);
            model.addAttribute("changeStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("changeStatus", "failure");
        }
            return "result";
    }

    @DeleteMapping
    public String deleteNote ( @RequestParam(name = "noteId") Integer noteId, Model model) {
        System.out.println("note deletion of note with noteId: " + noteId);
        try {
            noteService.deleteNote(noteId);
            model.addAttribute("changeStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("changeStatus", "failure");
        }
        return "result";
    }

}
