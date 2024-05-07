package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Opt {
    private Integer id;//选项id
    private Integer paperId;//问卷id
    private Integer questionId;//问题id
    private String optionName;//选项名称
}
