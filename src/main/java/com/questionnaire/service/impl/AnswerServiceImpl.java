package com.questionnaire.service.impl;

import com.questionnaire.mapper.AnswerMapper;
import com.questionnaire.mapper.OptMapper;
import com.questionnaire.pojo.Answer;
import com.questionnaire.pojo.OptAnswer;
import com.questionnaire.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private OptMapper optMapper;

    @Override
    public void add(List<Answer> answers) {

        for(Answer answer : answers)
        {
            answer.setCreateTime(LocalDateTime.now());
        }//补全设置时间
        answerMapper.insert(answers);

    }

    //通过问题id获取问题结果
    @Override
    public List<OptAnswer> getOptAnswerByQuestionId(Integer questionId) {
        //获取所有optid
        List<Integer> optIdList= optMapper.optIdList(questionId);
        List<OptAnswer> optAnswerList=new ArrayList<>();
        for(Integer optId : optIdList)
        {
            String name=optMapper.optName(optId);//选项名称
            Integer count =answerMapper.countOpt(optId);//选项答案数量
            optAnswerList.add(new OptAnswer(name,count));
        }

        return optAnswerList;
    }
}
