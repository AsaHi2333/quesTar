package com.questionnaire.service;

import com.questionnaire.pojo.Question;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuestionService {
    //查询对应问卷id查询问题
    List<Question> list(String id);
}
