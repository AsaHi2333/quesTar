package com.questionnaire.controller;

import com.questionnaire.pojo.Opt;
import com.questionnaire.pojo.Result;
import com.questionnaire.service.OptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /*
    //新建问卷
    @PostMapping("/paper/add")
    public Result add(@RequestBody Paper paper){
        log.info("新建问卷:{}",paper);
        //调用PaperService新建问卷
        paperService.add(paper);
        return Result.success();
    }

    //修改问卷（标题、开始时间、结束时间）
    @PutMapping("/paper/update")
    public Result update(@RequestBody Paper paper){
        log.info("修改问卷:{}",paper);
        //调用PaperService修改问卷
        paperService.update(paper);
        return Result.success();
    }

 */
}
