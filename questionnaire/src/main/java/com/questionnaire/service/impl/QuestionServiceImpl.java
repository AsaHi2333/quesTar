package com.questionnaire.service.impl;

import com.questionnaire.mapper.QuestionMapper;
import com.questionnaire.pojo.Question;
import com.questionnaire.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public List<Question> list(String id) {
        return questionMapper.listQuestionsById(id);
    }
}
