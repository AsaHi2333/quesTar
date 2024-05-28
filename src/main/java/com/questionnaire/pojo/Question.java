package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;//问题ID
    private Integer paperId;//试卷ID，外键
    private String questionType;//问题类型：单选题 多选题 简答题
    private String questionTitle;//问题标题
    private List<Opt> optList;//问题里包含的选项
}
