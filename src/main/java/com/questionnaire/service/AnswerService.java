package com.questionnaire.service;

import com.questionnaire.pojo.Answer;
import com.questionnaire.pojo.CompleteAnswer;
import com.questionnaire.pojo.OptAnswer;

import java.util.List;

public interface AnswerService {

    void add(List<Answer> answers);


    List<OptAnswer> getOptAnswerByQuestionId(Integer questionId);

  CompleteAnswer getCompleteAnswerByQuestionId(Integer questionId);
}
