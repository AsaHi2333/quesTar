package com.questionnaire.service;

import com.questionnaire.pojo.Question;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuestionService {
    //根据问卷id查询问题
    List<Question> list(Integer paperId);

    //根据问题id删除问题
    void delete(Integer id);

    //新增问题
    void add(Question question);

    //修改问题
    void update(Question question);
}
