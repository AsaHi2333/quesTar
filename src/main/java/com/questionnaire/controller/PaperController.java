package com.questionnaire.controller;

import com.questionnaire.pojo.Paper;
import com.questionnaire.pojo.Result;
import com.questionnaire.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/paper")

public class PaperController {
    @Autowired
    private PaperService paperService;

    //根据用户id查询问卷
    @GetMapping("/list/{userId}")
    public Result listByUserId(@PathVariable String userId){
        log.info("根据用户id查询问卷:{}",userId);
        //调用PaperService查询问卷
        List<Paper> paperList=paperService.list(userId);
        return Result.success(paperList);
    }

    //根据问卷id查询问卷
    @GetMapping("/{id}")
    public Result listByPaperId(@PathVariable Integer id){
        log.info("根据问卷id查询问卷:{}",id);
        //调用PaperService查询问卷
        Paper paper =paperService.list(id);
        return Result.success(paper);
    }

    //根据问卷id删除问卷
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据问卷id删除问卷:{}",id);
        //调用PaperService删除问卷
        paperService.delete(id);
        return Result.success();
    }

    //新建问卷
    @PostMapping("/add")
    public Result add(@RequestBody Paper paper){
        log.info("新建问卷:{}",paper);
        //调用PaperService新建问卷
        paperService.add(paper);
        return Result.success(paper.getId());
    }


    //修改问卷（标题、开始时间、结束时间）
    @PutMapping("/update")
    //修改问卷
    public Result update(@RequestBody Paper paper){
        log.info("修改问卷:{}",paper);
        //调用PaperService修改问卷
        paperService.update(paper);
        return Result.success(paper);
    }
}
