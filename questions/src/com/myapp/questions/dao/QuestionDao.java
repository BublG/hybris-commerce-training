package com.myapp.questions.dao;

import com.myapp.questions.model.QuestionModel;

import java.util.Date;
import java.util.List;

public interface QuestionDao {
    List<QuestionModel> getLastAddedQuestions(Date afterDate);
}
