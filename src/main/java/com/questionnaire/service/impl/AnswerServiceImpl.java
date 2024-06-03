package com.questionnaire.service.impl;

import com.questionnaire.mapper.AnswerMapper;
import com.questionnaire.mapper.OptMapper;
import com.questionnaire.mapper.PaperMapper;
import com.questionnaire.mapper.QuestionMapper;
import com.questionnaire.pojo.*;
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
    @Autowired
    private PaperMapper paperMapper;

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

        //统计所有选项选择人数的总数
        Integer sum=0;
        for(Integer optId:optIdList)
        {
            Integer perCount=answerMapper.countOpt(optId);
            sum+=perCount;
        }

        for(Integer optId : optIdList)
        {
            //单条optanswer封装
            String name=optMapper.optName(optId);//选项名称
            Integer count =answerMapper.countOpt(optId);//选项答案数量
            if(sum!=0) {
                Double rate = (double) count / ((double) sum) * 100;
                String formattedResult = String.format("%.2f", rate);
                optAnswerList.add(new OptAnswer(name,count,formattedResult));
            }
            else
                optAnswerList.add(new OptAnswer(name,count,"0"));
        }

        return optAnswerList;
    }


    //通过问题id获取完整的问题答案（包括选择题和文本题）
    @Override
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


    @Override
    public CompleteAnswerPlusPaperTitle getPaperAnswerByPaperId(Integer paperId) {
        //根据问卷id查询问卷标题
        String paperTitle=paperMapper.listPaperById(paperId).getTitle();
        //根据问卷id查询出所有问题的id
        List<Question> questionList=questionMapper.listQuestionsByPaperId(paperId);
        //对于每一条问题，查询其单条答案并封装进完整答案类当中
        List<CompleteAnswer> completeAnswerList=new ArrayList<>();
        for(Question question :questionList)
        {
            CompleteAnswer completeAnswer=getCompleteAnswerByQuestionId(question.getId());
            completeAnswerList.add(completeAnswer);
        }
        CompleteAnswerPlusPaperTitle completeAnswerPlusPaperTitle=new CompleteAnswerPlusPaperTitle(paperTitle,completeAnswerList);
        return completeAnswerPlusPaperTitle;
    }
}
