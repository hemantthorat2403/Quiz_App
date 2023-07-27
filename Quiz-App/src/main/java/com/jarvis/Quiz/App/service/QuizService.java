package com.jarvis.Quiz.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jarvis.Quiz.App.dao.QuestionDao;
import com.jarvis.Quiz.App.dao.QuizDao;
import com.jarvis.Quiz.App.model.Question;
import com.jarvis.Quiz.App.model.QuestionWrapper;
import com.jarvis.Quiz.App.model.Quiz;
import com.jarvis.Quiz.App.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String catagory, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCatagory(catagory, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);  // optional coz list must not return empty
                                                     // basically handling the null condition by using the optional    
                                                     
        List<Question> questionsFromDB = quiz.get().getQuestions(); //getting question from db
        List<QuestionWrapper> questionsForUser = new ArrayList<>();  // craeting arraylist to gave it to user 
        for(Question q : questionsFromDB){                           // adding each question in arraylist through for loop
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        
        return new ResponseEntity<>(questionsForUser , HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get(); // option for 'optional' but optional is better..!
        List<Question> questions = quiz.getQuestions(); // questions stored
        int right = 0;
        int i= 0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;  // checks if response and ans are equal if yes increment it !

            i++;    // index of responses
        }

        return new ResponseEntity<>(right, HttpStatus.OK);

    }
}
