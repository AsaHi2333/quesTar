package com.questionnaire.service.impl;

import com.questionnaire.mapper.PaperMapper;
import com.questionnaire.pojo.Paper;
import com.questionnaire.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Override
    //根据用户id查询问卷
    public List<Paper> list(String userId) {
        return paperMapper.listPaperByUserId(userId);
    }

    @Override
    //根据问卷id删除问卷
    public void delete(String id){
        paperMapper.deletePaperById(id);
    }

    @Override
    //新建问卷
    public void add(Paper paper) {
        paper.setCreateTime(LocalDateTime.now());
        paper.setStartTime(LocalDateTime.now());
        paper.setEndTime(LocalDateTime.now());
        paperMapper.insert(paper);
    }

    @Override
    //修改问卷（标题、开始时间、结束时间）
    public void update(Paper paper) {
        paperMapper.update(paper);
    }
}
