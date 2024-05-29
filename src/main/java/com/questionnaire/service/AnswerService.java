package com.questionnaire.service;

import com.questionnaire.pojo.Answer;
import com.questionnaire.pojo.OptAnswer;

import java.util.List;

public interface AnswerService {
    //新增答卷
    void add(List<Answer> answers);

    //根据问题id统计
    List<OptAnswer> getOptAnswerByQuestionId(Integer questionId);

}
