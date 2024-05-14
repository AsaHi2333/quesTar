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
    private Integer questionType;//问题类型：1：单选题2：多选题3：简答题
    private String questionTitle;//问题标题
    private List<Opt> optList;//问题里包含的选项
}
