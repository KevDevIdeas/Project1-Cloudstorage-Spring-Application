package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getNotes(int userId);


    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId} )")
    @Options(useGeneratedKeys = true, keyColumn = "noteId", keyProperty = "noteId")
    int insert(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteByNoteId(Integer noteId);

    @Update ("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription= #{noteDescription} WHERE noteId = #{noteId}")
    void updateNote(Note note);

}
