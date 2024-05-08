package com.questionnaire.mapper;

import com.questionnaire.pojo.Opt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OptMapper {
    //根据问题id查询所有选项
    List<Opt> listOptByQuestionId(Integer questionId);

    //根据选项id删除选项
    void deleteOptById(Integer id);

    //新建选项
    void insert(Opt opt);

    //修改选项
    void update(Opt opt);
}
