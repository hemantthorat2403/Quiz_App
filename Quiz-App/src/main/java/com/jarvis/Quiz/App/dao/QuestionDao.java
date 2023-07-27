package com.jarvis.Quiz.App.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jarvis.Quiz.App.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    
    // gives list of question based on catagory : 
    List<Question>findByCatagory(String category);

    @Query( value = "SELECT * FROM question q WHERE q.catagory=:catagory ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCatagory(String catagory, int numQ);

    // gives list of question based on difficultylevel:
         // List<Question>findByDifficultylevel(String difficultylevel);
}
