package com.jarvis.Quiz.App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jarvis.Quiz.App.dao.QuestionDao;
import com.jarvis.Quiz.App.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {

        /* Exception Handling */ 
       try {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
       }catch(Exception e){
        e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionsByCatagory(String catagory){
        /* Exception Handling */ 
       try {
        return new ResponseEntity<>(questionDao.findByCatagory(catagory), HttpStatus.OK);
       }catch(Exception e){
        e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
         questionDao.save(question);
         return new ResponseEntity<>("Success!",HttpStatus.CREATED);
       
    }


    // gives list of question based on Difficulty-level :
    // public List<Question> getQuestionsByDifficultylevel(String difficultylevel) {
    //     return questionDao.findByDifficultylevel(difficultylevel);
    // }


   
    
}
