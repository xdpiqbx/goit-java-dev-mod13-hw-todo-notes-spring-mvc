package com.dpiqb.notes.mvc;

import com.dpiqb.notes.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@RequestMapping("/note")
@Controller
public class NoteController {
  private final NoteService noteService;
  @GetMapping("/list")
  public ModelAndView getListOfNotes(){
    ModelAndView result = new ModelAndView("notes");
    result.addObject("noteList", noteService.listAll());
    return result;
  }
  @PostMapping("/delete")
  public RedirectView deleteNote(@RequestParam long id){
    ModelAndView result = new ModelAndView("notes");
    noteService.deleteById(id);
    System.out.println(id);
    return new RedirectView("/note/list");
  }
}
/*
+++ GET /note/list - отримати список нотаток. Виводиться список нотаток (title та content), кожну нотатку можна видалити або редагувати
+++ POST /note/delete - видалити нотатку по ID. Після видалення нотатки відбувається редирект на /note/list
GET /note/edit?id=xxx - сторінка редагування нотатку (відкривається по натисненню на кнопку Редагувати на списку нотаток).
POST /note/edit - сюди відправляється запит на редагування нотатки. Після збереження оновленого контенту нотатки відбувається редирект на /note/list

POST /note/add - сюди відправляється запит на додавання нотатки. Після збереження нотатки відбувається редирект на /note/list
*/