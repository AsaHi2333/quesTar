package com.questionnaire.controller;

import com.questionnaire.pojo.Opt;
import com.questionnaire.pojo.Question;
import com.questionnaire.pojo.Result;
import com.questionnaire.service.OptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class OptController {
    @Autowired
    private OptService optService;

    //根据问题id查询所有选项
    @GetMapping("/opt/list/{questionId}")
    public Result list(@PathVariable Integer questionId){
        log.info("根据问题id查询所有选项:{}",questionId);
        //调用OptService查询问卷
        List<Opt> optList = optService.list(questionId);
        return Result.success(optList);
    }

    //根据选项id删除选项
    @DeleteMapping("/opt/delete/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据选项id删除选项:{}",id);
        //调用OptService删除问卷
        optService.delete(id);
        return Result.success();
    }

    //新建选项
    @PostMapping("/opt/add")
    public Result add(@RequestBody List<Opt> opts){
        log.info("新建选项:{}",opts);
        //调用OptService新增选项
        optService.add(opts);
        List<Integer> idList =new ArrayList<>();
        for( Opt opt:opts)
        {
            idList.add(opt.getId());
        }
        return Result.success(idList);
    }

    //修改选项
    @PutMapping("/opt/update")
    public Result update(@RequestBody Opt opt){
        log.info("修改选项:{}",opt);
        //调用OptService修改选项
        optService.update(opt);
        return Result.success();
    }

}
