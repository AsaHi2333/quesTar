package com.questionnaire.mapper;

import com.questionnaire.pojo.Paper;
import com.questionnaire.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    //根据问卷id查询问题
    @Select("SELECT * from question where paper_id=#{id}")
    List<Question> listQuestionsById(String id);
}
