package com.questionnaire.mapper;

import com.questionnaire.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    //根据问卷id查询问题
    @Select("SELECT * from question where paper_id=#{id}")
    List<Question> listQuestionsByPaperId(Integer id);

    //根据问卷id查询问卷标题
    @Select("select question_title from question where id=#{questionId}")
    String getQuestionTitle(Integer questionId);

    //根据问卷id查询问卷类型
    @Select("select question_type from question where id=#{questionId}")
    String getQuestionType(Integer questionId);

    //根据问题id删除问题
    @Delete("DELETE from question where id=#{id}")
    void deleteQuestionById(Integer id);

    //根据问卷id删除问题
    @Delete("DELETE from question where paper_id=#{paperId}")
    void deleteQuestionByPaperId(Integer paperId);

    //添加一个问题
    void insert(List<Question> questions);

    //修改问题
    void update(Question question);
}
