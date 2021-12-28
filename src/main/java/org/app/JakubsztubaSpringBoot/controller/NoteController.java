package org.app.JakubsztubaSpringBoot.controller;

import org.app.JakubsztubaSpringBoot.model.Note;
import org.app.JakubsztubaSpringBoot.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {
    NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfNotes() {
        return noteService.listAllNotes();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long noteId) {
        return noteService.deleteNote(noteId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Note note) {
        return noteService.updateNote(note);
    }
}
