package com.jarvis.Quiz.App.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarvis.Quiz.App.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer>{

}
