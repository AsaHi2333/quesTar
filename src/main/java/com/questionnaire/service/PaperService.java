package com.questionnaire.service;

import org.springframework.stereotype.Service;
import com.questionnaire.pojo.Paper;

import java.util.List;


public interface PaperService {
    //根据用户id查询问卷
    List<Paper> list(String userId);

    //根据问卷id查询问卷
    Paper list(Integer id);

    //根据问卷id删除问卷
    void delete(Integer id);

    //新建问卷
    void add(Paper paper);

    //修改问卷
    void update(Paper paper);
}
