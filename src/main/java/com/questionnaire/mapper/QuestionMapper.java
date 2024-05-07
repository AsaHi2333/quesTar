package com.questionnaire.mapper;

import com.questionnaire.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    //根据问卷id查询问题
    @Select("SELECT * from question where paper_id=#{id}")
    List<Question> listQuestionsByPaperId(Integer id);

    //根据问题id删除问题
    @Delete("DELETE from question where id=#{id}")
    void deletePaperById(Integer id);

    //添加一个问题
    @Insert("INSERT INTO question (paper_id, question_type, question_title, question_option) VALUES (#{paperId},#{questionType},#{questionTitle},#{questionOption})")
    void insert(Question question);

    //
}
