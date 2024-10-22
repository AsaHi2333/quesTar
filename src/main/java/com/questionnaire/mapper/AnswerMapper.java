package com.questionnaire.mapper;


import com.questionnaire.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswerMapper {


    void insert(List<Answer> answers);//插入

    //根据optId统计该选项数量
    @Select("select count(*) from answer where opt_id=#{optId} ")
    Integer countOpt(Integer optId);

    //针对文本题：根据问题id查询答案
    @Select("select * from answer where question_id=#{quetionId}")
    List<Answer> answerList(Integer questionId);

}
