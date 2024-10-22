package com.questionnaire.service;

import com.questionnaire.pojo.Opt;

import java.util.List;

public interface OptService {
    //根据问题id查询所有选项
    List<Opt> list(Integer questionId);

    //根据选项id删除选项
    void delete(Integer id);

    //新建选项
    void add(List<Opt> opts);

    //修改选项
    void update(Opt opt);
}
