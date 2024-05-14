package com.questionnaire.service.impl;

import com.questionnaire.mapper.OptMapper;
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
    @Autowired
    private OptMapper optMapper;

    //根据问卷id查询问题
    @Override
    public List<Question> list(Integer paperId) {
        List<Question> questionList= questionMapper.listQuestionsByPaperId(paperId);
        for(Question question:questionList){
            question.setOptList(optMapper.listOptByQuestionId(question.getId()));
        }
        return questionList;
    }

    //根据问题id删除问题
    @Override
    public void delete(Integer id) {
        questionMapper.deletePaperById(id);
    }

    //新增问题Question question
    @Override
    public void add(List<Question> questions) {
        questionMapper.insert(questions);
    }

    //修改问题
    @Override
    public void update(Question question) {
        questionMapper.update(question);
    }
}
