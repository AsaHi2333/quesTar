package com.questionnaire.service.impl;

import com.questionnaire.mapper.OptMapper;
import com.questionnaire.pojo.Opt;
import com.questionnaire.service.OptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptServiceImpl implements OptService {
    @Autowired
    private OptMapper optMapper;

    @Override
    //根据问题id查询所有选项
    public List<Opt> list(Integer questionId) {
        return optMapper.listOptByQuestionId(questionId);
    }

    @Override
    //根据选项id删除选项
    public void delete(Integer id) {
        optMapper.deleteOptById(id);
    }
}
