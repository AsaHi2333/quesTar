package com.questionnaire.service.impl;

import com.questionnaire.mapper.AnswerMapper;
import com.questionnaire.mapper.OptMapper;
import com.questionnaire.mapper.QuestionMapper;
import com.questionnaire.pojo.Answer;
import com.questionnaire.pojo.CompleteAnswer;
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
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void add(List<Answer> answers) {

        for(Answer answer : answers)
        {
            answer.setCreateTime(LocalDateTime.now());
        }//补全设置时间
        answerMapper.insert(answers);

    }

    //通过问题id获取选择题答案
    @Override
    public List<OptAnswer> getOptAnswerByQuestionId(Integer questionId) {
        //获取所有optid
        List<Integer> optIdList= optMapper.optIdList(questionId);
        List<OptAnswer> optAnswerList=new ArrayList<>();
        for(Integer optId : optIdList)
        {
            //单条optanswer封装
            String name=optMapper.optName(optId);//选项名称
            Integer count =answerMapper.countOpt(optId);//选项答案数量
            optAnswerList.add(new OptAnswer(name,count));
        }

        return optAnswerList;
    }

    //通过问题id获取完整的问题答案（包括选择题和文本题）
    public CompleteAnswer getCompleteAnswerByQuestionId(Integer questionId)
    {
        //获取选择题答案
        List<OptAnswer> optAnswerList=getOptAnswerByQuestionId(questionId);
        //获取文本题答案
        List<Answer> answerList= answerMapper.answerList(questionId);
        List<String> textAnswerList=new ArrayList<>();
        for(Answer answer :answerList)
        {
            textAnswerList.add(answer.getText());

        }
        String Title=questionMapper.getQuestionTitle(questionId);
        String Type= questionMapper.getQuestionType(questionId);

        //封装
        CompleteAnswer completeAnswer=new CompleteAnswer(questionId,Title,Type,optAnswerList,textAnswerList);

        return completeAnswer;
    }





}
