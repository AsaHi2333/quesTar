package com.questionnaire.mapper;

import com.questionnaire.pojo.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface PaperMapper {
    //根据用户id查询问卷
    List<Paper> listPaperByUserId(String userId);

    //根据问卷id查询问卷
    Paper listPaperById(Integer id);

    //根据问卷id删除问卷
    void deletePaperById(Integer id);

    //新建问卷
    void insert(Paper paper);

    //修改问卷（标题、开始时间、结束时间）
    void update(Paper paper);


}
