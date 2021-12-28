package org.app.JakubsztubaSpringBoot.service;

import org.app.JakubsztubaSpringBoot.model.Note;
import org.app.JakubsztubaSpringBoot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public ResponseEntity<Object> listAllNotes() {
        List<Note> noteList = noteRepository.findAll();
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    public ResponseEntity<Object> addNote(Note note) {
        noteRepository.save(note);
        return new ResponseEntity<>("Note is Successfully added", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteNote(Long noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            noteRepository.delete(optionalNote.get());
            return new ResponseEntity<>("Note Successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Note against this Id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> updateNote(Note note) {
        Optional<Note> optionalNote = noteRepository.findById(note.getId());
        if (optionalNote.isPresent()) {
            noteRepository.save(note);
            return new ResponseEntity<>("Note Successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Note against this Id", HttpStatus.OK);
        }
    }
}
