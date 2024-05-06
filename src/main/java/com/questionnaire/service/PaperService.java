package com.questionnaire.service;

import org.springframework.stereotype.Service;
import com.questionnaire.pojo.Paper;

import java.util.List;


public interface PaperService {
    //根据用户id查询问卷
    public List<Paper> list(String userId);

    //根据问卷id删除问卷
    public void delete(String id);

    //新建问卷
    public void add(Paper paper);

    //修改问卷（标题、开始时间、结束时间）
    void update(Paper paper);
}
