package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private String id;//问题ID
    private String paperId;//试卷ID，外键
    private LocalDateTime createTime;// 问题创建时间
    private Integer questionType;//问题类型：1：单选题2：多选题3：简答题
    private String questionTitle;//问题标题
    private String questionOption;// 问题的选项：1.选择题：[option1,option2,option3...]2.简答题：空字符串
}
