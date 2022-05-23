package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    private UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NoteService bean");
    }

    public void addNote(Note note){
        noteMapper.insert(new Note(null, note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
    }

    public void updateNote (Note note){
        noteMapper.updateNote(note);
    }

    public void deleteNote (int noteId) {noteMapper.deleteByNoteId(noteId);}

    public List<Note> getNotes(int userId){

        List <Note> notes = noteMapper.getNotes(userId);
        System.out.println("The UserId from GetNotes is" + userId);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-7s %-17s %-17s %-7s", "NOTE ID", "NOTE TITLE", "NOTE DESCRIPTION", "USER ID");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (final Note row : notes) {
            System.out.format("%08d %-17s %-17s %08d%n", row.getNoteId(), row.getNoteTitle(), row.getNoteDescription(), row.getUserId());
        }
        return noteMapper.getNotes(userId);
    }

}
