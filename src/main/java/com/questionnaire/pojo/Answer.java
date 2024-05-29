package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    //答卷（只显示问卷的答案）的基本信息
    private Integer id;//答卷id
    private Integer userId;//用户ID，外键
    private Integer paperId;//问卷ID，外键
    private Integer questionId;//问题id,外键

    private Integer optId;//选项id,外键  》》选择题的答案
    private String text;//填空题的答案

    private LocalDateTime createTime;//答案创建时间



}
