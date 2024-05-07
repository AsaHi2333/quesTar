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

    //根据问卷id查询问题
    @Override
    public List<Question> list(Integer paperId) {
        return questionMapper.listQuestionsByPaperId(paperId);
    }

    //根据问题id删除问题
    @Override
    public void delete(Integer id) {
        questionMapper.deletePaperById(id);
    }

    //新增问题
    @Override
    public void add(Question question) {

    }
}
