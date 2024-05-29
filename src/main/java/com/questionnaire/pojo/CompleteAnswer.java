package com.questionnaire.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

//单个问题的结果：包括所有选项的统计和文本题的所有答案
public class CompleteAnswer {

    private Integer questionId;//问题id
    private String questionTitle;//问题标题
    private String questionType;//问题类型
    private List<OptAnswer> optAnswerList;//选择题结果
    private List<String> textAnswerList;//文本题结果

}
