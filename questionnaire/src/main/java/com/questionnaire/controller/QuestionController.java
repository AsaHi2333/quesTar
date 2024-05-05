package com.questionnaire.controller;

import com.questionnaire.pojo.Paper;
import com.questionnaire.pojo.Question;
import com.questionnaire.pojo.Result;
import com.questionnaire.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/questionlist/{id}")
    public Result list(@PathVariable String id){
        log.info("根据问卷id查询问题:{}",id);
        //调用questionService查询问卷
        List<Question> questionList=questionService.list(id);
        return Result.success(questionList);
    }
}
