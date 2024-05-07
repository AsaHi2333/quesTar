package com.questionnaire.controller;

import com.questionnaire.pojo.Paper;
import com.questionnaire.pojo.Question;
import com.questionnaire.pojo.Result;
import com.questionnaire.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //根据问卷id查询问题
    @GetMapping("/question/list/{paperId}")
    public Result list(@PathVariable int paperId){
        log.info("根据问卷id查询问题:{}",paperId);
        //调用QuestionService查询问题
        List<Question> questionList=questionService.list(paperId);
        return Result.success(questionList);
    }

    //根据问题id删除问题
    @DeleteMapping("/question/delete/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据问卷id删除问卷:{}",id);
        //调用PaperService删除问卷
        questionService.delete(id);
        return Result.success();
    }

    //新增问题
    @PostMapping("/question/add")
    public Result add(@RequestBody Question question){
        log.info("新建问卷:{}",question);
        //调用PaperService新建问卷
        questionService.add(question);
        return Result.success();
    }

    //修改问题（标题、问题类型、问题选项）
    @PutMapping("/question/update")
    public Result update(@RequestBody Question question){
        log.info("修改问卷:{}",question);
        //调用QuestionService修改问题
        questionService.update(question);
        return Result.success();
    }


}
