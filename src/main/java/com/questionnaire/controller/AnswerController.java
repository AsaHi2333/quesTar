package com.questionnaire.controller;


import com.questionnaire.pojo.*;
import com.questionnaire.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/answer")
public class AnswerController {
    //注入对象
    @Autowired
    private AnswerService answerService;


//添加答卷:只是添加一条答案
    //每输入一个问题的答案，便会存入数据库，如果用户填完之后又把原来的答案更改，会怎样？
    //所有题型都使用一种添加吗？

    //传入一个或是多个答案
    @PostMapping("/add")
    public Result add(@RequestBody List<Answer> answers) {

        log.info("添加答卷：{}", answers);
        answerService.add(answers);
        return Result.success();
    }

    //根据问题id查询该问题的完整答案
    @GetMapping("/getQuestionAnswer/{questionId}")
    public Result getQuestionAnswer(@PathVariable Integer questionId){
        log.info("查询问题的答案：{}",questionId);

        //List<OptAnswer> optAnswerList=answerService.getOptAnswerByQuestionId(questionId);
        CompleteAnswer completeAnswer=answerService.getCompleteAnswerByQuestionId(questionId);
        return Result.success(completeAnswer);
    }

    @GetMapping("/getPaperAnswer/{paperId}")
    public Result getPaperAnswer(@PathVariable Integer paperId)
    {
       log.info("查询问卷的所有问题的答卷：{}",paperId);
        CompleteAnswerPlusPaperTitle completeAnswerPlusPaperTitle=answerService.getPaperAnswerByPaperId(paperId);


        return Result.success(completeAnswerPlusPaperTitle);
    }




}
