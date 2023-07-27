package com.jarvis.Quiz.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jarvis.Quiz.App.model.Question;
import com.jarvis.Quiz.App.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService; // <-- object of Ques.Service

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // request to ques-Service to get List pf Q. alc. catagory :

    @GetMapping("catagory/{catagory}") // <-- getmapping becoz we fetching the data.
    public ResponseEntity<List<Question>> getQuestionsByCatagory(@PathVariable String catagory) {
        return questionService.getQuestionsByCatagory(catagory);
    }

    // request to ques-Service to get List pf Q. alc. difficulty-level :

    // @GetMapping("difficultylevel/{difficultylevel}")
    // public List<Question> getQuestionsByDifficultylevel(@PathVariable String
    // difficultylevel) {
    // return questionService.getQuestionsByDifficultylevel(difficultylevel);
    // }

    // to add question from user into DB -

    @PostMapping("add") // <-- postmappping coz we adding the data.
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
