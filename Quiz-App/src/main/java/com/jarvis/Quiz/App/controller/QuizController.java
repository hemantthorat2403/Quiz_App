package com.jarvis.Quiz.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jarvis.Quiz.App.model.QuestionWrapper;
import com.jarvis.Quiz.App.model.Response;
import com.jarvis.Quiz.App.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    //creation of quiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String catagory, @RequestParam int numQ,
            @RequestParam String title) {
        return quizService.createQuiz(catagory,numQ, title);
    }


    // getting quiz 
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    //since sending requesto submit the quiz 
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}