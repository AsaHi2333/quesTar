package com.questionnaire.mapper;

import com.questionnaire.pojo.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperMapper {
    //根据用户id查询问卷
    @Select("SELECT * from paper where user_id=#{userId}")
    List<Paper> listPaperByUserId(String userId);

    //根据问卷id删除问卷
    @Delete("DELETE from paper where id=#{id}")
    void deletePaperById(Integer id);

    //新建问卷
    @Insert("INSERT INTO paper (id, user_id, title, create_time, start_time, end_time) VALUES (#{id},#{userId},#{title},#{createTime},#{startTime},#{endTime})")
    void insert(Paper paper);

    //修改问卷（标题、开始时间、结束时间）
    void update(Paper paper);
}
